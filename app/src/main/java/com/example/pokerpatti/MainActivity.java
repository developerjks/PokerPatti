package com.example.teenpattinew;





import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;



import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
//import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
//import android.widget.RelativeLayout.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

class NetworkThread extends HandlerThread {

    private Handler mWorkerHandler;

    public NetworkThread(String name) {
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

 @SuppressLint("NewApi") public class MainActivity extends Activity implements OnClickListener
 {
	 //Handler variable initialization
	 	static  boolean handler_internet_connection_status=false;
	    Timer timer=null;
	    Handler myhandler_internet_connection_status=new Handler();
	    private NetworkThread mNetworkThread;
	    Runnable myupdateresults_internet_connection_status;
	//TextView txt_internet_response_message;
	//

    int screenWidthDp;
    int screenHeightDp;
    ImageView login_button;
    
    LinearLayout layout_P,layout_O,layout_K,layout_E,layout_R,layout_P2,layout_A,layout_T,layout_T2,layout_I;
    ImageView p_imageview,o_imageview,k_imageview,e_imageview,r_imageview,p2_imageview,a_imageview,t_imageview,t2_imageview,i_imageview;
    ImageView guestLogin_Imageview;
   // ImageView fbLogin_Imageview;
    ImageView wifi_status_image;
    TextView wifi_status_text;
    boolean getUsername_flag=false;
    boolean isUsernameset_flag=false;
    boolean guestLogin_flag=false;
    ImageView card1;
    ImageView patti2;
    ImageView patti3;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;
    ImageView card6;
    ImageView card7;
    ImageView card8;
    ImageView card9;
    ImageView card10;
    ImageView pattik;
    ImageView pattiq;
    ImageView card11;
    ImageView card12;
    ImageView card13;
    ImageView card14;
    ImageView card15;
    ImageView card16;
    ImageView card17;
    ImageView card18;
    ImageView card19;
    ImageView card20;
    ImageView cardAce;
    ImageView cards[];
    String cardName="";
    int cardTranslateFlag=0;
    int cardTranslateIndex=11;
    float cardDimx=0.03f;
    float cardDimy=-0.03f;
  // boolean init_InternetNotConnected_flag=false;
    //boolean threadStartedAgain_flag=false;
    
    AsyncTask<Void, Void, Void> CheckUser;
    private MediaPlayer mp,mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
             super.onCreate(savedInstanceState);

        /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/

        try{setContentView(R.layout.activity_main);}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
     
		/* to get screen width and height of real device starts  */
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidthDp=size.x;
        screenHeightDp=size.y;
        
        Global.setscreenHeightDp(screenHeightDp);
        Global.setscreenWidthDp(screenWidthDp);
        
        /* finding the window or device ID starts   */
	    Global.setWindow_id(GetWindowId());
	/* finding the window or device ID ends   */
        /* to get screen width and height of real device ends  */
	   //cards = new int[] {R.id.card1, R.id.card2};
	    cards=new ImageView[24];
	    
	    
	       mNetworkThread = new NetworkThread("NetworkThread");
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
													                        	
													                        	
													                        	
													                        	if(getUsername_flag==false)
													                        	{
													                        		wifi_status_image.setImageResource(0);
													                        		wifi_status_text.setText("Connecting...");
													                        		wifi_status_text.setTextColor(Color.WHITE);
													                        		CheckUser=new CheckUserAvailabilityClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
													                        		getUsername_flag=true;
													                        	}
													                        	if(isUsernameset_flag==true && guestLogin_flag==false)
													                        	{
													                        		On_Internet_Connection_Status_Update();
													                        		guestLogin_Imageview.setAlpha(1f);
														                        	//fbLogin_Imageview.setAlpha(1f);
														                        	rotate_anim();
														                        	guestLogin_flag=true;
														                        	
													                        	}
													                        
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
        super.onResume();

        
        LinearLayout mainlinearlayout=(LinearLayout)findViewById(R.id.first_Screen_ID);//main layout
        mainlinearlayout.setOrientation(LinearLayout.VERTICAL);
        first_screen_init(mainlinearlayout,screenWidthDp,screenHeightDp );
        
        cards[0]=card1;
        cards[1]=patti2;
        cards[2]=patti3;
        cards[3]=card2;
        cards[4]=card3;
        cards[5]=card4;
        cards[6]=card5;
        cards[7]=card6;
        cards[8]=card7;
        cards[9]=card8;
        cards[10]=card9;
        cards[11]=card10;
        cards[12]=pattik;
        cards[13]=pattiq;
        cards[14]=card11;
        cards[15]=card12;
        cards[16]=card13;
        cards[17]=card14;
        cards[18]=card15;
        cards[19]=card16;
        cards[20]=card17;
        cards[21]=card18;
        cards[22]=card19;
        cards[23]=card20;
        
       
        translate_anim();
       // rotateCards();
        //rotate_anim();
        
        
        }
    public void On_Internet_Connection_Status_Update()
    {
    	/* finding all the controls starts   */
    	

        if(handler_internet_connection_status==true)
    		{
        		
        			  wifi_status_image.setImageResource(R.drawable.wifi_connected);
                	  wifi_status_text.setText("Connected to internet");
                	  wifi_status_text.setTextColor(Color.GREEN);
	 
    		}	
    }
    
 
    public void  first_screen_init(LinearLayout mainlinearlayout,int screenWidthDp,int screenHeightDp)
    {
        //Mainlayout.setBackgroundColor(Color.RED);
		  /* to set background image bg in main grid starts  */
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.p4);
        mainlinearlayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
	      /* to set background image bg in main grid ends  */

        /*FirstRelative*/
        int first_vertical_layout_width=(int)(screenWidthDp);
        int first_vertical_layout_height=(int)(screenHeightDp*0.50);
        final RelativeLayout first_vertical_linear_layout=(RelativeLayout) mainlinearlayout.findViewById(R.id.firstRelativeLayout);
        first_vertical_linear_layout.setLayoutParams(new LayoutParams(first_vertical_layout_width, first_vertical_layout_height));

       
        //first_vertical_linear_layout.setBackgroundColor(Color.GREEN);
        
        /*Second Vertical first vertical*/
      int pokerpatti_title_layout_container_width=(int)(screenWidthDp*0.80);
      int pokerpatti_title_layout_container_height=(int)(first_vertical_layout_height*0.80);
      final LinearLayout pokerpatti_title_layout_container=(LinearLayout) first_vertical_linear_layout.findViewById(R.id.pokerpatti_title_layout_container);
      pokerpatti_title_layout_container.setLayoutParams(new RelativeLayout.LayoutParams(pokerpatti_title_layout_container_width,
    		  pokerpatti_title_layout_container_height));
      RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)pokerpatti_title_layout_container.getLayoutParams();
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
      layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
      pokerpatti_title_layout_container.setLayoutParams(layoutParams);
      
