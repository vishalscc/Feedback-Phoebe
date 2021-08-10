package com.scc.shake;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    Shake shake;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shake = new Shake();
        shake.launch(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shake.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shake.unRegister();
    }
}
