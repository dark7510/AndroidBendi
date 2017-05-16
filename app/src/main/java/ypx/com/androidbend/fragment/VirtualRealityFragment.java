package ypx.com.androidbend.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import ypx.com.androidbend.R;
import ypx.com.androidbend.adapter.SimpleRecyclerCardAdapter;
import ypx.com.androidbend.bean.Picvrfc;
import ypx.com.androidbend.common.Common;
import ypx.com.androidbend.common.ServerConfig;
import ypx.com.androidbend.view.activity.PicimgvrActivity;


public class VirtualRealityFragment extends Fragment {

    //@BindView(R.id.app_recyclerview)
   // RecyclerView appRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.app_recyclerview)
    RecyclerView appRecyclerview;
    private List<Picvrfc> mDatas = new ArrayList<>();

    private SimpleRecyclerCardAdapter mSimpleRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_reality, container, false);
        unbinder = ButterKnife.bind(this, view);
        setEvent();
        initData();
        return view;
    }


    private void setEvent() {
        mSimpleRecyclerAdapter = new SimpleRecyclerCardAdapter(getActivity(), mDatas);
        appRecyclerview.setAdapter(mSimpleRecyclerAdapter);
        //设置网格布局管理器
        appRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mSimpleRecyclerAdapter.setOnItemActionListener(new SimpleRecyclerCardAdapter.OnItemActionListener() {

            @Override
            public boolean onItemLongClickListener(View v, int pos) {
                Intent intent = new Intent(getActivity(), PicimgvrActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("picvrfc", mDatas.get(pos - 1));
                intent.putExtras(bundle);
                VirtualRealityFragment.this.startActivity(intent);
                Toast.makeText(getActivity(), "-长按-" + pos, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onItemClickListener(View v, int pos) {
                Intent intent = new Intent(getActivity(), PicimgvrActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("picvrfc", mDatas.get(pos - 1));
                intent.putExtras(bundle);
                VirtualRealityFragment.this.startActivity(intent);
                Toast.makeText(getActivity(), "-单击-" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ServerConfig.TIANXING_MEINV_JPKE_URL)
                .addParams("key", Common.TIANXING_JOKE_KEY)
                .addParams("num", "20")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //停止刷新

                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                        List<Picvrfc> newData = JSONArray.parseArray(jsonArray.toJSONString(), Picvrfc.class);
                        mDatas.addAll(newData);
                       mSimpleRecyclerAdapter.notifyDataSetChanged();
                    }

                });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