      /*Second Vertical first vertical*/
      int pokerpatti_title_layout_width=(int)(pokerpatti_title_layout_container_width);
      int pokerpatti_title_layout_height=(int)(pokerpatti_title_layout_container_height*0.60);
      final LinearLayout pokerpatti_title_layout=(LinearLayout)pokerpatti_title_layout_container.findViewById(R.id.pokerpatti_title_layout);
      pokerpatti_title_layout.setLayoutParams(new LayoutParams(pokerpatti_title_layout_width, pokerpatti_title_layout_height));
      
      int width_P=(int)(pokerpatti_title_layout_width*0.10);
      int height_P=(int)(pokerpatti_title_layout_height);
      layout_P2=(LinearLayout)pokerpatti_title_layout.findViewById(R.id.P2_layout);
      layout_P2.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageP2=(ImageView)layout_P2.findViewById(R.id.P2_imageview);
     imageP2.setImageResource(R.drawable.image_p2);
     

      layout_A=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.A_layout);
      layout_A.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageA=(ImageView)layout_A.findViewById(R.id.A_imageview);
      imageA.setImageResource(R.drawable.image_a);
      
     layout_T=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.T_layout);
      layout_T.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageT=(ImageView)layout_T.findViewById(R.id.T_imageview);
      imageT.setImageResource(R.drawable.image_t);
      
      layout_T2=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.T2_layout);
      layout_T2.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageT2=(ImageView)layout_T2.findViewById(R.id.T2_imageview);
      imageT2.setImageResource(R.drawable.image_t2);
      
       layout_I=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.I_layout);
      layout_I.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageI=(ImageView)layout_I.findViewById(R.id.I_imageview);
      imageI.setImageResource(R.drawable.image_i);
      
      layout_P=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.P_layout);
      layout_P.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageP=(ImageView)layout_P.findViewById(R.id.P_imageview);
      imageP.setImageResource(R.drawable.image_p);
      
     layout_O=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.O_layout);
      layout_O.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageO=(ImageView)layout_O.findViewById(R.id.O_imageview);
      imageO.setImageResource(R.drawable.image_o);
      
     layout_K=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.K_layout);
      layout_K.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageK=(ImageView)layout_K.findViewById(R.id.K_imageview);
      imageK.setImageResource(R.drawable.image_k);
      
     layout_E=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.E_layout);
      layout_E.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageE=(ImageView)layout_E.findViewById(R.id.E_imageview);
      imageE.setImageResource(R.drawable.image_e);
      
    layout_R=(LinearLayout) pokerpatti_title_layout.findViewById(R.id.R_layout);
      layout_R.setLayoutParams(new LayoutParams(width_P, height_P));
      
      ImageView imageR=(ImageView)layout_R.findViewById(R.id.R_imageview);
      imageR.setImageResource(R.drawable.image_r);
      
      
      int pokerpattiShadow_layout_width=(int)(pokerpatti_title_layout_container_width);
      int pokerpattiShadow_layout_height=(int)(pokerpatti_title_layout_container_height*0.40);
      final LinearLayout pokerpattiShadow_layout=(LinearLayout)pokerpatti_title_layout_container.findViewById(R.id.pokerpattiShadow_layout);
      pokerpattiShadow_layout.setLayoutParams(new LayoutParams(pokerpattiShadow_layout_width, pokerpattiShadow_layout_height));
     // pokerpattiShadow_layout.setBackgroundColor(Color.GREEN);
      
      int p_layout_width=(int)(pokerpattiShadow_layout_width*0.10);
      int p_layout_height=(int)(pokerpattiShadow_layout_height);
      LinearLayout p_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.p_layout);
      p_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
      p_imageview=(ImageView)p_layout.findViewById(R.id.p_imageview);
      p_imageview.setImageResource(R.drawable.image_p_shadow);
      p_imageview.setVisibility(View.GONE);
     

      LinearLayout o_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.o_layout);
      o_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       o_imageview=(ImageView)o_layout.findViewById(R.id.o_imageview);
      o_imageview.setImageResource(R.drawable.image_o_shadow);
      o_imageview.setVisibility(View.GONE);
      
      LinearLayout k_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.k_layout);
      k_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
      k_imageview=(ImageView)k_layout.findViewById(R.id.k_imageview);
      k_imageview.setImageResource(R.drawable.image_k_shadow);
      k_imageview.setVisibility(View.GONE);
      
      LinearLayout e_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.e_layout);
      e_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       e_imageview=(ImageView)e_layout.findViewById(R.id.e_imageview);
      e_imageview.setImageResource(R.drawable.image_e_shadow);
      e_imageview.setVisibility(View.GONE);
      
      LinearLayout r_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.r_layout);
      r_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       r_imageview=(ImageView)r_layout.findViewById(R.id.r_imageview);
      r_imageview.setImageResource(R.drawable.image_r_shadow);
      r_imageview.setVisibility(View.GONE);
      
      LinearLayout p2_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.p2_layout);
      p2_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       p2_imageview=(ImageView)p2_layout.findViewById(R.id.p2_imageview);
      p2_imageview.setImageResource(R.drawable.image_p2_shadow);
      p2_imageview.setVisibility(View.GONE);
      
      LinearLayout a_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.a_layout);
      a_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       a_imageview=(ImageView)a_layout.findViewById(R.id.a_imageview);
      a_imageview.setImageResource(R.drawable.image_a_shadow);
      a_imageview.setVisibility(View.GONE);
      
      LinearLayout t_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.t_layout);
      t_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       t_imageview=(ImageView)t_layout.findViewById(R.id.t_imageview);
      t_imageview.setImageResource(R.drawable.image_t_shadow);
      t_imageview.setVisibility(View.GONE);
      
      LinearLayout t2_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.t2_layout);
      t2_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       t2_imageview=(ImageView)t2_layout.findViewById(R.id.t2_imageview);
      t2_imageview.setImageResource(R.drawable.image_t2_shadow);
      t2_imageview.setVisibility(View.GONE);
      
      LinearLayout i_layout=(LinearLayout)pokerpattiShadow_layout.findViewById(R.id.i_layout);
      i_layout.setLayoutParams(new LayoutParams(p_layout_width, p_layout_height));
      
       i_imageview=(ImageView)i_layout.findViewById(R.id.i_imageview);
      i_imageview.setImageResource(R.drawable.image_i_shadow);
      i_imageview.setVisibility(View.GONE);

      
      
     
      /// second_vertical_first_vertical_linear_layout.setBackgroundColor(Color.YELLOW);

      
         /*SecondRelative*/
        int second_vertical_layout_width=(int)(screenWidthDp);
        int second_vertical_layout_height=(int)(screenHeightDp*0.50);
        final LinearLayout second_vertical_linear_layout=(LinearLayout) mainlinearlayout.findViewById(R.id.secondLinearLayout);
        second_vertical_linear_layout.setLayoutParams(new LayoutParams(second_vertical_layout_width, second_vertical_layout_height));
       // second_vertical_linear_layout.setOrientation(LinearLayout.HORIZONTAL);
       // second_vertical_linear_layout.setBackgroundColor(Color.RED);
        
        int secondLinearFirstLayout_width=(int)(second_vertical_layout_width);
        int secondLinearFirstLayout_height=(int)(second_vertical_layout_height*0.90);
        final RelativeLayout secondLinearFirstLayout=(RelativeLayout)second_vertical_linear_layout.findViewById(R.id.secondLinearFirstLayout);
        secondLinearFirstLayout.setLayoutParams(new LayoutParams(secondLinearFirstLayout_width, secondLinearFirstLayout_height));
       // second_vertical_linear_layout.setOrientation(LinearLayout.HORIZONTAL);
       // secondLinearFirstLayout.setBackgroundColor(Color.RED);
        
        int guestLoginLayout_width=(int)(secondLinearFirstLayout_width *0.25);
        int guestLoginLayout_height=(int)(secondLinearFirstLayout_height*0.70);
        final LinearLayout guestLoginLayout=(LinearLayout)secondLinearFirstLayout.findViewById(R.id.guestLoginLayout);
        guestLoginLayout.setLayoutParams(new RelativeLayout.LayoutParams(guestLoginLayout_width, guestLoginLayout_height));
        RelativeLayout.LayoutParams guestParam = (RelativeLayout.LayoutParams)guestLoginLayout.getLayoutParams();
        guestParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        guestParam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        guestLoginLayout.setLayoutParams(guestParam);
      //  guestLoginLayout.setBackgroundColor(Color.RED);
        
      guestLogin_Imageview=(ImageView)secondLinearFirstLayout.findViewById(R.id.guestLogin_image);
      guestLogin_Imageview.setImageResource(R.drawable.guest_login);
     // guestLogin_Imageview.setBackgroundColor(Color.YELLOW);
      guestLogin_Imageview.setOnClickListener(this);
       guestLogin_Imageview.setAlpha(0.5f);
  
       ImageView sampleCard1=(ImageView)secondLinearFirstLayout.findViewById(R.id.sampleCard1);
       sampleCard1.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
       sampleCard1.setImageResource(R.drawable.card_back2);
       sampleCard1.setScaleType(ScaleType.FIT_XY);
      // sampleCard1.setBackgroundColor(Color.YELLOW);
       sampleCard1.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams sampleparam = (RelativeLayout.LayoutParams)sampleCard1.getLayoutParams();
        sampleparam.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        sampleparam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        sampleCard1.setLayoutParams(sampleparam);
        
        ImageView sampleCard2=(ImageView)secondLinearFirstLayout.findViewById(R.id.sampleCard2);
        sampleCard2.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
        sampleCard2.setImageResource(R.drawable.card_back2);
        sampleCard2.setScaleType(ScaleType.FIT_XY);
        sampleCard2.setVisibility(View.INVISIBLE);
       // sampleCard1.setBackgroundColor(Color.YELLOW);
        //guestLogin_Imageview.setOnClickListener(this);
       //  guestLogin_Imageview.setAlpha(0.5f);
         RelativeLayout.LayoutParams sampleparam1 = (RelativeLayout.LayoutParams)sampleCard2.getLayoutParams();
         sampleparam1.addRule(RelativeLayout.RIGHT_OF, sampleCard1.getId());
         sampleparam1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
         sampleCard2.setLayoutParams(sampleparam1);
         
         card1=(ImageView)secondLinearFirstLayout.findViewById(R.id.card1);
         card1.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
         card1.setImageResource(R.drawable.patti292);
         card1.setScaleType(ScaleType.FIT_XY);
         card1.setVisibility(View.INVISIBLE);
        // sampleCard1.setBackgroundColor(Color.YELLOW);
          RelativeLayout.LayoutParams sampleparam3 = (RelativeLayout.LayoutParams)card1.getLayoutParams();
          sampleparam3.addRule(RelativeLayout.RIGHT_OF, sampleCard2.getId());
          sampleparam3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
          card1.setLayoutParams(sampleparam3);
          
          
          patti2=(ImageView)secondLinearFirstLayout.findViewById(R.id.patti2);
          patti2.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
          patti2.setImageResource(R.drawable.patti20);
          patti2.setScaleType(ScaleType.FIT_XY);
          patti2.setVisibility(View.INVISIBLE);
         // sampleCard1.setBackgroundColor(Color.YELLOW);
          patti2.setLayoutParams(sampleparam3);
           
           patti3=(ImageView)secondLinearFirstLayout.findViewById(R.id.patti3);
           patti3.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
           patti3.setImageResource(R.drawable.patti21);
           patti3.setScaleType(ScaleType.FIT_XY);
           patti3.setVisibility(View.INVISIBLE);
          // sampleCard1.setBackgroundColor(Color.YELLOW);
           patti3.setLayoutParams(sampleparam3);
          
          
          card2=(ImageView)secondLinearFirstLayout.findViewById(R.id.card2);
          card2.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
          card2.setImageResource(R.drawable.patti22);
          card2.setScaleType(ScaleType.FIT_XY);
          card2.setVisibility(View.INVISIBLE);
         // sampleCard1.setBackgroundColor(Color.YELLOW);
           card2.setLayoutParams(sampleparam3);
		
           card3=(ImageView)secondLinearFirstLayout.findViewById(R.id.card3);
           card3.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
           card3.setImageResource(R.drawable.patti13);
           card3.setScaleType(ScaleType.FIT_XY);
           card3.setVisibility(View.INVISIBLE);
          // sampleCard1.setBackgroundColor(Color.YELLOW);
            card3.setLayoutParams(sampleparam3);		
			
            card4=(ImageView)secondLinearFirstLayout.findViewById(R.id.card4);
            card4.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
            card4.setImageResource(R.drawable.patti24);
            card4.setScaleType(ScaleType.FIT_XY);
            card4.setVisibility(View.INVISIBLE);
           // sampleCard1.setBackgroundColor(Color.YELLOW);
             card4.setLayoutParams(sampleparam3);
					
             card5=(ImageView)secondLinearFirstLayout.findViewById(R.id.card5);
             card5.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
             card5.setImageResource(R.drawable.patti15);
             card5.setScaleType(ScaleType.FIT_XY);
             card5.setVisibility(View.INVISIBLE);
            // sampleCard1.setBackgroundColor(Color.YELLOW);
              card5.setLayoutParams(sampleparam3);
						
              card6=(ImageView)secondLinearFirstLayout.findViewById(R.id.card6);
              card6.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
              card6.setImageResource(R.drawable.patti26);
              card6.setScaleType(ScaleType.FIT_XY);
              card6.setVisibility(View.INVISIBLE);
             // sampleCard1.setBackgroundColor(Color.YELLOW);
               card6.setLayoutParams(sampleparam3);
							
							
               card7=(ImageView)secondLinearFirstLayout.findViewById(R.id.card7);
               card7.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
               card7.setImageResource(R.drawable.patti17);
               card7.setScaleType(ScaleType.FIT_XY);
               card7.setVisibility(View.INVISIBLE);
              // sampleCard1.setBackgroundColor(Color.YELLOW);
                card7.setLayoutParams(sampleparam3);
								
                card8=(ImageView)secondLinearFirstLayout.findViewById(R.id.card8);
                card8.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                card8.setImageResource(R.drawable.patti28);
                card8.setScaleType(ScaleType.FIT_XY);
                card8.setVisibility(View.INVISIBLE);
               // sampleCard1.setBackgroundColor(Color.YELLOW);
                 card8.setLayoutParams(sampleparam3);
									
                 card9=(ImageView)secondLinearFirstLayout.findViewById(R.id.card9);
                 card9.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                 card9.setImageResource(R.drawable.patti19);
                 card9.setScaleType(ScaleType.FIT_XY);
                 card9.setVisibility(View.INVISIBLE);
                // sampleCard1.setBackgroundColor(Color.YELLOW);
                  card9.setLayoutParams(sampleparam3);
										
                  card10=(ImageView)secondLinearFirstLayout.findViewById(R.id.card10);
                  card10.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                  card10.setImageResource(R.drawable.patti291);
                  card10.setScaleType(ScaleType.FIT_XY);
                  card10.setVisibility(View.INVISIBLE);
                 // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card10.setLayoutParams(sampleparam3);
                   
                   pattik=(ImageView)secondLinearFirstLayout.findViewById(R.id.pattik);
                   pattik.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   pattik.setImageResource(R.drawable.patti192);
                   pattik.setScaleType(ScaleType.FIT_XY);
                   pattik.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   RelativeLayout.LayoutParams sampleparam5 = (RelativeLayout.LayoutParams)pattik.getLayoutParams();
                   sampleparam5.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
                   sampleparam5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                   pattik.setLayoutParams(sampleparam5);
                   
                   pattiq=(ImageView)secondLinearFirstLayout.findViewById(R.id.pattiq);
                   pattiq.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   pattiq.setImageResource(R.drawable.patti191);
                   pattiq.setScaleType(ScaleType.FIT_XY);
                   pattiq.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   pattiq.setLayoutParams(sampleparam5);
                   
		
                   card11=(ImageView)secondLinearFirstLayout.findViewById(R.id.card11);
                   card11.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card11.setImageResource(R.drawable.patti29);
                   card11.setScaleType(ScaleType.FIT_XY);
                   card11.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   RelativeLayout.LayoutParams sampleparam4 = (RelativeLayout.LayoutParams)card11.getLayoutParams();
                   sampleparam4.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
                   sampleparam4.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                   card11.setLayoutParams(sampleparam4);
       
                   card12=(ImageView)secondLinearFirstLayout.findViewById(R.id.card12);
                   card12.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card12.setImageResource(R.drawable.patti18);
                   card12.setScaleType(ScaleType.FIT_XY);
                   card12.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card12.setLayoutParams(sampleparam4);
                   
                   card13=(ImageView)secondLinearFirstLayout.findViewById(R.id.card13);
                   card13.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card13.setImageResource(R.drawable.patti17);
                   card13.setScaleType(ScaleType.FIT_XY);
                   card13.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card13.setLayoutParams(sampleparam4);
                   
                   card14=(ImageView)secondLinearFirstLayout.findViewById(R.id.card14);
                   card14.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card14.setImageResource(R.drawable.patti16);
                   card14.setScaleType(ScaleType.FIT_XY);
                   card14.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card14.setLayoutParams(sampleparam4);
                   
                   card15=(ImageView)secondLinearFirstLayout.findViewById(R.id.card15);
                   card15.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card15.setImageResource(R.drawable.patti15);
                   card15.setScaleType(ScaleType.FIT_XY);
                   card15.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card15.setLayoutParams(sampleparam4);
                   
                   card16=(ImageView)secondLinearFirstLayout.findViewById(R.id.card16);
                   card16.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card16.setImageResource(R.drawable.patti14);
                   card16.setScaleType(ScaleType.FIT_XY);
                   card16.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card16.setLayoutParams(sampleparam4);
                   
                   card17=(ImageView)secondLinearFirstLayout.findViewById(R.id.card17);
                   card17.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card17.setImageResource(R.drawable.patti13);
                   card17.setScaleType(ScaleType.FIT_XY);
                   card17.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card17.setLayoutParams(sampleparam4);
                   
                   card18=(ImageView)secondLinearFirstLayout.findViewById(R.id.card18);
                   card18.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card18.setImageResource(R.drawable.patti12);
                   card18.setScaleType(ScaleType.FIT_XY);
                   card18.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card18.setLayoutParams(sampleparam4);
                   
                   card19=(ImageView)secondLinearFirstLayout.findViewById(R.id.card19);
                   card19.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card19.setImageResource(R.drawable.patti11);
                   card19.setScaleType(ScaleType.FIT_XY);
                   card19.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card19.setLayoutParams(sampleparam4);
                   
                   card20=(ImageView)secondLinearFirstLayout.findViewById(R.id.card20);
                   card20.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   card20.setImageResource(R.drawable.patti10);
                   card20.setScaleType(ScaleType.FIT_XY);
                   card20.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   card20.setLayoutParams(sampleparam4);
                   
                   cardAce=(ImageView)secondLinearFirstLayout.findViewById(R.id.cardAce);
                   cardAce.setLayoutParams(new RelativeLayout.LayoutParams((int)(secondLinearFirstLayout_width*0.05),(int)(secondLinearFirstLayout_height*0.28))); 
                   cardAce.setImageResource(R.drawable.patti393);
                   cardAce.setScaleType(ScaleType.FIT_XY);
                   cardAce.setVisibility(View.INVISIBLE);
                  // sampleCard1.setBackgroundColor(Color.YELLOW);
                   cardAce.setLayoutParams(sampleparam4);
                   
        int secondLinearSecondLayout_width=(int)(second_vertical_layout_width);
        int secondLinearSecondLayout_height=(int)(second_vertical_layout_height*0.10);
        final RelativeLayout secondLinearSecondLayout=(RelativeLayout)second_vertical_linear_layout.findViewById(R.id.secondLinearSecondLayout);
        secondLinearSecondLayout.setLayoutParams(new LayoutParams(secondLinearSecondLayout_width, secondLinearSecondLayout_height));
       // second_vertical_linear_layout.setOrientation(LinearLayout.HORIZONTAL);
        //secondLinearSecondLayout.setBackgroundColor(Color.GREEN);
        
       int wifiLayout_width=(int)(secondLinearSecondLayout_width*0.40);
      int wifiLayout_height=(int)(secondLinearSecondLayout_height);
      final LinearLayout wifiLayout=(LinearLayout)secondLinearSecondLayout.findViewById(R.id.wifi_layout);
      wifiLayout.setLayoutParams(new RelativeLayout.LayoutParams(wifiLayout_width, wifiLayout_height));
      
      RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)wifiLayout.getLayoutParams();
      layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
      wifiLayout.setLayoutParams(layoutParams1);
      
      int wifiImage_layout_width=(int)(wifiLayout_width*0.10);
      int wifiImage_layout_height=(int)(wifiLayout_height*0.90);
      final LinearLayout wifiImage_layout=(LinearLayout)wifiLayout.findViewById(R.id.wifiImage_layout);
      wifiImage_layout.setLayoutParams(new LayoutParams(wifiImage_layout_width, wifiImage_layout_height));
      //wifiImage_layout.setBackgroundColor(Color.BLUE);
      
      wifi_status_image=(ImageView)wifiImage_layout.findViewById(R.id.wifi_image);
      
      int wifiText_layout_width=(int)(wifiLayout_width*0.90);
      int wifiText_layout_height=(int)(wifiLayout_height);
      final LinearLayout wifiText_layout=(LinearLayout)wifiLayout.findViewById(R.id.wifiText_layout);
      wifiText_layout.setLayoutParams(new LayoutParams(wifiText_layout_width, wifiText_layout_height));
     // wifiText_layout.setBackgroundColor(Color.WHITE);
      
      wifi_status_text=(TextView)wifiText_layout.findViewById(R.id.wifi_text);
      wifi_status_text.setText("Connecting...");
      wifi_status_text.setTextColor(Color.WHITE);

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
   				wifi_status_image.setImageResource(R.drawable.wifi_disconnected);
   				wifi_status_text.setText("Check Your Connection"); 
   				wifi_status_text.setTextColor(Color.RED);
   			 
   				if(guestLogin_flag==true)
   				{
   					guestLogin_Imageview.setAlpha(0.5f);
   					//fbLogin_Imageview.setAlpha(0.5f);
   					guestLogin_Imageview.clearAnimation();		
   					//fbLogin_Imageview.clearAnimation();
   					guestLogin_flag=false;
   				}
   			 
   			
   			}
   		}
   		catch(Exception e)
   		{
   			e.printStackTrace();
   		}
   		
   		
   		return internet_connection_status;
   		
   		
   	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(guestLogin_flag==true)
		{
			if(v.getId()==R.id.guestLogin_image)
	        {
				
				  if (mp1 != null) {
				      mp1.release();
				   }
				  mp1 = MediaPlayer.create(getApplicationContext(), R.raw.buttonclicksound);
				   mp1.start();
				   
				   mp1.setOnCompletionListener(new OnCompletionListener() {
													@Override
													public void onCompletion(MediaPlayer mp1) {	mp1.release();	}
				   								});
				
				Global.setNetConnectionFlag(true);
	            Intent intent=new Intent(MainActivity.this,LoginSecond.class);
	            startActivity(intent);
	        }
		}
		   
