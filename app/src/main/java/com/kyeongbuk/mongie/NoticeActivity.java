package com.kyeongbuk.mongie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private NoticeListAdapter adapter; // 리스트뷰 커스터마이징을 위한 어답터
    private List<Notice> noticeList; // 리스트뷰에 들어갈 내용 ( 제목 , 이름 , 날짜, 조회수)
    private ListView noticeListview; // 리스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        new BackgroundTask().execute();// 어싱크태스크







    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String target;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            target="http://ksun1234.cafe24.com/NoticeList.php"; // 웹 호스팅 정보를 받기위한  php 파일 주소

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                InputStream inputStream = con.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                con.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(String result){

            noticeListview = findViewById(R.id.noticelistview);
            noticeList = new ArrayList<Notice>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                //int count=0;
                String noticeContent, noticeName , noticeDate, noticeSeeview;
                int count=0;
                while (count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    Log.d(this.getClass().getName(), noticeContent);
                    noticeName = object.getString("noticeName");
                    Log.d(this.getClass().getName(), noticeName);
                    noticeDate = object.getString("noticeDate");
                    Log.d(this.getClass().getName(), noticeDate);
                    noticeSeeview = object.getString("noticeSeeview");
                    Log.d(this.getClass().getName(), noticeSeeview);
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate, noticeSeeview); // noticeList에 넣기위함.
                    noticeList.add(notice);


                    count++;
                    adapter = new NoticeListAdapter(getApplicationContext(),noticeList);

                    noticeListview.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


}