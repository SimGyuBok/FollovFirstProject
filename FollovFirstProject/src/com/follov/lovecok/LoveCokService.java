package com.follov.lovecok;

import com.follov.*;
import com.follov.appwidget.*;
import com.follov.gcm.*;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class LoveCokService extends IntentService {

	public LoveCokService() {
		super("LoveCokService");
		Log.d("LoveCok", "Constructer in LoveCokService.");
	}
	
	public void onHandleIntent(Intent intent) {	
		GCMLoveCokSenderThread gcmst = new GCMLoveCokSenderThread(FollovCode.SEND_LOVECOK_TO_LOVER); // GCMSenderThread로 상대방의 gcmkey값 보내줘야 한다 
														 // 우선은 고정시키지만 실제 구현 시 서버에 있는 값가져온 것을 FollovCode나 
		                								 // 프리퍼런스에 담아놓고 꺼내서 넣는다.		
		gcmst.start();
	}
}