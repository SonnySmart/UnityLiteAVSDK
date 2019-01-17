//
//  LivePusher.h
//  Unity-iPhone
//
//  Created by Apple on 2019/1/15.
//

#ifndef LivePusher_h
#define LivePusher_h

#import <Foundation/Foundation.h>
 
#ifdef __cplusplus
extern "C" {
#endif
    
extern void __pusher_init();
extern void __start_push(const char *url);
extern void __stop_push();

#ifdef __cplusplus
}
#endif

#endif /* LivePusher_h */
