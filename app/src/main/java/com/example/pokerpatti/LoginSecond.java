package com.example.teenpattinew;


import java.util.Timer;
import java.util.TimerTask;

import com.example.teenpattinew.MenuActivity.user_Profile;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;


class NetworkThread_LoginSecond extends HandlerThread {

    private Handler mWorkerHandler;

    public NetworkThread_LoginSecond(String name) {
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


public class LoginSecond extends Activity implements OnClickListener
{

	  int screenWidthDp;
	  int screenHeightDp;
	  private int defaultImage_flag=0;
	  private ImageView userImage;
	  
	  //For Camera and Gallery
	  CameraPhoto cameraphoto;
	  final int CAMERA_REQUEST=13323;
	  GalleryPhoto galleryphoto;
	  final int GALLERY_REQUEST=221321;
	  Bitmap selected_bitmap_profile_image;
	  
	  EditText userName_edittext;
	  String userName="";
	  
	  //Handler variable initialization
	 	static  boolean handler_internet_connection_status=false;
	    Timer timer=null;
	    Handler myhandler_internet_connection_status=new Handler();
	    private NetworkThread_LoginSecond mNetworkThread;
	    Runnable myupdateresults_internet_connection_status;
	  
	    private MediaPlayer mp1;
	  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	       /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/
		
		try{setContentView(R.layout.activity_login_second);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidthDp=size.x;
        screenHeightDp=size.y;
        
        cameraphoto=new CameraPhoto(getApplicationContext());
        galleryphoto=new GalleryPhoto(getApplicationContext());
        
        
        mNetworkThread = new NetworkThread_LoginSecond("NetworkThread");
        myupdateresults_internet_connection_status = new Runnable() {
            @Override
            public void run()
            {
                        myhandler_internet_connection_status.post(new Runnable() {
										                        	@Override
										                        	public void run()
										                        	{
										                        		
											                        	handler_internet_connection_status= get_Internet_Connection_Status();
//												                        if(handler_internet_connection_status==true)
//												                        {
//												                        	On_Internet_Connection_Status_Update();
//												                        }
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
		
		 LinearLayout loginlayout=(LinearLayout)findViewById(R.id.main_login_layout);//main layout
	     loginlayout.setOrientation(LinearLayout.VERTICAL);
	     login_screen_init(loginlayout,screenWidthDp,screenHeightDp );
		
	}



	public void  login_screen_init(LinearLayout mainlinearlayout,int screenWidthDp,int screenHeightDp)
    {
        //Mainlayout.setBackgroundColor(Color.RED);
		  /* to set background image bg in main grid starts  */
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.p4);
        mainlinearlayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
	      /* to set background image bg in main grid ends  */
        
        /*LoginLayout_first*/
        int loginLayout_first_width=(int)(screenWidthDp);
        int loginLayout_first_height=(int)(screenHeightDp*0.45);
        final RelativeLayout loginLayout_first=(RelativeLayout) mainlinearlayout.findViewById(R.id.loginLayout_first);
        loginLayout_first.setLayoutParams(new LayoutParams(loginLayout_first_width, loginLayout_first_height));
        
        /*UserImage Layout*/
        int userNameImage_layout_width=(int)(loginLayout_first_width*0.80);
        int userNameImage_layout_height=(int)(loginLayout_first_height*0.90);
        final LinearLayout userNameImage_layout=(LinearLayout) loginLayout_first.findViewById(R.id.userNameImage_layout);
        userNameImage_layout.setLayoutParams(new RelativeLayout.LayoutParams(userNameImage_layout_width, userNameImage_layout_height));   
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)userNameImage_layout.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        userNameImage_layout.setLayoutParams(layoutParams);
        
        
        int cameraGallery_layout_width=(int)(userNameImage_layout_width*0.34);
        int cameraGallery_layout_height=(int)(userNameImage_layout_height);
        final RelativeLayout cameraGallery_layout=(RelativeLayout) userNameImage_layout.findViewById(R.id.cameraGallery_layout);
        cameraGallery_layout.setLayoutParams(new LayoutParams(cameraGallery_layout_width, cameraGallery_layout_height)); 
        

        int cameraGallery_layout2_width=(int)(cameraGallery_layout_width*0.20);
        int cameraGallery_layout2_height=(int)(cameraGallery_layout_height);
        final RelativeLayout cameraGallery_layout2=(RelativeLayout)cameraGallery_layout.findViewById(R.id.cameraGallery_layout2);
        cameraGallery_layout2.setLayoutParams(new RelativeLayout.LayoutParams(cameraGallery_layout2_width,cameraGallery_layout2_height)); 
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)cameraGallery_layout2.getLayoutParams();
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        cameraGallery_layout2.setLayoutParams(layoutParams1);
        
        int cameraGallery_layout3_width=(int)(cameraGallery_layout2_width);
        int cameraGallery_layout3_height=(int)(cameraGallery_layout2_height*0.70);
        final LinearLayout cameraGallery_layout3=(LinearLayout)cameraGallery_layout2.findViewById(R.id.cameraGallery_layout3);
        cameraGallery_layout3.setLayoutParams(new RelativeLayout.LayoutParams(cameraGallery_layout3_width,cameraGallery_layout3_height)); 
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams)cameraGallery_layout3.getLayoutParams();
        layoutParams4.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        cameraGallery_layout3.setLayoutParams(layoutParams4);
        
        int camera_layout_width=(int)(cameraGallery_layout3_width);
        int camera_layout_height=(int)(cameraGallery_layout3_height*0.50);
        final LinearLayout camera_layout=(LinearLayout)cameraGallery_layout3.findViewById(R.id.empty_gallery_layout);
        camera_layout.setLayoutParams(new LayoutParams(camera_layout_width,camera_layout_height)); 
        
        
        int gallery_layout_width=(int)(cameraGallery_layout3_width);
        int gallery_layout_height=(int)(cameraGallery_layout3_height*0.50);
        final LinearLayout gallery_layout=(LinearLayout)cameraGallery_layout3.findViewById(R.id.gallery_layout);
        gallery_layout.setLayoutParams(new LayoutParams(gallery_layout_width,gallery_layout_height)); 
        
        ImageView galleryImage=(ImageView)gallery_layout.findViewById(R.id.galleryImage);
        galleryImage.setImageResource(R.drawable.galleryupload2);
        galleryImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				try{
					startActivityForResult(galleryphoto.openGalleryIntent(),GALLERY_REQUEST);
				}
					
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        
        int userImage_layout_width=(int)(userNameImage_layout_width*0.32);
        int userImage_layout_height=(int)(userNameImage_layout_height);
        final LinearLayout userImage_layout=(LinearLayout)userNameImage_layout.findViewById(R.id.userImage_layout);
        userImage_layout.setLayoutParams(new LayoutParams(userImage_layout_width, userImage_layout_height));
        
        Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.second_character);
        
        
        userImage=(ImageView)userImage_layout.findViewById(R.id.userImage);
        if(defaultImage_flag==0)
        {
        	userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
        	Global.setImageBitmap(userImage_bitmap);
        	defaultImage_flag++;
        }
         
       
        
        
        int camera_Layout1_width=(int)(userNameImage_layout_width*0.34);
        int camera_Layout1_height=(int)(userNameImage_layout_height);
        final RelativeLayout camera_Layout1=(RelativeLayout)userNameImage_layout.findViewById(R.id.camera_Layout1);
        camera_Layout1.setLayoutParams(new LayoutParams(camera_Layout1_width, camera_Layout1_height)); 
        
        
        int camera_layout2_width=(int)(camera_Layout1_width*0.20);
        int camera_layout2_height=(int)(camera_Layout1_height);
        final RelativeLayout camera_layout2=(RelativeLayout)camera_Layout1.findViewById(R.id.camera_layout2);
        camera_layout2.setLayoutParams(new RelativeLayout.LayoutParams(camera_layout2_width,camera_layout2_height)); 
        RelativeLayout.LayoutParams Param1 = (RelativeLayout.LayoutParams)camera_layout2.getLayoutParams();
        Param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        camera_layout2.setLayoutParams(Param1);
        
        int camera_layout3_width=(int)(camera_layout2_width);
        int camera_layout3_height=(int)(camera_layout2_height*0.70);
        final LinearLayout camera_layout3=(LinearLayout)camera_layout2.findViewById(R.id.camera_layout3);
        camera_layout3.setLayoutParams(new RelativeLayout.LayoutParams(camera_layout3_width,camera_layout3_height)); 
        RelativeLayout.LayoutParams Params2 = (RelativeLayout.LayoutParams)camera_layout3.getLayoutParams();
        Params2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        camera_layout3.setLayoutParams(Params2);
        
        int empty_camera_layout_width=(int)(camera_layout3_width);
        int empty_camera_layout_height=(int)(camera_layout3_height*0.50);
        final LinearLayout empty_camera_layout=(LinearLayout)camera_layout3.findViewById(R.id.empty_camera_layout);
        empty_camera_layout.setLayoutParams(new LayoutParams(empty_camera_layout_width,empty_camera_layout_height)); 
        
        
        int cameraImage_layout_width=(int)(camera_layout3_width);
        int cameraImage_layout_height=(int)(camera_layout3_height*0.50);
        final LinearLayout cameraImage_layout=(LinearLayout)camera_layout3.findViewById(R.id.cameraImage_layout);
        cameraImage_layout.setLayoutParams(new LayoutParams(cameraImage_layout_width,cameraImage_layout_height)); 
        
        ImageView cameraImage=(ImageView)cameraImage_layout.findViewById(R.id.cameraImage);
        cameraImage.setImageResource(R.drawable.cameraupload2);
        cameraImage.setOnClickListener(new OnClickListener() {
		//#8C000000	
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				try
				{
					startActivityForResult(cameraphoto.takePhotoIntent(),CAMERA_REQUEST);
					cameraphoto.addToGallery();
				}
					
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        
        
       
       
        /*LoginLayout_second*/
        int loginLayout_second_width=(int)(screenWidthDp);
        int loginLayout_second_height=(int)(screenHeightDp*0.55);
        final RelativeLayout loginLayout_second=(RelativeLayout) mainlinearlayout.findViewById(R.id.loginLayout_second);
        loginLayout_second.setLayoutParams(new LayoutParams(loginLayout_second_width, loginLayout_second_height));
        
        /*DefaultUserImage Layout*/
        int defaultuserImage_layout_width=(int)(loginLayout_second_width);
        int defaultuserImage_layout_height=(int)(loginLayout_second_height*0.37);
        final LinearLayout defaultuserImage_layout=(LinearLayout)loginLayout_second.findViewById(R.id.defaultUserImage_layout);
        defaultuserImage_layout.setLayoutParams(new RelativeLayout.LayoutParams(defaultuserImage_layout_width, defaultuserImage_layout_height));
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)defaultuserImage_layout.getLayoutParams();
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        layoutParams2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        defaultuserImage_layout.setLayoutParams(layoutParams2);
        defaultuserImage_layout.setGravity(Gravity.CENTER);
        
        int imagesContainerBorder_layout1_width=(int)(defaultuserImage_layout_width);
        int imagesContainerBorder_layout1_height=(int)(defaultuserImage_layout_height*0.02);
        final LinearLayout imagesContainerBorder_layout1=(LinearLayout)defaultuserImage_layout.findViewById(R.id.imagesContainerBorder_layout1);
        imagesContainerBorder_layout1.setLayoutParams(new LayoutParams(imagesContainerBorder_layout1_width, imagesContainerBorder_layout1_height));
        
        
        
       HorizontalScrollView hview=(HorizontalScrollView)defaultuserImage_layout.findViewById(R.id.hview);
        hview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        hview.setHorizontalScrollBarEnabled(false);
        
        
        int imagesContainer_layout_width=(int)(defaultuserImage_layout_width*0.98);
        int imagesContainer_layout_height=(int)(defaultuserImage_layout_height*0.96);
        final LinearLayout imagesContainer_layout=(LinearLayout) hview.findViewById(R.id.imagesContainer_layout);
        imagesContainer_layout.setLayoutParams(new  FrameLayout.LayoutParams(imagesContainer_layout_width, imagesContainer_layout_height));
        imagesContainer_layout.setAlpha((float) 0.9);
        
        int empty_layout_width=(int)(imagesContainer_layout_width*0.02);
        int empty_layout_height=(int)(imagesContainer_layout_height);
        final LinearLayout empty_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout);
        empty_layout.setLayoutParams(new LayoutParams(empty_layout_width, empty_layout_height));
        
        int firstImage_layout_width=(int)(imagesContainer_layout_width*0.10);
        int firstImage_layout_height=(int)(imagesContainer_layout_height);
        final LinearLayout firstImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.firstImage_layout);
        firstImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView firstImage=(ImageView)firstImage_layout.findViewById(R.id.firstimage);
		Bitmap firstImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.first_character);
		firstImage.setImageBitmap(getCircleBitmap(firstImage_bitmap));
        firstImage.setOnClickListener(this);
        
        int empty_layout1_width=(int)(imagesContainer_layout_width*0.02);
        int empty_layout1_height=(int)(imagesContainer_layout_height);
        final LinearLayout empty_layout1=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout1);
        empty_layout1.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout secondImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.secondImage_layout);
        secondImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView secondimage=(ImageView)secondImage_layout.findViewById(R.id.secondimage);
        Bitmap secondImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.second_character);
        secondimage.setImageBitmap(getCircleBitmap(secondImage_bitmap));
        secondimage.setOnClickListener(this);
        
        final LinearLayout empty_layout2=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout2);
        empty_layout2.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout thirdImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.thirdImage_layout);
        thirdImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView thirdimage=(ImageView)thirdImage_layout.findViewById(R.id.thirdimage);
        Bitmap thirdImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.third_character);
        thirdimage.setImageBitmap(getCircleBitmap(thirdImage_bitmap));
        thirdimage.setOnClickListener(this);
        
        final LinearLayout empty_layout3=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout3);
        empty_layout3.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        
        final LinearLayout fourthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.fourthImage_layout);
        fourthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView fourthimage=(ImageView)fourthImage_layout.findViewById(R.id.fourthimage);
        Bitmap fourthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fourth_character);
        fourthimage.setImageBitmap(getCircleBitmap(fourthImage_bitmap));
        fourthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout4=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout4);
        empty_layout4.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        

        final LinearLayout fifthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.fifthImage_layout);
        fifthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView fifthimage=(ImageView)fifthImage_layout.findViewById(R.id.fifthimage);
        Bitmap fifthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fifth_character);
        fifthimage.setImageBitmap(getCircleBitmap(fifthImage_bitmap));
        fifthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout5=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout5);
        empty_layout5.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout sixthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.sixthImage_layout);
        sixthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView sixthimage=(ImageView)sixthImage_layout.findViewById(R.id.sixthimage);
       try{ 
    	   Bitmap sixthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sixth_character);
        sixthimage.setImageBitmap(getCircleBitmap(sixthImage_bitmap));
        sixthimage.setOnClickListener(this);
        }
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
        
        final LinearLayout empty_layout6=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout6);
        empty_layout6.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout seventhImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.seventhImage_layout);
        seventhImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView seventhimage=(ImageView)seventhImage_layout.findViewById(R.id.seventhimage);
        Bitmap seventhImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.seventh_character);
        seventhimage.setImageBitmap(getCircleBitmap(seventhImage_bitmap));
        seventhimage.setOnClickListener(this);
        
        final LinearLayout empty_layout7=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout7);
        empty_layout7.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout eighthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.eighthImage_layout);
        eighthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView eigthimage=(ImageView)eighthImage_layout.findViewById(R.id.eigthimage);
        Bitmap eigthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.eight_character);
        eigthimage.setImageBitmap(getCircleBitmap(eigthImage_bitmap));
        eigthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout8=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout8);
        empty_layout8.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout ninthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.ninthImage_layout);
        ninthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView ninthimage=(ImageView)ninthImage_layout.findViewById(R.id.ninthimage);
        Bitmap ninthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ninth_character);
        ninthimage.setImageBitmap(getCircleBitmap(ninthImage_bitmap));
        ninthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout9=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout9);
        empty_layout9.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        

        final LinearLayout tenthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.tenthImage_layout);
        tenthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView tenthimage=(ImageView)tenthImage_layout.findViewById(R.id.tenthimage);
        Bitmap tenthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tenth_character);
        tenthimage.setImageBitmap(getCircleBitmap(tenthImage_bitmap));
        tenthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout10=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout10);
        empty_layout10.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout eleventhImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.eleventhImage_layout);
        eleventhImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView eleventhimage=(ImageView)eleventhImage_layout.findViewById(R.id.eleventhimage);
        Bitmap eleventhImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.evelenth_character);
        eleventhimage.setImageBitmap(getCircleBitmap(eleventhImage_bitmap));
        eleventhimage.setOnClickListener(this);
        
        final LinearLayout empty_layout11=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout11);
        empty_layout11.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout twelthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.twelthImage_layout);
        twelthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView twelthimage=(ImageView)twelthImage_layout.findViewById(R.id.twelthimage);
        Bitmap twelthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.twelth_character);
        twelthimage.setImageBitmap(getCircleBitmap(twelthImage_bitmap));
        twelthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout12=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout12);
        empty_layout12.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout thirteenthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.thirteenthImage_layout);
        thirteenthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView thirteenthimage=(ImageView)thirteenthImage_layout.findViewById(R.id.thirteenthimage);
        Bitmap thirteenthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.thirteen_character);
        thirteenthimage.setImageBitmap(getCircleBitmap(thirteenthImage_bitmap));
        thirteenthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout13=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout13);
        empty_layout13.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        final LinearLayout fourteenthImage_layout=(LinearLayout)imagesContainer_layout.findViewById(R.id.fourteenthImage_layout);
        fourteenthImage_layout.setLayoutParams(new LayoutParams(firstImage_layout_width, firstImage_layout_height));
        
        ImageView fourteenthimage=(ImageView)fourteenthImage_layout.findViewById(R.id.fourteenthimage);
        Bitmap fourteenthImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fourteenth_character);
        fourteenthimage.setImageBitmap(getCircleBitmap(fourteenthImage_bitmap));
        fourteenthimage.setOnClickListener(this);
        
        final LinearLayout empty_layout14=(LinearLayout)imagesContainer_layout.findViewById(R.id.empty_layout14);
        empty_layout14.setLayoutParams(new LayoutParams(empty_layout1_width, empty_layout1_height));
        
        int imagesContainerBorder_layout2_width=(int)(defaultuserImage_layout_width);
        int imagesContainerBorder_layout2_height=(int)(defaultuserImage_layout_height*0.02);
        final LinearLayout imagesContainerBorder_layout2=(LinearLayout)defaultuserImage_layout.findViewById(R.id.imagesContainerBorder_layout2);
        imagesContainerBorder_layout2.setLayoutParams(new LayoutParams(imagesContainerBorder_layout2_width, imagesContainerBorder_layout2_height));
        
        /*userNameButton_layout Layout*/
        int userNameButton_layout_width=(int)(loginLayout_second_width*0.40);
        int userNameButton_layout_height=(int)(loginLayout_second_height*0.30);
        final LinearLayout userNameButton_layout=(LinearLayout)loginLayout_second.findViewById(R.id.userNameButton_layout);
        userNameButton_layout.setLayoutParams(new RelativeLayout.LayoutParams(userNameButton_layout_width, userNameButton_layout_height));
        RelativeLayout.LayoutParams Params3 = (RelativeLayout.LayoutParams)userNameButton_layout.getLayoutParams();
        Params3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        Params3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        userNameButton_layout.setLayoutParams(Params3);
        
        int userNameButton_layout2_width=(int)(userNameButton_layout_width);
        int userNameButton_layout2_height=(int)(userNameButton_layout_height*0.70);
        final LinearLayout userNameButton_layout2=(LinearLayout)userNameButton_layout.findViewById(R.id.userNameButton_layout2);
        userNameButton_layout2.setLayoutParams(new LayoutParams(userNameButton_layout2_width, userNameButton_layout2_height));
        
        int userName_layout_width=(int)(userNameButton_layout2_width*0.55);
        int userName_layout_height=(int)(userNameButton_layout2_height*0.90);
        final LinearLayout userName_layout=(LinearLayout)userNameButton_layout2.findViewById(R.id.userName_layout);
        userName_layout.setLayoutParams(new LayoutParams(userName_layout_width, userName_layout_height));
        
         userName_edittext=(EditText)userName_layout.findViewById(R.id.userName_edittext);
         if(Global.getUserName().equals("Enter Name"))
         {
        	 userName_edittext.setHint(Global.getUserName());
         }
         else
         {
           	 
             userName_edittext.setText(Global.getUserName()); 
         }
        userName_edittext.setGravity(Gravity.CENTER);
        userName_edittext.setCursorVisible(false);
        userName_edittext.setBackgroundColor(Color.TRANSPARENT);
        userName_edittext.setBackgroundDrawable((getResources().getDrawable(R.drawable.editbox)));
        // userName_edittext.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        
        int userLogin_layout_width=(int)(userNameButton_layout2_width*0.45);
        int userLogin_layout_height=(int)(userNameButton_layout2_height);
        final LinearLayout userLogin_layout=(LinearLayout)userNameButton_layout2.findViewById(R.id.userLogin_layout);
        userLogin_layout.setLayoutParams(new LayoutParams(userLogin_layout_width, userLogin_layout_height));
        
        ImageView userLogin_imageview=(ImageView)userLogin_layout.findViewById(R.id.userLogin_imageview);
        userLogin_imageview.setImageResource(R.drawable.button_login3);
        userLogin_imageview.setOnClickListener(this);
        
        int empty_userNameButton_layout_width=(int)(userNameButton_layout_width);
        int empty_userNameButton_layout_height=(int)(userNameButton_layout_height*0.10);
        final LinearLayout empty_userNameButton_layout=(LinearLayout)userNameButton_layout.findViewById(R.id.empty_userNameButton_layout);
        empty_userNameButton_layout.setLayoutParams(new LayoutParams(empty_userNameButton_layout_width, empty_userNameButton_layout_height)); 
  
        handler_internet_connection_status=Global.getNetConnectiongFlag();
        
    }
	
	private Bitmap getCircleBitmap(Bitmap bitmap) {
		final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(output);

		final int color = Color.RED;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawOval(rectF, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		// bitmap.recycle();

		return output;
	}



	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
		 if (mp1 != null) {
		      mp1.release();
		   }
		  mp1 = MediaPlayer.create(getApplicationContext(), R.raw.buttonclicksound);
		   mp1.start();
		   
		   mp1.setOnCompletionListener(new OnCompletionListener() {
											@Override
											public void onCompletion(MediaPlayer mp1) {	mp1.release();	}
		   								});
		
		if(v.getId()==R.id.firstimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.first_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
			
		}
		else if(v.getId()==R.id.secondimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.second_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.thirdimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.third_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.fourthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fourth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.fifthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fifth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.sixthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sixth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.seventhimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.seventh_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.eigthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.eight_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.ninthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ninth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.tenthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tenth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.eleventhimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.evelenth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.twelthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.twelth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.thirteenthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.thirteen_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.fourteenthimage)
		{
			Bitmap userImage_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fourteenth_character);
			userImage.setImageBitmap(getCircleBitmap(userImage_bitmap));
			Global.setImageBitmap(userImage_bitmap);
		}
		else if(v.getId()==R.id.userLogin_imageview)
		{
			 userName=userName_edittext.getText().toString();
			 if(!(userName.isEmpty()))
			 {
				 if(handler_internet_connection_status==true)
				 {
					 Global.setDataRetrieveActivityFlag(false);
					 new signUpClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				 }
				 else
					 Toast.makeText(getApplicationContext(), "Check your connection", Toast.LENGTH_SHORT).show();
				 
			 }
			 else
				 Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
			
		}
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK)
		{
			if(requestCode==CAMERA_REQUEST)
			{
				String photopath=cameraphoto.getPhotoPath();
				try {
		               selected_bitmap_profile_image = ImageLoader.init().from(photopath).requestSize(400,400).getBitmap(); 
		               userImage.setImageBitmap(getCircleBitmap(selected_bitmap_profile_image));
		              
		             
		             // user_profile_pic_string=ImageBase64.encode( selected_bitmap_profile_image);
		           //  String s= user_profile_pic_string.replaceAll("\\/","");
		             
		             
		            // Bitmap decode_image=ImageBase64.decode(user_profile_pic_string);
		             		            	 
		                  
		           
		              
		          } catch (Exception e) {
		        	  Toast.makeText(getApplicationContext(),"Something wrong while loading photo",
		        			  Toast.LENGTH_SHORT).show();
		          }	
			}
			else if(requestCode==GALLERY_REQUEST)
			{
				galleryphoto.setPhotoUri(data.getData());
				try {
					String photoPath = galleryphoto.getPath();  
				
					 selected_bitmap_profile_image = ImageLoader.init().from(photoPath).requestSize(512, 256).getBitmap();
					 userImage.setImageBitmap(getCircleBitmap( selected_bitmap_profile_image)); 
					 
		               //   user_profile_pic_string=ImageBase64.encode( selected_bitmap_profile_image);
		                // Bitmap decoded_image=ImageBase64.decode(user_profile_pic_string);
		                 
		              
		          } catch (Exception e) {
		        	  Toast.makeText(getApplicationContext(),"Something wrong while selecting photo",
		        			  Toast.LENGTH_SHORT).show();
		          }
			}
			
			Global.setImageBitmap(selected_bitmap_profile_image);
			//new user_Profile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			
		}
	}
	
    public void On_Internet_Connection_Status_Update()
    {
    	/* finding all the controls starts   */
    	

        if(handler_internet_connection_status==true)
    		{
        	  
    			 
    		}	
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
//   		try
//   		{
//   			if(internet_connection_status==false)
//   			{
//   			
//   			}
//   		}
//   		catch(Exception e)
//   		{
//   			e.printStackTrace();
//   		}
   		
   		
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
//		   boolean mWorkerThread_status=mNetworkThread.isAlive();
//					if(mWorkerThread_status==true)
//			        {
//						try{
//						    myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
//			        	    mNetworkThread.quit();
//			        	   
//						}catch(Exception e){
//							
//						}
//			        }
	}



	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		   boolean mWorkerThread_status=mNetworkThread.isAlive();
