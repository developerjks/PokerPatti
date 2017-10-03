package com.example.teenpattinew;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Exit_Dialog extends DialogFragment
{
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setMessage("Are you sure, you want to Exit?");
        
        setCancelable(false);
       
        
        
        
        
        builder.setNegativeButton(R.string.no,new DialogInterface.OnClickListener() 
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "Negative button was clicked", Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}
		});
		
        
        builder.setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() 
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "Positive button was clicked", Toast.LENGTH_SHORT).show();
				  String current_logged_userid= Global.getUserName();
		      		if(current_logged_userid!=null)
		      		{
		       	  Service_Client_Interaction service_client=new Service_Client_Interaction();
		       	  int del = service_client.Delete_User_Id_During_Logout_json(current_logged_userid,Global.getWindow_id());
		      		}
		       	
		      	Intent intent = new Intent(Intent.ACTION_MAIN);
		           intent.addCategory(Intent.CATEGORY_HOME);
		           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		           startActivity(intent);
		       	getActivity().finish();
		       	android.os.Process.killProcess(android.os.Process.myPid());
		          System.exit(1);
			}
		});
		
        
		
        Dialog dialog=builder.create();
		
		return dialog;
	}

	
}
