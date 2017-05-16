package ypx.com.androidbend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ypx.com.androidbend.R;
import ypx.com.androidbend.adapter.PictureAdpter;
import ypx.com.androidbend.bean.Picvrfc;
import ypx.com.androidbend.common.Common;
import ypx.com.androidbend.common.ServerConfig;
import ypx.com.androidbend.view.activity.PicimgvrActivity;

import static android.R.attr.data;

/**
 * Created by S01 on 2017/5/2.
 */

public class PictureFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Picvrfc> mList = new ArrayList<>();
    private PictureAdpter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_conunt);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sw_lot);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new PictureAdpter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);
        //设置数据
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        OkHttp();
        onClick();
        super.onActivityCreated(savedInstanceState);
    }


    private void onClick() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkHttp();
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void OkHttp() {
        OkHttpUtils.get()
                .url(ServerConfig.TIANXING_MEINV_JPKE_URL)
                .addParams("key", Common.TIANXING_JOKE_KEY)
                .addParams("num", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObj = JSONObject.parseObject(response);
                        JSONArray jsonArr = jsonObj.getJSONArray("newslist");
                        mList.addAll(JSONArray.parseArray(jsonArr.toJSONString(), Picvrfc.class));
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
