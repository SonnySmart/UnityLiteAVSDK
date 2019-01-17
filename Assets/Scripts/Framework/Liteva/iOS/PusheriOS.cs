using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

namespace liteva
{
    public class PusheriOS : Pusher
    {
        [DllImport("__Internal")]
        static extern void __pusher_init();

        [DllImport("__Internal")]
        static extern void __start_push(string url);

        [DllImport("__Internal")]
        static extern void __stop_push();

        public override void Init()
        {
            try
            {
                __pusher_init();
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }

        public override void StartPush(string url)
        {
            try
            {
                __start_push(url);
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }

        public override void StopPush()
        {
            try
            {
                __stop_push();
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }
    }
}


