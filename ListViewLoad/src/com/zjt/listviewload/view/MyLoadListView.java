package com.zjt.listviewload.view;

import com.zjt.listviewload.R;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

;

public class MyLoadListView extends ListView implements OnScrollListener {

	private final static String TAG = "MyLoadListView";
	private View footerView;
	private LayoutInflater mInflater;
	private LinearLayout ll;
	/** �ܵ�item�� ***/
	private int mTotalItemCount;
	/** ���һ���ɼ���item ***/
	private int mLastVisibleItem;
	/**�Ƿ��һ�ν���**/
	private boolean isFirstEnter = false;
	
	public interface LoadDataListener{
		public void onLoadData();
	}
	
	private LoadDataListener mDataListener;
	
	public void setLoadDataListener(LoadDataListener dataListener){
		mDataListener = dataListener;
	}

	public MyLoadListView(Context context) {
		this(context, null);
	}

	public MyLoadListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyLoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

		initViews(context);
	}

	private void initViews(Context context) {
		// TODO Auto-generated method stub
		mInflater = LayoutInflater.from(context);
		footerView = mInflater.inflate(R.layout.footer_layout, null);
		ll = (LinearLayout) footerView.findViewById(R.id.id_load_wrapper);
		ll.setVisibility(View.INVISIBLE);
		this.addFooterView(footerView);
		this.setOnScrollListener(this);
	}

	/**
	 * ������ɺ�����loading��
	 */
	public void loadComplete(){
		isFirstEnter = false;
		ll.setVisibility(View.GONE);
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "mLastVisibleItem = "+mLastVisibleItem);
		
		if (mLastVisibleItem == mTotalItemCount
				&& scrollState == SCROLL_STATE_IDLE) {//��ʾ����ֹͣ

			if(!isFirstEnter){
				isFirstEnter = true;
				ll.setVisibility(View.VISIBLE);
				
				//���ظ�������ݣ�����ͨ���ص��ӿ���ʵ��
				if(mDataListener != null){
					new Handler().postDelayed(new Runnable() {//ģ��������ݺ�ʱ
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mDataListener.onLoadData();
						}
					}, 2000);
				}
			}
			
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		Log.d(TAG, "firstVisibleItem = "+firstVisibleItem+" , visibleItemCount = "
		+visibleItemCount+" ,totalItemCount =  "+totalItemCount);
		mTotalItemCount = totalItemCount;
		mLastVisibleItem = firstVisibleItem + visibleItemCount;
	}
	

}
