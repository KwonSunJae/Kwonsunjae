package com.kyeongbuk.mongie;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter gadapter;
    private ArrayAdapter cadapter;
    private Spinner cspinner;
    private Spinner gspinner;
    private String userID;
    private String userPW;
    private String userEMAIL;
    private String userSEX;
    private String userGRADE;
    private String userNUM;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gspinner=(Spinner)findViewById(R.id.GradeSpinner);
        cspinner=(Spinner) findViewById(R.id.ClassSpinner);
        gadapter=ArrayAdapter.createFromResource(this,R.array.grade, android.R.layout.simple_spinner_dropdown_item);
        cadapter=ArrayAdapter.createFromResource(this,R.array.Class, android.R.layout.simple_spinner_dropdown_item);
        gspinner.setAdapter(gadapter);
        cspinner.setAdapter(cadapter);

        final EditText idText =(EditText) findViewById( R.id.idText);
        final EditText pwText = (EditText) findViewById(R.id.pwText);
        final EditText emailText = (EditText)findViewById(R.id.emailText);
        RadioGroup SEXGroup= (RadioGroup) findViewById(R.id.SEXGroup);
        int SEXGroupID = SEXGroup.getCheckedRadioButtonId();
        userSEX=((RadioButton)findViewById(SEXGroupID)).getText().toString();

        SEXGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton SEXButton = (RadioButton) findViewById(i);
                userSEX = SEXButton.getText().toString();
            }
        });


        final Button validateButton =(Button)findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                if(validate){
                    return;
                }
                if(userID.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈칸일 수 없습니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디 입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate= true;
                                idText.setBackgroundColor(getResources().getColor(R.color.color1));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.color1));

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 아이디 입니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });
        Button registerButton  = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPW = pwText.getText().toString();
                String userEMAIL = emailText.getText().toString();
                String userGRADE = gspinner.getSelectedItem().toString();
                String userCLASS = cspinner.getSelectedItem().toString();

                if(!validate)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("먼저 중복확인을 해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(userID.equals("")||userPW.equals("")||userEMAIL.equals("")||userGRADE.equals("")||userCLASS.equals("")||userSEX.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원가입에 성공하였습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                               finish();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원가입에 실패하였습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userEMAIL, userSEX, userGRADE, userCLASS, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);





            }
        });
    }
    @Override
    protected  void onStop(){
        super.onStop();
        if(dialog!=null){


            dialog.dismiss();
            dialog= null;

        }
    }
}
