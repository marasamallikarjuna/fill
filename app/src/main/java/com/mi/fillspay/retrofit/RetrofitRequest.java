package com.mi.fillspay.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*public class RetrofitRequest {

    private static Retrofit retrofit;

    private static final String BASE_URL = "http://192.168.0.254:8080";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}*/
public class RetrofitRequest {

    private static OkHttpClient okHttpClient;

    private static Retrofit retrofit;

    private static final String BASE_URL = "http://192.168.0.254:8080";

    public static Retrofit getRetrofitInstance() {
        initOkHttp();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                   // .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    private static void initOkHttp() {

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor(){
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Request-Type", "Android")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        okHttpClient = httpClient.build();
    }
}