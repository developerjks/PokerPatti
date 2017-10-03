package com.example.teenpattinew;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.teenpattinew.MenuActivity.tables_info;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;

class Network_User_PublicThread extends HandlerThread 
{

	
    private Handler mWorkerHandler;

    public Network_User_PublicThread(String name) {
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


@SuppressLint("NewApi")
public class PublicTable extends Activity implements OnClickListener 
{

	//Handler variable initialization
	// static  boolean handler_internet_connection_status=false;
	    Timer timer=null;
	    Handler myhandler_all_user_status=new Handler();
	    private Network_User_PublicThread mUserThread;
	    Runnable myupdateresults_all_user_status;
	    int flag_for_handler=0;
	//TextView txt_internet_response_message;

	
	// For translate animation
	//Button show, reverse;
	int i = 0, flag = 0,i_show=0,flag_show=0;
	ImageView player1[];
	ImageView player2[];
	ImageView player3[];
	ImageView player4[];
	ImageView player5[];

	ImageView player1_real_cards[];
	ImageView player2_real_cards[];
	ImageView player3_real_cards[];
	ImageView player4_real_cards[];
	ImageView player5_real_cards[];
	ImageView see_player_real_cards[];

	ImageView deck_of_cards[];
	ImageView deck_of_cards_real[];

	RelativeLayout oneH2;
	ImageView deck;
	// ImageView card_front;
	ImageView see[];
	ImageView card1;
	int flw2;
	int flh2;

	boolean isBackVisible = false;
	Button flip;
	int see_flag = 0, i_see = 0;
	int alloted_cards[];
	int alloted_cards_index_player1 = 0;
	int alloted_cards_index_player2 = 3;
	int alloted_cards_index_player3 = 6;
	int alloted_cards_index_player4 = 9;
	int alloted_cards_index_player5 = 12;

	int drawer_close_status = 0;

	int userChanceTimerflag=0;
	
	int screenWidthDp;
	int screenHeightDp;
	int w,h;
	static int second_h11_layout_width;
	static int second_h11_layout_height;
	float first = -0.41f;
	float third = 0.36f;
	float second = -0.03f;
	float first_show = -0.41f;
	float first_show_y = 0.6f;
	float third_show = 0.36f;
	float second_show = -0.03f;
	float second_show_y = 0.67f;
	float second_see= -0.03f;


	DrawerLayout drawerlayout;
	ListView tablesList;
	String table_name_array[];
	int flag_drawer = 0, index = 1;

	LinearLayout drawer_linear_layout;
	Button create_new_table_button;
	EditText new_table_name_edittext;
	Button createTable_button;
	int drawer_width;
	String table_name;

	// for each player dialog
	static ImageView mImage1;
	static ImageView mImage2;
	static ImageView mImage3;
	static ImageView mImage4;
	static ImageView player_3_image;
	//Button sideshow_button;
	ImageView pack;
	ImageView chaal;
	ImageView show_button;
	ImageView see_cards;
	int clear_cards_flag=0;
	int show_card_restrict_flag=0;
	TextView countdown_timer;
	
	// ViewGroup overlaying;
	
	//background thread
	AsyncTask<Void, Void, Void> task_list_go_click;
	 AsyncTask<Void, Void, Void> all_user_authentication_details;
	 AsyncTask<Void, Void, Void> all_user_pack_details;
	 AsyncTask<Void, Void, Void>  delete_user;
	 AsyncTask<Void, Void, Void>  show_click_button;
	 AsyncTask<Void, Void, Void>  winner_decider;
	 AsyncTask<Void, Void, Void> delete_two;
	 AsyncTask<Void, Void, Void> game_reset;
	 AsyncTask<Void, Void, Void>  see_click_button;
	 AsyncTask<Void, Void, Void>  sideshow_click_button;
	 AsyncTask<Void, Void, Void>  sideshow_reset_values;
	 AsyncTask<Void, Void, Void>  sideshow_pack;
	 AsyncTask<Void, Void, Void>  userChaal;
	 AsyncTask<Void, Void, Void>  winnerAmount;

	 int varaible_for_authentication=0;
	 int variable_for_pack=0;
	 int variable_for_pack_by_button_click=0;
	 int init_realcards_flag=0;
	 ViewGroup overlaying;
	 
	 //Last two player name Array
	 String last_two_player[];
	 int two_player_left_flag=0;
	 
	 //Sideshow 
	 String username_that_requested_sideshow;
	 String username_for_sideshow;
 
	 //for_showing_logic
	 int flag_for_two_player_show=0;
	 int flag_for_three_player_show=0;
	 int flag_for_four_player_show=0;
	 int flag_for_five_player_show=0;
	 
	 //sideshowflag
	 int sideshow_status=0;
	 int show_status=0;
	 int sideshow_decision_dialog_request_flag=0;
	 String sideshow_pack_user;
	 int pack_service_flag=0;
	 int show_sideshow_packed_username_flag=0;
	 int restart_game_flag=0;
	 
	 //userCoinsImageview
//	 ImageView player1coin;
//	 ImageView player2coin;
//	 ImageView player3coin;
//	 ImageView player4coin;
//	 ImageView player5coin;
	 LinearLayout player1Coin_layout;
	 LinearLayout player2Coin_layout;
	 LinearLayout player3Coin_layout;
	 LinearLayout player4Coin_layout;
	 LinearLayout player5Coin_layout;
	 
	 //userchance Flag
	 int userChance_flag=0;
	 Vibrator vib ;
	 boolean cardDistributedFlag=false;
	 boolean chanceAnimationFlag=false;
	 boolean userChanceDataFlag=false;
	 boolean userListUpdateFlag=false;
	 boolean userChaalDataFlag=false;
	 boolean userCoinAnimationRestrictFlag=false;
	 int potValue=0;
	 TextView potTextView;
	 ImageView potCoin;
	 boolean dynamicRestrictFlag=true;
	static int k = 0;
	static int kj = 0;
	static int kjl = 0;
	static int mjkl = 0;
	static int j = 0;
	String winnerPlayer;
	boolean seeButtonClickFlag=true;
	boolean packChaalShowClickFlag=true;
	
	//WinningCoins
	TextView winningTextView;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* to make display full screen ends */

		setContentView(R.layout.activity_public_table);

		//Global.setVacantTableStatus(0);
		table_name_array = Global.getTableDetails();
		Global.setFlag_getTableDetails();

	    vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		// drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		tablesList = new ListView(getApplicationContext());

//		ListAdapter1 adapter = new ListAdapter1(this, table_name_array);
//		tablesList.setAdapter(adapter);

		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		screenWidthDp = size.x;
		screenHeightDp = size.y;
		w=size.x;
		h=size.y;

		// For Translate Animation
		//show = (Button) findViewById(R.id.show);
		// reverse=(Button)findViewById(R.id.reverse);

		player1 = new ImageView[3];
		player2 = new ImageView[3];
		player3 = new ImageView[3];
		player4 = new ImageView[3];
		player5 = new ImageView[3];

		player1_real_cards = new ImageView[3];
		player2_real_cards = new ImageView[3];
		player3_real_cards = new ImageView[3];
		player4_real_cards = new ImageView[3];
		player5_real_cards = new ImageView[3];

		deck_of_cards = new ImageView[52];
		deck_of_cards_real = new ImageView[52];
		alloted_cards = new int[15];

		see = new ImageView[3];
		
		
		

		for (int i = 0; i < 52; i++) {
			deck_of_cards[i] = new ImageView(getApplicationContext());
			deck_of_cards[i].setImageResource(R.drawable.card_back2);

			deck_of_cards_real[i] = new ImageView(getApplicationContext());
			deck_of_cards_real[i].setImageResource(R.drawable.patti10 + i);

		}
		ArrayList<Integer> deck_of_cards_list = new ArrayList<Integer>();
		for (int i = 0; i < 52; i++) {
			deck_of_cards_list.add(new Integer(i));
		}
		Collections.shuffle(deck_of_cards_list);
		for (int i = 0; i < 15; i++) {

			alloted_cards[i] = deck_of_cards_list.get(i);

		}
		
		//CountDown Timer for users
		countdown_timer=(TextView)findViewById(R.id.countdown_timer);

		drawer_linear_layout = (LinearLayout) findViewById(R.id.drawer_linear_layout);
		new_table_name_edittext = new EditText(getApplicationContext());
		createTable_button = new Button(getApplicationContext());
		drawer_width = (int) (screenWidthDp * 0.40);
		DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) drawer_linear_layout.getLayoutParams();
		params.width = drawer_width;
		drawer_linear_layout.setLayoutParams(params);
		

		mUserThread= new Network_User_PublicThread("Network_User_Thread");
		myupdateresults_all_user_status= new Runnable() {
            @Override
            public void run()
            {
                        myhandler_all_user_status.post(new Runnable()
                        {
									@Override
									public void run()
									{ 
										                   		
										     	all_user_authentication_details= new all_users_details_default().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
										                        
										         //Toast.makeText(getApplicationContext(),"Toast running", Toast.LENGTH_SHORT).show();
										         int card_distribution_flag[]=Global.getuser_flag_cards_Details();
										         int show_cards_flag[]=Global.getuser_show_flag_Details();
										         int user_flag_card[]=Global.getuser_flag_cards_Details();
										         int see_sideshow_flag[]=Global.getuser_see_sideshow_flag_Details();
									             String user_name[]=Global.getall_Username_Details();
									             int userChance[]=Global.getuser_chance();
									             int userChaal[]=Global.getuser_chaalFlag();
									             int userPack[]=Global.getuser_packDetails();
									             int packFlag[]=Global.getuser_packFlag();
									        	 int show_status=0;
									             
									         
									             set_timer(card_distribution_flag);
									             
//									             if(Global.getuser_countdown_timer()==3 && card_distribution_flag.length!=1)
//									             {
//									            	 dynamicRestrictFlag=false;
//									             }
										         two_player_logic(card_distribution_flag,show_cards_flag,user_name,userChance);										        
										         three_player_logic(card_distribution_flag,show_cards_flag,user_name, userChance);										         
										         four_player_logic(card_distribution_flag,show_cards_flag,user_name, userChance);										         
										         five_player_logic(card_distribution_flag,show_cards_flag,user_name, userChance);
											       
										         fivePlayerChance(userPack, user_name, userChance,packFlag);
										         fourPlayerChance(userPack, user_name, userChance,packFlag);
										         threePlayerChance(userPack, user_name, userChance,packFlag);
										         twoPlayerChance(userPack, user_name, userChance,packFlag);
										         
										         if(userChanceDataFlag==true && userListUpdateFlag==true && cardDistributedFlag==true )
										         {
										        	 
										        	  	 fivePlayerAnimation(user_name,userPack,card_distribution_flag);
												         fourPlayerAnimation(user_name,userPack,card_distribution_flag);
												         threePlayerAnimation(user_name,userPack,card_distribution_flag);
												         twoPlayerAnimation(user_name,userPack);
												         
										         }
										         
										         
										         if(userListUpdateFlag==true && Global.getuser_countdown_timer()==15 && userCoinAnimationRestrictFlag==true)
										         {
										        	 	 userCoinAnimationRestrictFlag=false;
										        	  	 fivePlayerCoinAnimation(user_name,userPack,userChaal);
												         fourPlayerCoinAnimation(user_name,userPack,userChaal);
												         threePlayerCoinAnimation(user_name,userPack,userChaal);
												         twoPlayerCoinAnimation(user_name,userPack,userChaal);
												         
										         }										        
										         
										         display_show_sideshow_button_func(card_distribution_flag,see_sideshow_flag);
										         //display_sideshow_decision_dialog_func(see_sideshow_flag, user_name);
										         show_sideshow_packed_username_func(card_distribution_flag,user_name,see_sideshow_flag);
										         winner_decider_func(card_distribution_flag,user_flag_card,user_name);									                        
										         int show2=check_show_pack_status(show_status,card_distribution_flag);
										        // pack_func(show2,user_flag_card,user_name);
											     show_func(show2,show_cards_flag);
											                        		    		                  
										
										
									}

										                        											
	                        });    
                 }
        };
    
        try{
			   int delay=500;//delay for 1 sec
		        int period=500; //repeat every 4 sec
		        timer=new Timer();
		        timer.scheduleAtFixedRate(new TimerTask() {
					        				    @Override
					        				    public void run()
					        				    {
					        				    	 mUserThread.postTask(myupdateresults_all_user_status);
					        				    }
		                                  },delay,period);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

		
	        

	}

	@Override
	protected void onResume() {
		super.onResume();

		if(restart_game_flag==0)
		{
			
			RelativeLayout mainlinearlayout = (RelativeLayout) findViewById(R.id.publicTableMainLayout);// main
																								// layout
			mainlinearlayout.setBackgroundResource(R.drawable.p4);

			try{table1_screen_init(mainlinearlayout, screenWidthDp, screenHeightDp);}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	public void twoPlayerAnimation(String userName[],int []card_distribution_flag)
	{
		userChanceDataFlag=false;
		int []userChance=Global.getuser_chance();

		int no_of_players=0;
		//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2], Toast.LENGTH_SHORT).show();
		if(card_distribution_flag.length>=2 && chanceAnimationFlag==true)
		{
			 for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==2)
			    	{
			    		no_of_players++;
			    	}
			    	
			    }
			 
			 if(no_of_players==2)
			 {
				 if(Global.getUserName().equals(userName[0]) && userChance[0]==1)
					{			
						mImage4.clearAnimation();
						Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
				        player_3_image.startAnimation(startAnimation);	        
						vib.vibrate(400);
						packChaalShowClickFlag=true;
						pack.setAlpha(255);
						chaal.setAlpha(255);
						show_button.setAlpha(255);
						if(seeButtonClickFlag==true)
						{
							see_cards.setAlpha(255);
						}
		    			pack.setVisibility(View.VISIBLE);
		    			chaal.setVisibility(View.VISIBLE);
		    			see_cards.setVisibility(View.VISIBLE);
		    			show_button.setVisibility(View.VISIBLE);
		    			
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[0]==1)
					{
						
						player_3_image.clearAnimation();
						Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
						mImage4.startAnimation(startAnimation);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[1]==2)
					{
						mImage4.clearAnimation();
						Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
						player_3_image.startAnimation(startAnimation);
						vib.vibrate(400);
						packChaalShowClickFlag=true;
						pack.setAlpha(255);
						chaal.setAlpha(255);
						show_button.setAlpha(255);
						if(seeButtonClickFlag==true)
						{
							see_cards.setAlpha(255);
						}
		    			pack.setVisibility(View.VISIBLE);
		    			chaal.setVisibility(View.VISIBLE);
		    			see_cards.setVisibility(View.VISIBLE);
		    			show_button.setVisibility(View.VISIBLE);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[1]==2)
					{
						player_3_image.clearAnimation();
						Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
						mImage4.startAnimation(startAnimation);
					}
					
					chanceAnimationFlag=false;
			 }
			
		}
	}
	public void threePlayerAnimation(String userName[],int []card_distribution_flag,int []playersLeft)
	{
		userChanceDataFlag=false;
		int []userChance=Global.getuser_chance();
		int no_of_players=0;
		int no_of_players_active=0;
		//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2], Toast.LENGTH_SHORT).show();
		if(card_distribution_flag.length>=3 && chanceAnimationFlag==true)
		{
			 for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==3)
			    	{
			    		no_of_players++;
			    	}
			    	if(playersLeft[i]==3)
			    	{
			    		no_of_players_active++;
			    	}
			    	
			    }
			   
			if(no_of_players==3)
			{
				if(Global.getUserName().equals(userName[0]) && userChance[0]==1)
				{			
					mImage4.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
	    			
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[0]==1)
				{
					
					player_3_image.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
					
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[0]==1)
				{
					
					player_3_image.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[1]==2)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					player_3_image.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[1]==2)
				{
					//mImage2.startAnimation(chanceRotate);
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[2]==3)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					mImage2.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[2]==3)
				{
					//mImage2.startAnimation(chanceRotate);
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[2]==3)
				{
					//mImage2.startAnimation(chanceRotate);
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				chanceAnimationFlag=false;
			}
			
			}
	

		
	}
	public void fourPlayerAnimation(String userName[],int []card_distribution_flag,int []playersLeft)
	{
		userChanceDataFlag=false;
		int []userChance=Global.getuser_chance();
		int no_of_players=0;
		int no_of_players_active=0;
		//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2]+""+userChance[3], Toast.LENGTH_SHORT).show();
		if(card_distribution_flag.length>=4 && chanceAnimationFlag==true)
		{
			for(int i=0;i<card_distribution_flag.length;i++){
		    	if(card_distribution_flag[i]==4)
		    	{
		    		no_of_players++;
		    	}
		    	if(playersLeft[i]==4)
		    	{
		    		no_of_players_active++;
		    	}
		    }
			if(no_of_players==4)
			{
				if(Global.getUserName().equals(userName[0]) && userChance[0]==1)
				{			
				
					mImage2.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[1]==2)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[1]==2)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[2]==3)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage4.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[2]==3)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[2]==3)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[2]==3)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[3]==4)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage4.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage4.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				chanceAnimationFlag=false;
			}

		}
		

		
	}	
	public void fivePlayerAnimation(String userName[],int []card_distribution_flag,int []playersLeft)
	{
		userChanceDataFlag=false;
		int []userChance=Global.getuser_chance();
		int no_of_players=0;
		int no_of_players_active=0;
		//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2]+""+userChance[3]+""+userChance[4], Toast.LENGTH_SHORT).show();
		if(card_distribution_flag.length>=5 && chanceAnimationFlag==true)
		{
			for(int i=0;i<card_distribution_flag.length;i++){
		    	if(card_distribution_flag[i]==5)
		    	{
		    		no_of_players++;
		    	}
		    	if(playersLeft[i]==4)
		    	{
		    		no_of_players_active++;
		    	}
		    }
			if(no_of_players==5)
			{
				if(Global.getUserName().equals(userName[0]) && userChance[0]==1)
				{			
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[4]) && userChance[0]==1)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[1]==2)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[1]==2)
				{
					//mImage3.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage3.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[4]) && userChance[1]==2)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[2]==3)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[2]==3)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[2]==3)
				{
					//mImage4.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage3.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage4.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[2]==3)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[4]) && userChance[2]==3)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[3]==4)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage2.clearAnimation();
					mImage1.clearAnimation();
					mImage3.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[3]==4)
				{
					//mImage2.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage2.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[4]) && userChance[3]==4)
				{
					//mImage1.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage1.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[4]) && userChance[4]==5)
				{
					//player_3_image.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage1.clearAnimation();
					mImage2.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					player_3_image.startAnimation(startAnimation);
					vib.vibrate(400);
					packChaalShowClickFlag=true;
					pack.setAlpha(255);
					chaal.setAlpha(255);
					show_button.setAlpha(255);
					if(seeButtonClickFlag==true)
					{
						see_cards.setAlpha(255);
					}
	    			pack.setVisibility(View.VISIBLE);
	    			chaal.setVisibility(View.VISIBLE);
	    			see_cards.setVisibility(View.VISIBLE);
	    			if(no_of_players_active==2)
	    			{
	    				show_button.setVisibility(View.VISIBLE);
	    			}
				}
				else if(Global.getUserName().equals(userName[0]) && userChance[4]==5)
				{
					//mImage1.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage1.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[1]) && userChance[4]==5)
				{
					//mImage1.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage1.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[2]) && userChance[4]==5)
				{
					//mImage1.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage1.startAnimation(startAnimation);
				}
				else if(Global.getUserName().equals(userName[3]) && userChance[4]==5)
				{
					//mImage1.startAnimation(chanceRotate);
					mImage3.clearAnimation();
					mImage2.clearAnimation();
					player_3_image.clearAnimation();
					mImage4.clearAnimation();
					Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
					mImage1.startAnimation(startAnimation);
				}
				chanceAnimationFlag=false;
			}

		}

		
	}
	
	public void twoPlayerCoinAnimation(String userName[],int []card_distribution_flag,int []userChaal)
	{
		int no_of_players=0;
		for(int i=0;i<userChaal.length;i++)
		{
			if(userChaal[i]==1)
			{
				userChaalDataFlag=true;
				break;
			}
		}
		for(int i=0;i<card_distribution_flag.length;i++)
		{
	    	if(card_distribution_flag[i]==2)
	    	{
	    		no_of_players++;
	    	}
	    	
	    }
		if(userChaalDataFlag==true)
		{
			//userChaalDataFlag=false;
			int []userChance=Global.getuser_chance();
			
			//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2], Toast.LENGTH_SHORT).show();
			if(card_distribution_flag.length>=2 )
			{
				if(no_of_players==2)
				{
					if(Global.getUserName().equals(userName[0]) && userChance[0]==1 && userChaal[0]==1)
					{	
						potValue=potValue+100;
						player3chaal(0);							    			
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player2chaal(true);						
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player3chaal(0);						
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);						
					}
				}
				
				
			}
			userChaalDataFlag=false;
			
		}
		
	}
	public void threePlayerCoinAnimation(String userName[],int []card_distribution_flag,int []userChaal)
	{
		int no_of_players=0;
		for(int i=0;i<userChaal.length;i++)
		{
			if(userChaal[i]==1)
			{
				userChaalDataFlag=true;
				break;
			}
		}
		for(int i=0;i<card_distribution_flag.length;i++){
	    	if(card_distribution_flag[i]==3)
	    	{
	    		no_of_players++;
	    	}
	    	
	    }
		if(userChaalDataFlag==true)
		{
			int []userChance=Global.getuser_chance();
			
			//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2], Toast.LENGTH_SHORT).show();
			if(card_distribution_flag.length>=3)
			{
				if(no_of_players==3)
				{
					if(Global.getUserName().equals(userName[0]) && userChance[0]==1 && userChaal[0]==1)
					{	
						potValue=potValue+100;
						player3chaal(0);
						
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
						
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
						
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
				}
		
				
			}
			
			userChaalDataFlag=false;			
		}
	

		
	}
	public void fourPlayerCoinAnimation(String userName[],int []card_distribution_flag,int []userChaal)
	{
		int no_of_players=0;
		for(int i=0;i<userChaal.length;i++)
		{
			if(userChaal[i]==1)
			{
				userChaalDataFlag=true;
				break;
			}
		}
		for(int i=0;i<card_distribution_flag.length;i++){
	    	if(card_distribution_flag[i]==4)
	    	{
	    		no_of_players++;
	    	}
	    	
	    }
		if(userChaalDataFlag==true)
		{
			int []userChance=Global.getuser_chance();
			
			//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2]+""+userChance[3], Toast.LENGTH_SHORT).show();
			if(card_distribution_flag.length>=4)
			{
				if(no_of_players==4)
				{
					if(Global.getUserName().equals(userName[0]) && userChance[0]==1 && userChaal[0]==1)
					{			
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[0]==1 && userChaal[0]==1)
					{	
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
				}
		
				
			}
			userChaalDataFlag=false;
		}
	

		
	}	
	public void fivePlayerCoinAnimation(String userName[],int []card_distribution_flag,int []userChaal)
	{
		
		int no_of_players=0;
		for(int i=0;i<userChaal.length;i++)
		{
			if(userChaal[i]==1)
			{
				userChaalDataFlag=true;
				break;
			}
		}
		for(int i=0;i<card_distribution_flag.length;i++){
	    	if(card_distribution_flag[i]==5)
	    	{
	    		no_of_players++;
	    	}
	    	
	    }
		if(userChaalDataFlag==true)
		{
			int []userChance=Global.getuser_chance();
			
			//Toast.makeText(getApplicationContext(),userChance[0]+""+userChance[1]+""+userChance[2]+""+userChance[3]+""+userChance[4], Toast.LENGTH_SHORT).show();
			if(card_distribution_flag.length>=5)
			{
				if(no_of_players==5)
				{
					if(Global.getUserName().equals(userName[0]) && userChance[0]==1 && userChaal[0]==1)
					{	
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[0]==1 && userChaal[0]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[0]==1 && userChaal[0]==1)
					{
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[4]) && userChance[0]==1 && userChaal[0]==1)
					{
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player1chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[4]) && userChance[1]==2 && userChaal[1]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player2chaal(true);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[4]) && userChance[2]==3 && userChaal[2]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player4chaal(0);
					}
					else if(Global.getUserName().equals(userName[4]) && userChance[3]==4 && userChaal[3]==1)
					{
						potValue=potValue+100;
						player5chaal(0);
					}
					else if(Global.getUserName().equals(userName[4]) && userChance[4]==5 && userChaal[4]==1)
					{
						potValue=potValue+100;
						player3chaal(0);
					}
					else if(Global.getUserName().equals(userName[0]) && userChance[4]==5 && userChaal[4]==1)
					{
						potValue=potValue+100;
						player5chaal(0);
					}
					else if(Global.getUserName().equals(userName[1]) && userChance[4]==5 && userChaal[4]==1)
					{
						potValue=potValue+100;
						player5chaal(0);
					}
					else if(Global.getUserName().equals(userName[2]) && userChance[4]==5 && userChaal[4]==1)
					{
						potValue=potValue+100;
						player5chaal(0);
					}
					else if(Global.getUserName().equals(userName[3]) && userChance[4]==5 && userChaal[4]==1)
					{
						potValue=potValue+100;
						player5chaal(0);
					}
				}
		
				
			}
			userChaalDataFlag=false;
		}
		
	

		
	}

	
	public void show_cards() 
	{
		// TODO Auto-generated method stub
		if (flag_show < 3) 
		{

			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first_show,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first_show_y);
			translate2.setDuration(30);
			translate2.setFillAfter(true);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("NewApi")
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
//					try{
//					 overlaying = (ViewGroup) player2_real_cards[i_show].getParent().getParent().getParent();
//					overlaying.getOverlay().add(player2_real_cards[i_show]);
//					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//					}

					TranslateAnimation translate3 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, second_show,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, second_show_y);
					translate3.setDuration(30);//30
					translate3.setFillAfter(true);
					translate3
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// translate3.setInterpolator(new BounceInterpolator());
					translate3.setAnimationListener(new AnimationListener() {

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

							first_show = first_show + 0.02f;
							second_show = second_show + 0.02f;
							third_show = third_show + 0.02f;

							i_show++;
							flag_show++;
							show_cards();

						}
					});
					player2_real_cards[i_show].startAnimation(translate3);
				}
			});

			player1_real_cards[i_show].startAnimation(translate2);
		}
		else if(flag_show==3)
		{
			
				i_show=0;
			     flag_show=0;
				first_show = -0.41f;
				 third_show = 0.36f;
				 second_show = -0.03f;
				 first_show_y=0.6f;
				 second_show_y=0.67f;
			
		}

	}

	public void table1_screen_init(RelativeLayout table1layout,int screenWidthDp, int screenHeightDp) 
	{


		// main horizontal
		int flw = (int) (screenWidthDp * 0.70);
		int flh = (int) (screenHeightDp * 0.55);
		final LinearLayout oneH = new LinearLayout(getApplicationContext());
		oneH.setLayoutParams(new LayoutParams(flw, flh));
		// oneH.setBackgroundColor(Color.BLUE);
		oneH.setOrientation(LinearLayout.HORIZONTAL);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.pokertable1);
		oneH.setBackgroundDrawable(new BitmapDrawable(bm));
		oneH.setId(1);
		table1layout.addView(oneH);
		RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) oneH
				.getLayoutParams();
		param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		oneH.setLayoutParams(param);

		// oneH.addView(mImag);

		// dvsn one
		int flw1 = (int) (flw * 0.17);
		int flh1 = (int) (flh);
		final LinearLayout oneH1 = new LinearLayout(getApplicationContext());
		oneH1.setLayoutParams(new LayoutParams(flw1, flh1));
		// oneH1.setBackgroundColor(Color.RED);
		oneH1.setOrientation(LinearLayout.VERTICAL);
		oneH.addView(oneH1);

		// dvsn one 01
		int flw11 = (int) (flw1);
		int flh11 = (int) (flh * 0.31);
		final LinearLayout oneH11 = new LinearLayout(getApplicationContext());
		oneH11.setLayoutParams(new LayoutParams(flw11, flh11));
		// oneH11.setBackgroundColor(Color.GRAY);
		oneH11.setOrientation(LinearLayout.VERTICAL);
		oneH1.addView(oneH11);

		// create bitmap from resource
		Bitmap bm3 = BitmapFactory.decodeResource(getResources(),R.drawable.sixth_character);

		// set circle bitmap
		mImage3 = new ImageView(getApplicationContext());

		// mImage3.setLayoutParams(new
		// LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT ));
		mImage3.setScaleType(ScaleType.FIT_XY);
		mImage3.setImageBitmap(getCircleBitmap(bm3));
		mImage3.setAlpha(180);
		oneH11.addView(mImage3);
		mImage3.setId(2);
		mImage3.setOnClickListener(this);

		// dvsn one 02
		int flw12 = (int) (flw1);
		int flh12 = (int) (flh * 0.38);
		final LinearLayout oneH12 = new LinearLayout(getApplicationContext());
		oneH12.setLayoutParams(new LayoutParams(flw12, flh12));
		// oneH12.setBackgroundColor(Color.CYAN);
		oneH12.setOrientation(LinearLayout.VERTICAL);
		oneH1.addView(oneH12);

		// dvsn one 03
		int flw13 = (int) (flw1);
		int flh13 = (int) (flh * 0.31);
		final LinearLayout oneH13 = new LinearLayout(getApplicationContext());
		oneH13.setLayoutParams(new LayoutParams(flw13, flh13));
		// oneH13.setBackgroundColor(Color.MAGENTA);
		oneH13.setOrientation(LinearLayout.VERTICAL);
		oneH1.addView(oneH13);

		// create bitmap from resource
		Bitmap bm4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.third_character);

		// set circle bitmap
		mImage4 = new ImageView(getApplicationContext());
		mImage4.setScaleType(ScaleType.FIT_XY);
		mImage4.setImageBitmap(getCircleBitmap(bm4));
		mImage4.setAlpha(180);
		oneH13.addView(mImage4);
		mImage4.setId(3);
		mImage4.setOnClickListener(this);
       
		// dvsn two
		 flw2 = (int) (flw * 0.66);
		 flh2 = (int) (flh);
		oneH2 = new RelativeLayout(getApplicationContext());
		oneH2.setLayoutParams(new LayoutParams(flw2, flh2));
		 //oneH2.setBackgroundColor(Color.RED);
		oneH.addView(oneH2);
		//oneH2.setBackgroundColor(Color.YELLOW);
		
		potTextView=new TextView(getApplicationContext());
		potTextView.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.27),(int)(flh2*0.15)));
		potTextView.setText("\u20B9");
		potTextView.setTypeface(potTextView.getTypeface(), Typeface.BOLD_ITALIC);
		potTextView.setTextSize(22);
		potTextView.setId(2);
		//potTextView.setBackgroundColor(Color.MAGENTA);
		oneH2.addView(potTextView);
		RelativeLayout.LayoutParams potTextView_param = (RelativeLayout.LayoutParams)potTextView.getLayoutParams();
		potTextView_param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		//potTextView_param.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		potTextView.setLayoutParams(potTextView_param);
		//player1Coin_layout.setVisibility(View.GONE);
		
		winningTextView=new TextView(getApplicationContext());
		winningTextView.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.27),(int)(flh2*0.12)));
		winningTextView.setText("YOU WON");
		winningTextView.setVisibility(View.GONE);
		winningTextView.setTextColor(Color.parseColor("#FFD700"));
		winningTextView.setGravity(Gravity.CENTER);
		winningTextView.setTypeface(potTextView.getTypeface(), Typeface.BOLD_ITALIC);
		winningTextView.setTextSize(15);
		//winningTextView.setBackgroundColor(Color.GRAY);
		oneH2.addView(winningTextView);
		RelativeLayout.LayoutParams winning_param = (RelativeLayout.LayoutParams)winningTextView.getLayoutParams();
		winning_param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		winning_param.addRule(RelativeLayout.BELOW, potTextView.getId());
		winningTextView.setLayoutParams(winning_param);
		
		potCoin=new ImageView(getApplicationContext());
		potCoin.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.12),(int)(flh2*0.15)));
		//potCoin.setBackgroundColor(Color.RED);
		potCoin.setImageResource(R.drawable.playercoins);
		oneH2.addView(potCoin);
		RelativeLayout.LayoutParams potCoin_param = (RelativeLayout.LayoutParams)potCoin.getLayoutParams();
		potCoin_param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		potCoin_param.addRule(RelativeLayout.ABOVE, potTextView.getId());
		potCoin.setLayoutParams(potCoin_param);
		
		int player1Coin_layout_width = (int) (flw2*0.25);
		int player1Coin_layout_height = (int) (flh2*0.12);
		 player1Coin_layout = new LinearLayout(getApplicationContext());
		 player1Coin_layout.setLayoutParams(new RelativeLayout.LayoutParams(player1Coin_layout_width, player1Coin_layout_height));
		//player1Coin_layout.setBackgroundColor(Color.RED);
		 player1Coin_layout.setOrientation(LinearLayout.HORIZONTAL);
		 oneH2.addView(player1Coin_layout);
		RelativeLayout.LayoutParams param_player1coin = (RelativeLayout.LayoutParams)player1Coin_layout.getLayoutParams();
		param_player1coin.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		param_player1coin.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		player1Coin_layout.setLayoutParams(param_player1coin);
		player1Coin_layout.setVisibility(View.GONE);
		
		ImageView player1coin_image=new ImageView(getApplicationContext());
		player1coin_image.setLayoutParams(new LayoutParams((int)(player1Coin_layout_width*0.40),(int)(player1Coin_layout_height)));
		player1coin_image.setImageResource(R.drawable.playercoins);
		//player1coin_image.setBackgroundColor(Color.GREEN);
		player1Coin_layout.addView(player1coin_image);
		
		TextView player1coin_text=new TextView(getApplicationContext());
		player1coin_text.setLayoutParams(new LayoutParams((int)(player1Coin_layout_width*0.60),(int)(player1Coin_layout_height)));
		player1coin_text.setText("\u20B9100");
		player1coin_text.setTextSize(17);
		//player1coin_text.setBackgroundColor(Color.MAGENTA);
		player1Coin_layout.addView(player1coin_text);
	
		int player2Coin_layout_width = (int) (flw2*0.25);
		int player2Coin_layout_height = (int) (flh2*0.12);
		 player2Coin_layout = new LinearLayout(getApplicationContext());
		 player2Coin_layout.setLayoutParams(new RelativeLayout.LayoutParams(player2Coin_layout_width, player2Coin_layout_height));
		//player1Coin_layout.setBackgroundColor(Color.RED);
		 player2Coin_layout.setOrientation(LinearLayout.HORIZONTAL);
		 oneH2.addView(player2Coin_layout);
		RelativeLayout.LayoutParams param_player2coin = (RelativeLayout.LayoutParams)player2Coin_layout.getLayoutParams();
		param_player2coin.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		param_player2coin.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		player2Coin_layout.setLayoutParams(param_player2coin);
		player2Coin_layout.setVisibility(View.GONE);
		
		ImageView player2coin_image=new ImageView(getApplicationContext());
		player2coin_image.setLayoutParams(new LayoutParams((int)(player2Coin_layout_width*0.40),(int)(player2Coin_layout_height)));
		player2coin_image.setImageResource(R.drawable.playercoins);
		//player1coin_image.setBackgroundColor(Color.GREEN);
		player2Coin_layout.addView(player2coin_image);
		
		TextView player2coin_text=new TextView(getApplicationContext());
		player2coin_text.setLayoutParams(new LayoutParams((int)(player2Coin_layout_width*0.60),(int)(player2Coin_layout_height)));
		player2coin_text.setText("\u20B9100");
		player2coin_text.setTextSize(17);
		//player1coin_text.setBackgroundColor(Color.MAGENTA);
		player2Coin_layout.addView(player2coin_text);
		
		int player3Coin_layout_width = (int) (flw2*0.25);
		int player3Coin_layout_height = (int) (flh2*0.12);
		 player3Coin_layout = new LinearLayout(getApplicationContext());
		 player3Coin_layout.setLayoutParams(new RelativeLayout.LayoutParams(player3Coin_layout_width, player3Coin_layout_height));
		//player1Coin_layout.setBackgroundColor(Color.RED);
		 player3Coin_layout.setOrientation(LinearLayout.HORIZONTAL);
		 oneH2.addView(player3Coin_layout);
			RelativeLayout.LayoutParams param_player3coin = (RelativeLayout.LayoutParams)player3Coin_layout.getLayoutParams();
			param_player3coin.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param_player3coin.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			player3Coin_layout.setLayoutParams(param_player3coin);
			player3Coin_layout.setVisibility(View.GONE);
		
		ImageView player3coin_image=new ImageView(getApplicationContext());
		player3coin_image.setLayoutParams(new LayoutParams((int)(player3Coin_layout_width*0.40),(int)(player3Coin_layout_height)));
		player3coin_image.setImageResource(R.drawable.playercoins);
		//player1coin_image.setBackgroundColor(Color.GREEN);
		player3Coin_layout.addView(player3coin_image);
		
		TextView player3coin_text=new TextView(getApplicationContext());
		player3coin_text.setLayoutParams(new LayoutParams((int)(player3Coin_layout_width*0.60),(int)(player3Coin_layout_height)));
		player3coin_text.setText("\u20B9100");
		player3coin_text.setTextSize(17);
		//player1coin_text.setBackgroundColor(Color.MAGENTA);
		player3Coin_layout.addView(player3coin_text);
		
		int player4Coin_layout_width = (int) (flw2*0.25);
		int player4Coin_layout_height = (int) (flh2*0.12);
		 player4Coin_layout = new LinearLayout(getApplicationContext());
		 player4Coin_layout.setLayoutParams(new RelativeLayout.LayoutParams(player4Coin_layout_width, player4Coin_layout_height));
		//player1Coin_layout.setBackgroundColor(Color.RED);
		 player4Coin_layout.setOrientation(LinearLayout.HORIZONTAL);
		 oneH2.addView(player4Coin_layout);
			RelativeLayout.LayoutParams param_player4coin = (RelativeLayout.LayoutParams)player4Coin_layout.getLayoutParams();
			param_player4coin.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			param_player4coin.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			player4Coin_layout.setLayoutParams(param_player4coin);
			player4Coin_layout.setVisibility(View.GONE);
		
		ImageView player4coin_image=new ImageView(getApplicationContext());
		player4coin_image.setLayoutParams(new LayoutParams((int)(player4Coin_layout_width*0.40),(int)(player4Coin_layout_height)));
		player4coin_image.setImageResource(R.drawable.playercoins);
		//player1coin_image.setBackgroundColor(Color.GREEN);
		player4Coin_layout.addView(player4coin_image);
		
		TextView player4coin_text=new TextView(getApplicationContext());
		player4coin_text.setLayoutParams(new LayoutParams((int)(player4Coin_layout_width*0.60),(int)(player4Coin_layout_height)));
		player4coin_text.setText("\u20B9100");
		player4coin_text.setTextSize(17);
		//player1coin_text.setBackgroundColor(Color.MAGENTA);
		player4Coin_layout.addView(player4coin_text);
		
		int player5Coin_layout_width = (int) (flw2*0.25);
		int player5Coin_layout_height = (int) (flh2*0.12);
		 player5Coin_layout = new LinearLayout(getApplicationContext());
		 player5Coin_layout.setLayoutParams(new RelativeLayout.LayoutParams(player5Coin_layout_width, player5Coin_layout_height));
		//player1Coin_layout.setBackgroundColor(Color.RED);
		 player5Coin_layout.setOrientation(LinearLayout.HORIZONTAL);
		 oneH2.addView(player5Coin_layout);
			RelativeLayout.LayoutParams param_player5coin = (RelativeLayout.LayoutParams)player5Coin_layout.getLayoutParams();
			param_player5coin.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			param_player5coin.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player5Coin_layout.setLayoutParams(param_player5coin);
			player5Coin_layout.setVisibility(View.GONE);
		
		ImageView player5coin_image=new ImageView(getApplicationContext());
		player5coin_image.setLayoutParams(new LayoutParams((int)(player5Coin_layout_width*0.40),(int)(player5Coin_layout_height)));
		player5coin_image.setImageResource(R.drawable.playercoins);
		//player1coin_image.setBackgroundColor(Color.GREEN);
		player5Coin_layout.addView(player5coin_image);
		
		TextView player5coin_text=new TextView(getApplicationContext());
		player5coin_text.setLayoutParams(new LayoutParams((int)(player5Coin_layout_width*0.60),(int)(player5Coin_layout_height)));
		player5coin_text.setText("\u20B9100");
		player5coin_text.setTextSize(17);
		//player1coin_text.setBackgroundColor(Color.MAGENTA);
		player5Coin_layout.addView(player5coin_text);
		


	//	init_cards();// function call to initialize random cards
		init_common_deck_cards();
		

		// dvsn three
		int flw3 = (int) (flw * 0.17);
		int flh3 = (int) (flh);
		final LinearLayout oneH3 = new LinearLayout(getApplicationContext());
		oneH3.setLayoutParams(new LayoutParams(flw3, flh3));
		// oneH3.setBackgroundColor(Color.GREEN);
		oneH3.setOrientation(LinearLayout.VERTICAL);
		oneH.addView(oneH3);

		// dvsn three 01
		int flw31 = (int) (flw3);
		int flh31 = (int) (flh * 0.31);
		final LinearLayout oneH31 = new LinearLayout(getApplicationContext());
		oneH31.setLayoutParams(new LayoutParams(flw31, flh31));
		// oneH31.setBackgroundColor(Color.GRAY);
		oneH31.setOrientation(LinearLayout.VERTICAL);
		oneH3.addView(oneH31);

		// create bitmap from resource
		Bitmap bm1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.fourth_character);

		// set circle bitmap
		mImage1 = new ImageView(getApplicationContext());
		mImage1.setScaleType(ScaleType.FIT_XY);
		mImage1.setImageBitmap(getCircleBitmap(bm1));
		mImage1.setAlpha(180);
		oneH31.addView(mImage1);
		mImage1.setId(5);
		mImage1.setOnClickListener(this);

		// dvsn three 02
		int flw32 = (int) (flw3);
		int flh32 = (int) (flh * 0.38);
		final LinearLayout oneH32 = new LinearLayout(getApplicationContext());
		oneH32.setLayoutParams(new LayoutParams(flw32, flh32));
		// oneH32.setBackgroundColor(Color.CYAN);
		oneH32.setOrientation(LinearLayout.VERTICAL);
		oneH3.addView(oneH32);

		// dvsn one 03
		int flw33 = (int) (flw3);
		int flh33 = (int) (flh * 0.31);
		final LinearLayout oneH33 = new LinearLayout(getApplicationContext());
		oneH33.setLayoutParams(new LayoutParams(flw33, flh33));
		// oneH33.setBackgroundColor(Color.MAGENTA);
		oneH33.setOrientation(LinearLayout.VERTICAL);
		oneH3.addView(oneH33);

		// create bitmap from resource
		Bitmap bm2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.fifth_character);

		// set circle bitmap
		mImage2 = new ImageView(getApplicationContext());
		mImage2.setScaleType(ScaleType.FIT_XY);
		mImage2.setImageBitmap(getCircleBitmap(bm2));
		mImage2.setAlpha(180);
		oneH33.addView(mImage2);
		mImage2.setId(6);
		mImage2.setOnClickListener(this);
		
		
		int player_3_width = (int) (screenWidthDp*0.12);
		int player_3_height = (int) (screenHeightDp*0.19);
		final LinearLayout player_3_layout = new LinearLayout(getApplicationContext());
		player_3_layout.setLayoutParams(new LayoutParams(player_3_width,player_3_height));
		player_3_layout.setOrientation(LinearLayout.VERTICAL);
		table1layout.addView(player_3_layout);
		RelativeLayout.LayoutParams param11 = (RelativeLayout.LayoutParams) player_3_layout.getLayoutParams();
		param11.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param11.addRule(RelativeLayout.BELOW,oneH.getId());
		player_3_layout.setLayoutParams(param11);
		//player_3_layout.setBackgroundColor(Color.MAGENTA);

		// create bitmap from resource
		// Bitmap player_3_Bitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.face);

		// set circle bitmap
		player_3_image = new ImageView(getApplicationContext());
		player_3_image.setScaleType(ScaleType.FIT_XY);
		player_3_image.setImageBitmap(getCircleBitmap(Global.getImageBitmap()));
		player_3_image.setAlpha(230);
		player_3_layout.addView(player_3_image);
		player_3_image.setId(4);
		player_3_image.setOnClickListener(this);


		// see,pack,chaal buttons layout
		int three_button_layout_width = (int) (screenWidthDp * 0.65);
		int three_button_layout_height = (int) (screenHeightDp * 0.15);
		final LinearLayout three_button_layout = new LinearLayout(getApplicationContext());
		three_button_layout.setLayoutParams(new LayoutParams(three_button_layout_width, three_button_layout_height));
		three_button_layout.setOrientation(LinearLayout.HORIZONTAL);
		//three_button_layout.setBackgroundColor(Color.MAGENTA);
		table1layout.addView(three_button_layout);
		RelativeLayout.LayoutParams three_button_param = (RelativeLayout.LayoutParams) three_button_layout
		.getLayoutParams();
		three_button_param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
		three_button_param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,	RelativeLayout.TRUE);
		three_button_layout.setLayoutParams(three_button_param);

		// LinearLayout.LayoutParams
		// l=(LinearLayout.LayoutParams)three_button_layout.getLayoutParams();
		// l.setMargins(0, 0, 50, 0);

		// pack,chaal layout
		int pack_chaal_layout_width = (int) (three_button_layout_width * 0.40);
		int pack_chaal_layout_height = (int) (three_button_layout_height);
		final LinearLayout pack_chaal_layout = new LinearLayout(getApplicationContext());
		//pack_chaal_layout.setBackgroundColor(Color.YELLOW);
		pack_chaal_layout.setLayoutParams(new LayoutParams(pack_chaal_layout_width, pack_chaal_layout_height));
		pack_chaal_layout.setOrientation(LinearLayout.HORIZONTAL);
		three_button_layout.addView(pack_chaal_layout);
		//pack_chaal_layout.setPadding(0, 0, 120, 0);

		pack = new ImageView(getApplicationContext());
		pack.setLayoutParams(new LayoutParams((int)(pack_chaal_layout_width*0.45),(int)(pack_chaal_layout_height*0.80)));
		pack.setImageResource(R.drawable.pack);
		pack.setVisibility(View.GONE);
		pack_chaal_layout.addView(pack);
		pack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
			   if(packChaalShowClickFlag==true)
			   {
				   	pack_service_flag=1;
					delete_two= new user_pack_button_clicked().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					variable_for_pack=1;
					pack.setAlpha(100);
					chaal.setAlpha(100);
					see_cards.setAlpha(100);
					show_button.setAlpha(100);
					packChaalShowClickFlag=false;
			   }
				
				 
				
			}
		});

		int packChaal_empty_layout_width = (int) (pack_chaal_layout_width*0.10);
		int packChaal_empty_layout_height = (int) (pack_chaal_layout_height*0.80);
		final LinearLayout packChaalempty_layout = new LinearLayout(getApplicationContext());
		//packChaalempty_layout.setBackgroundColor(Color.RED);
		packChaalempty_layout.setLayoutParams(new LayoutParams(packChaal_empty_layout_width,packChaal_empty_layout_height));
		packChaalempty_layout.setOrientation(LinearLayout.HORIZONTAL);
		pack_chaal_layout.addView(packChaalempty_layout);
		
		chaal = new ImageView(getApplicationContext());
		chaal.setLayoutParams(new LayoutParams((int)(pack_chaal_layout_width*0.45),(int)(pack_chaal_layout_height*0.80)));
		chaal.setImageResource(R.drawable.chaal);
		chaal.setVisibility(View.GONE);
		pack_chaal_layout.addView(chaal);
		chaal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				LayoutInflater layoutinflater = LayoutInflater.from(Table1Activity.this);
