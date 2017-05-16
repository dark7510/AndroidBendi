package ypx.com.androidbend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import java.util.List;

import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.Picvrfc;

/**
 * Created by S01 on 2017/5/9.
 */

public class PictureAdpter extends RecyclerView.Adapter<PictureAdpter.MyViewHolder>{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Picvrfc> mList;

    public PictureAdpter(Context context, List<Picvrfc> list) {
        mList = list;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,final int viewType) {
        View view = mInflater.inflate(R.layout.picture_adapter, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        Glide.with(mContext)
                .load(mList.get(position).getPicUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.pic_img);
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView pic_img;
        public MyViewHolder(View itemView) {
            super(itemView);
            pic_img = (ImageView) itemView.findViewById(R.id.pic_img);
        }
    }

    //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
    public void setNewData(List<Picvrfc> newData) {
        mList.clear();
        mList.addAll(newData);
        notifyDataSetChanged();

    }

    //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
    public void setMoreData(List<Picvrfc> newData) {
        mList.addAll(newData);
        notifyDataSetChanged();

    }

}
