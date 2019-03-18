/**
 * 
 */
package io.clientapp.mobile.android;

import org.json.JSONObject;

/**
 * Implement this listener for getting callback for NetworkUtils.doUpload()
 * method.
 * 
 * @author shridutt.kothari
 * 
 */
public interface NetworkListener {

	/**
	 * On success, server response for given network task.
	 * 
	 * @param response
	 */
	void onSuccessResponse(JSONObject response);

	/**
	 * On failed, server response for given network task.
	 * 
	 * @param error
	 */
	void onErrorResponse(Throwable error);
}
