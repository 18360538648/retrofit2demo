package com.lsw.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luosiwei on 2017/11/7.
 */

public class NetworkUtils {

    private NetworkUtils() {
    }

    /**
     * 这个是需要返回的对象，非工具类名
     *
     * @return
     */
    public static Retrofit getInstance() {
        return NetworkHolder.retrofit;
    }

    private static class NetworkHolder {
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.171/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
