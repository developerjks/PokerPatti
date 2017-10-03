
package com.example.teenpattinew;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.ThreadPoolExecutor;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.LinearLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

@SuppressLint("NewApi") public class MenuActivity extends Activity implements OnClickListener,AnimationListener
{
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		rotate_wheel_thread_stopping.cancel(true);
		
	}

	static int screenWidthDp,screenHeightDp;
	static ImageView rotatingWheel_imageview, rotatingWheelPin_imageview;
	static RelativeLayout rotatingWheel_layout2_relative;
	
	static View custom_toast_layout;
	 static TextView custom_toast_textview;
	 
	 //For Camera and Gallery
	 CameraPhoto cameraphoto;
	 final int CAMERA_REQUEST=13323;
	 GalleryPhoto galleryphoto;
	 final int GALLERY_REQUEST=221321;
	 
	
	 
	//for navigation drawer
	public static DrawerLayout drawerlayout;
	ListView drawerlist;
	 String[] drawer_settings_string_array;
	 
	 AsyncTask<Void, Void, Void> rotate_wheel_thread_stopping;
	 
	 
	 String user_profile_pic_string;
	 Bitmap selected_bitmap_profile_image;
	 static Vibrator vib ;
	 ImageView publicTable;
	 ImageView privateTable;
	 ImageView luxuryTable;
	 boolean tableChosen=false; 
	
	 
	 @SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) 
	 {
         super.onCreate(savedInstanceState);
         
//       mDialog = new ProgressDialog(this);
//       mDialog.setMessage("Please wait...");
//       mDialog.setIndeterminate(false);
//       mDialog.setMax(100);
//       mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//       mDialog.setCancelable(true);
//      try{ mDialog.show();}
//      catch(Exception e)
//      {
//   	   e.printStackTrace();
//      }

    /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	/*to make display full screen ends*/

    setContentView(R.layout.activity_menu);
    
	
    cameraphoto=new CameraPhoto(getApplicationContext());
    galleryphoto=new GalleryPhoto(getApplicationContext());
    
    drawerlayout=(DrawerLayout)findViewById(R.id.menuActivityDrawer_layout);
	drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	
    Resources res=getResources();
	drawer_settings_string_array=res.getStringArray(R.array.settings);
	drawerlist=(ListView) findViewById(R.id.drawerList);
	drawerlist.setDivider(null);
	drawerlist.setDividerHeight(0);
	
    ListAdapter adapter=new ListAdapter(this, drawer_settings_string_array);
	drawerlist.setAdapter(adapter);

    Point size=new Point();
    getWindowManager().getDefaultDisplay().getSize(size);
    screenWidthDp=size.x;
    screenHeightDp=size.y;
    Global.setscreenHeightDp(screenHeightDp);
    Global.setscreenWidthDp(screenWidthDp);
    
    vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    /* to get screen width and height of real device ends  */
}
	 
	  protected void onResume()
	    {
	        super.onResume();

	        tableChosen=false;
	        LinearLayout menuLayout=(LinearLayout)findViewById(R.id.menuLayout);//main layout
	        menuLayout.setOrientation(LinearLayout.VERTICAL);
	        
	        menu_third_screen_init(menuLayout,screenWidthDp,screenHeightDp );
	        
	     }

	private void menu_third_screen_init(LinearLayout menulayout,int screenWidthDp2, int screenHeightDp2) 
	{
		
		
//for custom toat which is used after rotation of wheel
		//Creating the LayoutInflater instance  
        LayoutInflater li = getLayoutInflater();  
    //Getting the View object as defined in the customtoast.xml file  
        custom_toast_layout = li.inflate(R.layout.customtoast,  
          (ViewGroup) findViewById(R.id.custom_toast));
        int toastwidth = (int) (screenWidthDp2*0.80);
		int toastheight = (int) (screenHeightDp2 * 0.80);
		
        custom_toast_layout.setLayoutParams(new LayoutParams(
				toastwidth, toastheight));
         custom_toast_textview=(TextView)custom_toast_layout.findViewById(R.id.custom_toast_message);
 
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.p4);
        menulayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        int topMenu_layout_width=(int)(screenWidthDp);
        int topMenu_layout_height=(int)(screenHeightDp*0.15);   
        final LinearLayout topMenu_layout = (LinearLayout)menulayout.findViewById(R.id.topMenu_layout);
		topMenu_layout.setLayoutParams(new LayoutParams(topMenu_layout_width, topMenu_layout_height));
		//topMenu_layout.setBackgroundColor(Color.BLACK);
		
		
		int rotatingWheel_layout_width = (int) (topMenu_layout_width * 0.25);
		int rotatingWheel_layout_height = (int) (topMenu_layout_height);
		final LinearLayout rotatingWheel_layout = (LinearLayout)topMenu_layout.findViewById(R.id.rotatingWheel_layout);
		try{rotatingWheel_layout.setLayoutParams(new LayoutParams(rotatingWheel_layout_width,rotatingWheel_layout_height));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//rotatingWheel_layout.setBackgroundColor(Color.YELLOW);
		
		rotatingWheel_layout2_relative = (RelativeLayout)rotatingWheel_layout.findViewById(R.id.rotatingWheel_layout2_relative);
		rotatingWheel_imageview = (ImageView)rotatingWheel_layout2_relative.findViewById(R.id.rotatingWheel_imageview);
		rotatingWheel_imageview.setImageResource(R.drawable.wheel_img1);

		rotatingWheelPin_imageview = (ImageView)rotatingWheel_layout2_relative.findViewById(R.id.rotatingWheelPin_imageview);
		rotatingWheelPin_imageview.setImageResource(R.drawable.jko);
		RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) rotatingWheelPin_imageview.getLayoutParams();
		params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params2.height = (int) (rotatingWheel_layout_height * 0.15);
		params2.width = (int) (rotatingWheel_layout_width * 0.1);
		
		rotate_wheel_thread_stopping = new usergamedetailsclass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

		RotateAnimation rotate = new RotateAnimation(0, 15000,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		rotate.setFillAfter(true);
		rotate.setDuration(200000);
		rotate.setInterpolator(new AccelerateDecelerateInterpolator());
		rotate.setRepeatCount(Animation.INFINITE);
		rotatingWheel_imageview.startAnimation(rotate);

		rotatingWheel_imageview.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) 
			{
			
				final ViewGroup overlaying = (ViewGroup) rotatingWheel_layout2_relative
						.getParent().getParent().getParent().getParent();
				overlaying.getOverlay().add(rotatingWheel_layout2_relative);
				ScaleAnimation scale = new ScaleAnimation(1.0f, 4.3f, 1.0f,
						5.5f, Animation.RELATIVE_TO_SELF, 0.0f,
						Animation.RELATIVE_TO_SELF, 0.0f);

				scale.setFillAfter(true);
				scale.setDuration(5000);
				scale.setAnimationListener(MenuActivity.this);
				rotatingWheel_layout2_relative.startAnimation(scale);
				 

			}
		});
        
		
		
