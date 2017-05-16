package ypx.com.androidbend.adapter;

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


/**
 * Created by S01 on 2017/5/6.
 */

public class JokeAdapder extends BaseAdapter {
    private List<Joke> data;

    private ViewHolder holder;


    public JokeAdapder(List<Joke> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_joke, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.tv_joke.setText(data.get(i).getContent());
        return view;


    }

    class ViewHolder {


        TextView tv_joke;

        public ViewHolder(View view) {

            tv_joke = (TextView) view.findViewById(R.id.tv_joke);
        }
    }

    //下拉刷新第一页，先清空数据再从新添加数据，然后更新UI
    public void setNewData(List<Joke> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();

    }

    //加载更多，加载下一页数据那么请求page++,只需要把新的数据加到原数据的后面，然后更新UI
    public void setMoreData(List<Joke> newData) {
        data.addAll(newData);
        notifyDataSetChanged();

    }
}