//		  else if(R.id.exit==v.getId())
//          {
//       	   
//			  Exit_Dialog myalert=new Exit_Dialog();
//		    	 myalert.show(getFragmentManager(),"MY ALERT");
//
//          }
       
	        
	}
	
	public void translate_anim()
	{
		

		TranslateAnimation translate1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0.5f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0f);
		translate1.setDuration(2000);
		translate1.setFillAfter(true);
		translate1.setInterpolator(new AccelerateDecelerateInterpolator());
		layout_A.startAnimation(translate1);
		

		TranslateAnimation translate2 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, -0.5f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0f);
		translate2.setDuration(2000);
		translate2.setFillAfter(true);
		translate2.setInterpolator(new AnticipateInterpolator());
		layout_O.startAnimation(translate2);
		
		layout_T2.startAnimation(translate1);

		layout_E.startAnimation(translate2);
		
		TranslateAnimation translate5 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0.5f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0f);
		translate5.setDuration(2000);
		translate5.setFillAfter(true);
		translate5.setInterpolator(new BounceInterpolator());
	
		layout_P2.startAnimation(translate5);
		
		TranslateAnimation translate6 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, -0.5f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_PARENT, 0f);
		translate6.setDuration(2000);
		translate6.setFillAfter(true);
		translate6.setInterpolator(new OvershootInterpolator());
		translate6.setAnimationListener(new AnimationListener() {
			
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
				p_imageview.setVisibility(View.VISIBLE);
				o_imageview.setVisibility(View.VISIBLE);
				k_imageview.setVisibility(View.VISIBLE);
				e_imageview.setVisibility(View.VISIBLE);
				r_imageview.setVisibility(View.VISIBLE);
				p2_imageview.setVisibility(View.VISIBLE);
				a_imageview.setVisibility(View.VISIBLE);
				t_imageview.setVisibility(View.VISIBLE);
				t2_imageview.setVisibility(View.VISIBLE);
				i_imageview.setVisibility(View.VISIBLE);

				for(int i=0;i<12;i++)
				{
					cards[i].setVisibility(View.VISIBLE);
				}
				
				cardsAnim1();
				
			}
		});
		layout_P.startAnimation(translate6);
		
		layout_T.startAnimation(translate5);
		layout_K.startAnimation(translate6);
		layout_I.startAnimation(translate5);
		layout_R.startAnimation(translate6);
		
		
		
	}
   
	public void cardsAnim1()
	{
		
		if(cardTranslateFlag<11)
		{
			TranslateAnimation translate1 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, cardDimx,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, cardDimy);
			translate1.setDuration(100);
			translate1.setFillAfter(true);
			translate1.setInterpolator(new AccelerateDecelerateInterpolator());
			translate1.setAnimationListener(new AnimationListener() {
				
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
					cardTranslateIndex--;
					cardTranslateFlag++;
					cardDimx=cardDimx+0.03f;
					cardDimy=cardDimy-0.03f;
					cardsAnim1();
					
				}
			});
			cards[cardTranslateIndex].startAnimation(translate1);
		}
		else if(cardTranslateFlag==11)
		{
			
			cardTranslateIndex=23;
			cardDimx=0.03f;
			cardDimy=0.03f;
			for(int i=12;i<22;i++)
			{
				cards[i].setVisibility(View.VISIBLE);
			}
			cardAce.setVisibility(View.VISIBLE);
			cardsAnim2();
		}

	
	}
	
	public void cardsAnim2()
	{
		
		if(cardTranslateFlag<23)
		{
			TranslateAnimation translate1 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, cardDimx,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, cardDimy);
			translate1.setDuration(100);
			translate1.setFillAfter(true);
			translate1.setInterpolator(new AccelerateDecelerateInterpolator());
			translate1.setAnimationListener(new AnimationListener() {
				
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
					cardTranslateIndex--;
					cardTranslateFlag++;
					cardDimx=cardDimx+0.03f;
					cardDimy=cardDimy+0.03f;
					cardsAnim2();
				}
			});
			cards[cardTranslateIndex].startAnimation(translate1);
		}

	
	}
	
	public void rotate_anim()
	{
		RotateAnimation rotate_guestButton=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
		rotate_guestButton.setFillEnabled(true);
		rotate_guestButton.setFillAfter(true);
		rotate_guestButton.setDuration(15000);
		rotate_guestButton.setRepeatCount(Animation.INFINITE);
		//setCancelab le(false);
		rotate_guestButton.setInterpolator(new BounceInterpolator());
	
		rotate_guestButton.setAnimationListener(new AnimationListener(
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
		guestLogin_Imageview.startAnimation(rotate_guestButton);
		
//		RotateAnimation rotate_fbButton=new RotateAnimation(360,0,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
//		rotate_fbButton.setFillEnabled(true);
//		rotate_fbButton.setFillAfter(true);
//		rotate_fbButton.setDuration(15000);
//		rotate_fbButton.setRepeatCount(Animation.INFINITE);
//		//setCancelable(false);
//		rotate_fbButton.setInterpolator(new BounceInterpolator());
//	
//		rotate_fbButton.setAnimationListener(new AnimationListener(
//				) {
//			
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//			
//			}
//		});
//		
//		//fbLogin_Imageview.startAnimation(rotate_fbButton);
	}
	
	@Override
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
				catch(Exception e)
				{
					e.printStackTrace();
				}
	             
	        }
      
	       
	}
	


	@Override
	protected void onPause() {
		super.onPause();
//        boolean mWorkerThread_status=mNetworkThread.isAlive();
//			if(mWorkerThread_status==true)
//	        {
//				try{
//				    myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
//	        	    mNetworkThread.quit();
//	        	   
//				}catch(Exception e){
//					
//				}
//	        }
			
		
	}


		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
