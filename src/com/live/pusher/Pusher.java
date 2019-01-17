package com.live.pusher;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.live.LiveUtils;
import com.log.UnityLog;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.uchess.UnityUiThread;
import com.uchess.liteva.R;
import com.unity3d.player.UnityPlayer;

/**
 * 推流类
 * @author Administrator
 *
 */
public class Pusher {
	/**
	 * 上下文
	 */
	static Context mContext;
	/**
	 * 推流对象
	 */
	static TXLivePusher mLivePusher;
	/**
	 * 推流配置
	 */
	static TXLivePushConfig mLivePushConfig;
	/**
	 * 主播界面
	 */
	static TXCloudVideoView mCaptureView;
	/**
	 * 推流监听
	 */
	static PusherListener mPusherListener;
	
	/**
	 * 初始化推流
	 */
	public static void Init() {
		
		UnityLog.LOGD("Pusher Init .");
		
		mContext = UnityPlayer.currentActivity;
		
		// TODO Auto-generated method stub
		mLivePusher = new TXLivePusher(mContext);
		mLivePushConfig = new TXLivePushConfig();	
		
		/**
		 * step 6: 控制摄像头
		 */
		// 默认是前置摄像头
		//mLivePusher.switchCamera();
		/**
		 * step 7: 设置 Logo 水印
		 */
		// demo默认不加水印
		Bitmap mBitmap = LiveUtils.decodeResource(mContext.getResources(),R.drawable.watermark);
        mLivePushConfig.setWatermark(mBitmap, 0.02f, 0.05f, 0.2f);
        /**
         * step 8: 硬件编码
         */
        //自动选择是否启用硬件加速
        mLivePushConfig.setHardwareAcceleration(TXLiveConstants.ENCODE_VIDEO_AUTO);
		/**
		 * 10.1、设置 pauseImg
在开始推流前，使用 TXLivePushConfig 的 setPauseImg 接口设置一张等待图片，图片含义推荐为“主播暂时离开一下下，稍后回来”。
		 */
		mLivePushConfig.setPauseImg(300,5);
		// 300 为后台播放暂停图片的最长持续时间,单位是秒
		// 10 为后台播放暂停图片的帧率,最小值为 5,最大值为 20
		Bitmap bitmap = LiveUtils.decodeResource(mContext.getResources(), R.drawable.pause_publish);
		mLivePushConfig.setPauseImg(bitmap);
		// 设置推流暂停时,后台播放的暂停图片, 图片最大尺寸不能超过 1920*1920.
		/**
		 * 10.2、设置setPauseFlag
在开始推流前，使用 TXLivePushConfig 的 setPauseFlag 接口设置切后台 pause 推流时需要停止哪些采集，停止视频采集则会推送 pauseImg 设置的默认图，停止音频采集则会推送静音数据。
		 */
		//表示同时停止视频和音频采集，并且推送填充用的音视频流；
		mLivePushConfig.setPauseFlag(
				TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO|
				TXLiveConstants.PAUSE_FLAG_PAUSE_AUDIO);
		// 是否开启屏幕自适应
		mLivePushConfig.enableScreenCaptureAutoRotate(true);
		
		mLivePusher.setConfig(mLivePushConfig);
		//TO 设置视频参数(秀场直播)
		mLivePusher.setVideoQuality(
				TXLiveConstants.VIDEO_QUALITY_HIGH_DEFINITION, 
				false, 
				false);	
		mLivePusher.setBeautyFilter(TXLiveConstants.BEAUTY_STYLE_SMOOTH, 5, 3, 2);
		
		UnityLog.LOGD("Pusher Init Done .");
	}
	
	/**
	 * 开始推流
	 * @param url
	 */
	public static void StartPush(final String url) {
		
		UnityLog.LOGD("StartPush . url:" + url);
		
		if (url == null || url.length() == 0) {
			UnityLog.LOGE("StartPush url null .");
			return;
		}
		
		//先停止
		StopPush();
		
		UnityUiThread.runWithCallback(new UnityUiThread.Callback() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mLivePusher != null && mContext != null) {
					if (mLivePusher.isPushing())
						return;
					
					mCaptureView = (TXCloudVideoView)LiveUtils.findViewById((Activity)mContext, "video_view");
					mCaptureView.setVisibility(View.VISIBLE);
					mLivePusher.startCameraPreview(Pusher.mCaptureView);
					mLivePusher.setPushListener(new PusherListener());
					mLivePusher.startPusher(url);
				}
			}
		});	
	}
	
	/**
	 * 停止推流
	 */
	public static void StopPush() {		
		UnityLog.LOGD("StopPush .");
		// TODO Auto-generated method stub
		if (Pusher.mLivePusher != null) {
			Pusher.mLivePusher.stopCameraPreview(true); //停止摄像头预览
			Pusher.mLivePusher.stopPusher();            //停止推流
			Pusher.mLivePusher.setPushListener(null);   //解绑 listener			    
		}	
		
		if (Pusher.mCaptureView != null) {
			Pusher.mCaptureView.onDestroy();
			Pusher.mCaptureView = null;
		}
	}
	
	/**
	 * 发送消息
	 * @param msg
	 */
	public static void SendMsg(String msg) {
		UnityLog.LOGD("SendMsg . " + msg);
		
		if (msg == null || msg.length() == 0) {
			UnityLog.LOGD("SendMsg null .");
			return;
		}
		
		try {			
			mLivePusher.sendMessage(msg.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 暂停
	 */
	public static void onStop() {
		// TODO Auto-generated method stub		
		UnityLog.LOGD("PusherActivity onStop .");
		// TODO Auto-generated method stub
		if (Pusher.mCaptureView != null)
			Pusher.mCaptureView.onPause();  // mCaptureView 是摄像头的图像渲染view
		if (Pusher.mLivePusher != null)
			Pusher.mLivePusher.pausePusher(); // 通知 SDK 进入“后台推流模式”了
	}
	
	/**
	 * 恢复
	 */
	public static void onResume() {
		// TODO Auto-generated method stub		
		UnityLog.LOGD("PusherActivity onResume .");
		// TODO Auto-generated method stub
		if (Pusher.mCaptureView != null)
			Pusher.mCaptureView.onResume();     // mCaptureView 是摄像头的图像渲染view
		if (Pusher.mLivePusher != null)
			Pusher.mLivePusher.resumePusher();  // 通知 SDK 重回前台推流		
	}
}
