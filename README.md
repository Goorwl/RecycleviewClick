## 目的：
### 由于官方的recycleview没有条目点击事件，需要我们自己去实现
## 步骤：
* 1 在MyAdapter中定义如下接口：
	
        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , String data);
        }
* 2 声明这个接口的变量

		private OnRecyclerViewItemClickListener mOnItemClickListener = null;
* 3 在onCreateViewHolder中对每个view设置条目点击事件

	    @Override
	    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
	        view.setOnClickListener(this);		// 设置点击事件
	        return new MyViewHolder(view);
	    }
* 4 把点击事件转移给外面的调用者

	    @Override
	    public void onClick(View v) {
	        if (mOnItemClickListener !=null){
	            // 设置tag在onBindViewHolder中设置
	            mOnItemClickListener.onItemClick(v,(String)v.getTag());
	        }
	    }
* 5 为每个题目设置点击数据

	    @Override
	    public void onBindViewHolder(MyViewHolder holder, int position) {
	        holder.itemView.setTag("位置为：" + position);		// 设置点击数据
	        holder.mTv1.setText(mList.get(position));
	        holder.mTv2.setText(mList.get(position));
	    }
* 6 给外面的调用者提供方法
	
	    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
	        this.mOnItemClickListener = listener;
	    }
* 以上步骤都在MyAdapter中完成
* 7 调用者代码

        MyAdapter adapter = new MyAdapter(list);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRv.setHasFixedSize(true);
		//给adapter设置点击事件
        adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        mRv.setAdapter(adapter);
## 效果展示：
![效果演示](http://i.imgur.com/4ESzgu3.gif)
## 项目地址
* GitHub：[项目地址](https://github.com/Goorwl/RecycleviewClick)
* 邮箱：[goorwl@163.com]()