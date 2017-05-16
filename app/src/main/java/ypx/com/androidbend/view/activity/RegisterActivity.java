package ypx.com.androidbend.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import ypx.com.androidbend.MainActivity;
import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.User;
import ypx.com.androidbend.fragment.MyFragment;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_name_register, et_pasw_register, mnumber, mverify;
    private Button bt_register_ONE, mgi, mreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        // Bmob.initialize(this, "c58febb8aaf9e338e648db39b626d050");//初始化BmobSDK
        Bmob.initialize(this, "c58febb8aaf9e338e648db39b626d050");
        // BmobSMS.initialize(this, "c58febb8aaf9e338e648db39b626d050");
        //Bmob  连接Application
    }

    private void findViews() {
        et_name_register = (EditText) findViewById(R.id.et_name_register);
        et_pasw_register = (EditText) findViewById(R.id.et_pasw_register);
        // mnumber= (EditText) findViewById(R.id.callnumber);
        // mverify=(EditText) findViewById(R.id.sms);
        bt_register_ONE = (Button) findViewById(R.id.bt_register_ONE);
        // mgi= (Button) findViewById(R.id.getsms);
        mreturn = (Button) findViewById(R.id.breturn);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register_ONE:
                register_ONE();
                break;
            case R.id.breturn:
                ret();
                break;
        }
    }

    private void ret() {
        Intent intent1 = new Intent();
        intent1.setClass(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(intent1);
    }


    private void register_ONE() {

        String name = et_name_register.getText().toString();
        String password = et_pasw_register.getText().toString();
        // String number=mnumber.getText().toString();
        //String verify=mverify.getText().toString();
        if (name.equals("") || password.equals("")) {
            Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (name.length() < 6) {
            Toast.makeText(this, "帐号小于6位", Toast.LENGTH_LONG).show();
            return;
        }
        BmobUser bu = new BmobUser();
        bu.setUsername(name);
        bu.setPassword(password);
        //bu.setEmail("sendi@163.com");
        //注意：不能用save方法进行注册
        bu.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //验证码正确 添加用户信息
                    String name = et_name_register.getText().toString();
                    String password = et_pasw_register.getText().toString();
                    User user = new User();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.save(new SaveListener<String>() {

                        @Override
                        public void done(String arg0, BmobException arg1) {
                            // TODO Auto-generated method stub
                            if (arg1 == null) {
                                return;

                            } else {
                                return;
                            }
                        }
                    });

                    Intent intent2 = new Intent();
                    intent2.setClass(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(intent2);
                } else {
                    Toast.makeText(RegisterActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
