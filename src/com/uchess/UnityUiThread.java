package com.uchess;

import android.app.Activity;

/**
 * 刷新UI需要在UI线程中执行
 * @author Administrator
 *
 */
public class UnityUiThread {
	static Activity mActivity;
	static Callback mCallback;
	
	public interface Callback {
		public void run();
	}
	
	public static void onCreate(Activity ac) {
		mActivity = ac;
	}
	
	public static void onDestroy() {
		
	}
	
	public static void runWithCallback(Callback callback) {
		mCallback = callback;
		
		run();
	}
	
	static void run() {
		if (mActivity == null)
			return;
		if (mCallback == null)
			return;
		
		mActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mCallback.run();
			}
		});
	}
}