//			if(mWorkerThread_status==true)
//	        {
//				try{
//					   myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
//		        	   mNetworkThread.quit();
//					}catch(Exception e){
//						
//					}
//	        }
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




	public class signUpClass extends AsyncTask<Void, Integer, Integer>
	{
		
		//Context mContext;
        private ProgressDialog mDialog;

       	private int Sign_up_status;
		Service_Client_Interaction client1=new Service_Client_Interaction();
		
  
		@Override
		  protected Integer doInBackground(Void... arg0)
		  {
		        try
		        {
		    	  Sign_up_status= client1.signup_json(userName,Global.getWindow_id());
		        } 
		        catch (Exception e)
		        {
		        	e.printStackTrace();
			  	}
		     
			return Sign_up_status;
			
		  }

		@Override
		protected void onPostExecute(Integer result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			  //start
	    	  if (result==8 )
	    	  {		    		  
	    		  Toast.makeText(LoginSecond.this, "User Already exists", Toast.LENGTH_LONG).show();

	    	  }
	    	  else 
	    	  {
	    		  Global.setUserName(userName);
	    		  try{Intent i=new Intent(LoginSecond.this, DataRetrieve.class);
                  startActivity(i);}
	    		  catch(Exception e){
                  	e.printStackTrace();
                  	}
	    		  	
	    	  }
	    	  //setProgressBarIndeterminateVisibility(false);
	    	// mDialog.dismiss();
			
		}
		  
	
		  
	}

	
}
