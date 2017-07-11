package com.itzyf.utils;

import com.google.gson.Gson;
import com.itzyf.intercept.HttpLoggingInterceptor;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/11 15:46
 */
public class HttpUtils {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    public static <T> T request(String url, Class<T> tClass) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            return new Gson().fromJson(response.body().charStream(), tClass);
        } else {
            throw new IOException(response.message());
        }
    }
}
