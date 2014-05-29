package com.follov.gcm;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.follov.R;
import com.follov.lovecok.*;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends IntentService
{
	private static final String TAG = "GcmIntentService";

    public GcmIntentService() 
    {
        super("GcmIntentService");
    }
    

    @Override
    protected void onHandleIntent(Intent intent)
    {
    	Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        if (!extras.isEmpty())
        {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	String type = extras.getString("type");
            	
            	if (type.equals("loveCok")) {
            		Intent loveCokInent = new Intent(this, LoveCokActivity.class);
            		startActivity(loveCokInent);
            	}
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}

