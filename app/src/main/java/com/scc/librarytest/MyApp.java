package com.scc.librarytest;

import android.app.Application;

import com.scc.shake.FeedbackConfig;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        new FeedbackConfig(this)
                .setCancelButtonColor(R.color.teal_700)
                .setDialogButtonColor(R.color.blue)
                .setSubmitButtonTextColor(R.color.design_default_color_error)
                .setCancelButtonTextColor(R.color.teal_200)
                .setFontFromAssets("mark_pro.ttf")
                .setFontFromResource(R.font.mark_pro)
                .setSubmitButtonColor(R.color.teal_200);


    }
}
