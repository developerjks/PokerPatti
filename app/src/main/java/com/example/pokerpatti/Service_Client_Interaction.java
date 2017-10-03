package com.example.teenpattinew;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;


public class Service_Client_Interaction {

	static JSONObject objJSON;
		String IPAddressURL="http://pokerpatti.com/jd/Service.svc";
      		
	/* CheckUserAvailability JSON function starts  */
	 String signIn_status;
	 String winner_username;
	 int signup_status;
	 /*  SaveUserIdDuringLogin JSON function   (to save the information about userid and window id)  starts*/
	 int save_value_JSON;
	 @SuppressWarnings("deprecation")
	 public int SaveUserIdDuringLogin_json(String userid,String windowid) 
	 {
		 JSONObject jsonObject= func(IPAddressURL+ "/json/Save_User_Id_During_Login/"+userid+"/"+windowid+"");
			
	       try
	       {
	    	   save_value_JSON=jsonObject.getInt("Save_User_Id_During_LoginResult");
	       } catch (JSONException e) {
			    	e.printStackTrace();
		          }
	  
	  return save_value_JSON;
	 }
	 /*  SaveUserIdDuringLogin JSON function   (to save the information about userid and window id)  ends*/

	 @SuppressWarnings("deprecation")
	 public String signin_json(String pass)
	 {  
		 
		 String s=IPAddressURL+ "/json/CheckUserAvailability/"+pass+"";
				
		 JSONObject jsonObject= func(s);
				
		       try {
		    	   signIn_status=jsonObject.getString("CheckUserAvailabilityResult");
		       } catch (Exception e) {
				    	e.printStackTrace();
			          }
	  return signIn_status;
	 }
	 public int signup_json(String username, String pass) {
			// TODO Auto-generated method stub
		 String s=IPAddressURL+ "/json/newboston/"+username+"/"+pass+"";
			
		 JSONObject jsonObject= func(s);
				
		       try {
		    	   signup_status=jsonObject.getInt("newbostonResult");
		       } catch (JSONException e) {
				    	e.printStackTrace();
			          }
	  return signup_status;
	
			
		}
  
	  /* CheckUserAvailability JSON function ends  */

		 
			public static JSONObject func(String url)
			{
				
				
				try
				{    HttpGet request=new HttpGet(url);
				request.setHeader("Accept","application/json");
				request.setHeader("Content-type","application/json");
					@SuppressWarnings("deprecation")
					DefaultHttpClient httpClient=new DefaultHttpClient();
					HttpResponse response=httpClient.execute(request);
					HttpEntity responseEntity=response.getEntity();
					char[] buffer=new char[(int) responseEntity.getContentLength()];
					InputStream stream=responseEntity.getContent();
					InputStreamReader reader=new InputStreamReader(stream);
					reader.read(buffer);
					stream.close();
					objJSON =new JSONObject(new String(buffer));
					
				}catch(Exception e){
				   android.util.Log.i("loi Roi :",e.toString());	
				}
				
				return objJSON;
			}

			int del_value_JSON;
			 @SuppressWarnings("deprecation")
			 public int Delete_User_Id_During_Logout_json(String userid,String windowid) {
				 	//JSONObject jsonObject2= func("http://192.168.1.3/AndroidService/Service.svc/json/Delete_User_Id_During_Logout/"+userid+"/"+windowid+"");
				 JSONObject jsonObject2= func(IPAddressURL+"/json/Delete_User_Id_During_Logout/"+userid+"/"+windowid+"");
					
			       try {
			    	   del_value_JSON=jsonObject2.getInt("Delete_User_Id_During_LogoutResult");
			          } catch (JSONException e) {
					    	  e.printStackTrace();
				            }
			    return del_value_JSON;
			  }
			
