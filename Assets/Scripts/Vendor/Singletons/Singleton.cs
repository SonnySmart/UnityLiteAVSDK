
/** 
 *Author:       guo
 *Date:         2017
 *Description:  单例模板
*/ 

using UnityEngine;


public abstract class Singleton<T> : MonoBehaviour
	where T : MonoBehaviour
{
	private static T m_instance = null;

	public static T Instance
	{
		get { return m_instance; }
	}

	protected virtual void Awake()
	{
		m_instance = this as T;
	}
}
