package com.lsw.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lsw.retrofit2.api.GetApi;
import com.lsw.retrofit2.api.PostLogin;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postLogin();
    }

    /**
     * Get请求测试
     */
    public void getByRetrofit() {
        GetApi getApi = NetworkUtils.getInstance()
                .create(GetApi.class);
        getApi.getUserInf("59687c6116ee120007be4914", "29ca2d85d019851765b87292dddc62b6").enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("lsw", "response***--" + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    /**
     * Post请求测试
     */
    public void postLogin() {
        try {
            JSONObject object = new JSONObject();
            object.put("type", 2);
            object.put("login_name", "18360538648");
            object.put("password", "123456");
            object.put("deviceId", PKUtil.getM2(MainActivity.this));
            PostLogin postLogin = NetworkUtils.getInstance().create(PostLogin.class);
            postLogin.postLogin(object).enqueue(new Callback<Object>() {

                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Log.i("lsw", "response code ***--" + response.code());
                    Log.i("lsw", "response***--" + response.body());
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            });
        } catch (JSONException e) {

        }

    }
}
