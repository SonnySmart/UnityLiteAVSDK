package com.live;

import com.live.player.Player;
import com.live.pusher.Pusher;
import com.log.UnityLog;
import com.tencent.rtmp.TXLiveBase;

public class LiveBridge {	
	/**
	 * 初始化SDK
	 */
	public static void Init() {	
		
		UnityLog.LOGD("LiveBridge Init .");
		
		InitSelf();
		
		UnityLog.Init();
		Pusher.Init();
		Player.Init();
	}
	
	/**
	 * 初始化SDK
	 */
	static void InitSelf() {
	    
	    TXLiveBase.setConsoleEnabled(true);
        TXLiveBase.setAppID("1258433981");
	}
}
