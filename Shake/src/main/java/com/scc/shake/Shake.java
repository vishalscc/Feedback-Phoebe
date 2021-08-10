package com.scc.shake;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class Shake {

    private SensorManager mSensorManager;
    private ShakeEffect mSensorListener;
    private Vibrator vibrator;
    public static Context context;

    public void launch(Context context) {

        Shake.context = context;

        Toast.makeText(context, "Demo", Toast.LENGTH_SHORT).show();
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
                feedback.setDeviceOS("Android " + getAndroidVersion(android.os.Build.VERSION.SDK_INT));
                feedback.setDeviceType("Android");
                feedback.setPageName(context.getClass().getSimpleName());
                feedback.setText("");
                feedback.setManufacturer(Build.MANUFACTURER);

                Intent intent = new Intent(context, FeedBackActivity.class);
                intent.putExtra("feedback", feedback);
                context.startActivity(intent);
            }
        });

//        takeScreenshot(context,"");
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
