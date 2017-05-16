package ypx.com.androidbend.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.Picvrfc;

public class PicimgvrActivity extends AppCompatActivity {
    @BindView(R.id.wv_picimg)
    WebView wvPicimg;
    //private WebView wv_picimg;
    private Intent intent;
   private Picvrfc picvrfcn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picimgvr);
        ButterKnife.bind(this);
        setEvent();
        findViews();

    }

    private void setEvent() {
        intent = getIntent();
        Bundle bundle = new Bundle();
        picvrfcn = intent.getExtras().getParcelable("picvrfc");
        wvPicimg.loadUrl(picvrfcn.getPicUrl());
        WebSettings setting = wvPicimg.getSettings();
        setting.setJavaScriptEnabled(true);
        wvPicimg.setWebViewClient(new WebViewClient());
        wvPicimg.setWebChromeClient(new WebChromeClient());

    }

    private void findViews() {
        wvPicimg = (WebView) findViewById(R.id.wv_picimg);

    }
}
