#if FB_SONARKIT_ENABLED

#import "Reactotron.h"
#import "ReactotronFlipperPlugin.h"

#import <FlipperKit/FlipperClient.h>

@implementation Reactotron

- (instancetype)init {
    FlipperClient *client = [FlipperClient sharedClient];
    ReactotronFlipperPlugin *myPlugin = [client pluginWithIdentifier:@"flipper-plugin-reactotron"];

    [myPlugin receiveCommand:^(NSDictionary *obj) {
        [self sendEventWithName:@"CommandReceived" body:obj];
    }];

    return self;
}

RCT_EXPORT_MODULE()

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"CommandReceived"];
}

RCT_EXPORT_METHOD(sendCommand:(NSString *)method command:(NSDictionary *)obj)
{
    FlipperClient *client = [FlipperClient sharedClient];
    ReactotronFlipperPlugin *myPlugin = [client pluginWithIdentifier:@"flipper-plugin-reactotron"];

    [myPlugin sendCommand:method command:obj];
}

@end

#endif