//				View dialogview = layoutinflater.inflate(R.layout.choose_table_dialog,null);
//				choose_table_dialog_init(dialogview, screenWidthDp, screenHeightDp);
				if(packChaalShowClickFlag==true)
				{
					userChaal= new user_chaal().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					pack.setAlpha(100);
					chaal.setAlpha(100);
					see_cards.setAlpha(100);
					show_button.setAlpha(100);
					packChaalShowClickFlag=false;
				}
				
				
			}
		});
	
		

		// empty layout
		int empty_layout_width = (int) (three_button_layout_width * 0.20);
		int empty_layout_height = (int) (three_button_layout_height);
		final LinearLayout empty_layout = new LinearLayout(
				getApplicationContext());
		//empty_layout.setBackgroundColor(Color.GREEN);
		empty_layout.setLayoutParams(new LayoutParams(empty_layout_width,
				empty_layout_height));
		empty_layout.setOrientation(LinearLayout.HORIZONTAL);
		three_button_layout.addView(empty_layout);

		// see,show layout
				int see_show_layout_width = (int) (three_button_layout_width * 0.40);
				int see_show_layout_height = (int) (three_button_layout_height);
				final LinearLayout see_show_layout = new LinearLayout(getApplicationContext());
				//see_show_layout.setBackgroundColor(Color.YELLOW);
				see_show_layout.setLayoutParams(new LayoutParams(see_show_layout_width, see_show_layout_height));
				see_show_layout.setOrientation(LinearLayout.HORIZONTAL);
				three_button_layout.addView(see_show_layout);
		


		 see_cards = new ImageView(getApplicationContext());
		see_cards.setLayoutParams(new LayoutParams((int)(see_show_layout_width*0.45),(int)(see_show_layout_height*0.80)));
		see_cards.setImageResource(R.drawable.see);
		see_cards.setVisibility(View.GONE);
		see_show_layout.addView(see_cards);

		see_cards.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//second = -0.03f;
				if(seeButtonClickFlag==true)
				{
					see_click_button= new user_see_button_pressed().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					seeCards_func();			
					see_cards.setAlpha(100);
					seeButtonClickFlag=false;
				}
				
				
				
			}
		});
		
		int seeShow_empty_layout_width = (int) (see_show_layout_width*0.10);
		int seeShow_empty_layout_height = (int) (see_show_layout_height*0.80);
		final LinearLayout seeShowempty_layout = new LinearLayout(getApplicationContext());
		//seeShowempty_layout.setBackgroundColor(Color.RED);
		seeShowempty_layout.setLayoutParams(new LayoutParams(seeShow_empty_layout_width,seeShow_empty_layout_height));
		seeShowempty_layout.setOrientation(LinearLayout.HORIZONTAL);
		see_show_layout.addView(seeShowempty_layout);
		
		 show_button = new ImageView(getApplicationContext());
		 show_button.setLayoutParams(new LayoutParams((int)(see_show_layout_width*0.45),(int)(see_show_layout_height*0.80)));
			show_button.setImageResource(R.drawable.show);
			show_button.setVisibility(View.GONE);
			see_show_layout.addView(show_button);
			
			show_button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(packChaalShowClickFlag==true)
					{
						
						show_click_button= new user_show_button_pressed().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
						//show_card_restrict_flag=0;
						pack.setAlpha(100);
						chaal.setAlpha(100);
						see_cards.setAlpha(100);
						show_button.setAlpha(100);
						packChaalShowClickFlag=false;
					}
				
					
				}
			});
		
		RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams)show_button.getLayoutParams();
		param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		show_button.setLayoutParams(param1);
		
