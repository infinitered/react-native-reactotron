#if FB_SONARKIT_ENABLED

#import <Foundation/Foundation.h>

#import <FlipperKit/FlipperConnection.h>
#import <FlipperKit/FlipperPlugin.h>

@interface ReactotronFlipperPlugin : NSObject<FlipperPlugin>

@property id<FlipperConnection> connection;

-(void) sendCommand:(NSString*)seralizedCommand command:(NSDictionary *)obj;

@end

#endif
