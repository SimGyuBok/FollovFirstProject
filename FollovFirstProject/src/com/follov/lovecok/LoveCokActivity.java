package com.follov.lovecok;

import com.follov.*;
import com.follov.R.*;
import com.follov.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoveCokActivity extends Activity {
	private String notiMessage;
	private int cancelCnt = 10; // 다이얼로그가 꺼지는 시간(단위: 초)
	private TextView timeCntTextView;
	private TimeCalThread thread;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 화면이 꺼져 있으면 화면을 켜주는 코드 나중에 필요
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	    
		Bundle bun = getIntent().getExtras();
		notiMessage = bun.getString("notiMessage");
		
		setContentView(R.layout.love_cok);
		
		TextView adMessage = (TextView)findViewById(R.id.love_message);
		adMessage.setText(notiMessage);
		
		timeCntTextView = (TextView)findViewById(R.id.cancelCnt);
		timeCntTextView.setText("남은시간: "+cancelCnt);
		
		Button confirmButton = (Button)findViewById(R.id.confirmBtn);
		confirmButton.setOnClickListener(mConfirmBtnClickListener);
		Button cancelButton = (Button)findViewById(R.id.cancelBtn);
		cancelButton.setOnClickListener(mCancelBtnClickListener);

		thread = new TimeCalThread(timeHandler, cancelCnt);
		thread.setDaemon(true);
		thread.start();
	}
	
	private Button.OnClickListener mConfirmBtnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Me, too!", Toast.LENGTH_SHORT);
		}
	};
	
	private Button.OnClickListener mCancelBtnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
	
	Handler timeHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				finish();
			} else if (msg.what == 1) {
				timeCntTextView.setText("남은시간: "+msg.arg1);
			}
		}
	};
}