//		sideshow_button = new Button(getApplicationContext());
//		sideshow_button.setLayoutParams(new LayoutParams(show_layout_width,
//					LayoutParams.WRAP_CONTENT));
//		sideshow_button.setText("sideshow");
//			sideshow_button.setVisibility(View.GONE);
//			show_layout.addView(sideshow_button);
//		sideshow_button.setLayoutParams(param1);
//		sideshow_button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				sideshow_click_button=new user_sideshow_button_pressed().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//			}
//		});
		
		


		//drawer_init(drawer_linear_layout, screenHeightDp);

	}
	
    private void user_profileDialog_init(View dialogview,int screenWidthDp, int screenHeightDp,String user_name, int user_coins) {
		// TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/ 
    	
    	AlertDialog.Builder builder= new AlertDialog.Builder(PublicTable.this);    	
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
    	userName.setText(user_name);
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
    	if(user_coins==0)
    	{
    		userCoinText.setVisibility(View.GONE);
    	}
    	else
    	{
    		userCoinText.setText("\u20B9"+user_coins);
    	}   	
    	userCoinText.setGravity(Gravity.CENTER_VERTICAL);
    	userCoinText.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
    	userCoin_layout2.addView(userCoinText);
    	
    	
    			
	}
	
	

//	public void drawer_init(LinearLayout drawer_linear_layout,int screenHeightDp) {
//
//		TextView tables_title = (TextView) findViewById(R.id.table_title_textview);
//		tables_title.setLayoutParams(new LayoutParams(drawer_width,
//				(int) (screenHeightDp * 0.10)));
//
//		tablesList.setLayoutParams(new LayoutParams(drawer_width,(int) (screenHeightDp * 0.70)));
//
//		int create_new_table_layout_height = (int) (screenHeightDp * 0.20);
//		LinearLayout create_new_table_layout = (LinearLayout) findViewById(R.id.l1);
//		create_new_table_layout.setLayoutParams(new LayoutParams(drawer_width,
//				create_new_table_layout_height));
//
//		int create_new_table_button_width = (int) (drawer_width);
//		int create_new_table_button_height = (int) (create_new_table_layout_height * 0.50);
//		create_new_table_button.setLayoutParams(new LayoutParams(
//				create_new_table_button_width, create_new_table_button_height));
//		create_new_table_button.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (v.getId() == R.id.create_new_table_button) {
//					new_table_name_edittext.setVisibility(View.VISIBLE);
//					Go.setVisibility(View.VISIBLE);
//					
//				}
//
//			}
//		});
//
//		LinearLayout edittext_linear_layout = (LinearLayout) findViewById(R.id.l2);
//		edittext_linear_layout.setLayoutParams(new LayoutParams(
//				create_new_table_button_width, create_new_table_button_height));
//
//		new_table_name_edittext.setLayoutParams(new LayoutParams(
//				(int) (drawer_width * 0.60), create_new_table_button_height));
//		Go.setLayoutParams(new LayoutParams((int) (drawer_width * 0.30),
//				create_new_table_button_height));
//
//		Go.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (v.getId() == R.id.Go) {
//					table_name = new_table_name_edittext.getText().toString();
//					//task_list_go_click=new all_users_details().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//					flag_for_handler=1;
//					new_table_name_edittext.setVisibility(View.GONE);
//					Go.setVisibility(View.GONE);
//					drawerlayout.closeDrawer(Gravity.END);
//					
//					LayoutInflater layoutinflater = LayoutInflater.from(Table1Activity.this);
//					View dialogview = layoutinflater.inflate(R.layout.total_user_check,null);
//					//total_players_present_init(dialogview, screenWidthDp,Table1Activity.this.screenHeightDp);
//
//				}
//			}
//		});
//
//	}

	public void dialog_of_user_info(View dialogview, int scw, int sch,
			String s, int p, int o) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PublicTable.this);

		builder.setView(dialogview);
		final AlertDialog alert = builder.create();
		alert.setCancelable(true);
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		alert.show();
		/* To display alert dialog box on onclick button starts here */
		/* set width and height of dialogbox starts here */
		int screenWidthDpDialog = (int) (screenWidthDp * 0.80);
		int screenHeightDpDialog = (int) (screenHeightDp * 0.80);
		alert.getWindow().setLayout(screenWidthDpDialog, screenHeightDpDialog);
		alert.getWindow().findViewById(R.id.rll);

		int scw1 = (int) (screenWidthDpDialog * 0.30);
		alert.getWindow().findViewById(R.id.rll1).setLayoutParams(new LayoutParams(scw1, screenHeightDpDialog));
		final TextView choose_table = (TextView) dialogview.findViewById(R.id.rll11);

		choose_table.setText(s);
		choose_table.setTextSize(30);
		choose_table.setTextColor(Color.MAGENTA);
		choose_table.setTypeface(null, Typeface.BOLD_ITALIC);
		alert.getWindow().findViewById(R.id.rll2)
				.setLayoutParams(new LayoutParams(scw1, screenHeightDpDialog));
		final TextView choose_table1 = (TextView) dialogview
				.findViewById(R.id.rll12);

		choose_table1.setText("" + p);
		choose_table1.setTextSize(30);
		choose_table1.setTextColor(Color.MAGENTA);
		choose_table1.setTypeface(null, Typeface.BOLD_ITALIC);
		alert.getWindow().findViewById(R.id.rll3)
				.setLayoutParams(new LayoutParams(scw1, screenHeightDpDialog));
		final TextView choose_table2 = (TextView) dialogview
				.findViewById(R.id.rll13);

		choose_table2.setText("" + o);
		choose_table2.setTextSize(30);
		choose_table2.setTextColor(Color.MAGENTA);
		choose_table2.setTypeface(null, Typeface.BOLD_ITALIC);

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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int twoPlayer=0;
		int threePlayer=0;
		int fourPlayer=0;
		int fivePlayer=0;
		int card_distribution_flag[]=Global.getuser_packDetails();
		for(int i=0;i<card_distribution_flag.length;i++)
		{
			if(card_distribution_flag[i]==2)
			{
				twoPlayer++;
			}
			if(card_distribution_flag[i]==3)
			{
				threePlayer++;
			}
			if(card_distribution_flag[i]==4)
			{
				fourPlayer++;
			}
			if(card_distribution_flag[i]==5)
			{
				fivePlayer++;
			}
		}
		if (Global.all_users_info.size() == 1 ) 
		{
			one_user_table(v);
		}
		if (twoPlayer==2)
		{
			two_user_table(v);	
		}
		if ( threePlayer==3) 
		{
			three_user_table(v);
		}
		if (fourPlayer==4)
		{
			four_user_table(v);		
		}
		if (fivePlayer==5) 
		{
			five_user_table(v);	
		}

		
		// else if(v.getId()==R.id.reverse)
		// {
		// if(flag==3)
		// {
		// i=0;
		// first=-0.34f;
		// third=0.34f;
		// second=0.0f;
		// reverse_anim();
		// }
		//
		// }

	}

	private void five_user_table(View v) 
	{
		// TODO Auto-generated method stub
		String s[] = Global.getall_Username_Details();
		int p = Global.getUserCoin();
		int o[] = Global.getusergameidDetails();
		int flag_for_flag[]=Global.getuser_flag_cards_Details();
		i = 0;
//		int k = 0;
//		int kj = 0;
//		int kjl = 0;
//		int mjkl = 0;
		if(dynamicRestrictFlag==true)
		{
			int j = 0;
			for (j = 0; j < Global.all_users_info.size(); j++) {

				if (s[j].equals(Global.getUserName())) {
					if (j == 4) {

						k = 0;
						kj = 1;
						kjl = 2;
						mjkl = 3;
						break;
					} else if (j == 3) {

						k = 0;
						kj = 1;
						kjl = 2;
						mjkl = 4;
						break;
					} else if (j == 2) {
						k = 0;
						kj = 1;
						kjl = 3;
						mjkl = 4;
						break;
					} else if (j == 1) {
						k = 0;
						kj = 2;
						kjl = 3;
						mjkl = 4;
						break;
					} else if (j == 0) {
						k = 1;
						kj = 2;
						kjl = 3;
						mjkl = 4;
						break;
					}

				}
			}
		}

		if (v.getId() == PublicTable.player_3_image.getId()) {
			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[j], p);
		}

		else if (v.getId() == PublicTable.mImage3.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[k], 0);

		} else if (v.getId() == PublicTable.mImage4.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[kj], 0);

		} else if (v.getId() == PublicTable.mImage2.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[kjl], 0);

		} else if (v.getId() == PublicTable.mImage1.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[mjkl], 0);

		}

	}

	private void four_user_table(View v)
	{
		// TODO Auto-generated method stub
		String s[] = Global.getall_Username_Details();
		int p = Global.getUserCoin();
		int o[] = Global.getusergameidDetails();
		i = 0;
//		int k = 0;
//		int kj = 0;
//		int kjl = 0;
		
		if(dynamicRestrictFlag==true)
		{
			int j = 0;
			for (j = 0; j < Global.all_users_info.size(); j++) {

				if (s[j].equals(Global.getUserName())) {
					if (j == 3) {

						k = 0;
						kj = 1;
						kjl = 2;
						break;
					} else if (j == 2) {
						k = 0;
						kj = 1;
						kjl = 3;
						break;
					} else if (j == 1) {
						k = 0;
						kj = 2;
						kjl = 3;
						break;
					} else if (j == 0) {
						k = 1;
						kj = 2;
						kjl = 3;
						break;
					}

				}
			}
		}

		if (v.getId() == PublicTable.player_3_image.getId()) {
			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[j], p);
		}

		else if (v.getId() == PublicTable.mImage3.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[k],0);

		} else if (v.getId() == PublicTable.mImage4.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[kj], 0);

		} else if (v.getId() == PublicTable.mImage2.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[kjl], 0);

		}
	}

	private void three_user_table(View v) 
	{
		// TODO Auto-generated method stub
		
		String s[] = Global.getall_Username_Details();
		int p = Global.getUserCoin();
		int o[] = Global.getusergameidDetails();
		i = 0;
//		int k = 0;
//		int kj = 0;
		
		if(dynamicRestrictFlag==true)
		{
			int j = 0;
			for (j = 0; j < Global.all_users_info.size(); j++) {

				if (s[j].equals(Global.getUserName())) {
					if (j == 2) {
						k = 0;
						kj = 1;
						break;
					} else if (j == 1) {
						k = 0;
						kj = 2;
						break;
					} else if (j == 0) {
						k = 1;
						kj = 2;
						break;
					}

				}
			}
		}

		if (v.getId() == PublicTable.player_3_image.getId()) {
			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[j], p);
		}

		else if (v.getId() == PublicTable.mImage4.getId()) {

			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[k], 0);

		} else if (v.getId() == PublicTable.mImage2.getId()) {

			username_for_sideshow=s[kj];
			LayoutInflater layoutinflater = LayoutInflater
					.from(PublicTable.this);
			View dialogview = layoutinflater.inflate(
					R.layout.user_details_dialog, null);
			user_profileDialog_init(dialogview, screenWidthDp, screenHeightDp,
					s[kj], 0);

		}

	}

	private void two_user_table(View v)
	{
		// TODO Auto-generated method stub
		String s[] = Global.getall_Username_Details();
		int p = Global.getUserCoin();
		int o[] = Global.getusergameidDetails();
		i = 0;

		for (int j = 0; j < Global.all_users_info.size(); j++) {

			if (v.getId() == PublicTable.player_3_image.getId()) {
				if (s[j].equals(Global.getUserName())) {

					LayoutInflater layoutinflater = LayoutInflater
							.from(PublicTable.this);
					View dialogview = layoutinflater.inflate(
							R.layout.user_details_dialog, null);
					user_profileDialog_init(dialogview, screenWidthDp,
							screenHeightDp, s[j], p);

				}
			}

			else if (v.getId() == PublicTable.mImage4.getId()) {

				if (!s[j].equals(Global.getUserName())) {
					LayoutInflater layoutinflater = LayoutInflater
							.from(PublicTable.this);
					View dialogview = layoutinflater.inflate(
							R.layout.user_details_dialog, null);
					user_profileDialog_init(dialogview, screenWidthDp,
							screenHeightDp, s[j], 0);
				}

			}

		}

	}

	public void one_user_table(View v) 
	{
		// TODO Auto-generated method stub
		for (ArrayList<User_Info> innerList : Global.all_users_info) 
		{
			User_Info coinsgetbyuser = innerList.get(0);
			String s = coinsgetbyuser.user_name;
			int p = Global.getUserCoin();
			int o = coinsgetbyuser.user_game_id;

			if (v.getId() == PublicTable.player_3_image.getId()) 
			{

				LayoutInflater layoutinflater = LayoutInflater
						.from(PublicTable.this);
				View dialogview = layoutinflater.inflate(
						R.layout.user_details_dialog, null);
				user_profileDialog_init(dialogview, screenWidthDp,
						screenHeightDp, s, p);

			}

			
		}
	}

	public void seeCards_func()
	{

		see_player_real_cards = new ImageView[3];
		
		for(int i=0;i<3;i++)
		{
			see_player_real_cards[i]=player2_real_cards[i];
		}
		
		if (see_flag < 3) {

//			final ViewGroup overlaying = (ViewGroup) player2_real_cards[k]
//					.getParent().getParent().getParent();
//			overlaying.getOverlay().add(player2_real_cards[k]);

			TranslateAnimation translate3 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, second_see,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.67f);
			translate3.setDuration(30);
			translate3.setFillAfter(true);
			translate3.setInterpolator(new AccelerateDecelerateInterpolator());
			translate3.setInterpolator(new BounceInterpolator());

			translate3.setAnimationListener(new AnimationListener() {

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
					second_see = second_see + 0.02f;
					i_see++;
					see_flag++;
					seeCards_func();
				}
			});
			see_player_real_cards[i_see].startAnimation(translate3);
		}
		else if(see_flag==3)
		{		
//			i_see=0;
//		     see_flag=0;
//				seeCards_func2();
				i_see=0;
			     see_flag=0;
				 second_see = -0.03f;
		}

	}
	
	public void seeCards_func2()
	{

		
		if (see_flag < 3) {

			ScaleAnimation winTranslate1 = new ScaleAnimation(0f,2f, 0f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			winTranslate1.setDuration(30);
			winTranslate1.setFillAfter(true);
			winTranslate1.setInterpolator(new AccelerateDecelerateInterpolator());	
			winTranslate1.setAnimationListener(new AnimationListener() {
				
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
					
					i_see++;
					see_flag++;
					seeCards_func2();
					
				}
			});
			
			see_player_real_cards[i_see].startAnimation(winTranslate1);
		}

	}	
	

	public void init_common_deck_cards()
	{
		
		for (int i = 0; i < 3; i++) 
		{

			player1[i] = new ImageView(getApplicationContext());
			player1[i] = deck_of_cards[alloted_cards[alloted_cards_index_player1]];
			player1[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
			oneH2.addView(player1[i]);
			RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player1[i].getLayoutParams();
			param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player1[i].setLayoutParams(param1);
			alloted_cards_index_player1++;


			player2[i] = new ImageView(getApplicationContext());			
			player2[i] = deck_of_cards[alloted_cards[alloted_cards_index_player2]];
			player2[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
			oneH2.addView(player2[i]);
			RelativeLayout.LayoutParams param2 = (RelativeLayout.LayoutParams) player2[i]
					.getLayoutParams();
			param2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param2.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player2[i].setLayoutParams(param2);
			alloted_cards_index_player2++;


			player3[i] = new ImageView(getApplicationContext());
			player3[i] = deck_of_cards[alloted_cards[alloted_cards_index_player3]];
			player3[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
			oneH2.addView(player3[i]);
			RelativeLayout.LayoutParams param3 = (RelativeLayout.LayoutParams) player3[i]
					.getLayoutParams();
			param3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param3.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player3[i].setLayoutParams(param3);
			alloted_cards_index_player3++;

			player4[i] = new ImageView(getApplicationContext());
			player4[i] = deck_of_cards[alloted_cards[alloted_cards_index_player4]];
			player4[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
			oneH2.addView(player4[i]);
			RelativeLayout.LayoutParams param4 = (RelativeLayout.LayoutParams) player4[i]
					.getLayoutParams();
			param4.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param4.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player4[i].setLayoutParams(param4);
			alloted_cards_index_player4++;;

			player5[i] = new ImageView(getApplicationContext());
			player5[i] = deck_of_cards[alloted_cards[alloted_cards_index_player5]];
			player5[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
			oneH2.addView(player5[i]);
			RelativeLayout.LayoutParams param5 = (RelativeLayout.LayoutParams) player5[i]
					.getLayoutParams();
			param5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			param5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			player5[i].setLayoutParams(param5);
			alloted_cards_index_player5++;;
		}

	

	}
	
	public void init_real_cards_two_players() 
	{
		
		
		if(init_realcards_flag==1)
		{
			for(int k=0;k<3;k++)
			{
				oneH2.removeView(player1_real_cards[k]);
				//overlaying.getOverlay().remove(player2_real_cards[k]);
				oneH2.removeView(player2_real_cards[k]);
	
			}
			oneH2.removeView(deck);	
		}
		
		int card_one[] = Global.get_user_card_one();
		int card_two[] = Global.get_user_card_two();
		int card_three[] = Global.get_user_card_three();
		String users[] = Global.getall_Username_Details();

		
		for (int i = 0; i < 3; i++) 
		{
			
			
			if(users[0].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
			}
			else if(users[1].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
			}
			

		}

		deck = new ImageView(getApplicationContext());
		deck.setImageResource(R.drawable.card_back2);
		deck.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
		oneH2.addView(deck);
		RelativeLayout.LayoutParams param5 = (RelativeLayout.LayoutParams) deck.getLayoutParams();
		param5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		deck.setLayoutParams(param5);
		
		init_realcards_flag=1;
	
	}

	public void init_real_cards_three_players() 
	{
		
		
		if(init_realcards_flag==1)
		{
			for(int k=0;k<3;k++)
			{
				oneH2.removeView(player1_real_cards[k]);
				oneH2.removeView(player2_real_cards[k]);
				oneH2.removeView(player3_real_cards[k]);
	
			}
			oneH2.removeView(deck);	
		}
		
		int card_one[] = Global.get_user_card_one();
		int card_two[] = Global.get_user_card_two();
		int card_three[] = Global.get_user_card_three();
		String users[] = Global.getall_Username_Details();

		
		for (int i = 0; i < 3; i++) 
		{
			
			
			if(users[0].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
			}
			else if(users[1].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
			}
			
			else if(users[2].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
			}


		}

		deck = new ImageView(getApplicationContext());
		deck.setImageResource(R.drawable.card_back2);
		deck.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
		oneH2.addView(deck);
		RelativeLayout.LayoutParams param5 = (RelativeLayout.LayoutParams) deck.getLayoutParams();
		param5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		deck.setLayoutParams(param5);
		
		init_realcards_flag=1;
	
	}

	public void init_real_cards_four_players() 
	{
		
		
		if(init_realcards_flag==1)
		{
			for(int k=0;k<3;k++)
			{
				oneH2.removeView(player1_real_cards[k]);
				oneH2.removeView(player2_real_cards[k]);
				oneH2.removeView(player3_real_cards[k]);
				oneH2.removeView(player4_real_cards[k]);
	
			}
			oneH2.removeView(deck);	
		}
		
		int card_one[] = Global.get_user_card_one();
		int card_two[] = Global.get_user_card_two();
		int card_three[] = Global.get_user_card_three();
		String users[] = Global.getall_Username_Details();

		
		for (int i = 0; i < 3; i++) 
		{
			
			
			if(users[0].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
			}
			else if(users[1].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
			}
			
			else if(users[2].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
			}


			else if(users[3].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
			}

		}

		deck = new ImageView(getApplicationContext());
		deck.setImageResource(R.drawable.card_back2);
		deck.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
		oneH2.addView(deck);
		RelativeLayout.LayoutParams param5 = (RelativeLayout.LayoutParams) deck.getLayoutParams();
		param5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		deck.setLayoutParams(param5);
		
		init_realcards_flag=1;
	
	}
	
	public void init_real_cards_five_players() 
	{
		
		
		if(init_realcards_flag==1)
		{
			for(int k=0;k<3;k++)
			{
				oneH2.removeView(player1_real_cards[k]);
				oneH2.removeView(player2_real_cards[k]);
				oneH2.removeView(player3_real_cards[k]);
				oneH2.removeView(player4_real_cards[k]);
	            oneH2.removeView(player5_real_cards[k]);
			}
			oneH2.removeView(deck);	
		}
		
		int card_one[] = Global.get_user_card_one();
		int card_two[] = Global.get_user_card_two();
		int card_three[] = Global.get_user_card_three();
		String users[] = Global.getall_Username_Details();

		
		for (int i = 0; i < 3; i++) 
		{
			
			
			if(users[0].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
				
				player5_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player5_real_cards[i] = deck_of_cards_real[card_one[4]];}
				else if(i==1){player5_real_cards[i] = deck_of_cards_real[card_two[4]];}
				else if(i==2){player5_real_cards[i] = deck_of_cards_real[card_three[4]];}
				player5_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player5_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player5_real_cards[i].setLayoutParams(param1);
			}
			else if(users[1].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
				
				player5_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player5_real_cards[i] = deck_of_cards_real[card_one[4]];}
				else if(i==1){player5_real_cards[i] = deck_of_cards_real[card_two[4]];}
				else if(i==2){player5_real_cards[i] = deck_of_cards_real[card_three[4]];}
				player5_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player5_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player5_real_cards[i].setLayoutParams(param1);
			}
			
			else if(users[2].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
				
				player5_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player5_real_cards[i] = deck_of_cards_real[card_one[4]];}
				else if(i==1){player5_real_cards[i] = deck_of_cards_real[card_two[4]];}
				else if(i==2){player5_real_cards[i] = deck_of_cards_real[card_three[4]];}
				player5_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player5_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player5_real_cards[i].setLayoutParams(param1);
			}


			else if(users[3].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
				player5_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player5_real_cards[i] = deck_of_cards_real[card_one[4]];}
				else if(i==1){player5_real_cards[i] = deck_of_cards_real[card_two[4]];}
				else if(i==2){player5_real_cards[i] = deck_of_cards_real[card_three[4]];}
				player5_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player5_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player5_real_cards[i].setLayoutParams(param1);
			}

			else if(users[4].equals(Global.getUserName()))
			{

				player2_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player2_real_cards[i] = deck_of_cards_real[card_one[4]];}
				else if(i==1){player2_real_cards[i] = deck_of_cards_real[card_two[4]];}
				else if(i==2){player2_real_cards[i] = deck_of_cards_real[card_three[4]];}
				player2_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player2_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) player2_real_cards[i]
						.getLayoutParams();
				param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
				param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
				player2_real_cards[i].setLayoutParams(param1);
				
				
				player1_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player1_real_cards[i] = deck_of_cards_real[card_one[0]];}
				else if(i==1){player1_real_cards[i] = deck_of_cards_real[card_two[0]];}
				else if(i==2){player1_real_cards[i] = deck_of_cards_real[card_three[0]];}
				player1_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player1_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player1_real_cards[i].setLayoutParams(param1);
				
				player3_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player3_real_cards[i] = deck_of_cards_real[card_one[1]];}
				else if(i==1){player3_real_cards[i] = deck_of_cards_real[card_two[1]];}
				else if(i==2){player3_real_cards[i] = deck_of_cards_real[card_three[1]];}
				player3_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player3_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player3_real_cards[i].setLayoutParams(param1);
				
				player4_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player4_real_cards[i] = deck_of_cards_real[card_one[2]];}
				else if(i==1){player4_real_cards[i] = deck_of_cards_real[card_two[2]];}
				else if(i==2){player4_real_cards[i] = deck_of_cards_real[card_three[2]];}
				player4_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player4_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player4_real_cards[i].setLayoutParams(param1);
				
				player5_real_cards[i] = new ImageView(getApplicationContext());
				if(i==0){player5_real_cards[i] = deck_of_cards_real[card_one[3]];}
				else if(i==1){player5_real_cards[i] = deck_of_cards_real[card_two[3]];}
				else if(i==2){player5_real_cards[i] = deck_of_cards_real[card_three[3]];}
				player5_real_cards[i].setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
				try{oneH2.addView(player5_real_cards[i]);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				player5_real_cards[i].setLayoutParams(param1);
			}

		}

		deck = new ImageView(getApplicationContext());
		deck.setImageResource(R.drawable.card_back2);
		deck.setLayoutParams(new RelativeLayout.LayoutParams((int)(flw2*0.20),(int)(flh2*0.20)));
		oneH2.addView(deck);
		RelativeLayout.LayoutParams param5 = (RelativeLayout.LayoutParams) deck.getLayoutParams();
		param5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		deck.setLayoutParams(param5);
		
		init_realcards_flag=1;
	
	}
	
	public void distribute_cards_V() 
	{
		

		if (flag < 3) 
		{

			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.13f);
			translate2.setDuration(200);
			translate2.setFillAfter(true);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("NewApi")
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub

					TranslateAnimation translate3 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, first,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, 0.6f);
					translate3.setDuration(200);
					translate3.setFillAfter(true);
					translate3
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// translate3.setInterpolator(new BounceInterpolator());
					translate3.setAnimationListener(new AnimationListener() {

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


							TranslateAnimation translate1 = new TranslateAnimation(
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, second,
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, 0.67f);
							translate1.setDuration(200);
							translate1.setFillAfter(true);
							translate1
									.setInterpolator(new AccelerateDecelerateInterpolator());
							translate1
									.setAnimationListener(new AnimationListener() {

										@Override
										public void onAnimationStart(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationRepeat(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationEnd(
												Animation animation) {
											// TODO Auto-generated method stub
											// i++;
											// flag++;
											// start_anim();
											TranslateAnimation translate8 = new TranslateAnimation(
													Animation.RELATIVE_TO_SELF,
													0f,
													Animation.RELATIVE_TO_PARENT,
													third,
													Animation.RELATIVE_TO_SELF,
													0f,
													Animation.RELATIVE_TO_PARENT,
													0.6f);
											translate8.setDuration(200);
											translate8.setFillAfter(true);
											translate8
													.setInterpolator(new AccelerateDecelerateInterpolator());
											translate8
													.setAnimationListener(new AnimationListener() {

														@Override
														public void onAnimationStart(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub

														}

														@Override
														public void onAnimationRepeat(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub

														}

														@Override
														public void onAnimationEnd(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub
															TranslateAnimation translate9 = new TranslateAnimation(
																	Animation.RELATIVE_TO_SELF,
																	0f,
																	Animation.RELATIVE_TO_PARENT,
																	third,
																	Animation.RELATIVE_TO_SELF,
																	0f,
																	Animation.RELATIVE_TO_PARENT,
																	0.13f);
															translate9
																	.setDuration(200);
															translate9
																	.setFillAfter(true);
															translate9
																	.setInterpolator(new AccelerateDecelerateInterpolator());
															translate9
																	.setAnimationListener(new AnimationListener() {

																		@Override
																		public void onAnimationStart(
																				Animation animation) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub

																		}

																		@Override
																		public void onAnimationRepeat(
																				Animation animation) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub

																		}

																		@Override
																		public void onAnimationEnd(
																				Animation animation) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub
																			first = first + 0.02f;
																			second = second + 0.02f;
																			third = third + 0.02f;
																			i++;
																			flag++;
																			distribute_cards_V();

																		}
																	});
															player5[i]
																	.startAnimation(translate9);

															//
															//

														}
													});
											player4[i]
													.startAnimation(translate8);

										}
									});
							player3[i].startAnimation(translate1);

						}
					});
					player2[i].startAnimation(translate3);
				}
			});

			player1[i].startAnimation(translate2);
		}
		else if(flag==3)
		{
			 i=0;
		     flag=0;
			first = -0.41f;
			 third = 0.36f;
			 second = -0.03f;
				
			 cardDistributedFlag=true;

		}

	}
	
	public void distribute_cards_IV() 
	{
//		if(clear_cards_flag==1)
//		{
//			for(int i=0;i<3;i++)
//			{
//				player1[i].clearAnimation();
//				player2[i].clearAnimation();
//				player3[i].clearAnimation();
//				player4[i].clearAnimation();
//			}
//			clear_cards_flag=0;
//		}
	
		if (flag < 3) {

			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.13f);
			translate2.setDuration(200);
			translate2.setFillAfter(true);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("NewApi")
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub

					TranslateAnimation translate3 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, first,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, 0.6f);
					translate3.setDuration(200);
					translate3.setFillAfter(true);
					translate3
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// translate3.setInterpolator(new BounceInterpolator());
					translate3.setAnimationListener(new AnimationListener() {

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

							

							TranslateAnimation translate1 = new TranslateAnimation(
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, second,
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, 0.67f);
							translate1.setDuration(200);
							translate1.setFillAfter(true);
							translate1
									.setInterpolator(new AccelerateDecelerateInterpolator());
							translate1
									.setAnimationListener(new AnimationListener() {

										@Override
										public void onAnimationStart(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationRepeat(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationEnd(
												Animation animation) {
											// TODO Auto-generated method stub
											// i++;
											// flag++;
											// start_anim();
											TranslateAnimation translate8 = new TranslateAnimation(
													Animation.RELATIVE_TO_SELF,
													0f,
													Animation.RELATIVE_TO_PARENT,
													third,
													Animation.RELATIVE_TO_SELF,
													0f,
													Animation.RELATIVE_TO_PARENT,
													0.6f);
											translate8.setDuration(200);
											translate8.setFillAfter(true);
											translate8
													.setInterpolator(new AccelerateDecelerateInterpolator());
											translate8
													.setAnimationListener(new AnimationListener() {

														@Override
														public void onAnimationStart(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub

														}

														@Override
														public void onAnimationRepeat(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub

														}

														@Override
														public void onAnimationEnd(
																Animation animation) {
															// TODO
															// Auto-generated
															// method stub
															first = first + 0.02f;
															second = second + 0.02f;
															third = third + 0.02f;

															i++;
															flag++;
															distribute_cards_IV();

														}
													});
											player4[i]
													.startAnimation(translate8);

										}
									});
							player3[i].startAnimation(translate1);

						}
					});
					player2[i].startAnimation(translate3);
				}
			});

			player1[i].startAnimation(translate2);
		}
		else if(flag==3)
		{  i=0;
	     flag=0;
		first = -0.41f;
		 third = 0.36f;
		 second = -0.03f;

		 cardDistributedFlag=true;
			
		}

	}
	
	public void distribute_cards_III()
	{
//		if(clear_cards_flag==1)
//		{
//			for(int i=0;i<3;i++)
//			{
//				player1[i].clearAnimation();
//				player2[i].clearAnimation();
//				player3[i].clearAnimation();
//			}
//		}
	
		if (flag < 3) {

			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.6f);
			translate2.setDuration(200);
			translate2.setFillAfter(true);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("NewApi")
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub

//					final ViewGroup overlaying = (ViewGroup) player2[i]
//							.getParent().getParent().getParent();
//					overlaying.getOverlay().add(player2[i]);

					TranslateAnimation translate3 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, second,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, 0.67f);
					translate3.setDuration(200);
					translate3.setFillAfter(true);
					translate3
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// translate3.setInterpolator(new BounceInterpolator());
					translate3.setAnimationListener(new AnimationListener() {

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

							TranslateAnimation translate1 = new TranslateAnimation(
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, third,
									Animation.RELATIVE_TO_SELF, 0f,
									Animation.RELATIVE_TO_PARENT, 0.6f);
							translate1.setDuration(200);
							translate1.setFillAfter(true);
							translate1
									.setInterpolator(new AccelerateDecelerateInterpolator());
							translate1
									.setAnimationListener(new AnimationListener() {

										@Override
										public void onAnimationStart(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationRepeat(
												Animation animation) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onAnimationEnd(
												Animation animation) {
											// TODO Auto-generated method stub
											first = first + 0.02f;
											second = second + 0.02f;
											third = third + 0.02f;

											i++;
											flag++;
											distribute_cards_III();

										}
									});
							player3[i].startAnimation(translate1);

						}
					});
					player2[i].startAnimation(translate3);
				}
			});

			player1[i].startAnimation(translate2);
		}
		else if(flag==3)
		{  i=0;
	     flag=0;
		first = -0.41f;
		 third = 0.36f;
		 second = -0.03f;
			

			//	sideshow_button.setVisibility(View.GONE);
				show_button.setVisibility(View.GONE);
				sideshow_status=0;
				show_status=0;
				sideshow_decision_dialog_request_flag=0;
				
				cardDistributedFlag=true;
		//	show.setVisibility(View.VISIBLE);
			//clear_cards_flag=1;
		}

	}

	public void distribute_cards_II() 
	{

//		if(clear_cards_flag==1)
//		{
//			for(int i=0;i<3;i++)
//			{
//				player1[i].clearAnimation();
//				player2[i].clearAnimation();
//			}
//			clear_cards_flag=0;
////		}
//		player3Coin_layout.setVisibility(View.GONE);
//		player2Coin_layout.setVisibility(View.GONE);
		if (flag < 3) 
		{

			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, first,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.6f);
			translate2.setDuration(200);
			translate2.setFillAfter(true);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@SuppressLint("NewApi")
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
//					try{
//					ViewGroup overlaying = (ViewGroup) player2[i]	.getParent().getParent().getParent();
//					overlaying.getOverlay().add(player2[i]);
//					}
//					catch(Exception e){
//						e.printStackTrace();
//					}
					TranslateAnimation translate3 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, second,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_PARENT, 0.67f);
					translate3.setDuration(200);
					translate3.setFillAfter(true);
					translate3
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// translate3.setInterpolator(new BounceInterpolator());
					translate3.setAnimationListener(new AnimationListener() {

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

							first = first + 0.02f;
							second = second + 0.02f;
							third = third + 0.02f;

							i++;
							flag++;
							distribute_cards_II();

						}
					});
					player2[i].startAnimation(translate3);
				}
			});

			try{player1[i].startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(flag==3)
		{    
			
			i=0;
		     flag=0;
			first = -0.41f;
			 third = 0.36f;
			 second = -0.03f;
		//	clear_cards_flag=1;
			cardDistributedFlag=true;
			
		}

	}
	
	public void playerWinnerCoin(float dim1,float dim2) 
	{

			
			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, dim1,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, dim2);
			translate2.setDuration(1200);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {
					
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

			try{potCoin.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	
	public void player1chaal(boolean s) 
	{


			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.45f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.41f);
			translate2.setDuration(800);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			if(s)
			{
				translate2.setAnimationListener(new AnimationListener() {
					
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
						player1Coin_layout.setVisibility(View.GONE);
						potTextView.setText("\u20B9"+potValue);
					}
				});
			}
			
			
			player1Coin_layout.setVisibility(View.VISIBLE);
			try{player1Coin_layout.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	public void player2chaal(boolean s) 
	{


			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.45f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, -0.41f);
			translate2.setDuration(800);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			if(s)
			{
				translate2.setAnimationListener(new AnimationListener() {
					
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

						player2Coin_layout.setVisibility(View.GONE);
						potTextView.setText("\u20B9"+potValue);
					}
				});
			}
			
			
			player2Coin_layout.setVisibility(View.VISIBLE);
			try{player2Coin_layout.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	public void player3chaal(int s) 
	{

		final int chance=s;
			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, -0.46f);
			translate2.setDuration(800);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {
					
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
						
						player3Coin_layout.setVisibility(View.GONE);
						player2Coin_layout.setVisibility(View.GONE);
						potTextView.setText("\u20B9"+potValue);
						if(chance==2)
						{							
							distribute_cards_II();
						}
						
						
					}
				});
			
			player3Coin_layout.setVisibility(View.VISIBLE);
			try{player3Coin_layout.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	public void player4chaal(int s) 
	{

			final int chance=s;
			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, -0.36f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, -0.46f);
			translate2.setDuration(800);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {
					
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
						
						player1Coin_layout.setVisibility(View.GONE);
						player2Coin_layout.setVisibility(View.GONE);
						player3Coin_layout.setVisibility(View.GONE);
						player4Coin_layout.setVisibility(View.GONE);
						potTextView.setText("\u20B9"+potValue);
						if(chance==3)
						{					
							distribute_cards_III();
						}
						else if(chance==4)
						{					
							distribute_cards_IV();
						}
						
					}
				});

		
			player4Coin_layout.setVisibility(View.VISIBLE);
			try{player4Coin_layout.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	public void player5chaal(int s) 
	{

			final int chance=s;
			TranslateAnimation translate2 = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, -0.36f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_PARENT, 0.46f);
			translate2.setDuration(800);
			translate2.setFillAfter(false);
			translate2.setInterpolator(new AccelerateDecelerateInterpolator());
			translate2.setAnimationListener(new AnimationListener() {
					
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

						player1Coin_layout.setVisibility(View.GONE);
						player2Coin_layout.setVisibility(View.GONE);
						player3Coin_layout.setVisibility(View.GONE);
						player4Coin_layout.setVisibility(View.GONE);
						player5Coin_layout.setVisibility(View.GONE);
						potTextView.setText("\u20B9"+potValue);
						if(chance==5)
						{
							distribute_cards_V();
						}
						
					}
				});
			
		
			player5Coin_layout.setVisibility(View.VISIBLE);
			try{player5Coin_layout.startAnimation(translate2);}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	
	
	public void clearAnimation()
	{  int card_distribution_flag[]=Global.getuser_flag_cards_Details();
	   
	if(card_distribution_flag.length==5)
	   {
		   for(int i=0;i<3;i++)
			{
				player1[i].clearAnimation();
				player2[i].clearAnimation();
				player3[i].clearAnimation();
				player4[i].clearAnimation();
				player5[i].clearAnimation();
			//	see_player_real_cards[i].clearAnimation();
//				player1_real_cards[i].clearAnimation();
//				player2_real_cards[i].clearAnimation();		
				
			}  
		   
	   }
	 if(card_distribution_flag.length==4)
	   {
		   for(int i=0;i<3;i++)
			{
				player1[i].clearAnimation();
				player2[i].clearAnimation();
				player3[i].clearAnimation();
				player4[i].clearAnimation();
				//see_player_real_cards[i].clearAnimation();
//				player1_real_cards[i].clearAnimation();
//				player2_real_cards[i].clearAnimation();		
				
			}  
		   
	   }
	 
	if(card_distribution_flag.length==3)
	   {
		   for(int i=0;i<3;i++)
			{
				player1[i].clearAnimation();
				player2[i].clearAnimation();
				player3[i].clearAnimation();
				//see_player_real_cards[i].clearAnimation();
//				player1_real_cards[i].clearAnimation();
//				player2_real_cards[i].clearAnimation();		
				
			}  
		   
	   }
	
	   if(card_distribution_flag.length==2)
	   {
			for(int i=0;i<3;i++)
			{
				player1[i].clearAnimation();
				player2[i].clearAnimation();
				//see_player_real_cards[i].clearAnimation();
//				player1_real_cards[i].clearAnimation();
//				player2_real_cards[i].clearAnimation();		
				
			}  
	   }
		
	}

	public void two_player_logic(int []card_distribution_flag,int []show_cards_flag,String []userName,int []userChance)
	{
		if(card_distribution_flag.length>=2 && clear_cards_flag==0){
		    
		    int s=0;
		    int userChanceindex=0;
		    for(int i=0;i<card_distribution_flag.length;i++){
		    	if(card_distribution_flag[i]==2)
		    	{
		    		s++;
		    	}
		    	if(userChance[i]==0)
		    	{
		    		userChanceindex++;
		    	}
		    	
		    }

		    if(s==2 && Global.getuser_countdown_timer()==1)
		    {
		    	winningTextView.setVisibility(View.INVISIBLE);
		    	clear_cards_flag=1;		    	
		    	if(init_realcards_flag==1)
		    	{
		    		clearAnimation();
		    	}
		    	
		    	init_real_cards_two_players();
		    	
		    	Toast.makeText(getApplicationContext(),"distributing", Toast.LENGTH_SHORT).show();
		    	
			variable_for_pack_by_button_click=0;
		    	variable_for_pack=0;
		    	see_cards.setAlpha(255);
		    	seeButtonClickFlag=true;
		   	 last_two_player=new String[2];
				int user_flag_card[]=Global.getuser_flag_cards_Details();
				String user_name[]=Global.getall_Username_Details();
				for(int i=0;i<user_flag_card.length;i++)
				{
					if(user_flag_card[i]!=0)
					{
						last_two_player[i]=user_name[i];
					}
				}
				winner_decider= new winner_decider_class().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

				if(show_cards_flag[0]==0 && show_cards_flag[1]==0)
    			{
    			show_card_restrict_flag=0;
    			
    			}
				potValue=200;
				player2chaal(false);
		    	player3chaal(s);
				
				
		    }

		   
		   
		    
		}
	}
	
	public void three_player_logic(int []card_distribution_flag,int []show_cards_flag,String []userName,int []userChance)
	{
        if(card_distribution_flag.length>=3 && clear_cards_flag==0)
        {
   		    
   		    int s=0;
   		 int userChanceindex=0;
   		    for(int i=0;i<card_distribution_flag.length;i++){
   		    	if(card_distribution_flag[i]==3)
   		    	{
   		    		s++;
   		    	}
   		    	if(userChance[i]==0)
		    	{
		    		userChanceindex++;
		    	}
   		    }
   		    if(s==3 && Global.getuser_countdown_timer()==1)
   		    {
   		    	winningTextView.setVisibility(View.INVISIBLE);
   		    	clear_cards_flag=1;
   		    	
   		    	if(init_realcards_flag==1)
   		    	{
   		    		clearAnimation();
   		    	}
   		    	
   		    	init_real_cards_three_players();
   		    	
   		    	//Toast.makeText(getApplicationContext(),"distributing", Toast.LENGTH_SHORT).show();
   		    	see_cards.setAlpha(255);
   		    	seeButtonClickFlag=true;
   			variable_for_pack_by_button_click=0;
   		    	variable_for_pack=0;
   		      
				if((show_cards_flag[0]==0 && show_cards_flag[1]==0 && show_cards_flag[2]==0 ))
       			{
       			show_card_restrict_flag=0;                    
       			}
				Toast.makeText(getApplicationContext(),"distributing again", Toast.LENGTH_SHORT).show();
				show_sideshow_packed_username_flag=0;
				
				potValue=300;
				player2chaal(false);
				player3chaal(s);
				player4chaal(s);
				
   		    	
   		  
   		}
   		
       }
	}
	
	public void four_player_logic(int []card_distribution_flag,int []show_cards_flag,String []userName,int []userChance)
	{
        if(card_distribution_flag.length>=4 && clear_cards_flag==0)
        {
   		    
   		    int s=0;
   		    int userChanceindex=0;
   		    for(int i=0;i<card_distribution_flag.length;i++){
   		    	if(card_distribution_flag[i]==4)
   		    	{
   		    		s++;
   		    	}
   		    	if(userChance[i]==0)
		    	{
		    		userChanceindex++;
		    	}
   		    }
   		    if(s==4 && Global.getuser_countdown_timer()==1)
   		    {
   		    	winningTextView.setVisibility(View.INVISIBLE);
   		    	clear_cards_flag=1;
   		    	
   		    	if(init_realcards_flag==1)
   		    	{
   		    		clearAnimation();
   		    	}
   		    	
   		    	init_real_cards_four_players();
   		    	
   		    	//Toast.makeText(getApplicationContext(),"distributing", Toast.LENGTH_SHORT).show();
   		    	see_cards.setAlpha(255);
   			variable_for_pack_by_button_click=0;
   		    	variable_for_pack=0;
   		    	seeButtonClickFlag=true;
				if((show_cards_flag[0]==0 && show_cards_flag[1]==0 && show_cards_flag[2]==0 && show_cards_flag[3]==0))
       			{
       			show_card_restrict_flag=0;                    
       			}
				Toast.makeText(getApplicationContext(),"distributing again", Toast.LENGTH_SHORT).show();
				
				potValue=400;
				player1chaal(false);
				player2chaal(false);
				player3chaal(s);
				player4chaal(s);
   		    	
   		  
   		}

       }
	}
	
	public void five_player_logic(int []card_distribution_flag,int []show_cards_flag,String []userName,int []userChance)
	{
        if(card_distribution_flag.length>=5 && clear_cards_flag==0)
        {
   		    
   		    int s=0;
   		    int userChanceindex=0;
   		    for(int i=0;i<card_distribution_flag.length;i++){
   		    	if(card_distribution_flag[i]==5)
   		    	{
   		    		s++;
   		    	}
   		    	if(userChance[i]==0)
		    	{
		    		userChanceindex++;
		    	}
   		    	if(card_distribution_flag[i]==0)
		    	{
		    		userChanceindex--;
		    	}
   		    }
   		    if(s==5 && Global.getuser_countdown_timer()==1)
   		    {
   		    	winningTextView.setVisibility(View.INVISIBLE);
   		    	clear_cards_flag=1;
   		    	
   		    	if(init_realcards_flag==1)
   		    	{
   		    		clearAnimation();
   		    	}
   		    	
   		    	init_real_cards_five_players();
   		    	
   		    	//Toast.makeText(getApplicationContext(),"distributing", Toast.LENGTH_SHORT).show();
   		    	
   			variable_for_pack_by_button_click=0;
   		    	variable_for_pack=0;
   		    	see_cards.setAlpha(255);
   		    	seeButtonClickFlag=true;
				if((show_cards_flag[0]==0 && show_cards_flag[1]==0 && show_cards_flag[2]==0 && show_cards_flag[3]==0 && show_cards_flag[4]==0))
       			{
       			show_card_restrict_flag=0;                    
       			}
				Toast.makeText(getApplicationContext(),"distributing again", Toast.LENGTH_SHORT).show();
				
				potValue=500;
				player1chaal(false);
				player2chaal(false);
				player3chaal(s);
				player4chaal(s);
				player5chaal(s);
   		    	
   		  
   		}

       }
	}
	
	public void twoPlayerChance(int []card_distribution_flag,String userName[],int []userChance,int []packFlag)
	{
		 	
		if(card_distribution_flag.length>=2)
		{
			int chanceTimer=Global.getuser_countdown_timer();
			
			if( userChanceTimerflag==0 && cardDistributedFlag==true)
			{								
				int s=0;
			    int userChanceindex=0;
			    for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==2)
			    	{
			    		s++;
			    	}
			    	if(userChance[i]==0)
			    	{
			    		userChanceindex++;
			    	}
			    	if(card_distribution_flag[i]==0)
			    	{
			    		userChanceindex--;
			    	}
			    }
	    		
	    		if(s==2)
	    		{
	    		    if( Global.getuser_countdown_timer()==14 && userChanceindex==2)
				    {
				    		chanceAnimationFlag=true;
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;		    	
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		userListUpdateFlag=false;
				    		userChanceTimerflag=1;
				    		
				    		//vib.vibrate(400);	
		    	
				    }
				    else if( chanceTimer==14 && userChanceindex!=2 )
				    {
				    	if (packFlag[0]==1)
				    	{
				    		clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								//overlaying.getOverlay().remove(player2_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[1];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(0,0.46f);//player3image
				    			winningAnimation();
				    		}				    		
				    		//Toast.makeText(getApplicationContext(), userName[1], Toast.LENGTH_SHORT).show();
				    	}
				    	else if (packFlag[1]==1)
				    	{
				    		clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								//overlaying.getOverlay().remove(player2_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[0];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(0,0.46f);//player3image
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		
				    		
				    		//Toast.makeText(getApplicationContext(), userName[0], Toast.LENGTH_SHORT).show();
				    	}
				    	else if(userChance[0]==1 && packFlag[1]==0)
				    	{
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		userListUpdateFlag=false;
				    		//vib.vibrate(400);
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    
				    	}
				    	else if(userChance[1]==2 && packFlag[0]==0)
				    	{
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		userListUpdateFlag=false;
				    		//vib.vibrate(400);
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		
				    		
				    	}
				    	
				    }
	    		}
			
		}
		 if(chanceTimer==7 || chanceTimer==5)
		{
			userChanceTimerflag=0;
			userCoinAnimationRestrictFlag=true;
		}	
			
		}
	
	}
	
	public void threePlayerChance(int []card_distribution_flag,String userName[],int []userChance,int []packFlag)
	{
		 	
		if(card_distribution_flag.length>=3)
		{
			int chanceTimer=Global.getuser_countdown_timer();
			if( userChanceTimerflag==0 && cardDistributedFlag==true)
			{
				
				
				int s=0;
			    int userChanceindex=0;
			    for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==3)
			    	{
			    		s++;
			    	}
			    	if(userChance[i]==0)
			    	{
			    		userChanceindex++;
			    	}
			    	if(card_distribution_flag[i]==0)
			    	{
			    		userChanceindex--;
			    	}
			    }	
			    
			    if(s==3)
			    {
			    	if(Global.getuser_countdown_timer()==14 && userChanceindex==3)
				    {
				    	
				    	chanceAnimationFlag=true;
			    		Global.setChanceUserName(userName[0]);
			    		userChance_flag=1;		    	
			    		userListUpdateFlag=false;
			    		userChanceTimerflag=1;
			    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    }
				    else if( chanceTimer==14 && userChanceindex!=3)
				    {
				    	
				    	if(packFlag[0]==1 && packFlag[1]==1)
				    	{
				    		clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[2];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if((Global.getUserName().equals(userName[0])) || (Global.getUserName().equals(userName[1])))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Image
				    			winningAnimation();
				    		}
				    		//Toast.makeText(getApplicationContext(), userName[2], Toast.LENGTH_SHORT).show();				    		
				    	}
				    	else if(packFlag[0]==1 && packFlag[2]==1)
				    	{
				    		clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[1];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		//Toast.makeText(getApplicationContext(), userName[1], Toast.LENGTH_SHORT).show();				    		
				    	}
				    	else if(packFlag[1]==1 && packFlag[2]==1)
				    	{
				    		clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[0];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		//Toast.makeText(getApplicationContext(), userName[0], Toast.LENGTH_SHORT).show();				    		
				    	}
				    	else if((userChance[0]==1 && packFlag[1]==0) || (userChance[2]==3 && packFlag[0]==1) )
				    	{
				    	
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		//vib.vibrate(400);
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
				    	else if((userChance[1]==2 && packFlag[2]==0) || (userChance[0]==1 && packFlag[1]==1) )
				    	{
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[2]==3 && packFlag[0]==0) || (userChance[1]==2 && packFlag[2]==1))
				    	{
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		vib.vibrate(400);
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	
				    }
			    }
			    
		}
		 if(chanceTimer==5 || chanceTimer==7)
		{
			userChanceTimerflag=0;
			userCoinAnimationRestrictFlag=true;
		}	
			
		}
	
	}
	
	public void fourPlayerChance(int []card_distribution_flag,String userName[],int []userChance,int []packFlag)
	{
		 	
		if(card_distribution_flag.length>=4)
		{
			int chanceTimer=Global.getuser_countdown_timer();
			if( userChanceTimerflag==0 && cardDistributedFlag==true)
			{
				
				
				int s=0;
			    int userChanceindex=0;
			    for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==4)
			    	{
			    		s++;
			    	}
			    
			    	if(userChance[i]==0)
			    	{
			    		userChanceindex++;
			    	}
			    	if(card_distribution_flag[i]==0)
			    	{
			    		userChanceindex--;
			    	}
			    }	
			   
			    if(s==4)
			    {
			    	if(Global.getuser_countdown_timer()==14 && userChanceindex==4)
					{
					    	
					    	chanceAnimationFlag=true;
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;		    	
				    		userListUpdateFlag=false;
				    		userChanceTimerflag=1;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					}
			   		else if( chanceTimer==14 && userChanceindex!=4)
				    {
			   			
			   			if(packFlag[0]==1 && packFlag[1]==1 && packFlag[2]==1)
				    	{		
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[3];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[3], Toast.LENGTH_SHORT).show();
				    	}
			   			else if(packFlag[3]==1 && packFlag[1]==1 && packFlag[2]==1)
				    	{		
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[0];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[0], Toast.LENGTH_SHORT).show();
				    	}
			   			else if(packFlag[0]==1 && packFlag[3]==1 && packFlag[2]==1)
				    	{		
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[1];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[1], Toast.LENGTH_SHORT).show();
				    	}
			   			else if(packFlag[0]==1 && packFlag[3]==1 && packFlag[1]==1)
				    	{		
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[2];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		
			   				//Toast.makeText(getApplicationContext(), userName[2], Toast.LENGTH_SHORT).show();
				    	}
			   		    else if(userChance[0]==1 && packFlag[1]==1 && packFlag[2]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[3]);
				    		userChance_flag=4;
				    		vib.vibrate(400);
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[1]==2 && packFlag[2]==1 && packFlag[3]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		vib.vibrate(400);
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[2]==3 && packFlag[3]==1 && packFlag[0]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		//vib.vibrate(400);
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
			   			else if(userChance[3]==4 && packFlag[0]==1 && packFlag[1]==1)
				    	{
				    	
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   		    else if((userChance[0]==1 && packFlag[1]==0) || (userChance[3]==4 && packFlag[0]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		//vib.vibrate(400);
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
				    	else if((userChance[1]==2 && packFlag[2]==0)  || (userChance[0]==1 && packFlag[1]==1))
				    	{
				    	
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[2]==3 && packFlag[3]==0)  || (userChance[1]==2 && packFlag[2]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[3]);
				    		userChance_flag=4;
				    		vib.vibrate(400);
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[3]==4 && packFlag[0]==0)  || (userChance[2]==3 && packFlag[3]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		vib.vibrate(400);
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    }
			    }
		   	   
		}
		 if(chanceTimer==5 || chanceTimer==7)
		{
			userChanceTimerflag=0;
			userCoinAnimationRestrictFlag=true;
		}	
			
		}
	
	}

	public void fivePlayerChance(int []card_distribution_flag,String userName[],int []userChance,int []packFlag)
	{
		 	
		if(card_distribution_flag.length>=5)
		{
			int chanceTimer=Global.getuser_countdown_timer();
			if( userChanceTimerflag==0 && cardDistributedFlag==true)
			{
				
				
				int s=0;
			    int userChanceindex=0;
			    for(int i=0;i<card_distribution_flag.length;i++){
			    	if(card_distribution_flag[i]==5)
			    	{
			    		s++;
			    	}
			    	if(userChance[i]==0)
			    	{
			    		userChanceindex++;
			    	}
			    }	
			    
			    if(s==5)
			    {
			    	 if(Global.getuser_countdown_timer()==14 && userChanceindex==5)
					    {
					   			    	
					    	chanceAnimationFlag=true;
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;		    	
				    		userListUpdateFlag=false;
				    		userChanceTimerflag=1;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					    }
			   		 else if( chanceTimer==14 && userChanceindex!=5)
				    {
			   			if(packFlag[0]==1 && packFlag[1]==1 && packFlag[2]==1 && packFlag[3]==1)
			   			{
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					            oneH2.removeView(player5_real_cards[k]);
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[4];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(0.46f, -0.28f);//mImage1
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(0.46f, -0.28f);//mImage1
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(0.46f, -0.28f);//mImage1
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(0.46f, -0.28f);//mImage1
				    		}
				    		else if(Global.getUserName().equals(userName[4]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[4], Toast.LENGTH_SHORT).show();
			   			}
			   			else if(packFlag[4]==1 && packFlag[1]==1 && packFlag[2]==1 && packFlag[3]==1)
			   			{
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					            oneH2.removeView(player5_real_cards[k]);
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[0];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[4]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[0], Toast.LENGTH_SHORT).show();
			   			}
			   			else if(packFlag[4]==1 && packFlag[0]==1 && packFlag[2]==1 && packFlag[3]==1)
			   			{
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					            oneH2.removeView(player5_real_cards[k]);
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[1];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, -0.28f);//mImage3
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[4]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[1], Toast.LENGTH_SHORT).show();
			   			}	
			   			else if(packFlag[4]==1 && packFlag[1]==1 && packFlag[0]==1 && packFlag[3]==1)
			   			{
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					            oneH2.removeView(player5_real_cards[k]);
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[2];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(-0.41f, 0.45f);//mImage4
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[4]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[2], Toast.LENGTH_SHORT).show();
			   			}	
			   			else if(packFlag[4]==1 && packFlag[1]==1 && packFlag[2]==1 && packFlag[0]==1)
			   			{
			   				clear_cards_flag=0;
				    		cardDistributedFlag=false;
				    		mImage1.clearAnimation();
				    		mImage2.clearAnimation();
				    		mImage3.clearAnimation();
				    		mImage4.clearAnimation();
				    		player_3_image.clearAnimation();
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		clearAnimation();
				    		for(int k=0;k<3;k++)
							{
								oneH2.removeView(player1_real_cards[k]);
								oneH2.removeView(player2_real_cards[k]);
								oneH2.removeView(player3_real_cards[k]);
								oneH2.removeView(player4_real_cards[k]);
					            oneH2.removeView(player5_real_cards[k]);
							}
				    		game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		winnerPlayer=userName[3];
				    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    		dynamicRestrictFlag=true;
				    		if(Global.getUserName().equals(userName[0]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[1]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[2]))
				    		{
				    			playerWinnerCoin(0.46f, 0.35f);//mImage2
				    		}
				    		else if(Global.getUserName().equals(userName[3]))
				    		{
				    			playerWinnerCoin(-0.46f, 0);//player3Imag3
				    			winningAnimation();
				    		}
				    		else if(Global.getUserName().equals(userName[4]))
				    		{
				    			playerWinnerCoin(0.46f, -0.28f);//mImage1
				    		}
			   				//Toast.makeText(getApplicationContext(), userName[3], Toast.LENGTH_SHORT).show();
			   			}
			   			else if(userChance[0]==1 && packFlag[2]==1 && packFlag[3]==1 && packFlag[1]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[4]);
				    		userChance_flag=5;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[1]==2 && packFlag[3]==1 & packFlag[4]==1 && packFlag[2]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[2]==3 && packFlag[4]==1 && packFlag[0]==1 && packFlag[3]==1)
				    	{
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
			   			else if(userChance[3]==4 && packFlag[0]==1 && packFlag[1]==1 && packFlag[4]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[4]==5 && packFlag[1]==1 && packFlag[2]==1 && packFlag[0]==1)
				    	{
				    	
				    		Global.setChanceUserName(userName[3]);
				    		userChance_flag=4;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[0]==1 && packFlag[1]==1 && packFlag[2]==1)
				    	{
				    	
				    		Global.setChanceUserName(userName[3]);
				    		userChance_flag=4;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[1]==2 && packFlag[2]==1 && packFlag[3]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[4]);
				    		userChance_flag=5;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[2]==3 && packFlag[3]==1 & packFlag[4]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if(userChance[3]==4 && packFlag[4]==1 && packFlag[0]==1)
				    	{
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
			   			else if(userChance[4]==5 && packFlag[0]==1 && packFlag[1]==1)
				    	{
				    		
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
			   			else if((userChance[0]==1 && packFlag[1]==0)|| (userChance[4]==5 && packFlag[0]==1))
				    	{
				    		Global.setChanceUserName(userName[1]);
				    		userChance_flag=2;
				    		userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    
				    	}
				    	else if((userChance[1]==2 && packFlag[2]==0) || (userChance[0]==1 && packFlag[1]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[2]);
				    		userChance_flag=3;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[2]==3 && packFlag[3]==0) || (userChance[1]==2 && packFlag[2]==1))
				    	{
				    	
				    		Global.setChanceUserName(userName[3]);
				    		userChance_flag=4;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[3]==4 && packFlag[4]==0) || (userChance[2]==3 && packFlag[3]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[4]);
				    		userChance_flag=5;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    	else if((userChance[4]==5 && packFlag[0]==0)|| (userChance[3]==4 && packFlag[4]==1))
				    	{
				    		
				    		Global.setChanceUserName(userName[0]);
				    		userChance_flag=1;
				    		userChanceTimerflag=1;
				    		pack.setVisibility(View.INVISIBLE);
			    			chaal.setVisibility(View.INVISIBLE);
			    			see_cards.setVisibility(View.INVISIBLE);
			    			show_button.setVisibility(View.INVISIBLE);
			    			userListUpdateFlag=false;
				    		chanceAnimationFlag=true;
				    		new user_chance().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				    	}
				    }
			    }
		   		
		}
		 if(chanceTimer==5 || chanceTimer==7)
		{
			userChanceTimerflag=0;
			userCoinAnimationRestrictFlag=true;
		}	
			
		}
	
	}

	public void test(View v)
	{
		if(v.getId()==R.id.test)
		{
			winningAnimation();
		}
		
	}
	
	public void winningAnimation()
	{
		
		winningTextView.setVisibility(View.VISIBLE);
		ScaleAnimation winTranslate1 = new ScaleAnimation(0f, 4f, 0f, 4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		winTranslate1.setDuration(1500);
		winTranslate1.setFillAfter(false);
		winTranslate1.setInterpolator(new AccelerateDecelerateInterpolator());	
		winTranslate1.setAnimationListener(new AnimationListener() {
			
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
		winningTextView.startAnimation(winTranslate1);
		
	}
	
	public void winner_decider_func(int []card_distribution_flag,int []user_flag_card,String []user_name)
	{
		int s1=0;
		int s2=0;
		int s3=0;
	    for(int i=0;i<card_distribution_flag.length;i++){
	    	if(card_distribution_flag[i]==3)
	    	{
	    		s1++;
	    	}
	    	else if(card_distribution_flag[i]==4){
	    		s2++;
	    	}
	    	else if(card_distribution_flag[i]==5){
	    		s3++;
	    	}
	    }
	    
	    if(s1==2 && two_player_left_flag==0)
	    {
	    	two_player_left_flag=1;
	    	//show_button.setVisibility(View.VISIBLE);
	    	//Toast.makeText(getApplicationContext(),"show button aa gaya", Toast.LENGTH_SHORT).show();
	    	last_two_player=new String[2];
			int variable_for_last_two_player_index=0;
			for(int i=0;i<user_flag_card.length;i++)
			{
				if(user_flag_card[i]==3)
				{
					last_two_player[variable_for_last_two_player_index]=user_name[i];
					variable_for_last_two_player_index++;
				}
			}
			winner_decider= new winner_decider_class().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		
	    }
	    else if(s2==2 && two_player_left_flag==0)
	    {
	    	two_player_left_flag=1;
	    	//show_button.setVisibility(View.VISIBLE);
	    	//Toast.makeText(getApplicationContext(),"show button aa gaya", Toast.LENGTH_SHORT).show();
	    	last_two_player=new String[2];
			int variable_for_last_two_player_index=0;
			for(int i=0;i<user_flag_card.length;i++)
			{
				if(user_flag_card[i]==4)
				{
					last_two_player[variable_for_last_two_player_index]=user_name[i];
					variable_for_last_two_player_index++;
				}
			}
			winner_decider= new winner_decider_class().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		
	    }
	    
	    else if(s3==2 && two_player_left_flag==0)
	    {
	    	two_player_left_flag=1;
	    	//show_button.setVisibility(View.VISIBLE);
	    	//Toast.makeText(getApplicationContext(),"show button aa gaya", Toast.LENGTH_SHORT).show();
	    	last_two_player=new String[2];
			int variable_for_last_two_player_index=0;
			for(int i=0;i<user_flag_card.length;i++)
			{
				if(user_flag_card[i]==5)
				{
					last_two_player[variable_for_last_two_player_index]=user_name[i];
					variable_for_last_two_player_index++;
				}
			}
			winner_decider= new winner_decider_class().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		
	    }
	}
	
	public void pack_func(int show2, int []user_flag_card,String []user_name)
	{
		if(show2==1 && variable_for_pack_by_button_click==0)
	    {   
			clear_cards_flag=0;
	    	if(variable_for_pack==0)
	    	{
	    		pack_service_flag=1;
	    		
	    	 all_user_pack_details= new user_pack_button_clicked().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	    	 
	    		}
	        variable_for_pack_by_button_click=1;
	        String winner_user="";
	        for(int i=0;i<user_flag_card.length;i++)
				{
					if(user_flag_card[i]==3 || user_flag_card[i]==4 || user_flag_card[i]==5)
					{
						winner_user=user_name[i];
					}
				}
	    	Toast.makeText(getApplicationContext(),winner_user, Toast.LENGTH_LONG).show();
	    	//clearAnimation();
	    }
	}
	
	public void show_func(int show2, int []show_cards_flag)
	{
		if(show2==2 && show_card_restrict_flag==0)
		{   
//		
    		if((show_cards_flag[0]==1 && show_cards_flag[1]==1) && (flag_for_two_player_show==1 ) )
    		{
    			
    			show_card_restrict_flag++;
    			show_cards();		
    			if(Global.getUserName().equals(Global.getWinnerUsername()))
    			{
    				winningAnimation();
    			}
    			else
    				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
    			cardDistributedFlag=false;
	    		mImage1.clearAnimation();
	    		mImage2.clearAnimation();
	    		mImage3.clearAnimation();
	    		mImage4.clearAnimation();
	    		player_3_image.clearAnimation();
	    		pack.setVisibility(View.INVISIBLE);
    			chaal.setVisibility(View.INVISIBLE);
    			see_cards.setVisibility(View.INVISIBLE);
    			show_button.setVisibility(View.INVISIBLE);
    			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    			winnerPlayer=Global.getWinnerUsername();
	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    			dynamicRestrictFlag=true;
    			two_player_left_flag=0;
    			clear_cards_flag=0;
    			second_show_y = 0.67f;
    			first_show_y = 0.6f;
    		}
    		else if(flag_for_three_player_show==1)
    		{
    			if(show_cards_flag[0]==1 && show_cards_flag[1]==1)
        		{
    				String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
            			//third_show = 0.36f;
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{   
            				player2_real_cards[i]=player3_real_cards[i];
            			}
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_three_player_show=0;
        		}
        		else if(show_cards_flag[0]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{   player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player3_real_cards[i];
            			}        	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{   
            				player2_real_cards[i]=player3_real_cards[i];
            			}
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;          		
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_three_player_show=0;
        		}
        		else if(show_cards_flag[1]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{  
            				player2_real_cards[i]=player3_real_cards[i];
            			}        	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{   player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player3_real_cards[i];
            			}
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
            			second_show = 0.36f;
            			for(int i=0;i<3;i++)
            			{   player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player3_real_cards[i];
            			}      
//            			first_show = -0.41f;
//            			second_show = -0.03f;
//            			third_show = 0.36f;
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show(); 
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_three_player_show=0;
        		}    			
    		
    		
    		}
    				
    		
    		else if(flag_for_four_player_show==1)
    		{
    			if(show_cards_flag[0]==1 && show_cards_flag[1]==1)
        		{
    				String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.03f;
            			
            			//third_show = 0.36f;
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.03f;
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player2_real_cards[i]=player3_real_cards[i];
            			}
    				}
// new show funtion
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.41f;
        			second_show_y=0.6f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player3_real_cards[i];
        			}
    				}
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0;
        		}
        		else if(show_cards_flag[1]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player3_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
            			second_show_y = 0.67f;
            			first_show_y = 0.6f;
            			for(int i=0;i<3;i++)
            			{   //player1_real_cards[i]=player2_real_cards[i];
            			    player1_real_cards[i]=player3_real_cards[i];
            			}
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
            			second_show_y = 0.67f;
            			first_show_y = 0.6f;
            			for(int i=0;i<3;i++)
            			{   player1_real_cards[i]=player3_real_cards[i];
            				
            			}    
//            			first_show = -0.41f;
//            			second_show = -0.03f;
//            			third_show = 0.36f;
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0;
        		}    			
        		else if(show_cards_flag[3]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        		
        			}
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0;
        		}    			
    		
        		else if(show_cards_flag[3]==1 && show_cards_flag[0]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				//player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			
        			
    				}
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0; 
        		}   

        		else if(show_cards_flag[3]==1 && show_cards_flag[1]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];	
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				
        			}
        			
    				}
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0;
        		}
        		else if(show_cards_flag[2]==1 && show_cards_flag[0]==1)
        		{
        			String users[]=Global.getall_Username_Details();
        			if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = -0.03f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				
            				player2_real_cards[i]=player3_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.03f;
        			second_show_y=0.67f;
        			
        			
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player4_real_cards[i];
        				
        			}
        			
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_four_player_show=0;
        		}
    			
    		
    		}
    		//for five players
    		
    		else if(flag_for_five_player_show==1)
    		{
    			if(show_cards_flag[0]==1 && show_cards_flag[1]==1)
        		{
    				String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = -0.03f;
            			second_show_y=0.67f;
            			
            			//third_show = 0.36f;
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = -0.03f;
            			second_show_y=0.67f;
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player2_real_cards[i]=player3_real_cards[i];
            			}
    				}

    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.41f;
        			second_show_y=0.6f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player3_real_cards[i];
        			}
    				}
    				// new show funtion
      				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.41f;
        			second_show_y=0.6f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player3_real_cards[i];
        			}
    				}
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
        		else if(show_cards_flag[1]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player3_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
            			second_show_y = 0.67f;
            			first_show_y = 0.6f;
            			for(int i=0;i<3;i++)
            			{   //player1_real_cards[i]=player2_real_cards[i];
            			    player1_real_cards[i]=player3_real_cards[i];
            			}
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
            			second_show = -0.03f;
            			second_show_y = 0.67f;
            			first_show_y = 0.6f;
            			for(int i=0;i<3;i++)
            			{   player1_real_cards[i]=player3_real_cards[i];
            				
            			}    
//            			first_show = -0.41f;
//            			second_show = -0.03f;
//            			third_show = 0.36f;
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}    			
        		else if(show_cards_flag[3]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        		
        			}
    				}
    				
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = 0.36f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player4_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}
    				}
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}    			
    		
        		else if(show_cards_flag[3]==1 && show_cards_flag[0]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				//player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			
        			
    				}
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				//player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}
    				}
    				
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0; 
        		}   

        		else if(show_cards_flag[3]==1 && show_cards_flag[1]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player4_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        				
        			}
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				
        			}
        			
    				}
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = -0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}
        			
    				}
    				
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
        		else if(show_cards_flag[2]==1 && show_cards_flag[0]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = -0.03f;
            			second_show_y=0.67f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = -0.41f;
            			second_show_y=0.6f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				
            				player2_real_cards[i]=player3_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.03f;
        			second_show_y=0.67f;
        			
        			
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player4_real_cards[i];
        				
        			}
        			
    				}
    				
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = 0.36f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player2_real_cards[i]=player4_real_cards[i];
        				
        			}
        			
    				}
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
          		else if(show_cards_flag[4]==1 && show_cards_flag[1]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player2_real_cards[i]=player5_real_cards[i];
            				
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}  
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}
    				}
    				
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        				
        			}
        			
    				}
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
    			
          		else if(show_cards_flag[4]==1 && show_cards_flag[0]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            				
            			}       	
    				}
    				else if(users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.13f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				//player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            			}  
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
//        				player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}  
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{first_show = 0.36f;
					first_show_y= 0.6f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
        				//player1_real_cards[i]=player3_real_cards[i];
        				player2_real_cards[i]=player5_real_cards[i];
        			}
    				}
    				
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.41f;
					first_show_y= 0.13f;
        			second_show = -0.03f;
        			second_show_y=0.6f;
        			
        			
    				}
    			   
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
        		else if(show_cards_flag[4]==1 && show_cards_flag[3]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()) || users[1].equals(Global.getUserName()) || users[2].equals(Global.getUserName()))
    				{
    					first_show = 0.36f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player4_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            				
            			}       	
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            			}  
    				}
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.67f;
        			second_show = 0.36f;
        			second_show_y=0.13f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}  
    				}
    				
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}

        		else if(show_cards_flag[4]==1 && show_cards_flag[2]==1)
        		{
        			String users[]=Global.getall_Username_Details();
    				if(users[0].equals(Global.getUserName()) || users[1].equals(Global.getUserName()))
    				{
    					first_show = -0.41f;
    					first_show_y= 0.6f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            				
            			}       	
    				}
    				else if(users[2].equals(Global.getUserName()))
    				{
    					first_show = -0.03f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player2_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            			}  
    				}
    				else if(users[3].equals(Global.getUserName()))
    				{
    					first_show = 0.36f;
    					first_show_y= 0.67f;
            			second_show = 0.36f;
            			second_show_y=0.13f;
            			
            			for(int i=0;i<3;i++)
            			{   
            				player1_real_cards[i]=player3_real_cards[i];
            				player2_real_cards[i]=player5_real_cards[i];
            			}  
    				}
    				else if(users[4].equals(Global.getUserName()))
    				{first_show = -0.03f;
					first_show_y= 0.67f;
        			second_show = 0.36f;
        			second_show_y=0.67f;
        			for(int i=0;i<3;i++)
        			{   
        				player1_real_cards[i]=player2_real_cards[i];
        				player2_real_cards[i]=player4_real_cards[i];
        			}  
    				}
    				
    				
    				show_card_restrict_flag++;
        			show_cards();		
        			if(Global.getUserName().equals(Global.getWinnerUsername()))
        			{
        				winningAnimation();
        			}
        			else
        				Toast.makeText(getApplicationContext(),Global.getWinnerUsername(), Toast.LENGTH_LONG).show();
        			cardDistributedFlag=false;
		    		mImage1.clearAnimation();
		    		mImage2.clearAnimation();
		    		mImage3.clearAnimation();
		    		mImage4.clearAnimation();
		    		player_3_image.clearAnimation();
		    		pack.setVisibility(View.INVISIBLE);
	    			chaal.setVisibility(View.INVISIBLE);
	    			see_cards.setVisibility(View.INVISIBLE);
	    			show_button.setVisibility(View.INVISIBLE);
        			game_reset= new game_reset().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			winnerPlayer=Global.getWinnerUsername();
    	    		winnerAmount= new winnerAmount().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        			dynamicRestrictFlag=true;
        			two_player_left_flag=0;
        			clear_cards_flag=0;
        			second_show_y = 0.67f;
        			first_show_y = 0.6f;
        			flag_for_five_player_show=0;
        		}
 		
    		}
 		
    		
		}
	}
	
	public int check_show_pack_status(int show2,int []card_distribution_flag)
	{
		   
	      for(int i=0;i<card_distribution_flag.length;i++)
	      {
	            if(card_distribution_flag[i]==2)
	            {
	                 show2++;
	                 flag_for_two_player_show=1;
	            }											                       		    	
	            if(card_distribution_flag[i]==3)
	             {
	                 show2++;
	                 flag_for_three_player_show=1;
	             }
	            if(card_distribution_flag[i]==4)
	             {
	                 show2++;
	                 flag_for_four_player_show=1;
	             }
	            if(card_distribution_flag[i]==5)
	             {
	                 show2++;
	                 flag_for_five_player_show=1;
	             }
	      }
	      
	      return show2;
	        
	}
	
	public void display_show_sideshow_button_func(int []card_distribution_flag,int []see_sideshow_flag)
	{
		int sideshow_flag=0,show_flag=0;
		int x=card_distribution_flag.length;
		int y=see_sideshow_flag.length;
		if(card_distribution_flag.length==3 && see_sideshow_flag.length==3)
		{
			for(int i=0;i<see_sideshow_flag.length;i++)
			{
				if(card_distribution_flag[i]==3 && see_sideshow_flag[i]==1 && sideshow_status==0)
				{
					sideshow_flag++;
				}
				if(card_distribution_flag[i]==3 && show_status==0)
				{
					show_flag++;
				}
			}
		}
		if(sideshow_flag==3)
		{
			//sideshow_button.setVisibility(View.VISIBLE);
			sideshow_flag=0;sideshow_status=1;
		}
		else if(show_flag==2)
		{
			//sideshow_button.setVisibility(View.GONE);
			//show_button.setVisibility(View.VISIBLE);
			show_flag=0;show_status=1;
		}
	}
	
	public void display_sideshow_decision_dialog_func(int []see_sideshow_flag,String []user_name)
	{
		int show_dialog_flag=0;
		if(see_sideshow_flag.length==3 && sideshow_decision_dialog_request_flag==0 )
		{
			for(int i=0;i<see_sideshow_flag.length;i++)
			{
				if(see_sideshow_flag[i]==3 && user_name[i].equals(Global.getUserName()))
				{
					show_dialog_flag=1;		
				}
				if(see_sideshow_flag[i]==2)
				{
					username_that_requested_sideshow=user_name[i];
				}
			}
			if(show_dialog_flag==1)
			{
				last_two_player=new String[2];
				last_two_player[0]=Global.getUserName();
				last_two_player[1]=username_that_requested_sideshow;
				winner_decider= new winner_decider_class().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				LayoutInflater layoutinflater = LayoutInflater.from(PublicTable.this);
				View dialogview = layoutinflater.inflate(R.layout.sideshow_decision_dialog,null);
				sideshow_decision_dialog_init(dialogview, screenWidthDp, screenHeightDp);
				sideshow_decision_dialog_request_flag=1;show_dialog_flag=0;
			}
			
		}
	}
	
	public void set_timer(int []card_distribution_flag)
	{
		if(card_distribution_flag.length==1)
		{
			String s=Integer.toString(Global.getuser_countdown_timer());
			countdown_timer.setText(s);
		}
		if(card_distribution_flag.length==2)
		{
			String s=Integer.toString(Global.getuser_countdown_timer());
			countdown_timer.setText(s);
		}
		if(card_distribution_flag.length==3)
		{
			String s=Integer.toString(Global.getuser_countdown_timer());
			countdown_timer.setText(s);
		}
		if(card_distribution_flag.length==4)
		{
			String s=Integer.toString(Global.getuser_countdown_timer());
			countdown_timer.setText(s);
		}
		if(card_distribution_flag.length==5)
		{
			String s=Integer.toString(Global.getuser_countdown_timer());
			countdown_timer.setText(s);
		}
	}
	
	private void choose_table_dialog_init(View dialogview, int screenWidthDp,int screenHeightDp) {
		// TODO Auto-generated method stub
		/* To display alert dialog box on onclick button starts here */

		AlertDialog.Builder builder = new AlertDialog.Builder(
				PublicTable.this);

		builder.setView(dialogview);
		final AlertDialog alert = builder.create();
		alert.setCancelable(true);
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		alert.show();
		/* To display alert dialog box on onclick button starts here */
		/* set width and height of dialogbox starts here */
		int screenWidthDpDialog = (int) (screenWidthDp * 0.80);
		int screenHeightDpDialog = (int) (screenHeightDp * 0.80);
		alert.getWindow().setLayout(screenWidthDpDialog, screenHeightDpDialog);
    	alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		LinearLayout choose_table_Layout = (LinearLayout) alert.getWindow().findViewById(R.id.choose_table_layout);
		choose_table_Layout.setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
		choose_table_Layout.setBackgroundColor(Color.GRAY);
		

    	int choooseTableTitle_layout_width=(int)(screenWidthDpDialog);
    	int choooseTableTitle_layout_height=(int)(screenHeightDpDialog*0.20);
    	LinearLayout choooseTableTitle_layout=new LinearLayout(getApplicationContext());
    	choooseTableTitle_layout.setLayoutParams(new LayoutParams(choooseTableTitle_layout_width, choooseTableTitle_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	choooseTableTitle_layout.setOrientation(LinearLayout.HORIZONTAL);
    	choooseTableTitle_layout.setGravity(Gravity.CENTER);
    	choooseTableTitle_layout.setBackgroundColor(Color.BLACK);
    	choose_table_Layout.addView(choooseTableTitle_layout);
    	
    	TextView chooseTableTitle=new TextView(getApplicationContext());
    	chooseTableTitle.setText("Choose any table OR Create one of your own"); 
    	chooseTableTitle.setTextSize(25);
    	//chooseTableTitle.setTypeface(null,Typeface.BOLD);
    	chooseTableTitle.setTextColor(Color.YELLOW);
    	choooseTableTitle_layout.addView(chooseTableTitle);
    	//howtoplayTitle.setBackgroundColor(Color.GREEN);
    	
    	int chooseOrCreateTable_layout_width=(int)(screenWidthDpDialog);
    	int chooseOrCreateTable_layout_height=(int)(screenHeightDpDialog*0.80);
    	LinearLayout chooseOrCreateTable_layout=new LinearLayout(getApplicationContext());
    	chooseOrCreateTable_layout.setLayoutParams(new LayoutParams(chooseOrCreateTable_layout_width, chooseOrCreateTable_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	chooseOrCreateTable_layout.setOrientation(LinearLayout.HORIZONTAL);
    	chooseOrCreateTable_layout.setGravity(Gravity.CENTER);
    	//chooseOrCreateTable_layout.setBackgroundColor(Color.GREEN);
    	choose_table_Layout.addView(chooseOrCreateTable_layout);
    	
    	int chooseTableListview_layout_width=(int)(chooseOrCreateTable_layout_width*0.40);
    	int chooseTableListview_layout_height=(int)(chooseOrCreateTable_layout_height);
    	LinearLayout chooseTableListview_layout=new LinearLayout(getApplicationContext());
    	chooseTableListview_layout.setLayoutParams(new LayoutParams(chooseTableListview_layout_width, chooseTableListview_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	chooseTableListview_layout.setOrientation(LinearLayout.VERTICAL);
    	chooseTableListview_layout.setGravity(Gravity.CENTER);
    	chooseTableListview_layout.setBackgroundColor(Color.MAGENTA);
    	chooseOrCreateTable_layout.addView(chooseTableListview_layout);
    	
    	tablesList=new ListView(getApplicationContext()); 
    	tablesList.setLayoutParams(new LayoutParams(chooseTableListview_layout_width,chooseTableListview_layout_height));
    	ListAdapter1 adapter = new ListAdapter1(this, table_name_array);
		tablesList.setAdapter(adapter);
		chooseTableListview_layout.addView(tablesList);
		tablesList.setBackgroundColor(Color.BLACK);
		
		tablesList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
					table_name = table_name_array[position];
					flag_for_handler=1;
					alert.dismiss();

			}
		});
    	
    	int createTable_layout_width=(int)(chooseOrCreateTable_layout_width*0.60);
    	int createTable_layout_height=(int)(chooseOrCreateTable_layout_height);
    	RelativeLayout createTable_layout=new RelativeLayout(getApplicationContext());
    	createTable_layout.setLayoutParams(new LayoutParams(createTable_layout_width, createTable_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	//createTable_layout.setOrientation(LinearLayout.VERTICAL);
    	createTable_layout.setGravity(Gravity.CENTER);
    	//createTable_layout.setBackgroundColor(Color.RED);
    	chooseOrCreateTable_layout.addView(createTable_layout);
    	
    	int createTable_layout2_width=(int)(createTable_layout_width*0.80);
    	int createTable_layout2_height=(int)(createTable_layout_height*0.45);
    	LinearLayout createTable_layout2=new LinearLayout(getApplicationContext());
    	createTable_layout2.setLayoutParams(new RelativeLayout.LayoutParams(createTable_layout2_width, createTable_layout2_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	createTable_layout2.setOrientation(LinearLayout.VERTICAL);
    	createTable_layout2.setGravity(Gravity.CENTER);
    	//createTable_layout2.setBackgroundColor(Color.GREEN);
    	createTable_layout.addView(createTable_layout2);
    	
    	int createTableEdittext_layout_width=(int)(createTable_layout2_width);
    	int createTableEdittext_layout_height=(int)(createTable_layout2_height*0.50);
    	RelativeLayout createTableEdittext_layout=new RelativeLayout(getApplicationContext());
    	createTableEdittext_layout.setLayoutParams(new LayoutParams(createTableEdittext_layout_width, createTableEdittext_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	//createTable_layout.setOrientation(LinearLayout.VERTICAL);
    	createTableEdittext_layout.setGravity(Gravity.CENTER);
    	//createTableEdittext_layout.setBackgroundColor(Color.YELLOW);
    	createTable_layout2.addView(createTableEdittext_layout);
    	

		new_table_name_edittext.setLayoutParams(new RelativeLayout.LayoutParams((int) (createTableEdittext_layout_width * 0.80), createTableEdittext_layout_height));
    	new_table_name_edittext.setHint("Enter Table Name");
    	new_table_name_edittext.setGravity(Gravity.CENTER);
		//new_table_name_edittext.setBackgroundColor(Color.BLACK);
    	createTableEdittext_layout.addView(new_table_name_edittext);
    	RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) new_table_name_edittext.getLayoutParams();
		param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		new_table_name_edittext.setLayoutParams(param1);
		alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		
      	int createButton_layout_width=(int)(createTable_layout2_width);
    	int createButton_layout_height=(int)(createTable_layout2_height*0.50);
    	RelativeLayout createButton_layout=new RelativeLayout(getApplicationContext());
    	createButton_layout.setLayoutParams(new LayoutParams(createButton_layout_width, createButton_layout_height));
    	//userProfileTitle_layout.setBackgroundColor(Color.BLUE);
    	//createTable_layout.setOrientation(LinearLayout.VERTICAL);
    	createButton_layout.setGravity(Gravity.CENTER);
    	//createButton_layout.setBackgroundColor(Color.BLUE);
    	createTable_layout2.addView(createButton_layout);
    	
    	createTable_button.setLayoutParams(new RelativeLayout.LayoutParams((int) (createButton_layout_width * 0.40),createButton_layout_height));
    	createTable_button.setText("Create");
    	//Go.setBackgroundColor(Color.CYAN);
    	createButton_layout.addView(createTable_button);
    	RelativeLayout.LayoutParams param2 = (RelativeLayout.LayoutParams)createTable_button.getLayoutParams();
		param2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		createTable_button.setLayoutParams(param2);
		createTable_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					table_name = new_table_name_edittext.getText().toString();
					flag_for_handler=1;
					alert.dismiss();
					
//					LayoutInflater layoutinflater = LayoutInflater.from(Table1Activity.this);
//					View dialogview = layoutinflater.inflate(R.layout.total_user_check,null);
//					total_players_present_init(dialogview, screenWidthDp,Table1Activity.this.screenHeightDp);

				
			}
		});
    	
    	
//		final TextView choose_table = new TextView(getApplicationContext());
//		choose_table.setId(1);
//		choose_table.setText("CHOOSE TABLE");
//		choose_table.setTextSize(30);
//		choose_table.setTextColor(Color.DKGRAY);
//		choose_table.setTypeface(null, Typeface.BOLD_ITALIC);
//		choose_table_relativeLayout.addView(choose_table);
//		RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) choose_table
//				.getLayoutParams();
//		param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//		choose_table.setLayoutParams(param1);
//
//		 ImageView default_table_button = new ImageView(getApplicationContext());
//		try{//Bitmap bm1 = BitmapFactory.decodeResource(getResources(),R.drawable.button_default);	
//		default_table_button.setImageResource(R.drawable.button_default);}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		choose_table_relativeLayout.addView(default_table_button);
//		RelativeLayout.LayoutParams param2 = (RelativeLayout.LayoutParams) default_table_button.getLayoutParams();
//		param2.addRule(RelativeLayout.START_OF, choose_table.getId());
//		param2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//		default_table_button.setLayoutParams(param2);
//
//		default_table_button.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				new all_users_details_default()
//						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//			}
//		});
//
//		final ImageView create_table_button = new ImageView(
//				getApplicationContext());
//		Bitmap bm2 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.button_choose);
//		create_table_button.setBackgroundDrawable(new BitmapDrawable(bm2));
//		choose_table_relativeLayout.addView(create_table_button);
//		RelativeLayout.LayoutParams param3 = (RelativeLayout.LayoutParams) create_table_button
//				.getLayoutParams();
//		param3.addRule(RelativeLayout.END_OF, choose_table.getId());
//		param3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//		create_table_button.setLayoutParams(param3);
//
//		create_table_button.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				// if(status_vacant_table==1)
//				// {
//				alert.dismiss();
//				drawerlayout.openDrawer(Gravity.END);
//
//				// }
//
//			}
//		});

	}

	private void sideshow_decision_dialog_init(View dialogview,int screenWidthDp,int screenHeightDp)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(PublicTable.this);

		builder.setView(dialogview);
		final AlertDialog alert = builder.create();
		alert.setCancelable(false);
		//alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		alert.show();
		/* To display alert dialog box on onclick button starts here */
		/* set width and height of dialogbox starts here */
		int screenWidthDpDialog = (int) (screenWidthDp * 0.60);
		int screenHeightDpDialog = (int) (screenHeightDp * 0.60 );
		alert.getWindow().setLayout(screenWidthDpDialog, screenHeightDpDialog);
		//alert.getWindow().findViewById(R.id.sideshow_decision_dialog_layout);
		
		RelativeLayout sideshow_decision_layout = (RelativeLayout) alert
				.getWindow().findViewById(R.id.sideshow_decision_dialog_layout);
		
		//alert.getWindow().findViewById(R.id.sideshow_decision_dialog_layout).setLayoutParams(new LayoutParams(screenWidthDpDialog, screenHeightDpDialog));
		
		
		final TextView sideshow_request_user = new TextView(getApplicationContext());
		//sideshow_request_user.setText(username_that_requested_sideshow+" has requested for sideshow");
		sideshow_request_user.setText(username_that_requested_sideshow+" has requested for sideshow");
		sideshow_request_user.setTextSize(15);
		sideshow_request_user.setTextColor(Color.GREEN);
		sideshow_request_user.setTypeface(null, Typeface.BOLD_ITALIC);
		sideshow_decision_layout.addView(sideshow_request_user);
		RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) sideshow_request_user
				.getLayoutParams();
		//param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		sideshow_request_user.setLayoutParams(param1);
		
		int decision_layout_width = (int) (screenWidthDpDialog * 0.60);
		int decision_layout_height = (int) (screenHeightDpDialog * 0.60);
		final LinearLayout decision_layout=new LinearLayout(getApplicationContext());
		decision_layout.setLayoutParams(new LayoutParams(decision_layout_width, decision_layout_height));
		//decision_layout.setBackgroundColor(Color.BLUE);
		decision_layout.setOrientation(LinearLayout.HORIZONTAL);
		sideshow_decision_layout.addView(decision_layout);
		RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) decision_layout.getLayoutParams();
		param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		decision_layout.setLayoutParams(param);
		
		final Button accept_button = new Button(getApplicationContext());
		accept_button.setText("Accept");
		accept_button.setTextSize(20);
		accept_button.setTextColor(Color.RED);
		//accept_button.setTypeface(null, Typeface.BOLD_ITALIC);
		decision_layout.addView(accept_button);
		accept_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String winner_user=Global.getWinnerUsername();
				if(winner_user.equals(Global.getUserName()))
				{
					sideshow_pack_user=username_that_requested_sideshow;
				}
				else
					sideshow_pack_user=Global.getUserName();
				
				pack_service_flag=2;
				sideshow_pack= new user_pack_button_clicked().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				alert.dismiss();
			}
		});
