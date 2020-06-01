package com.example.googleimages.network;

import android.content.Context;

import com.example.googleimages.Constants;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit  getRetrofit(Context context){
        if(retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ChuckInterceptor(context));
            builder.connectTimeout(59, TimeUnit.SECONDS);
            builder.writeTimeout(59,TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return retrofit;
    }

}
