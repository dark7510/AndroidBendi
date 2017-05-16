package ypx.com.androidbend;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ypx.com.androidbend.fragment.JokeFragment;
import ypx.com.androidbend.fragment.MyFragment;
import ypx.com.androidbend.fragment.NewsFragment;
import ypx.com.androidbend.fragment.PictureFragment;
import ypx.com.androidbend.fragment.VirtualRealityFragment;
import ypx.com.androidbend.ui.widget.BottomNavigationViewEx;

;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationViewEx navigation;
    private NewsFragment newsFragment;
    private JokeFragment jokeFragment;
    private VirtualRealityFragment virtualRealityFragment;
    private MyFragment myFragment;
    private PictureFragment pictureFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        setDefaultFragment();

    }

    private void initView() {
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        NewsFragment fragment = new NewsFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            switch (item.getItemId()) {
                case R.id.news:
                    newsFragment = new NewsFragment();
                    transaction.replace(R.id.content, newsFragment);
                    transaction.commit();
                    return true;
                case R.id.shocjing:
                    jokeFragment = new JokeFragment();
                    transaction.replace(R.id.content, jokeFragment);
                    transaction.commit();
                    return true;
                case R.id.virtual_reality:
                   /* virtualRealityFragment = new VirtualRealityFragment();
                    transaction.replace(R.id.content, virtualRealityFragment);
                    transaction.commit();*/
                    pictureFragment=new PictureFragment();
                    transaction.replace(R.id.content,pictureFragment);
                    transaction.commit();

                    return true;
                case R.id.oneself:
                    myFragment = new MyFragment();
                    transaction.replace(R.id.content, myFragment);
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };


}
