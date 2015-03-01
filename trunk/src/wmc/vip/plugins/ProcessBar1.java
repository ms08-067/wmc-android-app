package wmc.vip.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.util.Log;

public class ProcessBar1 extends CordovaPlugin {

	ProgressDialog progressBar;
	
		
	
	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException  {
	
		progressBar = new ProgressDialog(cordova.getActivity());
		
		if (action.equals("show")) {
			
			String text = args.getString(0);
			show(text);
			callbackContext.success();
			return true;
			
		} else if (action.equals("hide")) {
			
			hide();
			callbackContext.success();
			return true;
		}

		return false;
	}
	
	/**
	 * This show the ProgressDialog
	 * @param text - Message to display in the Progress Dialog
	 */
	public void show(String text) {
		
		
		progressBar.setCancelable(true);
	
		progressBar.setMessage("Please wait ...");
		progressBar.setProgressStyle(ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		//This dialog can't be canceled by pressing the back key  
		progressBar.setCancelable(false);
		progressBar.setIndeterminate(false);
		progressBar.show();
		
		
	}

	/**
	 * This hide the ProgressDialog
	 */
	public void hide() {
		
		Log.d("hungphan", "dong diglog");
		progressBar.dismiss();
	}
	
}
