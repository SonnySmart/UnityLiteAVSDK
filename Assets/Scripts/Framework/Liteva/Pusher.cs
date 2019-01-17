using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace liteva
{
    /// <summary>
    /// 推流
    /// </summary>
    public class Pusher
    {
        /// <summary>
        /// 初始化
        /// </summary>
        public virtual void Init() { }
        /// <summary>
        /// 开始推流
        /// </summary>
        /// <param name="url"></param>
        public virtual void StartPush(string url) { }
        /// <summary>
        /// 停止推流
        /// </summary>
        public virtual void StopPush() { }
    }
}

