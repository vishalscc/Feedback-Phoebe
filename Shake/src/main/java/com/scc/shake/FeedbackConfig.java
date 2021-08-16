package com.scc.shake;

import android.content.Context;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;

/**
 * Configuration for button color so user can match theme with their app
 */
public class FeedbackConfig {

    private static int submitColor = 0;
    private static int submitTextColor = 0;
    private static int cancelTextColor = 0;
    private static int dialogButtonColor = 0;
    private static int cancelColor = 0;
    private static int customFont = 0;
    private static Typeface tfFromAssets;
    private static Typeface tfFromResources;
    private final Context context;

    public FeedbackConfig(Context context) {
        this.context = context;
    }

    public static int getSubmitButtonColor() {
        return submitColor;
    }

    public FeedbackConfig setSubmitButtonColor(int color) {
        submitColor = color;
        return this;
    }

    public static int getCancelButtonColor() {
        return cancelColor;
    }

    public FeedbackConfig setCancelButtonColor(int color) {
        cancelColor = color;
        return this;
    }

    public static int getDialogButtonColor() {
        return dialogButtonColor;
    }

    public FeedbackConfig setDialogButtonColor(int color) {
        dialogButtonColor = color;
        return this;
    }

    public static int getSubmitButtonTextColor() {
        return submitTextColor;
    }

    public FeedbackConfig setSubmitButtonTextColor(int color) {
        submitTextColor = color;
        return this;
    }

    public static int getCancelButtonTextColor() {
        return cancelTextColor;
    }

    public FeedbackConfig setCancelButtonTextColor(int color) {
        cancelTextColor = color;
        return this;
    }

    public static Typeface getFontFromResource() {
        return tfFromResources;
    }

    public FeedbackConfig setFontFromResource(int font) {
        tfFromResources = ResourcesCompat.getFont(context, font);
        return this;
    }

    public static Typeface getFontFromAssets() {
        return tfFromAssets;
    }

    public FeedbackConfig setFontFromAssets(String path) {
        tfFromAssets = Typeface.createFromAsset(context.getAssets(), path);
        return this;
    }

}
