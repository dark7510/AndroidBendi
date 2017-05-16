package ypx.com.androidbend.fragment;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import ypx.com.androidbend.R;
import ypx.com.androidbend.adapter.JokeAdapder;
import ypx.com.androidbend.bean.Joke;


import ypx.com.androidbend.common.Common;
import ypx.com.androidbend.common.ServerConfig;


public class JokeFragment extends Fragment {
    public static final int TYPE_REHRESH = 0x01;
    public static final int TYPE_LOADMORE = 0x02;

    Unbinder unbinder;
    @BindView(R.id.prg_joke)
    PullToRefreshListView prgJoke;
    private int page = 1;
    private List<Joke> data = new ArrayList<>();
    private JokeAdapder jokeadapder;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        //设置列表的刷新加载
        prgJoke.setMode(PullToRefreshBase.Mode.BOTH);
        //初始化适配器
        jokeadapder = new JokeAdapder(data);
        //绑定适配器
        prgJoke.setAdapter(jokeadapder);
        //添加监听事件
        prgJoke.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(),data.get(i).getContent(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ((Joke) jokeadapder.getItem(i - 1)).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        prgJoke.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
                getAsyncData(1, TYPE_REHRESH);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
                getAsyncData(page++, TYPE_LOADMORE);

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        getAsyncData(1, TYPE_REHRESH);
    }
    //获取异步请求的数据

    private void getAsyncData(int page, final int type) {

        OkHttpUtils.get()
                .url(ServerConfig.TIANXING_JPKE_URL)
                .addParams("key", Common.TIANXING_JOKE_KEY)
                .addParams("num", "20")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                        //停止刷新
                        prgJoke.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //停止刷新
                        prgJoke.onRefreshComplete();
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                        List<Joke> newData = JSONArray.parseArray(jsonArray.toJSONString(), Joke.class);
                        switch (type) {
                            case TYPE_REHRESH:

                                jokeadapder.setNewData(newData);
                                break;
                            case TYPE_LOADMORE:

                                jokeadapder.setMoreData(newData);
                                break;

                        }

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
