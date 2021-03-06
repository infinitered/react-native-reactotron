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
}

- (void)didDisconnect {
}

- (BOOL)runInBackground {
    return true;
}

- (void)sendCommand:(NSString *)seralizedCommand command:(NSDictionary *)obj {
    [self.connection send:seralizedCommand withParams:obj];
}

@end
