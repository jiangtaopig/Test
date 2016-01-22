package com.zjt.listviewload;

import java.util.ArrayList;
import java.util.List;

import com.zjt.listviewload.view.MyLoadListView;
import com.zjt.listviewload.view.MyLoadListView.LoadDataListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements LoadDataListener{

	private MyLoadListView mListView;
	private List<String> mDatas;
	private MyAdapter myAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initDatas();
        
        mListView = (MyLoadListView) findViewById(R.id.listview);
        mListView.setLoadDataListener(this);
        myAdapter = new MyAdapter(MainActivity.this);
        
        myAdapter.setDatas(mDatas);
        mListView.setAdapter(myAdapter);
        
    }

	private void initDatas() {
		// TODO Auto-generated method stub
		mDatas = new ArrayList<String>();
		for(int i=0;i<20;i++){
			mDatas.add("测试"+(i+1));
		}
	}

	@Override
	public void onLoadData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			mDatas.add("添加的数据"+i);
		}
		
		//刷新页面
		myAdapter.notifyDataSetChanged();
		//加载数据完成
		mListView.loadComplete();
	}


  
}
