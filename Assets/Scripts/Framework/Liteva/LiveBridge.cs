

using UnityEngine;

namespace liteva
{
    public class LiveBridge
    {
        static LiveBridge _instance;
        /// <summary>
        /// 实例
        /// </summary>
        public static LiveBridge Instance
        {
            get
            {
                if (_instance != null)
                    return _instance;
#if UNITY_ANDROID
                return (_instance = new LiveBridgeAndroid());
#elif UNITY_IPHONE
                return (_instance = new LiveBridgeiOS());
#else
                return (_instance = new LiveBridge());
#endif
            }
        }
        /// <summary>
        /// 推流
        /// </summary>
        public Pusher Pusher =
#if UNITY_ANDROID
            new PusherAndroid();
#elif UNITY_IPHONE
            new PusheriOS();
#else
            new Pusher();
#endif
        /// <summary>
        /// 播放
        /// </summary>
        public Player Player =
#if UNITY_ANDROID
            new PlayerAndroid();
#elif UNITY_IPHONE
            new PlayeriOS();
#else
            new Player();
#endif
        /// <summary>
        /// 初始化
        /// </summary>
        public virtual void Init() { }
    }
}