//        
//        final LinearLayout first_vertical_linear_layout=(LinearLayout) menulayout.findViewById(R.id.userInfo_layout);
//        first_vertical_linear_layout.setLayoutParams(new LayoutParams(topMenu_layout_width, topMenu_layout_height));
//      // first_vertical_linear_layout.setBackgroundColor(Color.BLACK);
       
       int howToPlay_layout_width=(int)(topMenu_layout_width*0.25);
       int howToPlay_layout_height=(int)(topMenu_layout_height);
       final RelativeLayout howToPlay_layout=(RelativeLayout)topMenu_layout.findViewById(R.id.howToPlay_Relativelayout);
       howToPlay_layout.setLayoutParams(new LayoutParams(howToPlay_layout_width, howToPlay_layout_height));
       //howToPlay_layout.setBackgroundColor(Color.GREEN);
       
       int howToPlay_LinearLayout_width=(int)(howToPlay_layout_width*0.50);
       int howToPlay_LinearLayout_height=(int)(howToPlay_layout_height*0.80);
       final LinearLayout howToPlay_LinearLayout=(LinearLayout)howToPlay_layout.findViewById(R.id.howToPlay_LinearLayout);
       howToPlay_LinearLayout.setLayoutParams(new RelativeLayout.LayoutParams(howToPlay_LinearLayout_width, howToPlay_LinearLayout_height));
      // howToPlay_LinearLayout.setBackgroundColor(Color.MAGENTA);
       RelativeLayout.LayoutParams howToPlay_LinearLayout_param = (RelativeLayout.LayoutParams)howToPlay_LinearLayout.getLayoutParams();
       howToPlay_LinearLayout_param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
       howToPlay_LinearLayout.setLayoutParams(howToPlay_LinearLayout_param);
       
       ImageView howToPlay_imageview=(ImageView)howToPlay_LinearLayout.findViewById(R.id.howToPlay_imageview);
       howToPlay_imageview.setImageResource(R.drawable.howtoplay);
       howToPlay_imageview.setOnClickListener(new Dialog_Click());

       int buyCoins_Relativelayout_width=(int)(topMenu_layout_width*0.25);
       int buyCoins_Relativelayout_height=(int)(topMenu_layout_height);
       final RelativeLayout buyCoins_Relativelayout=(RelativeLayout)topMenu_layout.findViewById(R.id.buyCoins_Relativelayout);
       buyCoins_Relativelayout.setLayoutParams(new LayoutParams(buyCoins_Relativelayout_width, buyCoins_Relativelayout_height));
      // buyCoins_Relativelayout.setBackgroundColor(Color.RED);
 
       int buyCoins_Linearlayout_width=(int)(buyCoins_Relativelayout_width*0.60);
       int buyCoins_Linearlayout_height=(int)(buyCoins_Relativelayout_height*0.80);
       final LinearLayout buyCoins_Linearlayout=(LinearLayout)buyCoins_Relativelayout.findViewById(R.id.buyCoins_Linearlayout);
       buyCoins_Linearlayout.setLayoutParams(new RelativeLayout.LayoutParams(buyCoins_Linearlayout_width, buyCoins_Linearlayout_height));
      // buyCoins_Linearlayout.setBackgroundColor(Color.LTGRAY);
       RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)buyCoins_Linearlayout.getLayoutParams();
       layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
       buyCoins_Linearlayout.setLayoutParams(layoutParams);
       
   		ImageView ratingImageView=(ImageView)buyCoins_Linearlayout.findViewById(R.id.ratingImageView);
   		//ratingImageView.setBackgroundColor(Color.MAGENTA);
   		ratingImageView.setImageResource(R.drawable.rating_image);
       
       int settingsButton_Relativelayout_width=(int)(topMenu_layout_width*0.25);
       int settingsButton_Relativelayout_height=(int)(topMenu_layout_height);
       final RelativeLayout settingsButton_Relativelayout=(RelativeLayout)topMenu_layout.findViewById(R.id.settingsButton_Relativelayout);
       settingsButton_Relativelayout.setLayoutParams(new LayoutParams(settingsButton_Relativelayout_width, settingsButton_Relativelayout_height));
      // settingsButton_Relativelayout.setBackgroundColor(Color.MAGENTA);
       
       int settingsButton_Linearlayout_width=(int)(settingsButton_Relativelayout_width*0.40);
       int settingsButton_Linearlayout_height=(int)(settingsButton_Relativelayout_height*0.40);
       final LinearLayout settingsButton_Linearlayout=(LinearLayout)settingsButton_Relativelayout.findViewById(R.id.settingsButton_Linearlayout);
       settingsButton_Linearlayout.setLayoutParams(new RelativeLayout.LayoutParams(settingsButton_Linearlayout_width, settingsButton_Linearlayout_height));
     //  settingsButton_Linearlayout.setBackgroundColor(Color.LTGRAY);
       RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)settingsButton_Linearlayout.getLayoutParams();
       layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
       layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
       settingsButton_Linearlayout.setLayoutParams(layoutParams1);
       
       ImageView settingsButton=(ImageView)settingsButton_Linearlayout.findViewById(R.id.settingsButton);
       settingsButton.setImageResource(R.drawable.settingsb1);
       settingsButton.setOnClickListener(this);

        int tablesOption_layout_width=(int)(screenWidthDp);
        int tablesOption_layout_height=(int)(screenHeightDp*0.71);
        final LinearLayout tablesOption_layout=(LinearLayout) menulayout.findViewById(R.id.tablesOption_layout);
        tablesOption_layout.setLayoutParams(new LayoutParams(tablesOption_layout_width, tablesOption_layout_height));
      //  tablesOption_layout.setBackgroundColor(Color.GREEN);
        
        int tablesOption_emptylayout1_width=(int)(tablesOption_layout_width);
        int tablesOption_emptylayout1_height=(int)(tablesOption_layout_height*0.15);
        final LinearLayout tablesOption_emptylayout1=(LinearLayout)tablesOption_layout.findViewById(R.id.tablesOption_emptylayout1);
        tablesOption_emptylayout1.setLayoutParams(new LayoutParams(tablesOption_emptylayout1_width, tablesOption_emptylayout1_height));
       // tablesOption_emptylayout1.setBackgroundColor(Color.GREEN);
        
        int tablesOption_layout2_width=(int)(tablesOption_layout_width);
        int tablesOption_layout2_height=(int)(tablesOption_layout_height*0.70);
        final LinearLayout tablesOption_layout2=(LinearLayout)tablesOption_layout.findViewById(R.id.tablesOption_layout2);
        tablesOption_layout2.setLayoutParams(new LayoutParams(tablesOption_layout2_width, tablesOption_layout2_height));
       // tablesOption_layout2.setBackgroundColor(Color.MAGENTA);
        
      int publicTable_layout_width=(int)(tablesOption_layout2_width*0.33);
      int publicTable_layout_height=(int)(tablesOption_layout2_height);
     final RelativeLayout publicTable_layout=(RelativeLayout)tablesOption_layout2.findViewById(R.id.publicTable_layout);
     publicTable_layout.setLayoutParams(new LayoutParams(publicTable_layout_width, publicTable_layout_height));
     //publicTable_layout.setBackgroundColor(Color.BLACK);
      
     publicTable=(ImageView)publicTable_layout.findViewById(R.id.publicTable);
    publicTable.setLayoutParams(new RelativeLayout.LayoutParams((int)(publicTable_layout_width),(int) (publicTable_layout_height*0.95)));
    publicTable.setImageResource(R.drawable.publict);
    //publicTable.setBackgroundColor(Color.GREEN);
    RelativeLayout.LayoutParams publiTable_param = (RelativeLayout.LayoutParams)publicTable.getLayoutParams();
    publiTable_param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
    publicTable.setLayoutParams(publiTable_param);
	  publicTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				
				if(v.getId()==R.id.publicTable)
				{
				RotateAnimation rotate_progress=new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
				//rotate_progress.setFillEnabled(true);
				rotate_progress.setFillAfter(false);
				rotate_progress.setDuration(2000);
				//rotate_progress.setRepeatCount(Animation.INFINITE);
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
					public void onAnimationEnd(Animation animation)
					{
						// TODO Auto-generated method stub
					
								 //task.cancel(true);
						Global.setDataRetrieveActivityFlag(true);
						Global.setNameOfTheTable("pub");
						 Intent intent=new Intent(MenuActivity.this,DataRetrieve.class);
				    		startActivity(intent);
					
					}
				});
				if(tableChosen==false)
				{
					publicTable.startAnimation(rotate_progress);
					tableChosen=true;
				}
				
				}

			}
		});  
    
    
     // Global.table_list.clear();
      //starting background thread
		//final AsyncTask<Void, Void, Void> task = new tables_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

     // table1Option_layout.setPadding(9, 0, 5, 18);
      int privateTable_layout_width=(int)(tablesOption_layout2_width*0.34);
      int privateTable_layout_height=(int)(tablesOption_layout2_height);
      final RelativeLayout privateTable_layout=(RelativeLayout)tablesOption_layout2.findViewById(R.id.privateTable_layout);
      privateTable_layout.setLayoutParams(new LayoutParams(privateTable_layout_width, privateTable_layout_height));
      //privateTable_layout.setBackgroundColor(Color.LTGRAY);
      
      privateTable=(ImageView)privateTable_layout.findViewById(R.id.privateTable);
      privateTable.setLayoutParams(new RelativeLayout.LayoutParams((int)(privateTable_layout_width),(int) (privateTable_layout_height*0.95)));
      privateTable.setImageResource(R.drawable.privatet);
      //privateTable.setBackgroundColor(Color.MAGENTA);
      RelativeLayout.LayoutParams privateTable_param = (RelativeLayout.LayoutParams)privateTable.getLayoutParams();
      privateTable_param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
      privateTable.setLayoutParams(privateTable_param);
	  privateTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(v.getId()==R.id.privateTable)
				{
				RotateAnimation rotate_progress=new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
				//rotate_progress.setFillEnabled(true);
				rotate_progress.setFillAfter(false);
				rotate_progress.setDuration(2000);
				//rotate_progress.setRepeatCount(Animation.INFINITE);
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
					public void onAnimationEnd(Animation animation)
					{
						// TODO Auto-generated method stub
						
								// task.cancel(true);
								Global.setDataRetrieveActivityFlag(true);
								Global.setNameOfTheTable("prv");
								 Intent intent=new Intent(MenuActivity.this,DataRetrieve.class);
						    		startActivity(intent);
					
					}
				});
				
				if(tableChosen==false)
				{
					privateTable.startAnimation(rotate_progress);
					tableChosen=true;
				}
				}

			}
		});
     

     // second_verticalh2_linear_layout.setPadding(9, 0, 5, 18);
      int luxuryTable_layout_width=(int)(tablesOption_layout2_width*0.33);
      int luxuryTable_layout_height=(int)(tablesOption_layout2_height);
      final RelativeLayout luxuryTable_layout=(RelativeLayout)tablesOption_layout2.findViewById(R.id.luxuryTable_layout);
      luxuryTable_layout.setLayoutParams(new LayoutParams(luxuryTable_layout_width, luxuryTable_layout_height));
      //luxuryTable_layout.setBackgroundColor(Color.RED);
      
      luxuryTable=(ImageView)luxuryTable_layout.findViewById(R.id.luxuryTable);
      luxuryTable.setLayoutParams(new RelativeLayout.LayoutParams((int)(luxuryTable_layout_width),(int) (luxuryTable_layout_height*0.95)));
      luxuryTable.setImageResource(R.drawable.luxuryt);
     // luxuryTable.setBackgroundColor(Color.BLACK);
      RelativeLayout.LayoutParams luxuryTable_param = (RelativeLayout.LayoutParams)luxuryTable.getLayoutParams();
      luxuryTable_param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
      luxuryTable.setLayoutParams(luxuryTable_param);
      luxuryTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(v.getId()==R.id.luxuryTable)
				{
				RotateAnimation rotate_progress=new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
				//rotate_progress.setFillEnabled(true);
				rotate_progress.setFillAfter(false);
				rotate_progress.setDuration(2000);
				//rotate_progress.setRepeatCount(Animation.INFINITE);
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
					public void onAnimationEnd(Animation animation)
					{
						// TODO Auto-generated method stub
						
								// task.cancel(true);
						Global.setDataRetrieveActivityFlag(true);
						Global.setNameOfTheTable("lux");
						 Intent intent=new Intent(MenuActivity.this,DataRetrieve.class);
				    		startActivity(intent);
					
					}
				});
				
				if(tableChosen==false)
				{
					luxuryTable.startAnimation(rotate_progress);
					tableChosen=true;
				}
				}

			}
		});
    
        int tablesOption_emptylayout2_width=(int)(tablesOption_layout_width);
        int tablesOption_emptylayout2_height=(int)(tablesOption_layout_height*0.15);
        final LinearLayout tablesOption_emptylayout2=(LinearLayout)tablesOption_layout.findViewById(R.id.tablesOption_emptylayout2);
        tablesOption_emptylayout2.setLayoutParams(new LayoutParams(tablesOption_emptylayout2_width, tablesOption_emptylayout2_height));
       // tablesOption_emptylayout2.setBackgroundColor(Color.CYAN);
             
        int bottomMenu_layout_width=(int)(screenWidthDp);
        int bottomMenu_layout_height=(int)(screenHeightDp*0.14);
        final LinearLayout bottomMenu_layout=(LinearLayout)menulayout.findViewById(R.id.bottomMenu_layout);
        bottomMenu_layout.setLayoutParams(new LayoutParams(bottomMenu_layout_width, bottomMenu_layout_height));
       // third_vertical_linear_layout.setBackgroundColor(Color.BLACK);
        
        int bottomMenu_emptylayout1_width=(int)(bottomMenu_layout_width*0.06);
        int bottomMenu_emptylayout1_height=(int)(bottomMenu_layout_height);
        final LinearLayout bottomMenu_emptylayout1=(LinearLayout)bottomMenu_layout.findViewById(R.id.bottomMenu_emptylayout1);
        bottomMenu_emptylayout1.setLayoutParams(new LayoutParams(bottomMenu_emptylayout1_width, bottomMenu_emptylayout1_height));
       // bottomMenu_emptylayout1.setBackgroundColor(Color.BLUE);
        
        int bottomMenu_emptylayout2_width=(int)(bottomMenu_emptylayout1_width);
        int bottomMenu_emptylayout2_height=(int)(bottomMenu_emptylayout1_height*0.33);
        final LinearLayout bottomMenu_emptylayout2=(LinearLayout)bottomMenu_emptylayout1.findViewById(R.id.bottomMenu_emptylayout2);
        bottomMenu_emptylayout2.setLayoutParams(new LayoutParams(bottomMenu_emptylayout2_width, bottomMenu_emptylayout2_height));
      //  bottomMenu_emptylayout2.setBackgroundColor(Color.MAGENTA);
        
        int bottomMenu_emptylayout3_width=(int)(bottomMenu_emptylayout1_width);
        int bottomMenu_emptylayout3_height=(int)(bottomMenu_emptylayout1_height*0.67);
        final LinearLayout bottomMenu_emptylayout3=(LinearLayout)bottomMenu_emptylayout1.findViewById(R.id.bottomMenu_emptylayout3);
        bottomMenu_emptylayout3.setLayoutParams(new LayoutParams(bottomMenu_emptylayout3_width, bottomMenu_emptylayout3_height));
      //  bottomMenu_emptylayout3.setBackgroundColor(Color.GRAY);
        
        int userInfo_layout_width=(int)(bottomMenu_layout_width*0.30);
        int userInfo_layout_height=(int)(bottomMenu_layout_height);
        final LinearLayout userInfo_layout=(LinearLayout)bottomMenu_layout.findViewById(R.id.userInfo_layout);
        userInfo_layout.setLayoutParams(new LayoutParams(userInfo_layout_width, userInfo_layout_height));
       // userInfo_layout.setBackgroundColor(Color.GREEN);
        
        int userImage_layout_width=(int)(userInfo_layout_width*0.28);
        int userImage_layout_height=(int)(userInfo_layout_height);
        final LinearLayout userImage_layout=(LinearLayout)userInfo_layout.findViewById(R.id.userImage_layout);
        userImage_layout.setLayoutParams(new LayoutParams(userImage_layout_width, userImage_layout_height));
       // userImage_layout.setBackgroundColor(Color.MAGENTA);
        
        int userImage_layout1_width=(int)(userImage_layout_width);
        int userImage_layout1_height=(int)(userImage_layout_height);
        final LinearLayout userImage_layout1=(LinearLayout)userImage_layout.findViewById(R.id.userImage_layout1);
        userImage_layout1.setLayoutParams(new LayoutParams(userImage_layout1_width, userImage_layout1_height));
       // userImage_layout1.setBackgroundColor(Color.BLACK);
       
        ImageView userProfilepic_imageview=(ImageView)userImage_layout1.findViewById(R.id.userProfilepic);
        userProfilepic_imageview.setImageBitmap(getCircleBitmap(Global.getImageBitmap()));
        userProfilepic_imageview.setOnClickListener(new Dialog_Click());
        
        int userImage_userName_emptylayout_width=(int)(userInfo_layout_width*0.10);
        int userImage_userName_emptylayout_height=(int)(userInfo_layout_height);
        final LinearLayout userImage_userName_emptylayout=(LinearLayout)userInfo_layout.findViewById(R.id.userImage_userName_emptylayout);
        userImage_userName_emptylayout.setLayoutParams(new LayoutParams(userImage_userName_emptylayout_width, userImage_userName_emptylayout_height));
       // userImage_userName_emptylayout.setBackgroundColor(Color.BLUE);
        
        int userImage_userName_emptylayout1_width=(int)(userImage_userName_emptylayout_width);
        int userImage_userName_emptylayout1_height=(int)(userImage_userName_emptylayout_height*0.33);
        final LinearLayout userImage_userName_emptylayout1=(LinearLayout)userImage_userName_emptylayout.findViewById(R.id.userImage_userName_emptylayout1);
        userImage_userName_emptylayout1.setLayoutParams(new LayoutParams(userImage_userName_emptylayout1_width, userImage_userName_emptylayout1_height));
        //userImage_userName_emptylayout1.setBackgroundColor(Color.BLUE);
        
        int userImage_userName_emptylayout2_width=(int)(userImage_userName_emptylayout_width);
        int userImage_userName_emptylayout2_height=(int)(userImage_userName_emptylayout_height*0.67);
        final LinearLayout userImage_userName_emptylayout2=(LinearLayout)userImage_userName_emptylayout.findViewById(R.id.userImage_userName_emptylayout2);
        userImage_userName_emptylayout2.setLayoutParams(new LayoutParams(userImage_userName_emptylayout2_width, userImage_userName_emptylayout2_height));
        //userImage_userName_emptylayout2.setBackgroundColor(Color.GREEN);
        
        int userName_layout_width=(int)(userInfo_layout_width*0.62);
        int userName_layout_height=(int)(userInfo_layout_height);
        final LinearLayout userName_layout=(LinearLayout)userInfo_layout.findViewById(R.id.userName_layout);
        userName_layout.setLayoutParams(new LayoutParams(userName_layout_width, userName_layout_height));
       // userName_layout.setBackgroundColor(Color.YELLOW);
                
        int userName_emptylayout1_width=(int)(userName_layout_width);
        int userName_emptylayout1_height=(int)(userName_layout_height*0.33);
        final LinearLayout userName_emptylayout1=(LinearLayout)userName_layout.findViewById(R.id.userName_emptylayout1);
        userName_emptylayout1.setLayoutParams(new LayoutParams(userName_emptylayout1_width, userName_emptylayout1_height));
       // userName_emptylayout1.setBackgroundColor(Color.MAGENTA);
        
        int userName_layout1_width=(int)(userName_layout_width);
        int userName_layout1_height=(int)(userName_layout_height*0.67);
        final LinearLayout userName_layout1=(LinearLayout)userName_layout.findViewById(R.id.userName_layout1);
        userName_layout1.setLayoutParams(new LayoutParams(userName_layout1_width, userName_layout1_height));
       // userName_layout1.setBackgroundColor(Color.GRAY);
       
        TextView userProfilename_textview=(TextView)userName_layout1.findViewById(R.id.userProfilename);
        userProfilename_textview.setAllCaps(true);
        userProfilename_textview.setText(Global.getUserName());
        userProfilename_textview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        userProfilename_textview.setGravity(Gravity.CENTER_VERTICAL);
        userProfilename_textview.setTypeface(null,Typeface.BOLD);
        userProfilename_textview.setTextColor(Color.parseColor("#FF8C00"));

        int other_layout_width=(int)(bottomMenu_layout_width*0.64);
        int other_layout_height=(int)(bottomMenu_layout_height);
        final LinearLayout other_layout=(LinearLayout)bottomMenu_layout.findViewById(R.id.other_layout);
        other_layout.setLayoutParams(new LayoutParams(other_layout_width, other_layout_height));
       // other_layout.setBackgroundColor(Color.RED);
        
        int other_layout_empty_width=(int)(other_layout_width);
        int other_layout_empty_height=(int)(other_layout_height*0.33);
        final LinearLayout other_layout_empty=(LinearLayout)other_layout.findViewById(R.id.other_layout_empty);
        other_layout_empty.setLayoutParams(new LayoutParams(other_layout_empty_width, other_layout_empty_height));
      //  bottomMenu_emptylayout2.setBackgroundColor(Color.MAGENTA);
        

        int fbw_rating_layout_width=(int)(other_layout_width);
        int fbw_rating_layout_height=(int)(other_layout_height*0.67);
        final LinearLayout fbw_rating_layout=(LinearLayout)other_layout.findViewById(R.id.fbw_rating_layout);
        fbw_rating_layout.setLayoutParams(new LayoutParams(fbw_rating_layout_width, fbw_rating_layout_height));
       // fbw_rating_layout.setBackgroundColor(Color.GREEN);
        
        int fbw_layout_width=(int)(fbw_rating_layout_width*0.60);
        int fbw_layout_height=(int)(fbw_rating_layout_height);
        final LinearLayout fbw_layout=(LinearLayout)fbw_rating_layout.findViewById(R.id.fbw_layout);
        fbw_layout.setLayoutParams(new LayoutParams(fbw_layout_width, fbw_layout_height));
       // fbw_layout.setBackgroundColor(Color.MAGENTA);
        
        
        int facebook_layout_width=(int)(fbw_layout_width*0.50);
        int facebook_layout_height=(int)(fbw_layout_height);
        final LinearLayout facebook_layout=(LinearLayout)fbw_layout.findViewById(R.id.facebook_layout);
        facebook_layout.setLayoutParams(new LayoutParams(facebook_layout_width, facebook_layout_height));
        //facebook_layout.setBackgroundColor(Color.LTGRAY);
        
