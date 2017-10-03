package com.example.teenpattinew;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageBase64 {
    final String TAG = this.getClass().getSimpleName();

    public static String encode(Bitmap bitmap){
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int quality = 100; //100: compress nothing
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, bao);
       try {
		bao.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

//        if(bitmap != null && !bitmap.isRecycled()){//important! prevent out of memory
//            bitmap.recycle();
//            bitmap = null;
//        }

        byte [] ba = bao.toByteArray();
        String encodedImage = Base64.encodeToString(ba, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap decode(String encodedImage)
    {
       // final String pureBase64Encoded = encodedImage.substring(encodedImage.indexOf(",")  + 1);
        byte[] decodedString = Base64.decode(encodedImage,0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }


}
