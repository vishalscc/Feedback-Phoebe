package com.scc.shake.utils;

import android.app.Activity;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class DialogUtil {

    private static final String TAG_FRAGMENT = "Dialog";

    private static DialogFragment mDialogFragment;

    public static void showProgressDialog(Activity activity, FragmentManager fragmentManager) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (mDialogFragment != null && mDialogFragment.isVisible()) {
            return;
        }

        DialogFragment mPrevious = mDialogFragment;
        mDialogFragment = new ProgressDialogFragment();

        mDialogFragment.setCancelable(false);

        if (mPrevious != null) {
            fragmentManager.beginTransaction().remove(mPrevious).commitAllowingStateLoss();
        }
        fragmentManager.beginTransaction().add(mDialogFragment, TAG_FRAGMENT).commitAllowingStateLoss();
    }

    public static void dismissProgressDialog() {
        if (mDialogFragment != null) {
            mDialogFragment.dismissAllowingStateLoss();
            mDialogFragment = null;
        }
    }



}