//        ImageView facebookShare_image=(ImageView)facebook_layout.findViewById(R.id.facebookShare_imageview);
//        facebookShare_image.setImageResource(R.drawable.facebook_share);
//        //facebookShare_image.setBackgroundColor(Color.RED);
//       // facebookShare_image.setOnClickListener(new Dialog_Click());
        
        int whatsapp_layout_width=(int)(fbw_layout_width*0.50);
        int whatsapp_layout_height=(int)(fbw_layout_height);
        final LinearLayout whatsapp_layout=(LinearLayout)fbw_layout.findViewById(R.id.whatsapp_layout);
        whatsapp_layout.setLayoutParams(new LayoutParams(whatsapp_layout_width, whatsapp_layout_height));
       // whatsapp_layout.setBackgroundColor(Color.MAGENTA);
        
    
        
        int rating_layout_width=(int)(fbw_rating_layout_width*0.40);
        int rating_layout_height=(int)(fbw_rating_layout_height);
        final RelativeLayout rating_layout=(RelativeLayout)fbw_rating_layout.findViewById(R.id.rating_layout);
        rating_layout.setLayoutParams(new LayoutParams(rating_layout_width, rating_layout_height));
       //rating_layout.setBackgroundColor(Color.YELLOW);
    
        
        int rating_layout1_width=(int)(rating_layout_width*0.80);
        int rating_layout1_height=(int)(rating_layout_height);
        final LinearLayout rating_layout1=(LinearLayout)rating_layout.findViewById(R.id.rating_layout1);
        rating_layout1.setLayoutParams(new RelativeLayout.LayoutParams(rating_layout1_width, rating_layout1_height));
        //rating_layout1.setBackgroundColor(Color.GREEN);
        RelativeLayout.LayoutParams ratingParam = (RelativeLayout.LayoutParams)rating_layout1.getLayoutParams();
    	ratingParam.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
    	rating_layout1.setLayoutParams(ratingParam);
     
        ImageView whatsappShare_image=(ImageView)rating_layout1.findViewById(R.id.whatsappShare_imageview);
        whatsappShare_image.setImageResource(R.drawable.whatsapp_share);
        //whatsappShare_image.setBackgroundColor(Color.GRAY);
        whatsappShare_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "Share POKERPATTI");
				sendIntent.setType("text/plain");
				sendIntent.setPackage("com.whatsapp");
				startActivity(sendIntent);
			}
		});

         
	}


	public class Dialog_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.howToPlay_imageview){
				LayoutInflater layoutinflater=LayoutInflater.from(MenuActivity.this);
				View dialogview=layoutinflater.inflate(R.layout.howtoplay_dialog, null);
  			    howToPlay_dialog_init(dialogview,screenWidthDp,screenHeightDp);
  			
			}
