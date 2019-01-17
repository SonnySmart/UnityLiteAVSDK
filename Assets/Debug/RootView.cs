using liteva;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class RootView : MonoBehaviour {

    void Awake()
    {
        Transform Pusher = transform.Find("Pusher");
        Transform StopPusher = transform.Find("StopPusher");
		Transform Player = transform.Find("Player");
		Transform StopPlayer = transform.Find("StopPlayer");

        Pusher.GetComponent<Button>().onClick.AddListener(OnClickPusher);
        StopPusher.GetComponent<Button>().onClick.AddListener(OnClickStopPusher);
        Player.GetComponent<Button>().onClick.AddListener(OnClickPlayer);
        StopPlayer.GetComponent<Button>().onClick.AddListener(OnClickStopPlayer);
    }

    void OnClickPusher()
    {
		string url = "rtmp://40029.livepush.myqcloud.com/live/6666?bizid=40029&txSecret=d1fd8c7bd80b002cd49c9f4f86d243bb&txTime=5C3F54FF";
        LiveBridge.Instance.Pusher.StartPush(url);
    }

    void OnClickStopPusher()
    {
        LiveBridge.Instance.Pusher.StopPush();
    }

    void OnClickPlayer()
    {
		string url = "rtmp://zhibo.yuzaokeji.com/live/6666";
        LiveBridge.Instance.Player.StartPlay(url);
    }

    void OnClickStopPlayer()
    {
        LiveBridge.Instance.Player.StopPlay();
    }
}
