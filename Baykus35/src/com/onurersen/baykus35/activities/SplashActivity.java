package com.onurersen.baykus35.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.onurersen.baykus35.R;

/**
 * 
 * @author onurersen
 * 
 */
public class SplashActivity extends Activity {

	private static String TAG = SplashActivity.class.getName();
	private static long SLEEP_TIME = 1;
	private static boolean showSplashScreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		if (!showSplashScreen) {
			setContentView(R.layout.splash);
			showSplashScreen = true;
			IntentLauncher launcher = new IntentLauncher();
			launcher.start();
		} else {
			Intent intent = new Intent(SplashActivity.this, RoutesActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
		}
	}
	
	/**
	 * Launches RoutesActivity from SplashScreen activity
	 * @author onurersen
	 *
	 */
	private class IntentLauncher extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(SLEEP_TIME * 1000);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			Intent intent = new Intent(SplashActivity.this, RoutesActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
		}
	}

}