//		
//		int empty_layout_width = (int) (decision_layout_width * 0.40);
//		int empty_layout_height = (int) (decision_layout_height);
//		final LinearLayout empty_layout=new LinearLayout(getApplicationContext());
//		decision_layout.setLayoutParams(new LayoutParams(empty_layout_width, empty_layout_height));
//		// oneH.setBackgroundColor(Color.BLUE);
//		decision_layout.addView(empty_layout);
		
		final Button reject_button = new Button(getApplicationContext());
		reject_button.setText("Reject");
		reject_button.setTextSize(20);
		reject_button.setTextColor(Color.YELLOW);
		//accept_button.setTypeface(null, Typeface.BOLD_ITALIC);
		decision_layout.addView(reject_button);
		reject_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sideshow_decision_dialog_request_flag=0;
				sideshow_reset_values=new user_sideshow_reset_values().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				alert.dismiss();
			}
		});



	}
	
	public void show_sideshow_packed_username_func(int card_distribution_flag[],String user_name[],int see_sideshow_flag[])
	{
		String sideshow_packed_user = "";
		for(int i=0;i<card_distribution_flag.length;i++)
		{
			if(card_distribution_flag[i]==0 && see_sideshow_flag[i]!=1 && see_sideshow_flag[i]!=0 && show_sideshow_packed_username_flag==0 && card_distribution_flag.length==3)
			{
				sideshow_packed_user=user_name[i];
				show_sideshow_packed_username_flag=1;
				
				Toast.makeText(getApplicationContext(),sideshow_packed_user+" is packed", Toast.LENGTH_SHORT).show();
				break;
			}
		}

	}
		
	 @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {

			// TODO Auto-generated method stub
	
	
	        if (keyCode == KeyEvent.KEYCODE_BACK) {

	            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	               alertDialog.setTitle("Exit Alert");
	              

	               alertDialog.setMessage("Do you really want to exit the Game?");
	               alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int which) {
	                		 
	                	
//	                	 boolean mWorkerThread_status=mUserThread.isAlive();
//	         			if(mWorkerThread_status==true)
//	         	        {
//	         				try{
//	         					   myhandler_all_user_status.removeCallbacks(myupdateresults_all_user_status);
//	         		        	   mUserThread.quit();
//	         					}catch(Exception e){
//	         						
//	         					}
//	         	        }
	         			 delete_user=new usergamedelete().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);                	 
	                     finish();
	                   return;
	               } }); 
	               alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int which) {
	                     dialog.cancel();
	                   return;
	               }}); 
	                 alertDialog.show();

	            return true;
	        }
	  

			return super.onKeyDown(keyCode, event);
		}
	 
