package com.scc.librarytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.scc.shake.BaseActivity;
import com.scc.shake.Shake;


public class MainActivity extends BaseActivity {

    Shake shake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}