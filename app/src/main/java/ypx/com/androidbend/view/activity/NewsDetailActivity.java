package ypx.com.androidbend.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.News;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView wv_detail;
    private Intent intent;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        findViews();
        setEvent();
    }

    private void setEvent() {
        intent = getIntent();
        Bundle bundle = new Bundle();
        news = intent.getExtras().getParcelable("news");
        wv_detail.loadUrl(news.getUrl());
        WebSettings setting = wv_detail.getSettings();
        setting.setJavaScriptEnabled(true);
        wv_detail.setWebViewClient(new WebViewClient());
        wv_detail.setWebChromeClient(new WebChromeClient());
    }

    private void findViews() {
        wv_detail = (WebView) findViewById(R.id.wb_news);
    }
}
