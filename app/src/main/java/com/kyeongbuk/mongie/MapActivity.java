package com.kyeongbuk.mongie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends AppCompatActivity {
    private TextView PlaceText;
    private Button ScaonButton;
    private ImageView imageView;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ScaonButton = findViewById(R.id.scanbutton);
        PlaceText = findViewById(R.id.placetext);
        imageView= findViewById(R.id.mapimage);

         qrScan= new IntentIntegrator(this);

        ScaonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.setPrompt("스캔중...");
                qrScan.initiateScan();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String ob;
        String door ="학교정문";
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if (result != null){
            if (result.getContents()==null){
                PlaceText.setText("실패");
            }else{
               try{
                    JSONObject obj = new JSONObject(result.getContents());
                    PlaceText.setText(obj.getString("name"));
                   if( PlaceText.getText().equals("학교정문")){
                       imageView.setImageResource(R.drawable.door);
                       imageView.invalidate();

                   }
                   if(PlaceText.getText().equals("강당")){
                       imageView.setImageResource(R.drawable.gangdang);
                       imageView.invalidate();
                   }
                   if(PlaceText.getText() .equals("급식실")){
                       imageView.setImageResource(R.drawable.schoolfood);
                       imageView.invalidate();
                   }
                   if(PlaceText.getText().equals("도서관")){
                       imageView.setImageResource(R.drawable.library);
                       imageView.invalidate();
                   }
                   if(PlaceText.getText().equals("매점")){
                       imageView.setImageResource(R.drawable.store);
                       imageView.invalidate();
                   }
                   if(PlaceText.getText().equals("분수대")){
                       imageView.setImageResource(R.drawable.comp);
                       imageView.invalidate();
                   }
                   if(PlaceText.getText().equals("연못")){
                       imageView.setImageResource(R.drawable.pond);
                       imageView.invalidate();
                   }



                } catch (JSONException e) {
                    e.printStackTrace();
                    PlaceText.setText(result.getContents());
                }


            }


        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}





