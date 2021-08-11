package com.scc.shake;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base Activity for all page so user only extend this class and rest will work automatically
 */
public class FeedbackPhoebeActivity extends AppCompatActivity {

    FeedbackPhoebe feedbackPhoebe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feedbackPhoebe = new FeedbackPhoebe();
        feedbackPhoebe.launch(this);

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
}
