package ypx.com.androidbend.view.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import ypx.com.androidbend.MainActivity;
import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.User;
import ypx.com.androidbend.utils.LogUtils;
import ypx.com.androidbend.utils.ToastUtils;

public class LoginActivity extends AppCompatActivity implements PlatformActionListener {
    private EditText et_username, et_password;
    private TextView forget_password, tv_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();


    }

    private void findViews() {
        et_username = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_pasw);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                land();
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.ibt_qq:
                loginByQQ();
                break;
            case R.id.ibt_sina:
                loginBySina();
                break;
            case R.id.ibt_wechat:
                loginByWechat();
                break;

        }
    }

    private void loginByWechat() {
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        wechat.authorize();//单独授权,OnComplete返回的hashmap是空的
        //wechat.showUser(null);//授权并获取用户信息
        //移除授权
        //wechat.removeAccount(true);
    }

    private void loginBySina() {
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //输出所有的授权信息
                PlatformDb data = platform.getDb();
                BmobUser.BmobThirdUserAuth authInfo=new BmobUser.BmobThirdUserAuth("qq",data.getToken(),String.valueOf(data.getExpiresIn()),data.getUserId());


            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                throwable.printStackTrace();

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });

        //authorize与showUser单独调用一个即可
        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        //weibo.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }



    private void loginByQQ() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        qq.authorize();//单独授权,OnComplete返回的hashmap是空的
        //qq.showUser(null);//授权并获取用户信息
        //移除授权
        //qq.removeAccount(true);
    }


    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Looper.prepare();
        PlatformDb userDB=platform.getDb();
        //输出所有授权信息
        String result=userDB.exportData();
        Log.i("TAG",result);
       // Toast.makeText(LoginActivity.this,result,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        String userID = userDB.getUserId();
        String icon=userDB.getUserIcon();

        String token =userDB.getToken();
        String nickname=userDB.getUserName();
        Looper.loop();
    }


    public void onError(Platform platform, int i, Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(LoginActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(LoginActivity.this,"授权取消",Toast.LENGTH_SHORT).show();

    }

    private void land() {
        String name = et_username.getText().toString();
        String password = et_password.getText().toString();
        if (name.equals("") || password.equals("")) {
            Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(name);
        bu2.setPassword(password);
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    Intent seccess = new Intent();
                    seccess.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(seccess);


                } else {
                    Toast.makeText(LoginActivity.this, "帐号或密码有误", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
