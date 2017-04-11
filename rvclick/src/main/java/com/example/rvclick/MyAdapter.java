package com.example.rvclick;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wule on 2017/4/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {

    // 2 声明变量
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    // 4 把点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener !=null){
            // 设置tag在onBindViewHolder中设置
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    // 5 暴漏给外面的调用者
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 1  定义接口，模拟点击事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    List<String> mList = null;

    public MyAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        // 3 给view设置点击事件
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag("位置为：" + position);
        holder.mTv1.setText(mList.get(position));
        holder.mTv2.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    public final TextView mTv1;
    public final TextView mTv2;

    public MyViewHolder(View itemView) {
        super(itemView);
        mTv1 = (TextView) itemView.findViewById(R.id.tv1);
        mTv2 = (TextView) itemView.findViewById(R.id.tv2);
    }
}
