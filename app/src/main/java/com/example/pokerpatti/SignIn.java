package com.example.teenpattinew;



import java.util.concurrent.ThreadPoolExecutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends Fragment implements OnClickListener
{
	int height,width;
	 static EditText txt_Username;
	    static  EditText txt_Password;
	    static String username;
	     static String pass;
	     
	     TextView goto_userpanel_textview;
	
	public SignIn() {
		// Required empty public constructor
	}

	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 
	  final	View v= inflater.inflate(R.layout.fragment_sign_in, container, false);
      height=Global.getscreenHeightDp();
      width=Global.getscreenWidthDp();
	  signIn_screen_init(v,width, height);
		
		return v;
	}

	private void signIn_screen_init(View signinlayout, int screenWidthDp, int screenHeightDp) 
	{
		// TODO Auto-generated method stub
		
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.signupback1);
   signinlayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
//      Drawable loginActivityBackground = signinlayout.getBackground();
//      loginActivityBackground.setAlpha(113);



      /*First Vertical*/
      int first_vertical_layout_width = (int)(screenWidthDp);
      int first_vertical_layout_height = (int) (screenHeightDp * 0.80);
      final LinearLayout first_vertical_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.firstvertical);
      first_vertical_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(first_vertical_layout_width, first_vertical_layout_height));
      //  first_vertical_linear_layout.setBackgroundColor(Color.GREEN);

       /*firstverticalfirsthorizontal*/
      int first_vertical_first_horizontal_layout_width = (int) (screenWidthDp * 0.15);
      int first_vertical_first_horizontal_layout_height = (int)(first_vertical_layout_height);
      final LinearLayout first_vertical_first_horizontal_linear_layout = (LinearLayout)signinlayout.findViewById(R.id.firstverticalfirsthorizontal);
      first_vertical_first_horizontal_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(first_vertical_first_horizontal_layout_width, first_vertical_first_horizontal_layout_height));
      //  first_vertical_first_horizontal_linear_layout.setBackgroundColor(Color.RED);

         /*firstverticalsecondhorizontal*/
      int first_vertical_second_horizontal_layout_width = (int) (screenWidthDp * 0.70);
      int first_vertical_second_horizontal_layout_height = (first_vertical_layout_height);
      final LinearLayout first_vertical_second_horizontal_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.firstverticalsecondhorizontal);
      first_vertical_second_horizontal_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(first_vertical_second_horizontal_layout_width, first_vertical_second_horizontal_layout_height));
      //   first_vertical_second_horizontal_linear_layout.setBackgroundColor(Color.BLUE);

           /*fvshfv*/
      int fvshfv_layout_width = (first_vertical_second_horizontal_layout_width);
      int fvshfv_layout_height = (int) (first_vertical_layout_height * 0.20);
      final LinearLayout fvshfv_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshfv);
      fvshfv_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshfv_layout_width, fvshfv_layout_height));
      // fvshfv_linear_layout.setBackgroundColor(Color.RED);

           /*fvshsv*/
      int fvshsv_layout_width = (first_vertical_second_horizontal_layout_width);
      int fvshsv_layout_height = (int) (first_vertical_layout_height * 0.70);
      final LinearLayout fvshsv_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv);
      fvshsv_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv_layout_width, fvshsv_layout_height));
      //  fvshsv_linear_layout.setBackgroundColor(Color.BLUE);

                /*fvshsv1*/
      int fvshsv1_layout_width = (int)(fvshsv_layout_width);
      int fvshsv1_layout_height = (int) (fvshsv_layout_height * 0.30);
      final LinearLayout fvshsv1_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv1);
      fvshsv1_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv1_layout_width, fvshsv1_layout_height));
      //fvshsv1_linear_layout.setBackgroundColor(Color.YELLOW);
      // fvshsv1_linear_layout.setPadding(0, 0, 0, 8);

                 /*fvshsv11*/
      int fvshsv11_layout_width = (int) (fvshsv1_layout_width * 0.50);
      int fvshsv11_layout_height = (int)(fvshsv1_layout_height * 0.80);
      final LinearLayout fvshsv11_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv11);
      fvshsv11_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv11_layout_width, fvshsv11_layout_height));
      // fvshsv11_linear_layout.setBackgroundColor(Color.WHITE);

                         /*fvshsv12*/
      int fvshsv12_layout_width = (int) (fvshsv1_layout_width * 0.50);
      int fvshsv12_layout_height = (int) (fvshsv1_layout_height * 0.80);
      final LinearLayout fvshsv12_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv12);
      fvshsv12_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv12_layout_width, fvshsv12_layout_height));
      //  fvshsv12_linear_layout.setBackgroundColor(Color.GRAY);
      txt_Username=(EditText)fvshsv12_linear_layout.findViewById(R.id.username);

                /*fvshsv2*/
      int fvshsv2_layout_width = (fvshsv_layout_width);
      int fvshsv2_layout_height = (int) (fvshsv_layout_height * 0.30);
      final LinearLayout fvshsv2_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv2);
      fvshsv2_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv2_layout_width, fvshsv2_layout_height));
      //  fvshsv2_linear_layout.setBackgroundColor(Color.RED);
      //fvshsv2_linear_layout.setPadding(0,0,0,8);

                   /*fvshsv21*/
      int fvshsv21_layout_width = (int) (fvshsv2_layout_width * 0.50);
      int fvshsv21_layout_height = (int)(fvshsv2_layout_height * 0.80);
      final LinearLayout fvshsv21_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv21);
      fvshsv21_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv21_layout_width, fvshsv21_layout_height));
      //  fvshsv21_linear_layout.setBackgroundColor(Color.GRAY);

                         /*fvshsv22*/
      int fvshsv22_layout_width = (int) (fvshsv2_layout_width * 0.50);
      int fvshsv22_layout_height = (int) (fvshsv2_layout_height * 0.80);
      final LinearLayout fvshsv22_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv22);
      fvshsv22_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv22_layout_width, fvshsv22_layout_height));
      //  fvshsv22_linear_layout.setBackgroundColor(Color.WHITE);
      txt_Password=(EditText)fvshsv22_linear_layout.findViewById(R.id.password);

                /*fvshsv3*/
      int fvshsv3_layout_width = (int) (fvshsv_layout_width * 0.50);
      int fvshsv3_layout_height = (int) (fvshsv_layout_height * 0.35);
      final LinearLayout fvshsv3_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshsv3);
      fvshsv3_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshsv3_layout_width, fvshsv3_layout_height));
      // fvshsv3_linear_layout.setBackgroundColor(Color.YELLOW);

      ImageView signin = (ImageView) fvshsv3_linear_layout.findViewById(R.id.signin);
      signin.setImageResource(R.drawable.button_signin);
      signin.setOnClickListener(this);
 
             /*fvshtv*/
      int fvshtv_layout_width = (first_vertical_second_horizontal_layout_width);
      int fvshtv_layout_height = (int) (first_vertical_layout_height * 0.10);
      final LinearLayout fvshtv_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.fvshtv);
      fvshtv_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(fvshtv_layout_width, fvshtv_layout_height));
      //fvshtv_linear_layout.setBackgroundColor(Color.YELLOW);


       /*firstverticalthirdhorizontal*/
      int first_vertical_third_horizontal_layout_width = (int) (screenWidthDp * 0.15);
      int first_vertical_third_horizontal_layout_height = (first_vertical_layout_height);
      final LinearLayout first_vertical_third_horizontal_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.firstverticalthirdhorizontal);
      first_vertical_third_horizontal_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(first_vertical_third_horizontal_layout_width, first_vertical_third_horizontal_layout_height));
      // first_vertical_third_horizontal_linear_layout.setBackgroundColor(Color.GREEN);

         /*second Vertical*/
      int second_vertical_layout_width = (screenWidthDp);
      int second_vertical_layout_height = (int) (screenHeightDp * 0.20);
      final LinearLayout second_vertical_linear_layout = (LinearLayout) signinlayout.findViewById(R.id.secondvertical);
      second_vertical_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(second_vertical_layout_width, second_vertical_layout_height));
      // second_vertical_linear_layout.setBackgroundColor(Color.GREEN);
      
      goto_userpanel_textview=(TextView)second_vertical_linear_layout.findViewById(R.id.go_to_user_panel);
      goto_userpanel_textview.setOnClickListener(this);
      
      
      
      Bitmap default_image = BitmapFactory.decodeResource(this.getResources(), R.drawable.default_image);
      Global.setImageBitmap(default_image);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(R.id.signin==v.getId())
        {
     	      	  //btn_Enter.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
     	  String txt_Account_value= txt_Username.getText().toString();
     	  String txt_Password_value=txt_Password.getText().toString();
     	  
		        	  
		        	     username=txt_Account_value;
		        	     pass=txt_Password_value;
		          	  
		        		  new CheckUserAvailabilityClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		        	  
		        	     
		        	  
		        		 
        }
		else if(R.id.go_to_user_panel==v.getId())
		{
			Intent i=new Intent(getActivity().getApplication(),UserPanelActivity.class);
			startActivity(i);
		}
	
	}
	public class CheckUserAvailabilityClass extends AsyncTask<Void, Void, Void>
	{
       private String user_availability_status;
		Service_Client_Interaction client=new Service_Client_Interaction();
		  @Override
		  protected Void doInBackground(Void... arg0)
		  {
			  
			  
	          if (username == "" || pass == "")
	          {Toast.makeText(getActivity(), "Please enter your id", Toast.LENGTH_SHORT).show();
		        }           

		      else
		     {
		        try
		        {
		    	 // user_availability_status= client.signin_json(username,pass);
		  	  
		    	  //start
		    	  if (user_availability_status.equals("exists") ){
                 int save = client.SaveUserIdDuringLogin_json(username, Global.getWindow_id());
                      
                      if (save > 0)
                      {
                    	  Global.setUserName(username);
                    	  
                         
                    try{Intent i=new Intent(getActivity().getApplication(), MenuActivity.class);
                    startActivity(i);}catch(Exception e){
                    	e.printStackTrace();
                    }
                    
                    }
                   	     
                   	     
                   	    
                   	     
                   /* to redirect to another MenuActivity containing user balance with it starts  */
                	
                   /* to redirect to another MenuActivity containing user balance with it ends  */
                   	     
                   	     
		    	  else if(user_availability_status.equals("not valid")){
                  	Toast.makeText(getActivity(), "Retry please....!!!!", Toast.LENGTH_SHORT).show();
                  }
		    	  else{
                      Toast.makeText(getActivity().getApplicationContext(),
                    		  "Wrong id please try again with ur assigned id", Toast.LENGTH_SHORT).show();
                     }
		    	  }
		    	  } catch (Exception e){
			    	   e.printStackTrace();
			    	 }
		     }
			return null;
		     }}}