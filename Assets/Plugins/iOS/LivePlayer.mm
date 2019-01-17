//
//  LivePlayer.m
//  Unity-iPhone
//
//  Created by Apple on 2019/1/15.
//

#import "LivePlayer.h"
#import "TXLivePlayer.h"

static TXLivePlayer *_txLivePlayer = nil;
static UIView *_playerView = nil;

#ifdef __cplusplus
extern "C" {
#endif
    
void __player_init()
{
    NSLog(@"__player_init call .");
    
    _txLivePlayer = [[TXLivePlayer alloc] init];
    
    TXLivePlayConfig*  _config = [[TXLivePlayConfig alloc] init];
    //极速模式
    _config.bAutoAdjustCacheTime   = YES;
    _config.minAutoAdjustCacheTime = 1;
    _config.maxAutoAdjustCacheTime = 1;
    
    [_txLivePlayer setConfig:_config];
}

void __start_play(const char *url)
{
    if (_txLivePlayer == nil)
        return;
    
    if (url == NULL)
        return;
    
    if (_txLivePlayer.isPlaying) {
        NSLog(@"__start_play isPlaying .");
        return;
    }
    
    NSLog(@"__start_play url:%s", url);
    
    //开启推流和本地预览
    UIWindow *window = UnityGetMainWindow();
    _playerView = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    [window addSubview:_playerView];
    [window sendSubviewToBack:_playerView];
    //用 setupVideoWidget 给播放器绑定决定渲染区域的view，其首个参数 frame 在 1.5.2 版本后已经被废弃
    [_txLivePlayer setupVideoWidget:CGRectMake(0, 0, 0, 0) containView:_playerView insertIndex:0];
    
    NSString *rtmpUrl = [NSString stringWithUTF8String:url];
    [_txLivePlayer startPlay:rtmpUrl type:PLAY_TYPE_LIVE_RTMP_ACC];
}

void __stop_play()
{
    NSLog(@"__stop_play call .");
    
    if (_txLivePlayer == nil)
        return;
    
    if (_playerView == nil)
        return;
    
    // 停止播放
    [_txLivePlayer stopPlay];
    [_txLivePlayer removeVideoWidget]; // 记得销毁view控件
    [_playerView removeFromSuperview];
    _playerView = nil;
}
#ifdef __cplusplus
}
#endif
