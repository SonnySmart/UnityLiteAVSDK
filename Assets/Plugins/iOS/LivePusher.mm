//
//  LivePusher.m
//  Unity-iPhone
//
//  Created by Apple on 2019/1/15.
//

#import "LivePusher.h"
#import "TXLivePush.h"
#import "TXLiveSDKTypeDef.h"
#import "UnityAppController.h"

static TXLivePush *_txLivePush = nil;
static UIView *_pusherView = nil;

#ifdef __cplusplus
extern "C" {
#endif
 
void __pusher_init()
{
    NSLog(@"__pusher_init call .");
    
    // 创建 LivePushConfig 对象，该对象默认初始化为基础配置
    TXLivePushConfig* _config = [[TXLivePushConfig alloc] init];
    //在 _config中您可以对推流的参数（如：美白，硬件加速，前后置摄像头等）做一些初始化操作，需要注意 _config不能为nil
    _txLivePush = [[TXLivePush alloc] initWithConfig: _config];
    [_txLivePush setVideoQuality:VIDEO_QUALITY_HIGH_DEFINITION adjustBitrate:NO adjustResolution:NO];
    //     beautyStyle     : 磨皮风格，目前支持 自然 和 光滑 两种。
    //     beautyLevel     : 磨皮级别，取值范围 0 ~ 9； 0 表示关闭 1 ~ 9值越大 效果越明显。
    //     whitenessLevel  : 美白级别，取值范围 0 ~ 9； 0 表示关闭 1 ~ 9值越大 效果越明显。
    //     ruddinessLevel  : 红润级别，取值范围 0 ~ 9； 0 表示关闭 1 ~ 9值越大 效果越明显。
    [_txLivePush setBeautyStyle:BEAUTY_STYLE_NATURE beautyLevel:9.f whitenessLevel:9.f ruddinessLevel:9.f];
    //setSpecialRatio 接口则可以设置滤镜的程度，从0到1，越大滤镜效果越明显，默认取值0.5。
    [_txLivePush setSpecialRatio:1];
    // 默认是前置摄像头，可以通过修改 LivePushConfig 的配置项 frontCamera 来修改这个默认值
    //[_txLivePush switchCamera];
    
    
}

void __start_push(const char *url)
{
    if (_txLivePush == nil)
        return;
    
    if (url == NULL)
        return;
    
    if (_txLivePush.isPublishing) {
        NSLog(@"__start_push isPublishing .");
        return;
    }
    
    NSLog(@"__start_push url:%s", url);
    
    //开启推流和本地预览
    UIWindow *window = UnityGetMainWindow();
    _pusherView = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    [window addSubview:_pusherView];
    [window sendSubviewToBack:_pusherView];
    
    NSString* rtmpUrl = [NSString stringWithUTF8String:url];
    [_txLivePush startPreview:_pusherView];  //_myView 就是step2中需要您指定的view
    [_txLivePush startPush:rtmpUrl];
}

void __stop_push()
{
    NSLog(@"__stop_push call .");
    
    if (_txLivePush == nil)
        return;
    
    if (_pusherView == nil)
        return;
    
    [_txLivePush stopPreview];
    [_txLivePush stopPush];
    _txLivePush.delegate = nil;
    [_pusherView removeFromSuperview];
    _pusherView = nil;
}

#ifdef __cplusplus
}
#endif
