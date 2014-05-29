package com.follov.util;

import android.os.Handler;
import android.os.Message;

public class TimeCalThread extends Thread {
	Handler timeHandler;
	private int cancelCnt;
	
	public TimeCalThread(Handler handler, int cancelCnt) {
		timeHandler = handler;
		this.cancelCnt = cancelCnt;
	}
	
	public void run() {
		while (true) {
			cancelCnt--;
			Message msg = new Message();
			
			msg.what = 1;
			if (cancelCnt == 0) {
				msg.what = 0;
			}
			
			msg.arg1 = cancelCnt;		
			timeHandler.sendMessage(msg);
			try { Thread.sleep(1000); } catch (InterruptedException e) {;}	
		}
	}
}
