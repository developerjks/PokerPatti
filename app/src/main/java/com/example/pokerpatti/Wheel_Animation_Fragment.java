package com.example.teenpattinew;

import java.util.Random;










import android.R.string;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
@SuppressLint("NewApi") public class Wheel_Animation_Fragment extends DialogFragment {
	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onDismiss(dialog);
		
	    

	
			
			ScaleAnimation anim3=new ScaleAnimation(4.3f,1.0f,5.5f,1.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
			anim3.setDuration(3000);
			anim3.setFillAfter(true);
			anim3.setFillEnabled(true);
			anim3.setAnimationListener(new AnimationListener() {
				
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
			MenuActivity.rotatingWheel_layout2_relative.startAnimation(anim3);
			
			
			
			int last=Global.getLastWheelDetails();
			String user=Global.getUserName();
			custom_toast_textview_fragment.setText("HEY"+" "+user+" "+" YOUR TODAY'S BONOUS ::"+rotating_wheel_bonus_point_array[last]);
			custom_toast_textview_fragment.setTextColor(Color.MAGENTA);
			custom_toast_textview_fragment.setTypeface(Typeface.SANS_SERIF);
			custom_toast_textview_fragment.setTextSize(16);
			
			 //Creating the Toast object   
	       Toast toast = new Toast(getActivity().getApplicationContext());  
	       toast.setDuration(Toast.LENGTH_LONG); 
	    
	       toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);  
	       toast.setView(custom_toast_layout_fragment);//setting the view of custom toast layout  
	       toast.show();  
	  

			

		}

	
	int angle;
	Random random;
	ImageView rotating_wheel=MenuActivity.rotatingWheel_imageview;
	View custom_toast_layout_fragment=MenuActivity.custom_toast_layout;
	TextView custom_toast_textview_fragment=MenuActivity.custom_toast_textview;
	
	 int rotating_wheel_bonus_point_array[]={15000,1500,300,0,1200,6500,1300,1000,600,0,1100,1800,1100,2000,400,0,200,3500,1000,1600,900,0,800,4000,1200,1400,700,0,500,1300};
    int random_bonus_generation;

    int last_value_of_rotating_wheel_stored;
    int arraytemp[] =new int[30];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		random=new Random();
		random_bonus_generation=random.nextInt(30);
		
		last_value_of_rotating_wheel_stored=Global.getLastWheelDetails();
		
		int k=0;
		for(int i=last_value_of_rotating_wheel_stored;i<=29;i++){
			arraytemp[k]=rotating_wheel_bonus_point_array[i];
			k++;
			
		}
		if(k!=30){
			for(int i=0;i<last_value_of_rotating_wheel_stored;i++){
				try{arraytemp[k]=rotating_wheel_bonus_point_array[i];}
				catch(Exception e){
					e.printStackTrace();}
				k++;
			}
		}
	
		  for(int i=0;i<30;i++){
			if(arraytemp[i]==rotating_wheel_bonus_point_array[random_bonus_generation]){
				angle=(i*12)+(360*4)+last_value_of_rotating_wheel_stored*12;
				  for(int o=0;o<30;o++){ if(rotating_wheel_bonus_point_array[o]==rotating_wheel_bonus_point_array[random_bonus_generation]){
				                                   Global.setLastWheelDetails(o);
				                                        break;
				                                        }
				                       }
				new setlastwheeldetailsclass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			
				 break;
			     
			        
				
			}  
		  }
		RotateAnimation rotate=new RotateAnimation(last_value_of_rotating_wheel_stored*12,angle,RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
		rotate.setFillEnabled(true);
		rotate.setFillAfter(true);
		rotate.setDuration(10000);
		setCancelable(false);
	rotate.setInterpolator(new AccelerateDecelerateInterpolator());
	
		rotate.setAnimationListener(new AnimationListener(
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
			getDialog().dismiss();	
			}
		});
		rotating_wheel.startAnimation(rotate);
		
				
	}

	
	public Wheel_Animation_Fragment() {
		// Required empty public constructor
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		View v=inflater.inflate(R.layout.wheel_animation_fragment, container, false);

		LinearLayout.LayoutParams layoutparams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		layoutparams.gravity=Gravity.CENTER;
	
		return v;
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        View view = View.inflate(getActivity(), R.layout.wheel_animation_fragment, null);
        dialog.setContentView(view);  
        return dialog;
	}
	
	@Override
	public void onResume() {
	
		super.onResume();
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
		ScaleAnimation anim2=new ScaleAnimation(4.3f,4.3f,5.5f,5.5f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
		anim2.setDuration(3000);
		anim2.setFillAfter(true);
		anim2.setFillEnabled(true);
		anim2.setAnimationListener(new AnimationListener() {
			
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
				
				animation.cancel();
				//getDialog().dismiss();
			}
		});
		MenuActivity.rotatingWheel_layout2_relative.startAnimation(anim2);
		

			
	}


@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		
		
	}

	public class setlastwheeldetailsclass extends AsyncTask<Void, Void, Void>{
		Service_Client_Interaction client=new Service_Client_Interaction();
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			int k=Global.getLastWheelDetails();
			String j=String.valueOf(k);
			
			
			client.setlastwheel(Global.getUserName(),j);
			return null;
		}
		
	}

}
