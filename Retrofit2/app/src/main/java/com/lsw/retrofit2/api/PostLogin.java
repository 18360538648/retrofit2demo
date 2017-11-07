package com.lsw.retrofit2.api;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Luosiwei on 2017/11/7.
 */

public interface PostLogin {
    // @Body为post请求参数
    @POST("v2/sessions")
    Call<Object> postLogin(@Body JSONObject inf);
}