//			if (v.getId() == R.id.buyCoins_imageview) {
//				
//				
//				vib.vibrate(400);
//				
//				LayoutInflater layoutinflater=LayoutInflater.from(MenuActivity.this);
//				try{View dialogview=layoutinflater.inflate(R.layout.activity_second_dialog, null);
//  			    chips_bonus_dialog_init(dialogview,screenWidthDp,screenHeightDp);}
//				catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			
//			}
			else if(v.getId()==R.id.userProfilepic)
			{
				vib.cancel();
				LayoutInflater layoutinflater=LayoutInflater.from(MenuActivity.this);
				View dialogview=layoutinflater.inflate(R.layout.user_details_dialog, null);
  			    user_profile_init(dialogview,screenWidthDp,screenHeightDp);
  			    
			}
			
		}
				
	}
	
    private void user_profile_init(View dialogview,int screenWidthDp, int screenHeightDp) {
		// TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/ 
    	
    	AlertDialog.Builder builder= new AlertDialog.Builder(MenuActivity.this);    	
    	builder.setView(dialogview);
    	final AlertDialog alert=builder.create();
    	alert.setCancelable(true);
       // alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    	alert.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //
    	alert.show();
    	/*To display alert dialog box on onclick button starts here*/ 
    	/*set width and height of dialogbox  starts here*/
    	int screenWidthDpDialog=(int)(screenWidthDp*0.70);
    	int screenHeightDpDialog=(int)(screenHeightDp*0.65);
    	alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
    	alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    	
//    	alert.getWindow().findViewById(R.id.user_details_layout).setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
//    	alert.getWindow().findViewById(R.id.user_details_layout).setBackgroundColor(Color.GREEN);
    	LinearLayout choose_table_linearLayout=(LinearLayout)alert.getWindow().findViewById(R.id.user_details_layout);
    	choose_table_linearLayout.setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
    	try{choose_table_linearLayout.setBackgroundColor(Color.GRAY);}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	int userProfileTitle_layout_width=(int)(screenWidthDpDialog);
    	int userProfileTitle_layout_height=(int)(screenHeightDpDialog*0.20);
    	LinearLayout userProfileTitle_layout=new LinearLayout(getApplicationContext());
    	userProfileTitle_layout.setLayoutParams(new LayoutParams(userProfileTitle_layout_width, userProfileTitle_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	userProfileTitle_layout.setOrientation(LinearLayout.HORIZONTAL);
    	userProfileTitle_layout.setGravity(Gravity.CENTER);
    	userProfileTitle_layout.setBackgroundColor(Color.BLACK);
    	choose_table_linearLayout.addView(userProfileTitle_layout);
    	
    	TextView userProfileTitle=new TextView(getApplicationContext());
    	userProfileTitle.setText("PROFILE"); 
    	userProfileTitle.setTextSize(25);
    	userProfileTitle.setTypeface(null,Typeface.BOLD);
    	userProfileTitle.setTextColor(Color.parseColor("#FF8C00"));
    	userProfileTitle_layout.addView(userProfileTitle);
    	
    	int userProfile_layout_width=(int)(screenWidthDpDialog);
    	int userProfile_layout_height=(int)(screenHeightDpDialog*0.80);
    	RelativeLayout userProfile_layout=new RelativeLayout(getApplicationContext());
    	userProfile_layout.setLayoutParams(new LayoutParams(userProfile_layout_width, userProfile_layout_height));
    	//userProfile_layout.setBackgroundColor(Color.GRAY);
    	//userProfile_layout.setBackgroundResource(R.drawable.p4);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	choose_table_linearLayout.addView(userProfile_layout);
    	
   	
    	int userProfileLinear_layout_width=(int)(userProfile_layout_width*0.85);
    	int userProfileLinear_layout_height=(int)(userProfile_layout_height*0.82);
    	LinearLayout userProfileLinear_layout=new LinearLayout(getApplicationContext());
    	userProfileLinear_layout.setLayoutParams(new RelativeLayout.LayoutParams(userProfileLinear_layout_width, userProfileLinear_layout_height));
    	//userProfileLinear_layout.setBackgroundColor(Color.MAGENTA);
    	userProfileLinear_layout.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userProfile_layout.addView(userProfileLinear_layout);
    	RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) userProfileLinear_layout.getLayoutParams();
		param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		userProfileLinear_layout.setLayoutParams(param1);
		
		int userProfileImage_layout_width=(int)(userProfileLinear_layout_width*0.40);
    	int userProfileImage_layout_height=(int)(userProfileLinear_layout_height);
    	RelativeLayout userProfileImage_layout=new RelativeLayout(getApplicationContext());
    	userProfileImage_layout.setLayoutParams(new LayoutParams(userProfileImage_layout_width, userProfileImage_layout_height));
    	//userProfileImage_layout.setBackgroundColor(Color.GREEN);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userProfileLinear_layout.addView(userProfileImage_layout);
    	
    	int userProfileImage_layout2_width=(int)(userProfileImage_layout_width);
    	int userProfileImage_layout2_height=(int)(userProfileImage_layout_height*0.90);
    	LinearLayout userProfileImage2_layout=new LinearLayout(getApplicationContext());
    	userProfileImage2_layout.setLayoutParams(new LayoutParams(userProfileImage_layout2_width, userProfileImage_layout2_height));
    	//userProfileImage2_layout.setBackgroundColor(Color.CYAN);
    	userProfileImage2_layout.setOrientation(LinearLayout.HORIZONTAL);
    	userProfileImage_layout.addView(userProfileImage2_layout);
    	RelativeLayout.LayoutParams param2 = (RelativeLayout.LayoutParams) userProfileImage2_layout.getLayoutParams();
		param2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		userProfileImage2_layout.setLayoutParams(param2);
		
		ImageView userProfileImage=new ImageView(getApplicationContext());
		userProfileImage.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		userProfileImage.setImageBitmap(getCircleBitmap(Global.getImageBitmap()));
		userProfileImage.setScaleType(ScaleType.FIT_XY);
		userProfileImage2_layout.addView(userProfileImage);
    	
    	
    	int userProfileInfo_layout_width=(int)(userProfileLinear_layout_width*0.60);
    	int userProfileInfo_layout_height=(int)(userProfileLinear_layout_height);
    	RelativeLayout userProfileInfo_layout=new RelativeLayout(getApplicationContext());
    	userProfileInfo_layout.setLayoutParams(new LayoutParams(userProfileInfo_layout_width, userProfileInfo_layout_height));
    	//userProfileInfo_layout.setBackgroundColor(Color.YELLOW);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userProfileLinear_layout.addView(userProfileInfo_layout);
    	
    	int userProfileInfo_layout2_width=(int)(userProfileInfo_layout_width);
    	int userProfileInfo_layout2_height=(int)(userProfileInfo_layout_height*0.60);
    	LinearLayout userProfileInfo_layout2=new LinearLayout(getApplicationContext());
    	userProfileInfo_layout2.setLayoutParams(new LayoutParams(userProfileInfo_layout2_width, userProfileInfo_layout2_height));
    	//userProfileInfo_layout2.setBackgroundColor(Color.BLACK);
    	userProfileInfo_layout2.setOrientation(LinearLayout.VERTICAL);
    	userProfileInfo_layout.addView(userProfileInfo_layout2);
    	RelativeLayout.LayoutParams param3 = (RelativeLayout.LayoutParams) userProfileInfo_layout2.getLayoutParams();
		param3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		userProfileInfo_layout2.setLayoutParams(param3);
		
		int userName_layout_width=(int)(userProfileInfo_layout2_width);
    	int userName_layout_height=(int)(userProfileInfo_layout2_height*0.50);
    	LinearLayout userName_layout=new LinearLayout(getApplicationContext());
    	userName_layout.setLayoutParams(new LayoutParams(userName_layout_width, userName_layout_height));
    	//userName_layout.setBackgroundColor(Color.RED);
    	userName_layout.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userProfileInfo_layout2.addView(userName_layout);
    	
    	int userNameempty_layout1_width=(int)(userName_layout_width*0.15);
    	int userNameempty_layout1_height=(int)(userName_layout_height);
    	LinearLayout userNameempty_layout1=new LinearLayout(getApplicationContext());
    	userNameempty_layout1.setLayoutParams(new LayoutParams(userNameempty_layout1_width, userNameempty_layout1_height));
    	//userNameempty_layout1.setBackgroundColor(Color.BLUE);
    	userNameempty_layout1.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userName_layout.addView(userNameempty_layout1);
    	
    	int userName_layout2_width=(int)(userName_layout_width*0.85);
    	int userName_layout2_height=(int)(userName_layout_height);
    	LinearLayout userName_layout2=new LinearLayout(getApplicationContext());
    	userName_layout2.setLayoutParams(new LayoutParams(userName_layout2_width, userName_layout2_height));
    	//userName_layout2.setBackgroundColor(Color.RED);
    	userName_layout2.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userName_layout.addView(userName_layout2);
    	
    	TextView userName=new TextView(getApplicationContext());
    	userName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	userName.setText(Global.getUserName());
    	userName.setGravity(Gravity.CENTER_VERTICAL);
    	userName.setTypeface(null,Typeface.BOLD);
    	//userName.setBackgroundColor(Color.WHITE);
    	userName.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
    	userName_layout2.addView(userName);
    	
    	int userCoin_layout_width=(int)(userProfileInfo_layout2_width);
    	int userCoin_layout_height=(int)(userProfileInfo_layout2_height*0.50);
    	LinearLayout userCoin_layout=new LinearLayout(getApplicationContext());
    	userCoin_layout.setLayoutParams(new LayoutParams(userCoin_layout_width, userCoin_layout_height));
    	//userCoin_layout.setBackgroundColor(Color.GREEN);
    	userCoin_layout.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userProfileInfo_layout2.addView(userCoin_layout);
    	
    	int userCoinempty_layout1_width=(int)(userCoin_layout_width*0.15);
    	int userCoinempty_layout1_height=(int)(userCoin_layout_height*0.78);
    	LinearLayout userCoinempty_layout1=new LinearLayout(getApplicationContext());
    	userCoinempty_layout1.setLayoutParams(new LayoutParams(userCoinempty_layout1_width, userCoinempty_layout1_height));
    	//userCoinempty_layout1.setBackgroundColor(Color.MAGENTA);
    	userCoinempty_layout1.setOrientation(LinearLayout.HORIZONTAL);
    	userCoin_layout.addView(userCoinempty_layout1);
    	LinearLayout.LayoutParams param_usercoin = (LinearLayout.LayoutParams) userCoinempty_layout1.getLayoutParams();
    	param_usercoin.gravity=Gravity.CENTER;
		userCoinempty_layout1.setLayoutParams(param_usercoin);
    	
    	ImageView userCoins=new ImageView(getApplicationContext());
    	userCoins.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	userCoins.setImageResource(R.drawable.usercoin);
    	//userCoins.setBackgroundColor(Color.GREEN);
    	userCoins.setScaleType(ScaleType.FIT_XY);
    	userCoinempty_layout1.addView(userCoins);
    	
    	int userCoin_layout2_width=(int)(userCoin_layout_width*0.85);
    	int userCoin_layout2_height=(int)(userCoin_layout_height);
    	LinearLayout userCoin_layout2=new LinearLayout(getApplicationContext());
    	userCoin_layout2.setLayoutParams(new LayoutParams(userCoin_layout2_width, userCoin_layout2_height));
    	//userCoin_layout2.setBackgroundColor(Color.BLACK);
    	userCoin_layout2.setOrientation(LinearLayout.HORIZONTAL);
    	//userProfile_layout.setGravity(Gravity.CENTER);
    	userCoin_layout.addView(userCoin_layout2);
    	
    	TextView userCoinText=new TextView(getApplicationContext());
    	userCoinText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	userCoinText.setText("\u20B9"+Global.getUserCoin());
//    	Drawable d=getResources().getDrawable(R.drawable.usercoin);
//    	Bitmap b = ((BitmapDrawable)d).getBitmap();
//        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, (int)(userCoin_layout2_width*0.30),(int)( userCoin_layout2_height*0.80), false);
//        Drawable d2 = new BitmapDrawable(getResources(), bitmapResized);
//    	userCoinText.setCompoundDrawablesWithIntrinsicBounds(d2, null,null, null);
    	//userCoinText.setCompoundDrawablePadding(...);

    	//userCoinText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.buycoin, 0);
    	//userCoinText.setGravity(Gravity.CENTER);
    	//userName.setBackgroundColor(Color.WHITE);
    	userCoinText.setGravity(Gravity.CENTER_VERTICAL);
    	userCoinText.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
    	userCoin_layout2.addView(userCoinText);
    	
    	
    			
	}

    private void howToPlay_dialog_init(View dialogview,int screenWidthDp, int screenHeightDp) {
		// TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/ 
    	
    	AlertDialog.Builder builder= new AlertDialog.Builder(MenuActivity.this);    	
    	builder.setView(dialogview);
    	final AlertDialog alert=builder.create();
    	alert.setCancelable(true);
       // alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    	alert.getWindow().getAttributes().windowAnimations = R.style.DialogTheme2; //
    	alert.show();
    	/*To display alert dialog box on onclick button starts here*/ 
    	/*set width and height of dialogbox  starts here*/
    	int screenWidthDpDialog=(int)(screenWidthDp*0.50);
    	int screenHeightDpDialog=(int)(screenHeightDp*0.99);
    	alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
    	alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    	
//    	alert.getWindow().findViewById(R.id.howtoplay_dialog_layout).setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
//    	alert.getWindow().findViewById(R.id.howtoplay_dialog_layout).setBackgroundColor(Color.GREEN);
    	LinearLayout howtoplaydialog_linearlayout=(LinearLayout)alert.getWindow().findViewById(R.id.howtoplaydialog_linearlayout);
    	howtoplaydialog_linearlayout.setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
    	try{howtoplaydialog_linearlayout.setBackgroundColor(Color.GRAY);}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	int howtoplayTitle_layout_width=(int)(screenWidthDpDialog);
    	int howtoplayTitle_layout_height=(int)(screenHeightDpDialog*0.12);
    	LinearLayout howtoplayTitle_layout=new LinearLayout(getApplicationContext());
    	howtoplayTitle_layout.setLayoutParams(new LayoutParams(howtoplayTitle_layout_width, howtoplayTitle_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	howtoplayTitle_layout.setOrientation(LinearLayout.HORIZONTAL);
    	howtoplayTitle_layout.setGravity(Gravity.CENTER);
    	howtoplayTitle_layout.setBackgroundColor(Color.parseColor("#8C000000"));
    	howtoplaydialog_linearlayout.addView(howtoplayTitle_layout);
    	
    	TextView howtoplayTitle=new TextView(getApplicationContext());
    	howtoplayTitle.setText("Cards Ranking"); 
    	howtoplayTitle.setTextSize(25);
    	howtoplayTitle.setTypeface(null,Typeface.BOLD);
    	howtoplayTitle.setTextColor(Color.parseColor("#FF8C00"));
    	howtoplayTitle_layout.addView(howtoplayTitle);
    	//howtoplayTitle.setBackgroundColor(Color.GREEN);
    	
    	int cardRanking_layout_width=(int)(screenWidthDpDialog);
    	int cardRanking_layout_height=(int)(screenHeightDpDialog*0.88);
    	LinearLayout cardRanking_layout=new LinearLayout(getApplicationContext());
    	cardRanking_layout.setLayoutParams(new LayoutParams(cardRanking_layout_width, cardRanking_layout_height));
    	cardRanking_layout.setOrientation(LinearLayout.HORIZONTAL);
    	howtoplaydialog_linearlayout.addView(cardRanking_layout);
    	//cardRanking_layout.setBackgroundColor(Color.YELLOW);
    	
    	int cardRankingMeter_layout_width=(int)(cardRanking_layout_width*0.05);
    	int cardRankingMeter_layout_height=(int)(cardRanking_layout_height);
    	LinearLayout cardRankingMeter_layout=new LinearLayout(getApplicationContext());
    	cardRankingMeter_layout.setLayoutParams(new LayoutParams(cardRankingMeter_layout_width, cardRankingMeter_layout_height));
    	cardRankingMeter_layout.setOrientation(LinearLayout.VERTICAL);
    	cardRanking_layout.addView(cardRankingMeter_layout);
    	cardRankingMeter_layout.setBackgroundColor(Color.BLACK);
    	
    	ImageView cardRankingMeter=new ImageView(getApplicationContext());
    	cardRankingMeter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	cardRankingMeter.setImageResource(R.drawable.cardrankingmeter);
    	cardRankingMeter.setScaleType(ScaleType.FIT_XY);
    	cardRankingMeter_layout.addView(cardRankingMeter);
    	//cardRankingMeter.setBackgroundColor(Color.BLUE);
    	
    	
    	int cardRanking_layout2_width=(int)(cardRanking_layout_width*0.95);
    	int cardRanking_layout2_height=(int)(cardRanking_layout_height);
    	LinearLayout cardRanking_layout2=new LinearLayout(getApplicationContext());
    	cardRanking_layout2.setLayoutParams(new LayoutParams(cardRanking_layout2_width, cardRanking_layout2_height));
    	cardRanking_layout2.setOrientation(LinearLayout.VERTICAL);
    	cardRanking_layout.addView(cardRanking_layout2);
    	//cardRanking_layout2.setBackgroundColor(Color.MAGENTA);
    	
     	int highestCard_layout_width=(int)(cardRanking_layout2_width);
    	int highestCard_layout_height=(int)(cardRanking_layout2_height*0.07);
    	LinearLayout highestCard_layout=new LinearLayout(getApplicationContext());
    	highestCard_layout.setLayoutParams(new LayoutParams(highestCard_layout_width, highestCard_layout_height));
    	highestCard_layout.setOrientation(LinearLayout.HORIZONTAL);
    	cardRanking_layout2.addView(highestCard_layout);
    	//highestCard_layout.setBackgroundColor(Color.GREEN);
    	
    	TextView highestCard=new TextView(getApplicationContext());
    	highestCard.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	highestCard.setText("HIGHEST");
    	highestCard.setTextSize(15);
    	highestCard.setTypeface(null,Typeface.BOLD);
    	highestCard.setTextColor(Color.BLACK);
    	//highestCard.setBackgroundColor(Color.GRAY);
    	highestCard_layout.addView(highestCard);
    	
    	int cardRanking_scrollview_width=(int)(cardRanking_layout2_width);
    	int cardRanking_scrollview_height=(int)(cardRanking_layout2_height*0.86);
    	ScrollView cardRanking_scrollview=new ScrollView(getApplicationContext());
    	cardRanking_scrollview.setLayoutParams(new LayoutParams(cardRanking_scrollview_width, cardRanking_scrollview_height));
    	//cardRanking_scrollview.setBackgroundColor(Color.RED);
    	cardRanking_layout2.addView(cardRanking_scrollview);
    	
     	int hightoLowcardRanking_layout_width=(int)(cardRanking_scrollview_width);
    	int hightoLowcardRanking_layout_height=(int)(cardRanking_scrollview_height);
    	LinearLayout hightoLowcardRanking_layout=new LinearLayout(getApplicationContext());
    	hightoLowcardRanking_layout.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	hightoLowcardRanking_layout.setOrientation(LinearLayout.VERTICAL);
    	//hightoLowcardRanking_layout.setBackgroundColor(Color.BLUE);
    	cardRanking_scrollview.addView(hightoLowcardRanking_layout);
    	
    	ImageView firstRule=new ImageView(getApplicationContext());
    	firstRule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
    	firstRule.setImageResource(R.drawable.firstrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
    	firstRule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(firstRule);
    	
    	ImageView secondRule=new ImageView(getApplicationContext());
    	secondRule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
    	secondRule.setImageResource(R.drawable.secondrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
    	secondRule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(secondRule);
    	
    	ImageView thirdrule=new ImageView(getApplicationContext());
    	thirdrule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
    	thirdrule.setImageResource(R.drawable.thirdrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
    	thirdrule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(thirdrule);
    	
    	ImageView fourthrule=new ImageView(getApplicationContext());
    	fourthrule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
    	fourthrule.setImageResource(R.drawable.fourthrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
    	fourthrule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(fourthrule);
    	
      	ImageView fifthrule=new ImageView(getApplicationContext());
      	fifthrule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
      	fifthrule.setImageResource(R.drawable.fifthrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
      	fifthrule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(fifthrule);
    	
      	ImageView sixthrule=new ImageView(getApplicationContext());
      	sixthrule.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(hightoLowcardRanking_layout_height*0.33)));
      	sixthrule.setImageResource(R.drawable.sixthrule);
    	//firstRule.setScaleType(ScaleType.FIT_XY);
      	sixthrule.setBackgroundColor(Color.BLACK);
    	hightoLowcardRanking_layout.addView(sixthrule);
    	
    	
    	
     	int lowestCard_layout_width=(int)(cardRanking_layout2_width);
    	int lowestCard_layout_height=(int)(cardRanking_layout2_height*0.07);
    	LinearLayout lowestCard_layout=new LinearLayout(getApplicationContext());
    	lowestCard_layout.setLayoutParams(new LayoutParams(lowestCard_layout_width, lowestCard_layout_height));
    	lowestCard_layout.setOrientation(LinearLayout.VERTICAL);
    	cardRanking_layout2.addView(lowestCard_layout);
    	//lowestCard_layout.setBackgroundColor(Color.RED);
    	
    	TextView lowestCard=new TextView(getApplicationContext());
    	lowestCard.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	lowestCard.setText("LOWEST");
    	lowestCard.setTextSize(15);
    	lowestCard.setTypeface(null,Typeface.BOLD);
    	lowestCard.setTextColor(Color.BLACK);
    	//lowestCard.setBackgroundColor(Color.GRAY);
    	lowestCard_layout.addView(lowestCard);
    	
    	


    	
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

	private void chips_bonus_dialog_init(View dialogview,
			int screenWidthDp, int screenHeightDp) {
		// TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/ 
    	AlertDialog.Builder builder= new AlertDialog.Builder(MenuActivity.this);
    	builder.setView(dialogview);
    	final AlertDialog alert=builder.create();
    	alert.setCancelable(false);
	    
    	alert.show();
    	/*To display alert dialog box on onclick button starts here*/ 
    	/*set width and height of dialogbox  starts here*/
    	int screenWidthDpDialog=(int)(screenWidthDp*0.80);
    	int screenHeightDpDialog=(int)(screenHeightDp*0.80);

        int secondrow_firstdialogvertical_width=(int)(screenWidthDpDialog*0.30);
        int secondrow_seconddialogvertical_width=(int)(screenWidthDpDialog*0.30);
        int secondrow_thirddialogvertical_width=(int)(screenWidthDpDialog*0.40);
        int save_width_one=(int)(screenWidthDpDialog*0.85);
         int save_width_two=(int)(screenWidthDpDialog*0.15);
    	alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
    	alert.getWindow().findViewById(R.id.dialog1space).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.10)));
    	alert.getWindow().findViewById(R.id.dialog1space1).setLayoutParams(new LayoutParams(save_width_one,(int)(screenHeightDpDialog*0.10)));
    	alert.getWindow().findViewById(R.id.dialog1space2).setLayoutParams(new LayoutParams(save_width_two,(int)(screenHeightDpDialog*0.10)));
   	    
    	/*firstdialogrow starts here*/
    	alert.getWindow().findViewById(R.id.dialog1vertical1).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.30)));
    	ImageView save = (ImageView)dialogview.findViewById(R.id.save);
		save.setImageResource(R.drawable.save);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(R.id.save==v.getId()){
					alert.dismiss();
				}
			}
		});
    	alert.getWindow().findViewById(R.id.dialog1vertical1h1).setLayoutParams(new LayoutParams(secondrow_firstdialogvertical_width,(int)(screenHeightDpDialog*0.30)));
    	ImageView one = (ImageView)dialogview.findViewById(R.id.image1);
		one.setImageResource(R.drawable.dialog1);
		alert.getWindow().findViewById(R.id.dialog1vertical1h2).setLayoutParams(new LayoutParams(secondrow_seconddialogvertical_width,(int)(screenHeightDpDialog*0.30)));

		ImageView two = (ImageView)dialogview.findViewById(R.id.image2);
		two.setImageResource(R.drawable.dialog2);
		alert.getWindow().findViewById(R.id.dialog1vertical1h3).setLayoutParams(new LayoutParams(secondrow_seconddialogvertical_width,(int)(screenHeightDpDialog*0.30)));
		
		ImageView three = (ImageView)dialogview.findViewById(R.id.image3);
		three.setImageResource(R.drawable.dialog3);
		
       alert.getWindow().findViewById(R.id.dialog1vertical2).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.15)));

    	alert.getWindow().findViewById(R.id.dialog1vertical3).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.45)));
    	alert.getWindow().findViewById(R.id.dialog1vertical3h1).setLayoutParams(new LayoutParams(secondrow_firstdialogvertical_width,(int)(screenHeightDpDialog*0.30)));
    	ImageView seven = (ImageView)dialogview.findViewById(R.id.image7);
		
		try{seven.setImageResource(R.drawable.dialog7);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		alert.getWindow().findViewById(R.id.dialog1vertical3h2).setLayoutParams(new LayoutParams(secondrow_seconddialogvertical_width,(int)(screenHeightDpDialog*0.30)));
		
		ImageView eight = (ImageView)dialogview.findViewById(R.id.image8);
		eight.setImageResource(R.drawable.dialog8);
		alert.getWindow().findViewById(R.id.dialog1vertical3h4).setLayoutParams(new LayoutParams(secondrow_seconddialogvertical_width,(int)(screenHeightDpDialog*0.30)));
		ImageView nine = (ImageView)dialogview.findViewById(R.id.image9);
		nine.setImageResource(R.drawable.dialog9);
			
		
	}



		public void invite_players_dialog_init(View dialogview,
				int screenWidthDp, int screenHeightDp) {
			// TODO Auto-generated method stub
			/*To display alert dialog box on onclick button starts here*/ 
	    	AlertDialog.Builder builder= new AlertDialog.Builder(MenuActivity.this);
	    	builder.setView(dialogview);
	    	final AlertDialog alert=builder.create();
	    	alert.setCancelable(false);
		    
	    	alert.show();
	    	/*To display alert dialog box on onclick button starts here*/ 
	    	/*set width and height of dialogbox  starts here*/
	    	int screenWidthDpDialog=(int)(screenWidthDp*0.80);
	    	int screenHeightDpDialog=(int)(screenHeightDp*0.80);
            int first_width=(int) (screenWidthDpDialog*0.85);
            int second_width=(int)(screenWidthDpDialog*0.15);
            alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
	    	alert.getWindow().findViewById(R.id.invitevertical1).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.10)));
	    	alert.getWindow().findViewById(R.id.invitevertical1h1).setLayoutParams(new LayoutParams(first_width,(int)(screenHeightDpDialog*0.10)));
	    	alert.getWindow().findViewById(R.id.invitevertical1h2).setLayoutParams(new LayoutParams(second_width,(int)(screenHeightDpDialog*0.10)));
	    	ImageView save = (ImageView)dialogview.findViewById(R.id.invitevertical1h21);
			save.setImageResource(R.drawable.save);
			save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(R.id.invitevertical1h21==v.getId()){
						alert.dismiss();
					}
				}
			});
			
			int first_width1=(int) (screenWidthDpDialog*0.10);
            int second_width1=(int)(screenWidthDpDialog*0.70);
            int third_width1=(int)(screenWidthDpDialog*0.20);
            alert.getWindow().findViewById(R.id.invitevertical2).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.60)));
	    	alert.getWindow().findViewById(R.id.invitevertical2h1).setLayoutParams(new LayoutParams(first_width1,(int)(screenHeightDpDialog*0.60)));
	    	alert.getWindow().findViewById(R.id.invitevertical2h2).setLayoutParams(new LayoutParams(second_width1,(int)(screenHeightDpDialog*0.60)));
	    	ImageView s = (ImageView)dialogview.findViewById(R.id.inviteverticalh21);
			s.setImageResource(R.drawable.sa);
	    	alert.getWindow().findViewById(R.id.invitevertical2h3).setLayoutParams(new LayoutParams(third_width1,(int)(screenHeightDpDialog*0.60)));
	    	
	    	
	    	int fb=(int) (screenWidthDpDialog*0.40);
	    	int wa=(int)(screenWidthDpDialog*0.30);
	    	 alert.getWindow().findViewById(R.id.invitevertical3).setLayoutParams(new LayoutParams(screenWidthDpDialog,(int)(screenHeightDpDialog*0.30)));
		    	alert.getWindow().findViewById(R.id.invitevertical3h1).setLayoutParams(new LayoutParams(first_width1,(int)(screenHeightDpDialog*0.30)));
		    	alert.getWindow().findViewById(R.id.invitevertical3h2).setLayoutParams(new LayoutParams(fb,(int)(screenHeightDpDialog*0.30)));
		    	ImageView ss= (ImageView)dialogview.findViewById(R.id.fb);
				ss.setImageResource(R.drawable.pp);
		    	alert.getWindow().findViewById(R.id.invitevertical3h3).setLayoutParams(new LayoutParams(wa,(int)(screenHeightDpDialog*0.30)));
		    	ImageView sss = (ImageView)dialogview.findViewById(R.id.whatsapp);
				sss.setImageResource(R.drawable.qq);
		    	alert.getWindow().findViewById(R.id.invitevertical3h4).setLayoutParams(new LayoutParams(second_width1,(int)(screenHeightDpDialog*0.30)));

		}
		
		

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub
			 if(v.getId()==R.id.settingsButton)
			{
				
				
				try{
					drawerlayout.openDrawer(Gravity.END);
			     }
			     catch(Exception e)
			     {
			     	e.printStackTrace();
			     }	
				
			}
			else if(v.getId()==R.id.back)
			{
				
				
				try
				{
					drawerlayout.closeDrawer(Gravity.END);
			     }
			     catch(Exception e)
			     {
			     	e.printStackTrace();
			     }	
				
			}
	
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@SuppressLint("NewApi") @Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			Wheel_Animation_Fragment fragment = new Wheel_Animation_Fragment();
			FragmentManager manager = getFragmentManager();
			fragment.show(manager, "Myfragment");
			
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		public class usergamedetailsclass extends AsyncTask<Void, Void, Void> {
			Service_Client_Interaction client = new Service_Client_Interaction();

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					client.Show_usergame_detail(Global.getUserName());

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;

			}
		}
		
		public class tables_info extends AsyncTask<Void,Void,Void>
		{
			
			Service_Client_Interaction client = new Service_Client_Interaction();
		
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
			
		}
		public class user_Profile extends AsyncTask<Void,Void,Void>
		{
			
			Service_Client_Interaction client = new Service_Client_Interaction();
		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_profile(user_profile_pic_string,Global.getUserName());
					

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
		}

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

		@Override
		protected void onPause() 
		{
			// TODO Auto-generated method stub
			super.onPause();
		}
		
		
		
		
	}

