package com.scc.shake;

import static com.scc.shake.utils.SharedPrefs.LOCAL_DATA;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scc.shake.api.ApiClient;
import com.scc.shake.api.FeedbackModel;
import com.scc.shake.utils.DialogUtil;
import com.scc.shake.utils.NetworkUtil;
import com.scc.shake.utils.SharedPrefs;
import com.scc.shake.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Feedback page user can submit their feedback in this page and send to server
 */
public class FeedBackActivity extends AppCompatActivity {

    private final AppCompatActivity activity = this;
    private Feedback feedback;
    private FeedbackPhoebe feedbackPhoebe;
    private SpannableString title;
    private SpannableString msg;
    private SpannableString posBtn;
    private SpannableString negBtn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        feedbackPhoebe = new FeedbackPhoebe();

        feedback = getIntent().getParcelableExtra("feedback");


        TextView submit_btn = findViewById(R.id.submit_btn);
        TextView cancel_btn = findViewById(R.id.cancel_btn);
        EditText edt_report = findViewById(R.id.edt_report);


        if (FeedbackConfig.getSubmitButtonColor() != 0) {
            submit_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(FeedbackConfig.getSubmitButtonColor())));
        }
        if (FeedbackConfig.getCancelButtonColor() != 0) {
            cancel_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(FeedbackConfig.getCancelButtonColor())));
        }

        if (FeedbackConfig.getSubmitButtonTextColor() != 0) {
            submit_btn.setTextColor(ColorStateList.valueOf(getColor(FeedbackConfig.getSubmitButtonTextColor())));
        }

        if (FeedbackConfig.getCancelButtonTextColor() != 0) {
            cancel_btn.setTextColor(ColorStateList.valueOf(getColor(FeedbackConfig.getCancelButtonTextColor())));
        }

        if (FeedbackConfig.getFontFromResource() != null) {
            edt_report.setTypeface(FeedbackConfig.getFontFromResource());
            cancel_btn.setTypeface(FeedbackConfig.getFontFromResource());
            submit_btn.setTypeface(FeedbackConfig.getFontFromResource());
            title = Utils.typeface(FeedbackConfig.getFontFromResource(), "Feedback");
            msg = Utils.typeface(FeedbackConfig.getFontFromResource(), "Are you sure you want to submit feedback?");
            posBtn = Utils.typeface(FeedbackConfig.getFontFromResource(), "Yes");
            negBtn = Utils.typeface(FeedbackConfig.getFontFromResource(), "No");
        }

        if (FeedbackConfig.getFontFromAssets() != null) {
            edt_report.setTypeface(FeedbackConfig.getFontFromAssets());
            cancel_btn.setTypeface(FeedbackConfig.getFontFromAssets());
            submit_btn.setTypeface(FeedbackConfig.getFontFromAssets());
            title = Utils.typeface(FeedbackConfig.getFontFromAssets(), "Feedback");
            msg = Utils.typeface(FeedbackConfig.getFontFromAssets(), "Are you sure you want to submit feedback?");
            posBtn = Utils.typeface(FeedbackConfig.getFontFromAssets(), "Yes");
            negBtn = Utils.typeface(FeedbackConfig.getFontFromAssets(), "No");
        }


        submit_btn.setOnClickListener(view -> {

            if (edt_report.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton(posBtn, (dialogInterface, i) -> {

                        feedback.setText(edt_report.getText().toString());
                        send(feedback);

                    })
                    .setNegativeButton(negBtn, (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .create();


            alertDialog.setOnShowListener(dialogInterface -> {
                if (FeedbackConfig.getDialogButtonColor() != 0) {
                    int color = FeedbackConfig.getDialogButtonColor();
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(getResources().getColor(color));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                            .setTextColor(getResources().getColor(color));
                }
            });

            alertDialog.show();


        });

        cancel_btn.setOnClickListener(view -> {
            onBackPressed();
        });


    }

    private void send(Feedback feedback) {

        if (Utils.getToken(this).isEmpty()) {
            Toast.makeText(this, "Api Token can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (NetworkUtil.isInternetConnect(this)) {
            saveRemotely(feedback);
        } else {
            saveLocally(feedback);
        }

        /*String msg = "Description:- " + feedback.getText() + "\n\n" +
                "Device OS:- " + feedback.getDeviceOS() + "\n\n" +
                "Device Type:- " + feedback.getDeviceType() + "\n\n" +
                "Device Model:- " + feedback.getDeviceModel() + "\n\n" +
                "Page Name:- " + feedback.getPageName() + "\n\n" +
                "Manufacturer:- " + feedback.getManufacturer() + "\n\n" +
                "API Token:- " + Utils.getToken(this);

        new AlertDialog.Builder(this)
                .setTitle("Feedback Details")
                .setMessage(msg)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();*/

    }

    private void saveLocally(Feedback feedback) {

        Gson gson = new Gson();
        String getJson = SharedPrefs.getString(activity, LOCAL_DATA);
        List<Feedback> list;

        if (getJson.isEmpty()) {
            list = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Feedback>>() {
            }.getType();
            list = gson.fromJson(getJson, type);
        }

        list.add(feedback);

        String setjson = gson.toJson(list);
        SharedPrefs.setString(activity, LOCAL_DATA, setjson);

        complete();
    }

    private void saveRemotely(Feedback feedback) {


        DialogUtil.showProgressDialog(activity, getSupportFragmentManager());
        ApiClient.getApiService()
                .sentFeedback(Utils.getToken(this),
                        Locale.getDefault().getLanguage(),
                        feedback.getDeviceType(),
                        feedback.getDeviceModel(),
                        feedback.getPageName(),
                        feedback.getText(),
                        feedback.getDeviceOS())
                .enqueue(new Callback<FeedbackModel>() {
                    @Override
                    public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                        DialogUtil.dismissProgressDialog();
                        if (response.isSuccessful() && response.body().getCode() == 200) {

                            complete();
                        } else if (response.body().getCode() == 401) {
                            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeedbackModel> call, Throwable t) {
                        DialogUtil.dismissProgressDialog();
                        t.printStackTrace();
                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void complete() {
        Toast.makeText(activity, "Feedback send successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}