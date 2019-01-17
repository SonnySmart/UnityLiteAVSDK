package com.live.player;

import android.os.Bundle;

import com.log.UnityLog;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;

public class PlayerListener implements ITXLivePlayListener {

	@Override
	public void onNetStatus(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayEvent(int event, Bundle bundle) {
		// TODO Auto-generated method stub
		UnityLog.LOGD("event:" + event);
		String msg = bundle.getString(TXLiveConstants.EVT_DESCRIPTION);
		
		String log = "msg:" + msg;
		
		if (event < 0) {
			UnityLog.Toast(log);
		}
		else {
			UnityLog.LOGD(log);
		}
	}

}
