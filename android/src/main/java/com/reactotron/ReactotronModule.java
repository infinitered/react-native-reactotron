package com.reactotron;

import androidx.annotation.Nullable;

import com.facebook.flipper.core.FlipperArray;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.core.FlipperObject;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ReactotronModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReactotronModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        final FlipperClient client = AndroidFlipperClient.getInstance(this.reactContext);

        if (client != null) {
            final ReactotronFlipperPlugin plugin = client.getPluginByClass(ReactotronFlipperPlugin.class);

            plugin.setReactotron(this);
        }
    }

    @Override
    public String getName() {
        return "Reactotron";
    }

    @ReactMethod
    public void sendCommand(String method, ReadableMap obj) {
        final FlipperClient client = AndroidFlipperClient.getInstance(this.reactContext);

        if (client != null) {
            final ReactotronFlipperPlugin plugin = client.getPluginByClass(ReactotronFlipperPlugin.class);

            FlipperObject payload = buildObjectFromReadableMap(obj);

            plugin.sendCommand(method, payload);
        }
    }

    public void sendCommandToClient(FlipperObject obj) {
        this.sendEvent(this.reactContext, "CommandReceived", obj);
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable FlipperObject params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params.toJsonString());
    }

    private static FlipperObject buildObjectFromReadableMap(ReadableMap readableMap) {
        FlipperObject.Builder object = new FlipperObject.Builder();

        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();

            switch (readableMap.getType(key)) {
                case Null:
                    object.put(key, (Object) null);
                    break;
                case Boolean:
                    object.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    object.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    object.put(key, readableMap.getString(key));
                    break;
                case Map:
                    object.put(key, buildObjectFromReadableMap(readableMap.getMap(key)));
                    break;
                case Array:
                    object.put(key, buildArrayFromReadableArray(readableMap.getArray(key)));
                    break;
            }
        }

        return object.build();
    }

    private static FlipperArray buildArrayFromReadableArray(ReadableArray readableArray) {
        FlipperArray.Builder array = new FlipperArray.Builder();

        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    array.put(buildObjectFromReadableMap(readableArray.getMap(i)));
                    break;
                case Array:
                    array.put(buildArrayFromReadableArray(readableArray.getArray(i)));
                    break;
            }
        }

        return array.build();
    }
}
