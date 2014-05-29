package com.follov.appwidget;

import com.follov.*;
import com.follov.R.*;
import com.follov.lovecok.*;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class MySimpleWidget extends AppWidgetProvider {	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
//		SharedPreferences prefs = context.getSharedPreferences("LoveCok", 0);
//		SharedPreferences.Editor edit = prefs.edit();
//		edit.putInt("updateCnt", updateCnt);

  		final int N = appWidgetIds.length;
  
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            updateWidget(context, appWidgetManager, appWidgetId);
//            RemoteViews views = buildViews(context);
//            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
	}
	
	private void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_widget_layout);
		views.setTextViewText(R.id.simple_widget_layout_text, "Love Point: " + LoveCokBroadcastReceiver.updateCnt);
		
		Intent intent = new Intent(context, LoveCokBroadcastReceiver.class);
		intent.setAction(LoveCokBroadcastReceiver.ACTION_LOVECOK);
		intent.putExtra("appWidgetId", appWidgetId);
		
//		Intent intent2 = new Intent(context, MySimpleWidget.class);
		
		PendingIntent pi = PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
		
		views.setOnClickPendingIntent(R.id.lovecokbtn, pi);
		
		Log.d("LoveCok", "updateWidget.");
		
		appWidgetManager.updateAppWidget(appWidgetId, views);
//		context.startService(intent);
	}
	
/*	
	private PendingIntent buildActivityIntent(Context context) {
//		Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://google.com"));
//		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
//		return pi;

		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.androidhuman.example.SimpleWidget", "com.androidhuman.example.SimpleWidget.LoveCokService"));
//		context.startService(intent);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		return pi;
	}
	
	private RemoteViews buildViews(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_widget_layout);
		views.setOnClickPendingIntent(R.id.simple_widget_layout_activity, buildActivityIntent(context));
		return views;
	}
*/
}
