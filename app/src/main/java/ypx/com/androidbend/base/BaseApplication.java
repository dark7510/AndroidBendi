package ypx.com.androidbend.base;

import android.app.Application;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.OkHttpClient;

/**
 * Created by YPX on 2017/5/2.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //第一：默认初始化
        ShareSDK.initSDK(this);
        //Bmob.initialize(this, "c58febb8aaf9e338e648db39b626d050");
        initOkhtttp();

    }

    private void initOkhtttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("News"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
