package com.scc.shake;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FeedBackActivity extends AppCompatActivity {

    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedback = getIntent().getParcelableExtra("feedback");


        TextView submit_btn = findViewById(R.id.submit_btn);
        TextView cancel_btn = findViewById(R.id.cancel_btn);
        EditText edt_report = findViewById(R.id.edt_report);

        submit_btn.setOnClickListener(view -> {

            if (edt_report.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Feedback")
                    .setMessage("Are you sure you want to submit feedback?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {

                        feedback.setText(edt_report.getText().toString());
                        sendToServer(feedback);

                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();

        });

        cancel_btn.setOnClickListener(view -> {
            onBackPressed();
        });


    }

    private void sendToServer(Feedback feedback) {

        String msg = "Description:- " + feedback.getText() + "\n\n" +
                "Device OS:- " + feedback.getDeviceOS() + "\n\n" +
                "Device Type:- " + feedback.getDeviceType() + "\n\n" +
                "Device Model:- " + feedback.getDeviceModel() + "\n\n" +
                "Page Name:- " + feedback.getPageName() + "\n\n" +
                "Manufacturer:- " + feedback.getManufacturer();

        new AlertDialog.Builder(this)
                .setTitle("Feedback Details")
                .setMessage(msg)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();

    }
}