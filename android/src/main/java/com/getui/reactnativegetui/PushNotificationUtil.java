package com.getui.reactnativegetui;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dieam.reactnativepushnotification.modules.RNPushNotificationHelper;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Jason on 2017/11/22.
 */

public class PushNotificationUtil {

    private static RNPushNotificationHelper mRNPushNotificationHelper;
    private static final Random mRandomNumberGenerator = new Random(System.currentTimeMillis());
    public static void addLocalNotification(Context context, String payload){

        if (mRNPushNotificationHelper == null) {
            mRNPushNotificationHelper = new RNPushNotificationHelper((Application)context);
        }
        try {

            JSONObject jsonObject = new JSONObject(payload);
            String idCode = "" + System.currentTimeMillis();
            String idStr = idCode.substring(idCode.length() - 8);
            WritableMap map = Arguments.createMap();

            map.putString("largeIcon", "icon");
            map.putString("smallIcon", "small_icon");
            map.putString("title", jsonObject.getString("title"));
            map.putString("message", jsonObject.getString("content"));
            map.putString("data", payload);
            map.putString("number", "1");
            Log.i("GEtui", "" + System.currentTimeMillis());
            map.putDouble("fireDate", (System.currentTimeMillis()));

            Bundle bundle = Arguments.toBundle(map);
            bundle.putString("id", String.valueOf(mRandomNumberGenerator.nextInt()));

            mRNPushNotificationHelper.sendToNotificationCentre(bundle);
//            mRNPushNotificationHelper.sendNotificationScheduled(bundle);

        } catch (Throwable t) {
            Log.e("GeTuiSdkPushReceiver", "Could not parse payload Data: " + payload);
        }

    }
}