//		private void total_players_present_init(View dialogview,int screenWidthDp, int screenHeightDp) {
//			// TODO Auto-generated method stub
//			/*To display alert dialog box on onclick button starts here*/ 
//			AlertDialog.Builder builder = new AlertDialog.Builder(Table1Activity.this);
//
//			builder.setView(dialogview);
//			final AlertDialog alert = builder.create();
//			//alert.setCancelable(false);
//			//alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//			alert.show();
//			/* To display alert dialog box on onclick button starts here */
//			/* set width and height of dialogbox starts here */
//			int screenWidthDpDialog = (int) (screenWidthDp * 0.40);
//			int screenHeightDpDialog = (int) (screenHeightDp * 0.40);
//			alert.getWindow().setLayout(screenWidthDpDialog, screenHeightDpDialog);
//
//			RelativeLayout total_players_relativeLayout = (RelativeLayout) alert.getWindow().findViewById(R.id.total_players);
//			
//			final Button okay= new Button(getApplicationContext());
//			okay.setText("okay");
//			okay.setTextSize(30);
//			okay.setTextColor(Color.RED);
//			okay.setTypeface(null, Typeface.BOLD_ITALIC);
//			total_players_relativeLayout.addView(okay);
//			RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) okay.getLayoutParams();
//			param1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//			okay.setLayoutParams(param1);
//			okay.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v)
//				{
//					// TODO Auto-generated method stub
//					//task_list_go_click.cancel(true);
//					flag_for_handler=1;
////					 boolean mWorkerThread_status=mUserThread.isAlive();
////						if(mWorkerThread_status==false)
////				        {
////							try
////							{  
////								 mUserThread.start();
////								 flag_for_handler=1;
////					              mUserThread.onLooperPrepared();
////					              
////							}
////							catch(Exception e){e.printStackTrace();}
////				             
////				        }
//				}
//			});
//
//		}
		

		@Override
		protected void onStart() {
			super.onStart();
	        boolean mWorkerThread_status=mUserThread.isAlive();
				if(mWorkerThread_status==false)
		        {
					try
					{
						 mUserThread.start();
			              mUserThread.onLooperPrepared();
					}
					catch(Exception e){e.printStackTrace();}
		             
		        }
		        
		       
		}
	 
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
//
//			delete_user=new usergamedelete().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			restart_game_flag=1;
//			boolean mWorkerThread_status=mUserThread.isAlive();
//			if(mWorkerThread_status==true)
//	        {
//				try{
//					   myhandler_all_user_status.removeCallbacks(myupdateresults_all_user_status);
//		        	   mUserThread.quit();
//					}catch(Exception e){
//						
//					}
//	        }
			
		}

		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			delete_user=new usergamedelete().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			restart_game_flag=1;
			
