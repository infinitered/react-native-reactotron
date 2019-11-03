#if FB_SONARKIT_ENABLED

#import "ReactotronFlipperPlugin.h"

#import <FlipperKit/FlipperClient.h>
#import <FlipperKit/FlipperConnection.h>
#import <FlipperKit/FlipperResponder.h>

@implementation ReactotronFlipperPlugin

- (NSString*)identifier {
    return @"flipper-plugin-reactotron";
}

- (void)didConnect:(id<FlipperConnection>)connection {
    self.connection = connection;

    [connection receive:@"sendReactotronCommand" withBlock:^(NSDictionary *params, id<FlipperResponder> responder) {
        self.commandFromDesktopReceivedHandler(params);
        [responder success:@{}];
    }];
}

- (void)didDisconnect {
}

- (BOOL)runInBackground {
    return true;
}

- (void)sendCommand:(NSString *)seralizedCommand command:(NSDictionary *)obj {
    [self.connection send:seralizedCommand withParams:obj];
}

- (void)receiveCommand:(commandFromDesktop) compblock {
    self.commandFromDesktopReceivedHandler = compblock;
}

@end

#endif
