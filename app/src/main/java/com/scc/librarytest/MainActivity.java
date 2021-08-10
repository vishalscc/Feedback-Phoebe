package com.scc.librarytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.scc.shake.Config;
import com.scc.shake.FeedbackPhoebe;


public class MainActivity extends AppCompatActivity {

    FeedbackPhoebe feedbackPhoebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedbackPhoebe = new FeedbackPhoebe();
        feedbackPhoebe.launch(this);

//        feedbackPhoebe.setSubmitButtonColor(R.color.teal_200);
//        feedbackPhoebe.setCancelButtonColor(R.color.teal_700);
//        feedbackPhoebe.setDialogButtonColor(R.color.black);

        Config.dialogButtonColor = R.color.black;
        Config.submitColor = R.color.teal_200;
        Config.cancelColor = R.color.teal_700;

    }

    @Override
    protected void onResume() {
        super.onResume();
        feedbackPhoebe.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        feedbackPhoebe.unRegister();
    }

    public void button(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }
}