package com.kyeongbuk.mongie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DGCOM on 2018-02-01.
 */

public class RegisterRequest extends StringRequest {
    final static private String URL= "http://ksun1234.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPW, String userEMAIL, String userSEX, String userGRADE,String userCLASS,  Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPW", userPW);
        parameters.put("userEMAIL", userEMAIL);
        parameters.put("userSEX", userSEX);
        parameters.put("userGRADE", userGRADE);
        parameters.put("userCLASS", userCLASS);




    }
    @Override
    public Map<String, String>getParams(){


        return parameters;
    }


}
