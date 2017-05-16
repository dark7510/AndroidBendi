package ypx.com.androidbend.fragment;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import ypx.com.androidbend.adapter.NewsAdapter;
import ypx.com.androidbend.bean.Joke;
import ypx.com.androidbend.bean.News;
import ypx.com.androidbend.common.Common;
import ypx.com.androidbend.common.ServerConfig;
import ypx.com.androidbend.view.activity.NewsDetailActivity;


public class NewsFragment extends ListFragment {
    public static final int TYPE_REHRESH=0x01;
    public static final int TYPE_LOADMORE=0x02;


    @BindView(R.id.list_news)
    PullToRefreshListView listNews;
    Unbinder unbinder;
    private int page = 1;
    private List<News> data = new ArrayList<>();
    private NewsAdapter newAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View contatcsview = inflater.inflate(R.layout.fragment_news, container,false);
        unbinder = ButterKnife.bind(this, contatcsview);
        return contatcsview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setEvent();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private void setEvent() {
        //设置列表的刷新加载
        listNews.setMode(PullToRefreshBase.Mode.BOTH);
        newAdapter = new NewsAdapter(data, getActivity());
        // Log.i("log",newAdapter.toString());

        listNews.setAdapter(newAdapter);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("news", data.get(i-1));
                intent.putExtras(bundle);
                NewsFragment.this.startActivity(intent);
            }
        });
        listNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
                getAsyncData(1,TYPE_REHRESH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
                getAsyncData(page++,TYPE_LOADMORE);

            }
        });

    }
    private void initData() {
        getAsyncData(1,TYPE_REHRESH);

    }

    private void getAsyncData(int page, final int type) {
        OkHttpUtils
                .post()
                .url(ServerConfig.BASE_URL)
                .addParams("key", Common.API_NEWS_KEY)
                .addParams("type", "top")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                        //tingzhishuaxin
                        listNews.onRefreshComplete();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //停止刷新
                        listNews.onRefreshComplete();
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
                        switch (type){
                            case  TYPE_REHRESH:
                                newAdapter.setNewData(JSONArray.parseArray(jsonArray.toJSONString(), News.class));

                                break;
                            case TYPE_LOADMORE:
                                newAdapter.setMoreData(JSONArray.parseArray(jsonArray.toJSONString(), News.class));

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
