package com.wmcgroup.wmcvip;

import org.apache.cordova.CordovaActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;


public class StartActivity extends CordovaActivity {

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

		super.loadUrl("file:///android_asset/www/index.html");

	}
	
	

}


 