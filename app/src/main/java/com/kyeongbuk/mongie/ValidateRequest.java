package com.kyeongbuk.mongie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DGCOM on 2018-02-01.
 */

public class ValidateRequest extends StringRequest {
    final static private String URL= "http://ksun1234.cafe24.com/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("userID", userID);



    }
    @Override
    public Map<String, String>getParams(){


        return parameters;
    }


}
