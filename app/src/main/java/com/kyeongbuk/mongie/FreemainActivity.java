package com.kyeongbuk.mongie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FreemainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freemain);
        Button Fmapbutton = findViewById(R.id.fmapButton);
        Button Fnoticebutton=findViewById(R.id.fnoticeButton);
        Button Ffoodbutton = findViewById(R.id.ffoodButton);

        Fmapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(FreemainActivity.this, MapActivity.class);
                FreemainActivity.this.startActivity(mapIntent);
            }
        });
        Fnoticebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticeIntent = new Intent(FreemainActivity.this, NoticeActivity.class);
                FreemainActivity.this.startActivity(noticeIntent);
            }
        });
    }


}
