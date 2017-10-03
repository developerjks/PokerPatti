package com.example.teenpattinew;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class SignUp extends Fragment implements OnClickListener {
	int height,width;
	static EditText txt_Username;
    static  EditText txt_Password;
    static String username;
    ImageView b;
     static String pass;
	public SignUp() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		 final	View v= inflater.inflate(R.layout.fragment_sign_up, container, false);
	      height=Global.getscreenHeightDp();
	      width=Global.getscreenWidthDp();
		  signUp_screen_init(v,width, height);
			
			return v;
	}
	public void signUp_screen_init(View signuplayout, int screenWidthDp, int screenHeightDp) {
        //Mainlayout.setBackgroundColor(Color.RED);
		  /* to set background image bg in main grid starts  */
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.signupback1);
        signuplayout.setBackgroundDrawable(new BitmapDrawable(bitmap));




        /*First Vertical*/
        int first_vertical_layout_width = (int) (screenWidthDp);
        int first_vertical_layout_height = (int) (screenHeightDp * 0.10);
        final LinearLayout first_vertical_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.firstvertical);
        first_vertical_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(first_vertical_layout_width, first_vertical_layout_height));
       // first_vertical_linear_layout.setBackgroundColor(Color.GREEN);

         /*Second Vertical*/
        int second_vertical_layout_width= (int) (screenWidthDp);
        int second_vertical_layout_height = (int) (screenHeightDp * 0.70);
        final LinearLayout second_vertical_linear_layout= (LinearLayout) signuplayout.findViewById(R.id.secondvertical);
        second_vertical_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(second_vertical_layout_width, second_vertical_layout_height));
       // second_vertical_linear_layout.setBackgroundColor(Color.RED);

           /*svf*/
        int svf_layout_width = (int) (second_vertical_layout_width * 0.80);
        int svf_layout_height = (int) (second_vertical_layout_height * 0.27);
        final LinearLayout svf_linear_layout = (LinearLayout)signuplayout.findViewById(R.id.svf);
       svf_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svf_layout_width, svf_layout_height));
       
       /*svf1*/
       int svf1_layout_width = (int) (svf_layout_width * 0.50);
       int svf1_layout_height = (int) (svf_layout_height * 0.80);
       final LinearLayout svf1_linear_layout = (LinearLayout)signuplayout.findViewById(R.id.svf1);
      svf1_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svf1_layout_width, svf1_layout_height));
      
      /*svf2*/
      int svf2_layout_width = (int) (svf_layout_width * 0.50);
      int svf2_layout_height = (int) (svf_layout_height * 0.80);
      final LinearLayout svf2_linear_layout = (LinearLayout)signuplayout.findViewById(R.id.svf2);
     svf2_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svf2_layout_width, svf2_layout_height));

        txt_Username=(EditText)svf2_linear_layout.findViewById(R.id.username);

      //  svf_linear_layout.setBackgroundColor(Color.BLUE);

             /*svs*/
        int svs_layout_width = (int) (second_vertical_layout_width * 0.80);
        int svs_layout_height = (int) (second_vertical_layout_height * 0.27);
        final LinearLayout svs_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svs);
        svs_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svs_layout_width, svs_layout_height));
        
        /*svs1*/
        int svs1_layout_width = (int) (svs_layout_width  * 0.50);
        int svs1_layout_height = (int) (svs_layout_height * 0.80);
        final LinearLayout svs1_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svs1);
        svs1_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svs1_layout_width, svs1_layout_height));
        
        
        /*svs2*/
        int svs2_layout_width = (int) (svs_layout_width  * 0.50);
        int svs2_layout_height = (int) (svs_layout_height * 0.80);
        final LinearLayout svs2_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svs2);
        svs2_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svs2_layout_width, svs2_layout_height));
        
        
        
        txt_Password=(EditText)svs2_linear_layout.findViewById(R.id.password);
     //   svs_linear_layout.setBackgroundColor(Color.GRAY);

              /*svt*/
        int svt_layout_width = (int) (second_vertical_layout_width * 0.80);
        int svt_layout_height = (int) (second_vertical_layout_height * 0.27);
        final LinearLayout svt_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svt);
        svt_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svt_layout_width, svt_layout_height));
      //  svt_linear_layout.setBackgroundColor(Color.YELLOW);
        
        /*svt1*/
        int svt1_layout_width = (int) (svt_layout_width * 0.50);
        int svt1_layout_height = (int) (svt_layout_height * 0.80);
        final LinearLayout svt1_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svt1);
        svt1_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svt1_layout_width, svt1_layout_height));
        
        /*svt2*/
        int svt2_layout_width = (int) (svt_layout_width * 0.50);
        int svt2_layout_height = (int) (svt_layout_height * 0.80);
        final LinearLayout svt2_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.svt2);
        svt2_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(svt2_layout_width, svt2_layout_height));

         /*Third Vertical*/
        int third_vertical_layout_width = (int) (screenWidthDp);
        int third_vertical_layout_height = (int) (screenHeightDp * 0.20);
        final LinearLayout third_vertical_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.thirdvertical);
        third_vertical_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(third_vertical_layout_width, third_vertical_layout_height));
       // third_vertical_linear_layout.setBackgroundColor(Color.BLUE);

                 /*tvf*/
        int tvf_layout_width = (int) (third_vertical_layout_width * 0.40);
        int tvf_layout_height = (int) (third_vertical_layout_height * 0.80);
        final LinearLayout tvf_linear_layout = (LinearLayout) signuplayout.findViewById(R.id.tvf);
        tvf_linear_layout.setLayoutParams(new LinearLayout.LayoutParams(tvf_layout_width, tvf_layout_height));
        b=(ImageView)tvf_linear_layout.findViewById(R.id.cracc);
        b.setImageResource(R.drawable.button_signup);
        b.setOnClickListener(this);
       // tvf_linear_layout.setBackgroundColor(Color.BLUE);


    }


		
	

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(R.id.cracc==v.getId())
	        {
	     	      	  //btn_Enter.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
	     	  String txt_Account_value= txt_Username.getText().toString();
	     	  String txt_Password_value=txt_Password.getText().toString();
	     	  
			        	  
			        	     username=txt_Account_value;
			        	     pass=txt_Password_value;
			          	  
			        		  new signUpClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			        	  
			        	 
			        	  
			        		 
	        }
		
		}
		public class signUpClass extends AsyncTask<Void, Void, Void>
		{
	       private int Sign_up_status;
			Service_Client_Interaction client1=new Service_Client_Interaction();
			  @Override
			  protected Void doInBackground(Void... arg0)
			  {
				  
				  
		          if (username == "" || pass == "")
			        {
			        }           

			      else
			     {
			        try
			        {
			    	  Sign_up_status= client1.signup_json(username,pass);
			  	  
			    	  //start
			    	  if (Sign_up_status==1 )
			    	  {
			    		  
			    		  Toast.makeText(getActivity(), "User Already exists", Toast.LENGTH_LONG).show();
	                    
	                   	     
	                   }
			    	  else if(Sign_up_status==2)
			    	  {
			    		  Global.setUserName(username);
			    		  try{Intent i=new Intent(getActivity().getApplication(), MenuActivity.class);
		                    startActivity(i);}catch(Exception e){
		                    	e.printStackTrace();
		                    }
			    		  	
	                   }
			        } catch (Exception e){
				    	   
				    	 }
			     }
				return null;
				
			  }


			
		}	}

