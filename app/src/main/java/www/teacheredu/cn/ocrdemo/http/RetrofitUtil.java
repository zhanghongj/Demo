package www.teacheredu.cn.ocrdemo.http;

import android.util.Log;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import www.teacheredu.cn.ocrdemo.BaseApplication;
import www.teacheredu.cn.ocrdemo.utils.Constants;
import www.teacheredu.cn.ocrdemo.utils.SPUtils;

public class RetrofitUtil {

    private static Retrofit.Builder get(String baseUrl) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder();
                        builder.header("token", (String) SPUtils.get(BaseApplication.getAppContext(), Constants.TOKEN,""));
                        Request.Builder requestBuilder =builder.method(originalRequest.method(), originalRequest.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .writeTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .connectTimeout(600, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder;
    }

    public static ApiService getApi(String baseuRL) {
        return get(baseuRL).build().create(ApiService.class);
    }

}
