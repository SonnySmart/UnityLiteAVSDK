package com.live.player;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.live.LiveUtils;
import com.log.UnityLog;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.uchess.UnityUiThread;
import com.unity3d.player.UnityPlayer;

public class Player {
	
	/**
	 * 上下文
	 */
	static Context mContext;
	/**
	 * 播放器
	 */
	static TXLivePlayer mLivePlayer;
	static TXLivePlayConfig mPlayConfig;
	/**
	 * 播放页面
	 */
	static TXCloudVideoView mVideoView;
	
	/**
	 * 播放器初始化
	 */
	public static void Init() {
		UnityLog.LOGD("Player Init Start .");
		mContext = UnityPlayer.currentActivity;
		
		// TODO Auto-generated method stub
		mLivePlayer = new TXLivePlayer(mContext);
		mPlayConfig = new TXLivePlayConfig();
		
		//极速模式
		mPlayConfig.setAutoAdjustCacheTime(true);
		mPlayConfig.setMinAutoAdjustCacheTime(1);
		mPlayConfig.setMaxAutoAdjustCacheTime(1);
		
		mLivePlayer.setConfig(mPlayConfig);
		// 设置填充模式
		mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
		// 设置画面渲染方向
		mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
	}
	
	/**
	 * 播放flv直播视频
	 * @param url
	 */
	public static void StartPlay(final String url) {
		UnityLog.LOGD("Player StartPlay Start . url:" + url);
		if (url == null || url.length() == 0)
			return;
		
		StopPlay();
		
		UnityUiThread.runWithCallback(new UnityUiThread.Callback() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mLivePlayer != null && mContext != null) {	
					
					if (mLivePlayer.isPlaying())
						return;
					
					mVideoView = (TXCloudVideoView)LiveUtils.findViewById((Activity)mContext, "video_view");
					mVideoView.setVisibility(View.VISIBLE);
					mLivePlayer.setPlayerView(Player.mVideoView);
					mLivePlayer.setPlayListener(new PlayerListener());
					mLivePlayer.startPlay(url, TXLivePlayer.PLAY_TYPE_LIVE_RTMP_ACC); //推荐 FLV
				}
			}
		});	
	}
	
	/**
	 * 停止播放
	 */
	public static void StopPlay() {
		UnityLog.LOGD("StopPush .");
		// TODO Auto-generated method stub
		if (Player.mLivePlayer != null) {
			Player.mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
			Player.mLivePlayer.setPlayListener(null);
		}
		
		if (Player.mVideoView != null) {
			Player.mVideoView.onDestroy();
			Player.mVideoView = null;
		}	
	}
	
	/**
	 * 暂停
	 */
	public static void onStop() {	
		// TODO Auto-generated method stub
		if (Player.mLivePlayer != null) {
			Player.mLivePlayer.pause();
		}
	}
	
	/**
	 * 恢复
	 */
	public static void onResume() {
		// TODO Auto-generated method stub
		if (Player.mLivePlayer != null) {
			Player.mLivePlayer.resume();
		}
	}
}
