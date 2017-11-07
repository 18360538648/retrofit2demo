package com.lsw.retrofit2.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Luosiwei on 2017/11/7.
 */

public interface GetApi {
    // Query为get拼接参数
    @GET("v2/achievements")
    Call<Object> getUserInf(@Query("uId") String id, @Header("Authorization") String auth);
}
