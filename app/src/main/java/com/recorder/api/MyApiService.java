package com.recorder.api;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recorder.model.Bill;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MyApiService extends AppCompatActivity {
    OkHttpClient client=new OkHttpClient();
    String conn="https://localhost:7162/Account/";
    List<Bill>res;
    public List<Bill> get(String getUrl){
        Request request=new Request.Builder().url(conn+getUrl).build();
        Log.d("my", conn+getUrl);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("my", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("my", "running");
                        Gson gson=new Gson();
                        ResponseBody responsebody=response.body();
                        if(responsebody!=null){
                            Type listType = new TypeToken<List<Bill>>(){}.getType();
                            res = gson.fromJson(responsebody.toString(), listType);
                        }
                    }
                });
            }
        });
        return res;
    }
    public void post(String postUrl){
        RequestBody requestBody=new FormBody.Builder()
                .add("key_name","value")
                .build();
        Request request=new Request.Builder().url(conn+postUrl).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}
