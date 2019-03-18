package io.clientapp.mobile.android;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Singleton class for network operations.
 * 
 * It uses Volly.
 * 
 * @see <a
 *      href=" http://developer.android.com/training/volley/index.html">Volly</a>
 * 
 * 
 * @author shridutt.kothari
 * 
 */
public class NetworkUtils {

	private static final String TAG = "NetworkUtils";
	private static NetworkUtils networkUtils;
	private static RequestQueue mRequestQueue;

	public static final int METHOD_POST = Request.Method.POST;
	public static final int METHOD_GET = Request.Method.GET;
	public static final int METHOD_PUT = Request.Method.PUT;
	public static final int METHOD_DELETE = Request.Method.DELETE;

	private NetworkUtils(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		// Start the queue
		mRequestQueue.start();
	}

	public static NetworkUtils getInstance(Context context) {
		if (networkUtils == null) {
			networkUtils = new NetworkUtils(context);
		}
		return networkUtils;
	}

	/**
	 * Implement NetworkUtils.CallbackListener for getting callback for this
	 * method.
	 * 
	 * @param url
	 *            : url to be used for network call.
	 * @param method
	 *            : HTTP method type.
	 * @param jsonRequest
	 *            : JSON object to be sent
	 * @param callbackListener
	 *            : callback listener for getting success or error callback.
	 */
	public void doRestAPICall(String url, int method, JSONObject jsonRequest,
			final NetworkListener callbackListener) {
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url,
				jsonRequest, new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject response) {
						callbackListener.onSuccessResponse(response);
					};
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(null != error) {
							callbackListener.onErrorResponse(error.getCause());
						}
					}
				});
		mRequestQueue.add(jsObjRequest);
	}

}
