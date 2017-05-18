package ypx.com.androidbend.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import ypx.com.androidbend.R;

import ypx.com.androidbend.view.activity.LoginActivity;
import ypx.com.androidbend.view.activity.RegisterActivity;
import ypx.com.androidbend.view.activity.SettingsActivity;


public class MyFragment extends Fragment {



    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.bt_fragment_login)
    Button btFragmentLogin;
    @BindView(R.id.ll_login_re)
    RelativeLayout llLoginRe;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_feed_back)
    TextView tvFeedBack;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.bt_fragment_setup)
    TextView btFragmentSetup;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @OnClick({ R.id.profile_image, R.id.bt_fragment_login, R.id.ll_login_re, R.id.textView, R.id.tv_share, R.id.tv_feed_back, R.id.tv_about_us, R.id.bt_fragment_setup})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.profile_image:
                break;
            case R.id.bt_fragment_login:
                startActivity(new Intent(view.getContext(), LoginActivity.class));
                break;
            case R.id.ll_login_re:
                break;
            case R.id.textView:
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_feed_back:
                break;
            case R.id.tv_about_us:
                showAppInfo();
                break;
            case R.id.bt_fragment_setup:
                startActivity(new Intent(getActivity(),SettingsActivity.class));
                break;
        }
    }

    private void showAppInfo() {
        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity())
                .setTitle("关于我们")
                .setMessage("开发人:DARKER\n地址:https://github.com/dark7510/AndroidBend")
                .setPositiveButton("确定",null);
        builer.create().show();
    }
}
