package com.scc.shake;

/**
 * Configuration for button color so user can match theme with their app
 */
public class FeedbackConfig {

    private static int submitColor = 0;
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

    public static int getDialogButtonColor() {
        return dialogButtonColor;
    }

    public FeedbackConfig setDialogButtonColor(int color) {
        dialogButtonColor = color;
        return this;
    }

}
