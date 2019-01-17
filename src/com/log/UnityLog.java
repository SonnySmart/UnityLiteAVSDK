package com.log;

import com.unity3d.player.UnityPlayer;

import android.content.Context;

public class UnityLog {
	
	static Context mContext;
	
	final static String TAG = "Unity";
	
	public static void Init() {
		mContext = UnityPlayer.currentActivity.getApplicationContext();
	}
	
	public static void LOGD(String msg) {
		android.util.Log.d(TAG, msg);
	}
	
	public static void LOGI(String msg) {
		android.util.Log.i(TAG, msg);
	}
	
	public static void LOGE(String msg) {
		android.util.Log.e(TAG, msg);
	}
	
	public static void Toast(String text) {
		android.widget.Toast.makeText(mContext, text, android.widget.Toast.LENGTH_SHORT).show();
	}
}