//         boolean mWorkerThread_status=mUserThread.isAlive();
//			if(mWorkerThread_status==true)
//	        {
//				try{
//					   myhandler_all_user_status.removeCallbacks(myupdateresults_all_user_status);
//		        	   mUserThread.quit();
//					}catch(Exception e){
//						
//					}
//	        }
			
    	
		}
		
		@Override
		protected void onRestart() {
			// TODO Auto-generated method stub
			super.onRestart();
			
		}

		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onStop();
			delete_user.cancel(true);
			
//			try
//			{
//				show_click_button.cancel(true);
//				see_click_button.cancel(true);
//				sideshow_click_button.cancel(true);
//				sideshow_reset_values.cancel(true);
//				sideshow_pack.cancel(true);
//				winner_decider.cancel(true);
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
			
	        boolean mWorkerThread_status=mUserThread.isAlive();
				if(mWorkerThread_status==true)
		        {
					try{
						   myhandler_all_user_status.removeCallbacks(myupdateresults_all_user_status);
			        	   mUserThread.quit();
						}catch(Exception e){
							
						}
		        }
		}

		
		

		

		public class all_users_details_default extends AsyncTask<Void, Void, Void> {

			Service_Client_Interaction client = new Service_Client_Interaction();

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub

				try {
					client.all_users_table_Details_Default(Global.getUserName());

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(Global.getuser_countdown_timer()==12)
				{
					userListUpdateFlag=true;
				}
				
			}

		}
	
    public class usergamedelete extends AsyncTask<Void,Void,Void>
   		{
   			
   			Service_Client_Interaction client = new Service_Client_Interaction();

   			@Override
   			protected Void doInBackground(Void... params)
   			{
   				// TODO Auto-generated method stub
   				http://www.pokerpatti.com/jd/Service.svc/json/delete_user_details/shilpi
   				try {
   					client.user_game_delete_after_playing_method(Global.getUserName(),Global.getall_Username_Details().length);
   					

   				} catch (Exception e) {
   					e.printStackTrace();
   				}
   				return null;
   			}
   			
   		}
    
    public class user_pack_button_clicked extends AsyncTask<Void,Void,Void>
   		{
   			
   			Service_Client_Interaction client = new Service_Client_Interaction();
   		
   			@Override
   			protected Void doInBackground(Void... params)
   			{
   				// TODO Auto-generated method stub
   				String pack_username = "";
   				if(pack_service_flag==1)
   				{
   					pack_username=Global.getUserName();
   					pack_service_flag=0;
   				}
   				else if(pack_service_flag==2)
   				{
   					pack_username=sideshow_pack_user;
   					pack_service_flag=0;
   				}
   				try {
   					
   					client.user_button_packed_clicked(pack_username);
   					

   				} catch (Exception e) {
   					e.printStackTrace();
   				}
   				return null;
   			}
   			
   		}

    public class user_show_button_pressed extends AsyncTask<Void,Void,Void>

    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_show_button(Global.getUserName());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }

    public class game_reset extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.game_reset_function(Global.getUserName());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }
  
    public class winner_decider_class extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.winner_decider_service(last_two_player[0],last_two_player[1]);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }
    
    public class user_see_button_pressed extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_see_func(Global.getUserName());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }

    public class user_sideshow_button_pressed extends AsyncTask<Void,Void,Void>

    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_sideshow_func(Global.getUserName(),username_for_sideshow);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }

    public class user_sideshow_reset_values extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_sideshow_flag_reset_func(Global.getUserName(),username_that_requested_sideshow);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
    }
    public class user_chance extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.user_chance(Global.getChanceUserName(), userChance_flag);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
					userChanceDataFlag=true;
				
				
			}
			
    }
    public class user_chaal extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.userChaalUpdate(Global.getUserName(),32);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
					
				
				
			}
			
    }
    
    public class winnerAmount extends AsyncTask<Void,Void,Void>
    {
    	Service_Client_Interaction client = new Service_Client_Interaction();
   		
			@Override
			protected Void doInBackground(Void... params)
			{
				// TODO Auto-generated method stub
				
				try {
					client.addWinnerAmount(winnerPlayer, 30);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
					
				
				
			}
			
    }

    
}

// NavigationDrawer TableList Class
class ListAdapterPublicTable extends ArrayAdapter<String> {
	final Context context;
	final String[] titlearray;

	ListAdapterPublicTable(Context c, String[] titles) {
		super(c, R.layout.listtemplate, R.id.vacant_tables, titles);
		this.context = c;
		this.titlearray = titles;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = null;
		// if(position==0)
		// {
		row = inflater.inflate(R.layout.listtemplate, parent, false);
		TextView Mytextview = (TextView) row.findViewById(R.id.vacant_tables);
		Mytextview.setText(titlearray[position]);

		// else
		// {
		// row=inflater.inflate(R.layout.customdrawer, parent,false);
		// TextView Mytextview=(TextView) row.findViewById(R.id.textView1);
		// ToggleButton t=(ToggleButton)row.findViewById(R.id.toggle);
		// t.setOnClickListener(new View.OnClickListener() {
		// private final String[] values =
		// getContext().getResources().getStringArray(R.array.settings);
		//
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(getContext(), titlearray[position] + " checked",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		//
		// Mytextview.setText(titlearray[position]);
		// }

		return row;
	}
    
}
