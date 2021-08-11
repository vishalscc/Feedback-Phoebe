package com.scc.shake;

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

    public FeedbackConfig setCancelButtonTextColor(int color) {
        cancelTextColor = color;
        return this;
    }

    public FeedbackConfig setSubmitButtonTextColor(int color) {
        submitTextColor = color;
        return this;
    }

    public static int getDialogButtonColor() {
        return dialogButtonColor;
    }

    public static int getSubmitButtonTextColor() {
        return submitTextColor;
    }

    public static int getCancelButtonTextColor() {
        return cancelTextColor;
    }

    public FeedbackConfig setDialogButtonColor(int color) {
        dialogButtonColor = color;
        return this;
    }

}
