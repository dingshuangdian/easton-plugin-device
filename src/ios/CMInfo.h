/********* CMInfo.m Cordova Plugin Implementation *******/

#import <Cordova/CDVPlugin.h>

@interface CMInfo : CDVPlugin {
  // Member variables go here.
}

- (void)getCommonInfo:(CDVInvokedUrlCommand*)command;
 
- (void)exitApp:(CDVInvokedUrlCommand*)command;
    
@end

