using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Main : MonoBehaviour {

    void Awake()
    {
        Debug.Log("Main Init Start .");
        liteva.LiveBridge.Instance.Init();
    }

    // Use this for initialization
    void Start () {

    }
}
