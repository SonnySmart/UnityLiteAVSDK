using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace liteva
{
    /// <summary>
    /// 播放器
    /// </summary>
    public class Player
    {
        /// <summary>
        /// 初始化
        /// </summary>
        public virtual void Init() { }
        /// <summary>
        /// 开始播放
        /// </summary>
        /// <param name="url"></param>
        public virtual void StartPlay(string url) { }
        /// <summary>
        /// 停止播放
        /// </summary>
        public virtual void StopPlay() { }
    }
}