class ListAdapter extends ArrayAdapter<String>
{
	final Context context;
	final String[] titlearray;
	 Vibrator vib1;
	
	ListAdapter(Context c,String[] titles)
	{
		super(c, R.layout.customdrawer,R.id.textView1,titles);
		this.context=c;
		this.titlearray=titles;
	}
	@SuppressLint("ViewHolder") @Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=null;
		if(position==0)
		{
			 row=inflater.inflate(R.layout.customdrawer1, parent,false);
			TextView Mytextview=(TextView) row.findViewById(R.id.textView2);
			ImageView back=(ImageView)row.findViewById(R.id.back);
			back.setImageResource(R.drawable.back);
			Mytextview.setText(titlearray[position]);
			 back.setOnClickListener(new MenuActivity() );
		}		
		else
		{
			 row=inflater.inflate(R.layout.customdrawer, parent,false);
			TextView Mytextview=(TextView) row.findViewById(R.id.textView1);
			final Switch t=(Switch)row.findViewById(R.id.switch_button);
			 t.setOnClickListener(new View.OnClickListener() {
		            private final String[] values = getContext().getResources().getStringArray(R.array.settings);
		 
		            @Override
		            public void onClick(View v) 
		            {
		            	if(titlearray[position].equals("Vibrate"))
		            	{
		            		t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									// TODO Auto-generated method stub
									if (isChecked) 
									{
						                
										Toast.makeText(getContext(), titlearray[position] + " checked", Toast.LENGTH_SHORT).show();
						                // The toggle is enabled
						            } 
									else 
									{
										vib1=MenuActivity.vib;
										vib1.cancel();
						               // return;
						                // The toggle is disabled
						            }
								}
							});
		            			
		            		
		            	}
		                //Toast.makeText(getContext(), titlearray[position] + " checked", Toast.LENGTH_SHORT).show();
		            }
		        });
			
			Mytextview.setText(titlearray[position]);
		}
		
		
		return row;
	}
	
	

}




