package com.example.teenpattinew;





import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserPanelActivity extends Activity
{
	int screenWidthDp,screenHeightDp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/
   
		setContentView(R.layout.activity_user_panel);
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		screenWidthDp = size.x;
		screenHeightDp = size.y;
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		
		RelativeLayout userpanel_relative_layout=(RelativeLayout)findViewById(R.id.userpanel_linear_layout);
		userpanel_screen_init(userpanel_relative_layout,screenWidthDp,screenHeightDp);

	}

	public void userpanel_screen_init(RelativeLayout userpanel_relative_layout, int screenWidth,int screenHeight) 
	{
		
		
		int userpanel_title_height=(int)(screenHeight * 0.17);
		int userpanel_title_width=(int)(screenWidth);
		LinearLayout userpanel_title_layout=new LinearLayout(getApplicationContext());
		userpanel_title_layout.setLayoutParams(new LayoutParams(userpanel_title_width, userpanel_title_height));
		userpanel_relative_layout.addView(userpanel_title_layout);
		//userpanel_title_layout.setBackgroundColor(Color.CYAN);
		RelativeLayout.LayoutParams param1 = (RelativeLayout.LayoutParams) userpanel_title_layout.getLayoutParams();
		param1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		userpanel_title_layout.setLayoutParams(param1);
		
		final TextView userpanel_title_textview=new TextView(getApplicationContext());
		userpanel_title_textview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		userpanel_title_textview.setText("USERPANEL");
		userpanel_title_textview.setTextSize(35);
		userpanel_title_textview.setTypeface(Typeface.SERIF);
		userpanel_title_textview.setTypeface(userpanel_title_textview.getTypeface(), Typeface.BOLD);
		//userpanel_title_textview.setShadowLayer(20, 8,8, Color.BLACK);
		userpanel_title_textview.setTextColor(Color.MAGENTA);
		//username_textview.setBackgroundColor(Color.WHITE);
		userpanel_title_textview.setGravity(Gravity.CENTER);
		userpanel_title_layout.addView(userpanel_title_textview);
		
	
		int mainLW=(int)(screenWidth * 0.80);
		int mainLH=(int)(screenHeight * 0.65);
		final LinearLayout mainL=new LinearLayout(getApplicationContext());
		mainL.setLayoutParams(new LayoutParams(mainLW, mainLH));
		mainL.setOrientation(LinearLayout.VERTICAL);
		userpanel_relative_layout.addView(mainL);
		//mainL.setBackgroundColor(Color.BLUE);
		RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) mainL.getLayoutParams();
		param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		mainL.setLayoutParams(param);
		
		int username_layout_height=(int)(mainLH * 0.30);
		final LinearLayout username_layout=new LinearLayout(getApplicationContext());
		username_layout.setLayoutParams(new LayoutParams(mainLW, username_layout_height));
		username_layout.setOrientation(LinearLayout.HORIZONTAL);
		mainL.addView(username_layout);
		
		int username_textview_layout_width = (int) (mainLW * 0.50);
		int username_textview_layout_height=(int)(username_layout_height * 0.80);
		final LinearLayout username_textview_layout = new LinearLayout(getApplicationContext());
		username_textview_layout.setLayoutParams(new LayoutParams(username_textview_layout_width, username_textview_layout_height));
		username_textview_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//username_textview_layout.setBackgroundColor(Color.YELLOW);
		username_textview_layout.setGravity(Gravity.CENTER);
		username_layout.addView(username_textview_layout);
		
		final TextView username_textview=new TextView(getApplicationContext());
		username_textview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		username_textview.setText("USERNAME");
		username_textview.setTextSize(25);
		username_textview.setTypeface(Typeface.SERIF);
		username_textview.setTypeface(username_textview.getTypeface(), Typeface.BOLD);
		//username_textview.setShadowLayer(20, 8,8, Color.BLACK);
		username_textview.setTextColor(Color.BLACK);
		//username_textview.setBackgroundColor(Color.WHITE);
		username_textview.setGravity(Gravity.CENTER);
		username_textview_layout.addView(username_textview);
		
		int username_edittext_layout_width = (int) (mainLW * 0.50);
		int username_edittext_layout_height=(int)(username_layout_height * 0.80);
		final LinearLayout username_edittext_layout = new LinearLayout(getApplicationContext());
		username_edittext_layout.setLayoutParams(new LayoutParams(username_edittext_layout_width, username_edittext_layout_height));
		username_edittext_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//username_edittext_layout.setBackgroundColor(Color.RED);
		username_edittext_layout.setGravity(Gravity.CENTER);
		username_layout.addView(username_edittext_layout);
		
		
		final EditText username_edittext=new EditText(getApplicationContext());
		username_edittext.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		//username_edittext.setHint("Username");
		//username_edittext.setTextSize(25);
		username_edittext.setTextColor(Color.BLACK);
		//username_edittext.setBackgroundColor(Color.BLACK);
		username_edittext.setBackgroundResource(R.drawable.editbox);
		username_edittext.setGravity(Gravity.CENTER);
		username_edittext_layout.addView(username_edittext);
		
		
		int password_layout_height = (int) (mainLH * 0.30);
		final LinearLayout password_layout = new LinearLayout(getApplicationContext());
		password_layout.setLayoutParams(new LayoutParams(mainLW, password_layout_height));
		password_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//password_layout.setBackgroundColor(Color.MAGENTA);
		password_layout.setGravity(Gravity.CENTER);
		mainL.addView(password_layout);
		
		int password_textview_layout_width = (int) (mainLW * 0.50);
		int password_textview_layout_height = (int) (password_layout_height * 0.80);
		final LinearLayout password_textview_layout = new LinearLayout(getApplicationContext());
		password_textview_layout.setLayoutParams(new LayoutParams(password_textview_layout_width, password_textview_layout_height));
		password_textview_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//username_textview_layout.setBackgroundColor(Color.YELLOW);
		password_textview_layout.setGravity(Gravity.CENTER);
		password_layout.addView(password_textview_layout);
		
		final TextView password_textview=new TextView(getApplicationContext());
		password_textview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		password_textview.setText("PASSWORD");
		password_textview.setTextSize(25);
		password_textview.setTypeface(Typeface.SERIF);
		password_textview.setTypeface(password_textview.getTypeface(),Typeface.BOLD);
		password_textview.setTextColor(Color.BLACK);
		//password_textview.setShadowLayer(20, 8,8, Color.BLACK);
		//password_textview.setBackgroundColor(Color.WHITE);
		password_textview.setGravity(Gravity.CENTER);
		password_textview_layout.addView(password_textview);
		
		int password_edittext_layout_width = (int) (mainLW * 0.50);
		int password_edittext_layout_height = (int) (password_layout_height * 0.80);
		final LinearLayout password_edittext_layout = new LinearLayout(getApplicationContext());
		password_edittext_layout.setLayoutParams(new LayoutParams(password_edittext_layout_width, password_edittext_layout_height));
		password_edittext_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//username_edittext_layout.setBackgroundColor(Color.RED);
		password_edittext_layout.setGravity(Gravity.CENTER);
		password_layout.addView(password_edittext_layout);
		
		final EditText password_edittext=new EditText(getApplicationContext());
		password_edittext.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		//password_edittext.setHint("Password");
		password_edittext.setTextColor(Color.BLACK);
		//password_edittext.setBackgroundColor(Color.BLACK);
		password_edittext.setBackgroundResource(R.drawable.editbox);
		password_edittext.setGravity(Gravity.CENTER);
		password_edittext_layout.addView(password_edittext);
		
		int login_button_layout_height=(int)(mainLH * 0.40);
		final LinearLayout login_button_layout = new LinearLayout(getApplicationContext());
		login_button_layout.setLayoutParams(new LayoutParams(mainLW, login_button_layout_height));
		login_button_layout.setOrientation(LinearLayout.HORIZONTAL);	
		//password_layout.setBackgroundColor(Color.MAGENTA);
		login_button_layout.setGravity(Gravity.CENTER);
		mainL.addView(login_button_layout);
		
		Button login_button=new Button(getApplicationContext());
		login_button.setText("Login");
		login_button.setTextSize(25);
		login_button.setGravity(Gravity.CENTER);
		login_button_layout.addView(login_button);

		
		
	}

	
}
