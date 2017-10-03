package com.example.teenpattinew;

import java.util.Timer;
import java.util.TimerTask;

import com.example.teenpattinew.MenuActivity.tables_info;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


class NetworkThread_DataRetrieve extends HandlerThread {

    private Handler mWorkerHandler;

    public NetworkThread_DataRetrieve(String name) {
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

public class DataRetrieve extends Activity {

	 private int screenWidthDp;
	 private int screenHeightDp;
	 private ImageView progressStatus_image;
	 private TextView progressStatus_textview;
	 boolean dataRetrieval_flag=false;
	 private ImageView progressStatus_image1;
	 
	  //Handler variable initialization
	 	static  boolean handler_internet_connection_status=false;
	    Timer timer=null;
	    Handler myhandler_internet_connection_status=new Handler();
	    private NetworkThread_DataRetrieve mNetworkThread;
	    Runnable myupdateresults_internet_connection_status;
	    AsyncTask<Void, Void, Void> task;
	    AsyncTask<Void, Void, Void> userCoins;
	    AsyncTask<Void, Integer, Integer> userExistence;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		   /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/
		
		setContentView(R.layout.activity_data_retrieve);
		
		  Point size=new Point();
	      getWindowManager().getDefaultDisplay().getSize(size);
	      screenWidthDp=size.x;
	      screenHeightDp=size.y;
	      
	        mNetworkThread = new NetworkThread_DataRetrieve("NetworkThread3");
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
													                        	if(dataRetrieval_flag==false)
													                        	{
														                        	progressStatus_textview.setText("Retrieving Tables...");
															                  	    Global.table_list.clear();
															                  	        //starting background thread
															                  	    userCoins=new userCoinsRetreival().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); 
															                  	    task = new tables_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);													                  	      
															                  	    dataRetrieval_flag=true;
													                        	}
													                        	if(Global.getDataRetrieveActivityFlag()==true)
															                  	{
															                  	   userExistence=new updateUserExistenceinTable().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);														                  	        
															                  	}

													                        }
													                        else if(handler_internet_connection_status==false)
													                        {
													                        	progressStatus_textview.setText("Reconnecting...");
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

	@Override
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
		RelativeLayout data_retrieve_layout=(RelativeLayout)findViewById(R.id.data_retrieve_layout);//main layout
	    login_screen_init(data_retrieve_layout,screenWidthDp,screenHeightDp );
	    rotate_anim();
	    
	}

	private void login_screen_init(RelativeLayout data_retrieve_layout,int screenWidthDp2, int screenHeightDp2) 
	{
		// TODO Auto-generated method stub
	    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.p4);
	    data_retrieve_layout.setBackgroundDrawable(new BitmapDrawable(bitmap));
		
	    
	    int progressStatus_layout_width=(int)(screenWidthDp2*0.40);
        int progressStatus_layout_height=(int)(screenHeightDp2*0.60);
        final LinearLayout progressStatus_layout=(LinearLayout)data_retrieve_layout.findViewById(R.id.progressStatus_layout);
        progressStatus_layout.setLayoutParams(new RelativeLayout.LayoutParams(progressStatus_layout_width, progressStatus_layout_height));   
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)progressStatus_layout.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        progressStatus_layout.setLayoutParams(layoutParams);
       // progressStatus_layout.setBackgroundColor(Color.RED);
        
        int progressStatusImage_layout_width=(int)(progressStatus_layout_width);
        int progressStatusImage_layout_height=(int)(progressStatus_layout_height);
        final RelativeLayout progressStatusImage_layout=(RelativeLayout)progressStatus_layout.findViewById(R.id.progressStatusImage_layout);
      //  progressStatusImage_layout.setLayoutParams(new LayoutParams(progressStatusImage_layout_width, progressStatusImage_layout_height)); 
       // progressStatusImage_layout.setBackgroundColor(Color.GREEN);
        
         progressStatus_image=(ImageView)progressStatusImage_layout.findViewById(R.id.progressStatus_imageview);
        progressStatus_image.setImageResource(R.drawable.dataretrieverotate2);
        
        progressStatus_image1=(ImageView)progressStatusImage_layout.findViewById(R.id.progressStatus_imageview1);
       progressStatus_image1.setImageResource(R.drawable.dataretrieverotate1);
       RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)progressStatus_image1 .getLayoutParams();
		params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params2.height = (int) (progressStatusImage_layout_height);
		params2.width = (int) (progressStatusImage_layout_width);
        
        int progressStatusText_layout_width=(int)(screenWidthDp2*0.40);
        int progressStatusText_layout_height=(int)(screenHeightDp2*0.40);
        final LinearLayout progressStatusText_layout=(LinearLayout)data_retrieve_layout.findViewById(R.id.progressStatusText_layout);
        progressStatusText_layout.setLayoutParams(new RelativeLayout.LayoutParams(progressStatusText_layout_width, progressStatusText_layout_height)); 
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)progressStatusText_layout.getLayoutParams();
        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        layoutParams1.addRule(RelativeLayout.BELOW, R.id.progressStatus_layout);
        progressStatusText_layout.setLayoutParams(layoutParams1);
        // progressStatusText_layout.setBackgroundColor(Color.MAGENTA);
        
        progressStatus_textview=(TextView)progressStatusText_layout.findViewById(R.id.progressStatus_textview);
        progressStatus_textview.setText("Connecting...");
        progressStatus_textview.setTextColor(Color.WHITE);
        progressStatus_textview.setGravity(Gravity.CENTER);
        progressStatus_textview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        
	    
	}
	
	public void rotate_anim()
	{
		RotateAnimation rotate_progress=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
		rotate_progress.setFillEnabled(true);
		rotate_progress.setFillAfter(true);
		if(Global.getDataRetrieveActivityFlag()==true)
			rotate_progress.setDuration(2000);
		else 
			rotate_progress.setDuration(5000);
		rotate_progress.setRepeatCount(Animation.INFINITE);
		//setCancelab le(false);
		rotate_progress.setInterpolator(new AccelerateDecelerateInterpolator());
	
		rotate_progress.setAnimationListener(new AnimationListener(
				) {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
			
			}
		});
		progressStatus_image1.startAnimation(rotate_progress);
		
	
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
	   		
	   		return internet_connection_status;
	   		
	   	}
	  
	  
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		super.onDestroy();
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


	public class tables_info extends AsyncTask<Void,Void,Void>
	{
		
		Service_Client_Interaction client = new Service_Client_Interaction();
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			//rotate_anim();
		}


		@Override
		protected Void doInBackground(Void... params)
		{
			// TODO Auto-generated method stub

			try {
				client.table_Details();
				//Global.setVacantTableStatus(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(Global.getDataRetrieveActivityFlag()==false)
			{
				Intent i=new Intent(DataRetrieve.this, MenuActivity.class);
	            startActivity(i);
	            finish();
			}
					
			
		}
	
		
	}
	
	public class userCoinsRetreival extends AsyncTask<Void,Void,Void>
	{
		
		Service_Client_Interaction client = new Service_Client_Interaction();
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			//rotate_anim();
		}


		@Override
		protected Void doInBackground(Void... params)
		{
			// TODO Auto-generated method stub

			try {
				client.userCoinsRetreival(Global.getUserName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
		
	}
	public class updateUserExistenceinTable extends AsyncTask<Void,Integer,Integer>
	{
		
		Service_Client_Interaction client = new Service_Client_Interaction();
		int existenceStatus=0;
		
		@Override
		protected Integer doInBackground(Void... params)
		{
			// TODO Auto-generated method stub
			
			try 
			{
				existenceStatus=client.updateUserExistence(Global.getUserName());
				//Global.setVacantTableStatus(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return existenceStatus;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result==0)
			{
				if(Global.getNameofTheTable().equals("pub"))
				{
					startActivity(new Intent(DataRetrieve.this,PublicTable.class));
					finish();
				}
				else if(Global.getNameofTheTable().equals("prv"))
				{
					startActivity(new Intent(DataRetrieve.this,Table1Activity.class));
					finish();
				}
				else if(Global.getNameofTheTable().equals("lux"))
				{
					startActivity(new Intent(DataRetrieve.this,LuxuryTable.class));
					finish();
				}
			}
		}
		
		
		
	}
	
}
