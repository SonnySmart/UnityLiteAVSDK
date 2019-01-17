//
//  LiveBridge.m
//  Unity-iPhone
//
//  Created by Apple on 2019/1/15.
//

#import "TXLiveBase.h"
#import "LiveBridge.h"

#ifdef __cplusplus
extern "C" {
#endif
    
void __bridge_init()
{
    NSLog(@"__bridge_init call .");
    
    [TXLiveBase setConsoleEnabled:NO];
    [TXLiveBase setAppID:@"1258433981"];
}

#ifdef __cplusplus
}
#endif


