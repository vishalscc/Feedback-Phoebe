package com.scc.shake;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Feedback page user can submit their feedback in this page and send to server
 */
public class FeedBackActivity extends AppCompatActivity {

    Feedback feedback;
    FeedbackPhoebe feedbackPhoebe;

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

        int submitColor = getIntent().getIntExtra("submitColor", R.color.blue);
        int cancelColor = getIntent().getIntExtra("cancelColor", R.color.white);
        int dialogButtonColor = getIntent().getIntExtra("dialogButtonColor", R.color.white);

        if (FeedbackConfig.getSubmitButtonColor() != 0) {
            submit_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(FeedbackConfig.getSubmitButtonColor())));
        }
        if (FeedbackConfig.getCancelButtonColor() != 0) {
            cancel_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(FeedbackConfig.getCancelButtonColor())));
        }

        if (FeedbackConfig.getSubmitButtonTextColor() != 0){
            submit_btn.setTextColor(ColorStateList.valueOf(getColor(FeedbackConfig.getSubmitButtonTextColor())));
        }

        if (FeedbackConfig.getCancelButtonTextColor() != 0){
            cancel_btn.setTextColor(ColorStateList.valueOf(getColor(FeedbackConfig.getCancelButtonTextColor())));
        }


        submit_btn.setOnClickListener(view -> {

            if (edt_report.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Feedback")
                    .setMessage("Are you sure you want to submit feedback?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {

                        feedback.setText(edt_report.getText().toString());
                        sendToServer(feedback);

                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
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

    private void sendToServer(Feedback feedback) {

        if (Utils.getToken(this).isEmpty()) {
            Toast.makeText(this, "Api Token can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String msg = "Description:- " + feedback.getText() + "\n\n" +
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
                .show();

    }
}