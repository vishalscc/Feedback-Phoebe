package com.scc.librarytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.scc.shake.Shake;


public class MainActivity extends AppCompatActivity {

    Shake shake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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