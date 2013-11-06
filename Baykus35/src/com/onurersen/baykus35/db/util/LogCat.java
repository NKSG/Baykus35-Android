package com.onurersen.baykus35.db.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

import android.util.Log;

/**
 * 
 * @author onurersen
 *
 */
public enum LogCat {

	INSTANCE;

	public void error(String tag, String msg) {
		Log.e("*" + tag, msg);
	}

	public void errorException(String tag, Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String msg = sw.toString();
		this.error(tag, msg);
	}

	public void warn(String tag, String msg) {
		Log.w("*" + tag, msg);
	}

	public void info(String tag, String msg) {
		Log.i("*" + tag, msg);
	}

	public void debug(String tag, String msg) {
		Log.d("*" + tag, msg);
	}

	public void verbose(String tag, String msg) {
		Log.v("*" + tag, msg);
	}

	public void memory() {
		Log.d("*MEMORY", "Free Memory : "
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	}

	public void timeInMilis(String s) {
		Log.d("*TIME", s + " : " + Calendar.getInstance().getTimeInMillis());
	}
}
