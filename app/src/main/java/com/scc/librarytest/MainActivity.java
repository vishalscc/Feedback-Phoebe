package com.scc.librarytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.scc.shake.FeedbackConfig;
import com.scc.shake.FeedbackPhoebe;
import com.scc.shake.FeedbackPhoebeActivity;


public class MainActivity extends FeedbackPhoebeActivity {

    FeedbackPhoebe feedbackPhoebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        feedbackPhoebe = new FeedbackPhoebe();
//        feedbackPhoebe.launch(this);

//        feedbackPhoebe.setSubmitButtonColor(R.color.teal_200);
//        feedbackPhoebe.setCancelButtonColor(R.color.teal_700);
//        feedbackPhoebe.setDialogButtonColor(R.color.black);

//        FeedbackConfig.dialogButtonColor = R.color.black;
//        FeedbackConfig.submitColor = R.color.teal_200;
//        FeedbackConfig.cancelColor = R.color.teal_700;

//        FeedbackConfig.setCancelButtonColor(R.color.teal_700);
//        FeedbackConfig.setSubmitButtonColor(R.color.teal_200);
//        FeedbackConfig.setDialogButtonColor(R.color.black);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        feedbackPhoebe.register();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        feedbackPhoebe.unRegister();
//    }


    public void button(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }
}