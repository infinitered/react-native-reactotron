#if FB_SONARKIT_ENABLED

#import "ReactotronFlipperPlugin.h"

#import <FlipperKit/FlipperClient.h>
#import <FlipperKit/FlipperConnection.h>
#import <FlipperKit/FlipperResponder.h>
#include <stdlib.h>

@implementation ReactotronFlipperPlugin

- (NSString*)identifier {
    return @"flipper-plugin-reactotron";
}

- (void)didConnect:(id<FlipperConnection>)connection {
    [connection receive:@"config"
              withBlock:^(NSDictionary* params, id<FlipperResponder> responder){
                  // set received port and host to dev tools
              }];
    
    NSString* r = [NSString stringWithFormat:@"%d",arc4random_uniform(74)];
    
    [connection send:r withParams:NSDictionary.new];
}

- (void)didDisconnect {
}

- (BOOL)runInBackground {
    return true;
}

@end

#endif
