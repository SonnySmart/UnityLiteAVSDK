using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

namespace liteva
{
    public class PlayeriOS : Player
    {
        [DllImport("__Internal")]
        static extern void __player_init();

        [DllImport("__Internal")]
        static extern void __start_play(string url);

        [DllImport("__Internal")]
        static extern void __stop_play();

        public override void Init()
        {
            try
            {
                __player_init();
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }

        public override void StartPlay(string url)
        {
            try
            {
                __start_play(url);
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }

        public override void StopPlay()
        {
            try
            {
                __stop_play();
            }
            catch (Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }
    }
}


