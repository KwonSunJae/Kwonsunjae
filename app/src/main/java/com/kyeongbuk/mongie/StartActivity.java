package com.kyeongbuk.mongie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button loginstart = findViewById(R.id.loginStart);
        Button freestart = findViewById(R.id.freestart);
        loginstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(StartActivity.this, LoginActivity.class);
                StartActivity.this.startActivity(loginIntent);
            }
        });
        freestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FmainIntent = new Intent(StartActivity.this, FreemainActivity.class);
                StartActivity.this.startActivity(FmainIntent);
            }
        });
    }
}
