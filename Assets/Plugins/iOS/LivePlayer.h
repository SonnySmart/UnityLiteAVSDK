//
//  LivePlayer.h
//  Unity-iPhone
//
//  Created by Apple on 2019/1/15.
//

#ifndef LivePlayer_h
#define LivePlayer_h

#import <Foundation/Foundation.h>

#ifdef __cplusplus
extern "C" {
#endif

extern void __player_init();
extern void __start_play(const char *url);
extern void __stop_play();

#ifdef __cplusplus
}
#endif

#endif /* LivePlayer_h */
