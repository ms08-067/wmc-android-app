package com.wmcgroup.wmcvip;

import org.apache.cordova.CordovaActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

public class StartActivityUri extends CordovaActivity {

	public static final String PREFS_NAME = "jpHoloSharedPreferences";

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		super.init();
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if ( 0 != ( getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE ) ) {
				
				WebView.setWebContentsDebuggingEnabled(true);
			}
		}
		
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor = settings.edit();
		final Uri data = getIntent().getData();
		
		final String message = data.getQueryParameter("message");
		editor.putString("UriMessage", message);
		editor.commit();
		
		super.loadUrl("file:///android_asset/www/index.html");
	
	}
	
	
}
