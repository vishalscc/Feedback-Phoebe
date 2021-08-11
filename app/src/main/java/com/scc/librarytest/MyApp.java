package com.scc.librarytest;

import android.app.Application;

import com.scc.shake.FeedbackConfig;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new FeedbackConfig()
                .setCancelButtonColor(R.color.teal_700)
                .setDialogButtonColor(R.color.blue)
                .setSubmitButtonColor(R.color.teal_200);

    }
}
