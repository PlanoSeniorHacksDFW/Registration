package com.example.programming.registration;

import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import java.io.*;
/**
 * Created by Programming on 4/17/16.
 */
public class DbBitmapUtility {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(CompressFormat.PNG, 0, stream);
        //return stream.toByteArray();
        return null;
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        //return BitmapFactory.decodeByteArray(image, 0, image.length);
        return null;
    }
}