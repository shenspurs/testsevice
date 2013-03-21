package com.example.testservice;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.testservice.R;

public class ServiceDemo extends Activity implements OnClickListener{
   
	private MyService  mMyService;
	private TextView mTextView;
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button bindServiceButton;
	private Button unbindServiceButton;
	private Context mContext;
	
	//这里需要用到ServiceConnection在Context.bindService和context.unBindService()里用到
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		//当我bindService时，让TextView显示MyService里getSystemTime()方法的返回值	
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mMyService = ((MyService.MyBinder)service).getService();
			mTextView.setText("I am frome Service :" + mMyService.getSystemTime());
		}
		
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
	};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }
    
    public void setupViews(){
    
    	mContext = ServiceDemo.this;
    	mTextView = (TextView)findViewById(R.id.text);
    	
    	
    	
    	startServiceButton = (Button)findViewById(R.id.startservice);
    	stopServiceButton = (Button)findViewById(R.id.stopservice);
    	bindServiceButton = (Button)findViewById(R.id.bindservice);
    	unbindServiceButton = (Button)findViewById(R.id.unbindservice);
    	
    	startServiceButton.setOnClickListener(this);
    	stopServiceButton.setOnClickListener(this);
    	bindServiceButton.setOnClickListener(this);
    	unbindServiceButton.setOnClickListener(this);
    }
   
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == startServiceButton){
			Intent i  = new Intent();
			i.setClass(ServiceDemo.this, MyService.class);
			mContext.startService(i);
		}else if(v == stopServiceButton){
			Intent i  = new Intent();
			i.setClass(ServiceDemo.this, MyService.class);
			mContext.stopService(i);
		}else if(v == bindServiceButton){
			Intent i  = new Intent();
			i.setClass(ServiceDemo.this, MyService.class);
			mContext.bindService(i, mServiceConnection, BIND_AUTO_CREATE);
		}else{
			mContext.unbindService(mServiceConnection);
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mContext.unbindService(mServiceConnection);
		super.onDestroy();
		
	}
	
}