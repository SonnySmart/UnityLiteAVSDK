package com.live.pusher;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

import com.log.UnityLog;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;

public class PusherListener implements ITXLivePushListener {

	Map<Integer, String> mEventMap = new HashMap<Integer, String>() {
		{
			//normal -1301
			put(TXLiveConstants.PUSH_EVT_CONNECT_SUCC, "已经成功连接到腾讯云推流服务器");
			put(TXLiveConstants.PUSH_EVT_PUSH_BEGIN, "与服务器握手完毕,一切正常，准备开始推流");
			put(TXLiveConstants.PUSH_EVT_OPEN_CAMERA_SUCC, "推流器已成功打开摄像头（Android 部分手机这个过程需要1-2秒）");
			put(TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION, "推流动态调整分辨率");
			put(TXLiveConstants.PUSH_EVT_CHANGE_BITRATE, "推流动态调整码率");
			//error 1001
			put(TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL, "打开摄像头失败");
			put(TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL, "打开麦克风失败");
			put(TXLiveConstants.PUSH_ERR_VIDEO_ENCODE_FAIL, "视频编码失败");
			put(TXLiveConstants.PUSH_ERR_AUDIO_ENCODE_FAIL, "音频编码失败");
			put(TXLiveConstants.PUSH_ERR_UNSUPPORTED_RESOLUTION, "不支持的视频分辨率");
			put(TXLiveConstants.PUSH_ERR_UNSUPPORTED_SAMPLERATE, "不支持的音频采样率");
			put(TXLiveConstants.PUSH_ERR_NET_DISCONNECT, "网络断连,且经三次抢救无效,可以放弃治疗,更多重试请自行重启推流");
			//waring 1101
			put(TXLiveConstants.PUSH_WARNING_NET_BUSY, "网络状况不佳：上行带宽太小，上传数据受阻");
			put(TXLiveConstants.PUSH_WARNING_RECONNECT, "网络断连, 已启动自动重连 (自动重连连续失败超过三次会放弃)");
			put(TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL, "硬编码启动失败，采用软编码");
			put(TXLiveConstants.PUSH_WARNING_DNS_FAIL, "RTMP -DNS 解析失败（会触发重试流程）");
			put(TXLiveConstants.PUSH_WARNING_SEVER_CONN_FAIL, "RTMP 服务器连接失败（会触发重试流程）");
			put(TXLiveConstants.PUSH_WARNING_SHAKE_FAIL, "RTMP 服务器握手失败（会触发重试流程）");
			put(TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT, "RTMP 服务器主动断开连接（会触发重试流程）");
		}
	};
		
	/**
	 * 网络状态
	 */
	@Override
	public void onNetStatus(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 推流回调事件
	 */
	@Override
	public void onPushEvent(int event, Bundle bundle) {
		// TODO Auto-generated method stub
		UnityLog.LOGD("event:" + event);
		String msg = bundle.getString(TXLiveConstants.EVT_DESCRIPTION);
		
		String log = "msg:" + msg;
		if (mEventMap.containsKey(event))
			log += mEventMap.get(event);
		
		if (event < 0) {
			UnityLog.Toast(log);
		}
		else {
			UnityLog.LOGD(log);
		}
	}

}