//	        boolean mWorkerThread_status=mNetworkThread.isAlive();
//	        
//	        
//				if(mWorkerThread_status==true)
//		        {
//					try{
//						   myhandler_internet_connection_status.removeCallbacks(myupdateresults_internet_connection_status);
//			        	   mNetworkThread.quit();
//						}catch(Exception e){
//							
//						}
//		        }
				
		}
		
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onStop();
			
		//	CheckUser.cancel(true);
			
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
		
	 public String GetWindowId()
	    {
		 String deviceId="";
		 String tmDevice="", tmSerial="", androidId="";
		 
			//Permission for Android Marshmallow
			int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
		 try{
			 
			 if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
		            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
		        }
			 else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			 {
				  if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
					        != PackageManager.PERMISSION_GRANTED) 
							        {
							        	   
							        	    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
							        	            1);

							           }
				  if(checkSelfPermission(Manifest.permission.CAMERA)
					        != PackageManager.PERMISSION_GRANTED) 
							        {
							        	   
							        	    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
							        	            1);

							           }
			 }
		
			 
	    	final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
	    	
		    
		    tmDevice = "" + tm.getDeviceId();
		    tmSerial = "" + tm.getSimSerialNumber();
		    androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		   deviceId = deviceUuid.toString();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		    return deviceId;
	    }


	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
		 switch (requestCode) {
	        case 1:
	            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
	                //TODO
	            }
	            break;

	        default:
	            break;
	    }
		
	}
	
	public class CheckUserAvailabilityClass extends AsyncTask<Void, Void, Void>
	{
       private String user_availability_status;
		Service_Client_Interaction client=new Service_Client_Interaction();
		  @Override
		  protected Void doInBackground(Void... arg0)
		  {
		        
		    	  user_availability_status= client.signin_json(Global.getWindow_id());
		  	  
		    	  //start
		    	  
                           if(user_availability_status.equals("not exist"))
                           {
                        	   user_availability_status="Enter Name";
                        	   Global.setUserName(user_availability_status);
                        	   isUsernameset_flag=true;
                           }
                           else 
                           {
                
                        	   Global.setUserName(user_availability_status);
                        	   isUsernameset_flag=true;
                                        
                           }

			return null;
		     }
		@Override
		protected void onPostExecute(Void result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			
		}
		  
	}
	
	

	 


}



