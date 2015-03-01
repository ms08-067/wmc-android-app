package wmc.vip.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

import android.app.ProgressDialog;
import android.os.Handler;

public class ProcessBar extends CordovaPlugin {

	public static final String LOG_PROV = "PhoneGapLog";
	public static final String LOG_NAME = "ProcessBar Plugin";
	//private Toast toast = null;
	
	
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarbHandler = new Handler();

	private long fileSize = 0;
	
	
	
	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
		cordova.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				
				addButtonListener();
				
				
			}
		});
		return true;
	}
	
	public void addButtonListener() {

			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
				// create and display a new ProgressBarDialog
				progressBar = new ProgressDialog(cordova.getActivity());
				progressBar.setCancelable(true);
				//progressBar.setTitle("Loading");
				progressBar.setMessage("Please wait ...");
				progressBar.setProgressStyle(ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
				
				//This dialog can't be canceled by pressing the back key  
				progressBar.setCancelable(false);
				progressBar.setIndeterminate(false);
				progressBar.setProgress(0);
				progressBar.setMax(100);
				
				progressBar.show();
				progressBarStatus = 0;

				fileSize = 0;

				new Thread(new Runnable() {

					public void run() {
						while (progressBarStatus < 100) {

							// process some tasks
							progressBarStatus = downloadFile();

							// sleep 1 second (simulating a time consuming task...)
							try {
								Thread.sleep(1200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// Update the progress bar
							progressBarbHandler.post(new Runnable() {
								public void run() {
									
									progressBar.setProgress(progressBarStatus);
								}
							});
						}

						// if the file is downloaded,
						if (progressBarStatus >= 100) {

							// sleep 2 seconds, so that you can see the 100%
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// and then close the progressbar dialog
							progressBar.dismiss();
						}
					}
				}).start();

			
				}
			});	
		

	}
	
	// file download simulator...
		public int downloadFile() {

			while (fileSize <= 1000000) {

				fileSize++;

				if (fileSize == 100000) {
					return 10;
					
				} else if (fileSize == 200000) {
					return 20;
					
				} else if (fileSize == 300000) {
					return 30;

				} else if (fileSize == 400000) {
					return 40;

				} else if (fileSize == 500000) {
					
					return 50;
				} else if (fileSize == 700000) {
					
					return 70;
				} else if (fileSize == 800000) {
					
					return 80;
				}
				//...

			}

			return 100;

		}
	
	/*
	private void showToast(final String message, final int length) {
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				toast = Toast.makeText(cordova.getActivity(), message, length);
				toast.show();
			}
		});
	}	
	*/
}
