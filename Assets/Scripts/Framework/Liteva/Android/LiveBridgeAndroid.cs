
using System;
using UnityEngine;

namespace liteva
{
    public class LiveBridgeAndroid : LiveBridge
    {
        const string ClassName = "com.live.LiveBridge";

        AndroidJavaObject jo;

        public override void Init()
        {
            Debug.Log("LiveBridgeAndroid Init Start .");

            Pusher.Init();
            Player.Init();

            try
            {
                jo = new AndroidJavaObject(ClassName);
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }

            try
            {
                jo.CallStatic("Init");
            }
            catch(Exception e)
            {
                Debug.LogException(e);
            }           

            Debug.Log("LiveBridgeAndroid Init Done .");
        }


    }
}


