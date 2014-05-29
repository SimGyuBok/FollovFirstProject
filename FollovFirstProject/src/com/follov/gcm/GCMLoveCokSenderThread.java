package com.follov.gcm;

import java.io.*;

import com.follov.*;
import com.follov.util.*;
import com.google.android.gcm.server.*;
import com.google.android.gcm.server.Message;

import android.os.*;

public class GCMLoveCokSenderThread extends Thread {
	public static String TAG = "GCMSenderThread";	
	Sender sender;
	String apiKey = "AIzaSyBlZQVIai6Odl7R8M4BGW0r1aOAPFFiv2g";
	String loverGcm = ""; // 일단 테스트 단계기 떄문에 스레드 클래스에 고정
	int flag;
	
	public Handler sendGCMHandler;
	
	public GCMLoveCokSenderThread(int flag) {
		this.flag = flag;
	}
	
	public void run() {
		switch (flag) {
		case FollovCode.SEND_LOVECOK_TO_LOVER: 
			sendLoveCok();
		}
	}
	
	private void sendLoveCok() {
		if (sender == null) return;
		
		Message msg = new Message.Builder().addData("type", "loveCok").build();
		
		try {
			sender.send(msg, loverGcm, 2);
		} catch (IOException e) {
			DebugUtil.MyStackTrace(TAG, e);
		}
	}	
}
