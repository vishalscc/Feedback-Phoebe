package com.scc.shake;

import static com.scc.shake.utils.SharedPrefs.LOCAL_DATA;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scc.shake.api.ApiClient;
import com.scc.shake.api.FeedbackModel;
import com.scc.shake.utils.NetworkUtil;
import com.scc.shake.utils.SharedPrefs;
import com.scc.shake.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FeedbackPhoebe contains Shake sensor detect and open feedback page
 */
public class FeedbackPhoebe {

    public static int submitColor = 0;
    public static int dialogButtonColor = 0;
    public static int cancelColor = 0;
    private SensorManager mSensorManager;
    private ShakeEffect mSensorListener;
    private Vibrator vibrator;

    public void launch(Context context) {

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEffect();
        mSensorListener.setOnShakeListener(new ShakeEffect.OnShakeListener() {
            public void onShake() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100L, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(100L);
                }
                mSensorManager.unregisterListener(mSensorListener);

                Feedback feedback = new Feedback();

                feedback.setDeviceModel(Build.MODEL);
                feedback.setDeviceOS("Android " + Build.VERSION.RELEASE);
                feedback.setDeviceType("Android");
                feedback.setPageName(context.getClass().getSimpleName());
                feedback.setText("");
                feedback.setManufacturer(Build.MANUFACTURER);

                Intent intent = new Intent(context, FeedBackActivity.class);
                intent.putExtra("feedback", feedback);
                intent.putExtra("cancelColor", cancelColor);
                intent.putExtra("submitColor", submitColor);
                intent.putExtra("dialogButtonColor", dialogButtonColor);
                context.startActivity(intent);
            }
        });

        syncData(context);

    }

    public void syncData(Context context) {

        if (NetworkUtil.isInternetConnect(context)) {
            String getJson = SharedPrefs.getString(context, LOCAL_DATA);

            if (!getJson.isEmpty()) {

                Gson gson = new Gson();

                Type type = new TypeToken<List<Feedback>>() {
                }.getType();
                List<Feedback> list = gson.fromJson(getJson, type);

                JSONArray jsonArray = new JSONArray();

                for (Feedback feedback : list) {

                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("text", feedback.getText());
                        jsonObject.put("os", feedback.getDeviceOS());
                        jsonObject.put("device_type", feedback.getDeviceType());
                        jsonObject.put("model", feedback.getDeviceModel());
                        jsonObject.put("page_name", feedback.getPageName());

                        jsonArray.put(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                ApiClient.getApiService()
                        .syncFeedback(Utils.getToken(context),
                                Locale.getDefault().getLanguage(),
                                jsonArray)
                        .enqueue(new Callback<FeedbackModel>() {
                            @Override
                            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                                if (response.isSuccessful() && response.body().getCode() == 200) {
                                    SharedPrefs.clearPrefs(context);
                                }
                            }

                            @Override
                            public void onFailure(Call<FeedbackModel> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

            }
        }

    }

    public void setSubmitButtonColor(int color) {
        submitColor = color;
    }

    public void setCancelButtonColor(int color) {
        cancelColor = color;
    }

    public void setDialogButtonColor(int color) {
        dialogButtonColor = color;
    }

    private String getAndroidVersion(int version) {
        switch (version) {
            case 21:
                return "5.0";
            case 22:
                return "5.1";
            case 23:
                return "6.0";
            case 24:
                return "7.0";
            case 25:
                return "7.1.1";
            case 26:
                return "8.0";
            case 27:
                return "8.1";
            case 28:
                return "9.0";
            case 29:
                return "10.0";
            case 30:
                return "11.0";
            case 31:
                return "12.0";
        }

        return "";
    }

    private void takeScreenshot(Activity context, String pageName) {

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = context.getCacheDir() + "/" + System.currentTimeMillis() + ".jpg";

            // create bitmap screen capture
            View v1 = context.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


            Intent intent = new Intent(context, FeedBackActivity.class);
            intent.putExtra("page_name", pageName);
            context.startActivity(intent);


        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    public void register() {
        //Resume
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    public void unRegister() {
        //Pause
        mSensorManager.unregisterListener(mSensorListener);
    }

}
