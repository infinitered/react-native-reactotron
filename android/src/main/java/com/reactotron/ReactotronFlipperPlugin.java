package com.reactotron;

import com.facebook.flipper.core.FlipperConnection;
import com.facebook.flipper.core.FlipperObject;
import com.facebook.flipper.core.FlipperPlugin;
import com.facebook.flipper.core.FlipperReceiver;
import com.facebook.flipper.core.FlipperResponder;

public class ReactotronFlipperPlugin implements FlipperPlugin {
    private FlipperConnection connection;
    private ReactotronModule reactotron;

    @Override
    public String getId() {
        return "flipper-plugin-reactotron";
    }

    @Override
    public void onConnect(FlipperConnection connection) throws Exception {
        this.connection = connection;

        this.connection.receive("sendReactotronCommand", new FlipperReceiver() {
            @Override
            public void onReceive(FlipperObject params, FlipperResponder responder) throws Exception {
                if (reactotron != null) {
                    reactotron.sendCommandToClient(params);
                }

                responder.success();
            }
        });
    }

    @Override
    public void onDisconnect() throws Exception {

    }

    @Override
    public boolean runInBackground() {
        return true;
    }

    public void sendCommand(String seralizedCommand, FlipperObject obj) {
        if (this.connection != null) {
            this.connection.send(seralizedCommand, obj);
        }
    }

    // TODO: Consider if this is how I really should do it. I feel like there is a more "Java" way to do this.
    // Look at on FlipperConnect.receive does it!
    public void setReactotron(ReactotronModule reactotron) {
        this.reactotron = reactotron;
    }
}
