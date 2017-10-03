package com.example.teenpattinew;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

class NetworkThread1 extends HandlerThread {

    private Handler mWorkerHandler;

    public NetworkThread1(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        mWorkerHandler = new Handler(getLooper());
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }
}


@SuppressLint("NewApi") public class Login extends FragmentActivity implements TabListener
{
	ActionBar actionbar;
	ViewPager viewPager;
	int screenWidthDp,screenHeightDp,actionbarheight,newscreenHeightDp;
	
	//Handler Variable Initialization
		static  boolean handler_internet_connection_status=false;
	   Timer timer=null;
	   Handler myhandler_internet_connection_status=new Handler();
	    private NetworkThread1 mNetworkThread;
	    Runnable myupdateresults_internet_connection_status;
	//
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN| View.SYSTEM_UI_FLAG_IMMERSIVE );		
		
		getActionBar().setDisplayShowTitleEnabled(false);
		setContentView(R.layout.activity_login);
		
		viewPager=(ViewPager)findViewById(R.id.pager);
		viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionbar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				if(arg0==ViewPager.SCROLL_STATE_IDLE){}
				if(arg0==ViewPager.SCROLL_STATE_DRAGGING){}
				if(arg0==ViewPager.SCROLL_STATE_SETTLING){}
				
			}
		});
		
		actionbar=getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8b0000")));
			
			//Whole Screen Dimensions
		    Point size=new Point();
	        getWindowManager().getDefaultDisplay().getSize(size);
	        screenWidthDp=size.x;
	        screenHeightDp=size.y;
	        
	        //Action Bar Height
	        final TypedArray styledAttributes = this.getTheme().obtainStyledAttributes( new int[] { android.R.attr.actionBarSize });
	         actionbarheight = (int) styledAttributes.getDimension(0, 0);
	        
	         //ViewPager Height
	        newscreenHeightDp=screenHeightDp-actionbarheight; 
	        
	        Global.setscreenHeightDp(newscreenHeightDp);
	        Global.setscreenWidthDp(screenWidthDp);
		
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab signin=actionbar.newTab();
		signin.setText("SignIn");
		signin.setTabListener(this);
		
		ActionBar.Tab signup=actionbar.newTab();
		signup.setText("SignUp");
		signup.setTabListener(this);
		
		actionbar.addTab(signin);
		actionbar.addTab(signup);
		
		mNetworkThread = new NetworkThread1("NetworkThread");
        myupdateresults_internet_connection_status = new Runnable() {
            @Override
            public void run()
            {
                        myhandler_internet_connection_status.post(new Runnable() {
										                        	@Override
										                        	public void run()
										                        	{
											                        	handler_internet_connection_status= get_Internet_Connection_Status();
												                        if(handler_internet_connection_status==true)
												                        {
												                        	On_Internet_Connection_Status_Update();
												                        }
										                        	}
	                        									});

            
            }
        };

        int delay=4000;//delay for 1 sec
        int period=4000; //repeat every 4 sec
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
			        				    @Override
			        				    public void run()
			        				    {
			        				    	 mNetworkThread.postTask(myupdateresults_internet_connection_status);
			        				    }
                                  },delay,period);

		
		
	}
	
	class ViewPagerAdapter extends FragmentPagerAdapter
	{
		public ViewPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}
		public Fragment getItem(int arg)
		{
			Fragment fragment=null;
			if(arg==0)
			{
				fragment=new SignIn();
			}
			if(arg==1)
			{
				fragment=new SignUp();
			}
			return fragment;
		}
		
		public int getCount()
		{
			return 2;
		}
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	 public boolean get_Internet_Connection_Status()
	   	{
	   		boolean internet_connection_status=false;
	   		
	   		
	   		try {
	   			ConnectivityManager cManager=(ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
	   			NetworkInfo nInfo=cManager.getActiveNetworkInfo();
	   			
	   			if(nInfo!=null && nInfo.isConnected())
	   			{
	   				internet_connection_status=true;
	   			}
	   			else
	   			{
	   				internet_connection_status=false;
	   			}
	   		} catch (Exception e) {
	   			
	   			e.printStackTrace();
	   		}
	   		try
	   		{
	   			if(internet_connection_status==false)
	   			{
	   		   
	   				 Toast.makeText(this, "Check your Wifi/Internet Connection", Toast.LENGTH_LONG) .
	   				 setGravity(Gravity.CENTER_VERTICAL, 0,0);
	   				
	   				 
	   				 
	   			}
	   		}
	   		catch(Exception e)
	   		{
	   			e.printStackTrace();
	   		}
	   		
	   		
	   		return internet_connection_status;
	   		
	   		
	   	}
	 protected void onStart() {
			super.onStart();
	        boolean mWorkerThread_status=mNetworkThread.isAlive();
				if(mWorkerThread_status==false)
		        {
					try
					{
						 mNetworkThread.start();
			              mNetworkThread.onLooperPrepared();
					}
					catch(Exception e){e.printStackTrace();}
		             
		        }
		        
		       
		}
		

		
		@Override
		protected void onPause() {
			super.onPause();
	        boolean mWorkerThread_status=mNetworkThread.isAlive();
				if(mWorkerThread_status==true)
		        {
					try{
					    myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
		        	    mNetworkThread.quit();
		        	   
					}catch(Exception e){
						
					}
		        }
			
		}
		public void On_Internet_Connection_Status_Update()
	    {
	    	

	         
	        if(handler_internet_connection_status==true)
	    		{
	    			 
	    			
	    			 Toast.makeText(this, " Network Available", Toast.LENGTH_LONG).show();
	    		}
	    		
	    }
	    



			@Override
			protected void onStop() {
				// TODO Auto-generated method stub
				super.onStop();
		        boolean mWorkerThread_status=mNetworkThread.isAlive();
					if(mWorkerThread_status==true)
			        {
						try{
							   myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
				        	   mNetworkThread.quit();
							}catch(Exception e){
								
							}
			        }
			}
			
			@Override
			protected void onDestroy() {
				// TODO Auto-generated method stub
				super.onStop();
		        boolean mWorkerThread_status=mNetworkThread.isAlive();
					if(mWorkerThread_status==true)
			        {
						try{
							   myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
				        	   mNetworkThread.quit();
							}catch(Exception e){
								
							}
			        }
			}



}

