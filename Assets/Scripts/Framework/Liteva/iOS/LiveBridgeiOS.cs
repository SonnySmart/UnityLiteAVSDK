using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

namespace liteva
{
    public class LiveBridgeiOS : LiveBridge
    {
        [DllImport("__Internal")]
        static extern void __bridge_init();

        public override void Init()
        {
            try
            {
                __bridge_init();
                Player.Init();
                Pusher.Init();
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }            
        }
    }
}


