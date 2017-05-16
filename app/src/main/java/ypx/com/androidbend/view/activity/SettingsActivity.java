package ypx.com.androidbend.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ypx.com.androidbend.R;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.layout_update)
    LinearLayout layoutUpdate;
    @BindView(R.id.btn_loginout)
    Button btnLoginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.layout_update, R.id.btn_loginout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_update:
                break;
            case R.id.btn_loginout:
                loginout();
                break;
        }
    }

    private void loginout() {

    }
}
