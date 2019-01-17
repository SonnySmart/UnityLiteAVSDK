
using System;
using UnityEngine;

namespace liteva
{
    public class PusherAndroid : Pusher
    {
        const string ClassName = "com.live.pusher.Pusher";

        AndroidJavaObject jo;

        public override void Init()
        {
            Debug.Log("PusherAndroid Init Start .");
            try
            {
                jo = new AndroidJavaObject(ClassName);
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
            Debug.Log("PusherAndroid Init Start .");
        }

        public override void StartPush(string url)
        {
            Debug.Log("PusherAndroid StartPush Start .");
            try
            {
                jo.CallStatic("StartPush", url);
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
            Debug.Log("PusherAndroid StartPush Done .");
        }

        public override void StopPush()
        {
            try
            {
                jo.CallStatic("StopPush");
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
        }
    }

}

