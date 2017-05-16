package ypx.com.androidbend.adapter;




import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;

import java.util.List;

import ypx.com.androidbend.R;


import ypx.com.androidbend.bean.Picvrfc;

/**
 * Created by YPX on 2017/5/7.
 */



public class SimpleRecyclerCardAdapter  extends RecyclerView.Adapter<SimpleCardViewHolder>{

    private Context mCtx;
    private LayoutInflater mInflater;
    private final List<Picvrfc> mDataSource = new ArrayList<>();
    private OnItemActionListener mOnItemActionListener;

    public SimpleRecyclerCardAdapter(Context mCtx, List<Picvrfc> dataList) {
        super();
        this.mCtx = mCtx;
        mInflater = LayoutInflater.from(mCtx);
        this.mDataSource.addAll(dataList);
    }
    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
    @Override
    public void onBindViewHolder(final SimpleCardViewHolder viewHolder,  int i) {
        Glide.with(mCtx)
                .load(mDataSource.get(i).getPicUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.itemIv);
        viewHolder.itemTv.setText(mDataSource.get(i).getTitle());
        if(mOnItemActionListener!=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    mOnItemActionListener.onItemClickListener(v,viewHolder.getPosition());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    return mOnItemActionListener.onItemLongClickListener(v, viewHolder.getPosition());
                }
            });
        }
    }
    @Override
    public SimpleCardViewHolder onCreateViewHolder(ViewGroup viewgroup, int i) {

        View v =  mInflater.inflate(R.layout.item_pacivr, viewgroup,false);
        SimpleCardViewHolder simpleViewHolder = new SimpleCardViewHolder(v);
        simpleViewHolder.setIsRecyclable(true);

        return simpleViewHolder;
    }
    /**********定义点击事件**********/
    public   interface OnItemActionListener
    {
        public   void onItemClickListener(View v,int pos);
        public   boolean onItemLongClickListener(View v,int pos);
    }
    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }
}
class SimpleCardViewHolder extends ViewHolder
{
    public TextView itemTv;
    public ImageView itemIv;

    public SimpleCardViewHolder(View layout) {
        super(layout);
        itemTv = (TextView) layout.findViewById(R.id.item_title);
        itemIv = (ImageView) layout.findViewById(R.id.item_img);
    }
}


    //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
   /* public void setNewData(List<Picvrfc> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();

    }

    //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
    public void setMoreData(List<Picvrfc> newData) {
        data.addAll(newData);
        notifyDataSetChanged();

    }*/


