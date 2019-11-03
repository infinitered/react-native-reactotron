#import <Foundation/Foundation.h>

#import <FlipperKit/FlipperConnection.h>
#import <FlipperKit/FlipperPlugin.h>

typedef void (^commandFromDesktop)(NSDictionary*);

@interface ReactotronFlipperPlugin : NSObject<FlipperPlugin>

@property id<FlipperConnection> connection;
@property commandFromDesktop commandFromDesktopReceivedHandler;

-(void) sendCommand:(NSString*)seralizedCommand command:(NSDictionary *)obj;

-(void) receiveCommand:(commandFromDesktop) compblock;

@end
