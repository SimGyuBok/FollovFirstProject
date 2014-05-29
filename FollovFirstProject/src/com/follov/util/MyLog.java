package com.follov.util;

import com.follov.*;

import android.util.Log;

public class MyLog {

	public static final boolean DEBUG = true;

	public static void i(String TAG, String msg){

		if(DEBUG && BuildConfig.DEBUG){
			Log.i(TAG, msg);
	
		}
	}

	public static void d(String TAG, String msg){

		if(DEBUG && BuildConfig.DEBUG){
			Log.d(TAG, msg);
		}
	}

	public static void e(String TAG, String msg){

		if(DEBUG && BuildConfig.DEBUG){
			Log.e(TAG, msg);
		}
	}

	public static void v(String TAG, String msg){

		if(DEBUG && BuildConfig.DEBUG){
			Log.v(TAG, msg);
		}
	}

	public static void w(String TAG, String msg){

		if(DEBUG && BuildConfig.DEBUG){
			Log.w(TAG, msg);
		}
	}

}
