package com.zjt.listviewload;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mDatas;
	
	public MyAdapter(Context context){
		mContext = context;
	}
	
	public void setDatas(List<String> data){
		mDatas = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder = null;
		if(convertView == null){
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
			mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.id_listview_tv);
			mViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.id_listview_img);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		mViewHolder.mTextView.setText(mDatas.get(position));
		
		return convertView;
	}

	public class ViewHolder{
		private TextView mTextView;
		private ImageView mImageView;
	}
	
}
