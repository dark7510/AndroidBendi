package ypx.com.androidbend.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ypx.com.androidbend.R;
import ypx.com.androidbend.bean.Joke;
import ypx.com.androidbend.bean.News;


/**
 * Created by S01 on 2017/4/28.
 */

public class NewsAdapter extends BaseAdapter {
    private List<News> data;
    private ViewHolder holder;

    public NewsAdapter(List<News> data, Activity activity) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int positon) {
        return data.get(positon);
    }

    @Override
    public long getItemId(int positon) {
        return positon;
    }

    @Override
    public View getView(int positon, View converView, ViewGroup parent) {
        if (converView == null) {
            converView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null);
            holder = new ViewHolder(converView);
            converView.setTag(holder);

        } else {
            holder = (ViewHolder) converView.getTag();
        }
        //加载网络图片
        Glide.with(parent.getContext())
                .load(data.get(positon).getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.img_news);

        holder.tv_title.setText(data.get(positon).getTitle());
        return converView;
    }

    class ViewHolder {
        ImageView img_news;
        TextView tv_title;

        public ViewHolder(View converView) {
            img_news = (ImageView) converView.findViewById(R.id.img_news);
            tv_title = (TextView) converView.findViewById(R.id.tv_title);
        }
    }

    //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
    public void setNewData(List<News> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();

    }

    //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
    public void setMoreData(List<News> newData) {
        data.addAll(newData);
        notifyDataSetChanged();

    }
}