				public void Show_usergame_detail(String userName) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL+ "/json/Get_usergame_detail/"+userName+"");
					 JSONArray User_Details;	

				       try
				       {
				    	   User_Details= jsonObject.getJSONArray("Get_usergame_detailResult");
				    	 
				    	   
				    	   for(int i=0;i<User_Details.length();i++){
					    		  JSONObject user_details_JSONobject =User_Details.getJSONObject(i);
				    	         GameDetails.lastwheelno=user_details_JSONobject.getInt("lastwheelno");
				    	         Global.setLastWheelDetails(GameDetails.lastwheelno);
				    	         
				    	   }   	  	    	        
				    	   }
					    catch (JSONException e) {
						    	e.printStackTrace();
					          }
	
				}

			
		 
			
				public static class GameDetails
				{
					  public static int lastwheelno;
				      
				}

				public void setlastwheel(String username, String j) {
					// TODO Auto-generated method stub
					JSONObject jsonObject= func(IPAddressURL+ "/json/set_usergame_detail/"+username+"/"+j+"");
					
					
				}
				
				public void table_Details() 
				{
					// TODO Auto-generated method stub
					
					
					 JSONObject jsonObject= func(IPAddressURL + "/json/selecting_available_table_details");
					 JSONArray Table_Details;
					 
					 List<String> table_list = new ArrayList<String>();

				       try
				       {
				    	   Table_Details= jsonObject.getJSONArray("selecting_available_table_detailsResult");
				    	   
				    	   
				    	   for(int i=0;i<Table_Details.length();i++){
					    		  JSONObject table_details_JSONobject =Table_Details.getJSONObject(i);
					    		  table_list.add(table_details_JSONobject.getString("Total_Table"));
					    		  String myArray[] =new String[table_list.size()];
					    		  for (int j = 0; j < table_list.size(); j++) 
					    		  {
					    			   myArray[j] =table_list.get(j);
					    		  }
				    	       
				    	         Global.setTableDetails(myArray[i]);
				    	   }
				    	   Global.setFlag_setTableDetails();
				    	   
				       }
					    catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				public void all_users_table_Details(String table_name,String username) 
				{
					// TODO Auto-generated method stub
					
					
					 JSONObject jsonObject= func(IPAddressURL + "/json/Table_Details/"+table_name+"/"+username+"");
					 JSONArray Table_Details;
					 
					 User_Info uu=new User_Info();
					 
					 
				       try
				       {
				    	   Table_Details= jsonObject.getJSONArray("Table_DetailsResult");
				    	   int length=Table_Details.length();
				    	   Global.users_name_list.clear();
				    	   Global.users_coin_list.clear();
				    	   Global.users_gameId_list.clear();
				    	   Global.users_flag_list.clear();
				    	   Global.users_showflag_list.clear();
				    	   Global.users_countDownTimer_list.clear();
				    	   Global.card_one_list.clear();
				    	   Global.card_two_list.clear();
				    	   Global.card_three_list.clear();
				    	   Global.users_see_sideshow_flag_list.clear();
				    	   Global.users_chance_list.clear();
				    	   Global.users_chaal_list.clear();
				    	   Global.users_flag_list.clear();
				    	   Global.users_pack_list.clear();
				    	   Global.pack_flag_list.clear();				
				    	   
				    	   Global.all_users_info.clear();
				    	   for(int i=0;i<Table_Details.length();i++){
					    		  JSONArray table_details_JSONarray =Table_Details.getJSONArray(i);
					    		  JSONObject table_details_JSONobject =table_details_JSONarray.getJSONObject(0);
					    		  uu.user_game_id=table_details_JSONobject.getInt("game_id");
					    		  uu.user_name=table_details_JSONobject.getString("username");
					    		  uu.user_coins=table_details_JSONobject.getInt("coins");
					    		  uu.user_flag=table_details_JSONobject.getInt("flag_cards_dist");
					    		  uu.user_show_flag=table_details_JSONobject.getInt("show_flag");
					    		  uu.user_countdown_timer=table_details_JSONobject.getInt("timer");
					    		  uu.card_one=table_details_JSONobject.getInt("card_one");
					    		  uu.card_two=table_details_JSONobject.getInt("card_two");
					    		  uu.card_three=table_details_JSONobject.getInt("card_three");
					    		  uu.user_see_sideshow_flag=table_details_JSONobject.getInt("see_sideshow_flag");
					    		  uu.user_chance=table_details_JSONobject.getInt("user_chance");
					    		  uu.user_chaal=table_details_JSONobject.getInt("user_chaal");
					    		  uu.user_pack=table_details_JSONobject.getInt("user_pack");
					    		  uu.pack_flag=table_details_JSONobject.getInt("pack_flag");
					    		  
					    		Global.setAllUsersDetail(uu);
					    		
				    	   }
				    	   
				    	   
				       }
					    catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				public void all_users_table_Details_Default(String username) 
				{
					// TODO Auto-generated method stub

					 JSONObject jsonObject= func(IPAddressURL + "/json/default_table_allotment/"+username+"");
					 JSONArray Table_Details;
					 
					 User_Info uu=new User_Info();
					 
					 
				       try
				       {
				    	   Table_Details= jsonObject.getJSONArray("default_table_allotmentResult");
				    	   int length=Table_Details.length();
				    	   Global.users_name_list.clear();
				    	   Global.users_coin_list.clear();
				    	   Global.users_gameId_list.clear();
				    	   Global.users_flag_list.clear();
				    	   Global.users_showflag_list.clear();
				    	   Global.users_countDownTimer_list.clear();
				    	   Global.card_one_list.clear();
				    	   Global.card_two_list.clear();
				    	   Global.card_three_list.clear();
				    	   Global.users_see_sideshow_flag_list.clear();
				    	   Global.users_chance_list.clear();
				    	   Global.users_chaal_list.clear();
				    	   Global.users_flag_list.clear();
				    	   Global.users_pack_list.clear();
				    	   Global.pack_flag_list.clear();				
				    	   
				    	   Global.all_users_info.clear();
				    	   for(int i=0;i<Table_Details.length();i++){
					    		  JSONArray table_details_JSONarray =Table_Details.getJSONArray(i);
					    		  JSONObject table_details_JSONobject =table_details_JSONarray.getJSONObject(0);
					    		  uu.user_game_id=table_details_JSONobject.getInt("game_id");
					    		  uu.user_name=table_details_JSONobject.getString("username");
					    		  uu.user_coins=table_details_JSONobject.getInt("coins");
					    		  uu.user_flag=table_details_JSONobject.getInt("flag_cards_dist");
					    		  uu.user_show_flag=table_details_JSONobject.getInt("show_flag");
					    		  uu.user_countdown_timer=table_details_JSONobject.getInt("timer");
					    		  uu.card_one=table_details_JSONobject.getInt("card_one");
					    		  uu.card_two=table_details_JSONobject.getInt("card_two");
					    		  uu.card_three=table_details_JSONobject.getInt("card_three");
					    		  uu.user_see_sideshow_flag=table_details_JSONobject.getInt("see_sideshow_flag");
					    		  uu.user_chance=table_details_JSONobject.getInt("user_chance");
					    		  uu.user_chaal=table_details_JSONobject.getInt("user_chaal");
					    		  uu.user_pack=table_details_JSONobject.getInt("user_pack");
					    		  uu.pack_flag=table_details_JSONobject.getInt("pack_flag");
					    		  
					    		Global.setAllUsersDetail(uu);
					    		
				    	   }
				    	   
				    	   
				       }
					    catch (JSONException e) {
						    	e.printStackTrace();
					          }

					 	
				}
				
				
				public void user_profile(String user_profile_pic_string,String user_Image_filename ) {
					// TODO Auto-generated method stub
					
					
					 JSONObject jsonObject= func(IPAddressURL + "/json/users_profile_image/"+user_profile_pic_string+"/"+user_Image_filename+"");
					  try {
				    	  String signup_status=jsonObject.getString("users_profile_imageResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
				}
				
				public void user_game_delete_after_playing_method(String username,int username_Length) {
					// TODO Auto-generated method stub
					
					String length=Integer.toString(username_Length);
					
//					Toast.makeText(, "length", Toast.LENGTH_SHORT).show();
//					
					 JSONObject jsonObject= func(IPAddressURL + "/json/delete_user_details/"+username+"/"+length+"");
					  try {
				    	  int signup_status=jsonObject.getInt("delete_user_detailsResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				public void user_button_packed_clicked(String username) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL + "/json/pack_button_flag_update/"+username+"");
					  try {
				    	  int signup_status=jsonObject.getInt("pack_button_flag_updateResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				
				public void user_show_button(String username) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL + "/json/show_button_flag_update/"+username+"");
					  try {
				    	  int signup_status=jsonObject.getInt("show_button_flag_updateResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}

				public void game_reset_function(String username) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL + "/json/game_reset_service/"+username+"");
					  try {
				    	  int signup_status=jsonObject.getInt("game_reset_serviceResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				public void user_see_func(String username) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL + "/json/see_flag_update/"+username+"");
					  try {
				    	  int see_sideshow_status=jsonObject.getInt("see_flag_updateResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				public void user_sideshow_func(String username1,String username2) {
					// TODO Auto-generated method stub
					 JSONObject jsonObject= func(IPAddressURL + "/json/sideshow_flag_update/"+username1+"/"+username2+"");
					  try {
				    	  int sideshow_status=jsonObject.getInt("sideshow_flag_updateResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
					
				}
				
				public void user_sideshow_flag_reset_func(String username1,String username2)
				{
					 JSONObject jsonObject= func(IPAddressURL + "/json/sideshow_flag_reset/"+username1+"/"+username2+"");
					  try {
				    	  int sideshow_status=jsonObject.getInt("sideshow_flag_resetResult");
				       } catch (JSONException e) {
						    	e.printStackTrace();
					          }
				}
				
								
				
				 public void winner_decider_service(String username1,String username2)
				 {  
					 
					 String s=IPAddressURL+ "/json/winner_decider_service/"+username1+"/"+username2+"";							
					 JSONObject jsonObject= func(s);
							
					       try {
					    	   winner_username=jsonObject.getString("winner_decider_serviceResult");
					       } catch (Exception e) {
							    	e.printStackTrace();
						          }
					       Global.setWinnerUsername(winner_username);
				 }
				 
					
					public void user_chance(String username,int userChance_flag) {
						// TODO Auto-generated method stub
						 JSONObject jsonObject= func(IPAddressURL + "/json/user_chance/"+username+"/"+userChance_flag+"");
						  try {
					    	  int userChanceStatus=jsonObject.getInt("user_chanceResult");
					       } catch (JSONException e) {
							    	e.printStackTrace();
						          }
						
					}
					
					
					public void userCoinsRetreival(String username) {
						// TODO Auto-generated method stub
						 JSONObject jsonObject= func(IPAddressURL + "/json/usercoincalculate/"+username+"");
						  try {
					    	  int userCoins=jsonObject.getInt("usercoincalculateResult");
					    	  Global.setUserCoin(userCoins);
					       } catch (JSONException e) {
							    	e.printStackTrace();
						          }
						
					}
					
					public void userChaalUpdate(String username,int usercoins) {
						// TODO Auto-generated method stub
						 JSONObject jsonObject= func(IPAddressURL + "/json/chaalcoincut/"+username+"/"+usercoins+"");
						  try {
					    	  int userCoinsUpdate=jsonObject.getInt("chaalcoincutResult");
					    	  Global.setUserCoin(userCoinsUpdate);
					       } catch (JSONException e) {
							    	e.printStackTrace();
						          }
						
					}
					
					public void addWinnerAmount(String username,int winAmount) {
						// TODO Auto-generated method stub
						 JSONObject jsonObject= func(IPAddressURL + "/json/winnercoinalltogether/"+username+"/"+winAmount+"");
						  try {
					    	  int userCoins=jsonObject.getInt("winnercoinalltogetherResult");
					    	  Global.setUserCoin(userCoins);
					       } catch (JSONException e) {
							    	e.printStackTrace();
						          }
						
					}
					
					public int updateUserExistence(String username) 
					{
						// TODO Auto-generated method stub
						int existenceStatus=0;
						 JSONObject jsonObject= func(IPAddressURL + "/json/select_user_update/"+username+"");
						  try 
						  {
					    	  existenceStatus=jsonObject.getInt("select_user_updateResult");					    	  
					      } 
						  catch (JSONException e) 
						  {
							e.printStackTrace();
						  }
						return existenceStatus;
					}

	
	}

	

