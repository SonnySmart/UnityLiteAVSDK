package com.uchess;

import com.live.LiveUtils;
import com.live.player.Player;
import com.live.pusher.Pusher;
import com.uchess.liteva.R;
import com.unity3d.player.UnityPlayerActivity;

import android.os.Bundle;
import android.widget.FrameLayout;


public class MainActivity extends UnityPlayerActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        UnityUiThread.onCreate(this);

        setContentView(R.layout.activity_main);
        
        //Unity 父布局
        FrameLayout layout = (FrameLayout)LiveUtils.findViewById(this, "unity_layout");
        //把Unity游戏加载进来        
        layout.addView(mUnityPlayer.getView());
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	
    	Pusher.onStop();
    	Player.onStop();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	Pusher.onResume();
    	Player.onResume();
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	
    	Pusher.StopPush();
    	Player.StopPlay();
    	
    	super.onDestroy();
    }
}
