package com.follov.lovecok;

import com.follov.util.*;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LoveCokBroadcastReceiver extends BroadcastReceiver {
	public String TAG = "LoveCokBroadcastReceiver";
	public final static String ACTION_LOVECOK = "LoveCok";
	public static int updateCnt = 0;
	private String dialogMsg;
	
	public void onReceive(Context context, Intent intent) {
		Intent popupIntent = new Intent(context, LoveCokService.class);
		PendingIntent pi = PendingIntent.getService(context, updateCnt, popupIntent, PendingIntent.FLAG_ONE_SHOT);
		
		try {
			pi.send();
			MyLog.d(TAG, "onReceive and send()");
		} catch (CanceledException e) {
			MyLog.d("CanceledException", e.getMessage());
		}
		
/*		
		dialogMsg = "Love Count: " + ++this.updateCnt;

		Bundle bun = new Bundle();
		bun.putString("notiMessage", dialogMsg);
		
		Intent popupIntent = new Intent(context, LoveCokActivity.class);
		popupIntent.putExtras(bun);
		popupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		PendingIntent pi = PendingIntent.getActivity(context, updateCnt, popupIntent, PendingIntent.FLAG_ONE_SHOT);
		
		try {
			pi.send();
		} catch (CanceledException e) {
			Log.d("CanceledException", e.getMessage());
		}
*/
	}
}
