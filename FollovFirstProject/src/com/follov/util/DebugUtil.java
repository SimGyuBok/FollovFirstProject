package com.follov.util;

public class DebugUtil {
	
	public static void MyStackTrace(String tag, Throwable e) {
		String errorMsg;
		errorMsg = "Error: " + e.getMessage() + "\n";
		StackTraceElement[] elem = e.getStackTrace();
		
		for (int i = 0; i < elem.length; i++) {
			errorMsg += elem[i].toString() + "\n";
		}
		MyLog.e(tag, errorMsg);
	}
}
