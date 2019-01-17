
using System;
using UnityEngine;

namespace liteva
{
    public class PlayerAndroid : Player
    {
        const string ClassName = "com.live.player.Player";

        AndroidJavaObject jo;

        public override void Init()
        {
            Debug.Log("PlayerAndroid Init Start .");
            try
            {
                jo = new AndroidJavaObject(ClassName);
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
            Debug.Log("PlayerAndroid Init Start .");
        }

        public override void StartPlay(string url)
        {
            Debug.Log("PlayerAndroid StartPlay Start .");
            try
            {
                jo.CallStatic("StartPlay", url);
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
            Debug.Log("PlayerAndroid StartPlay Done .");
        }

        public override void StopPlay()
        {
            try
            {
                jo.CallStatic("StopPlay");
            }
            catch (Exception e)
            {
                Debug.LogException(e);
            }
        }
    }

}

