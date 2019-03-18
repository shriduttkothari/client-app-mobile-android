package io.clientapp.mobile.android;

/**
 * Dweet Server utils.
 * 
 * @author shridutt.kothari
 * 
 */
public class DweetRestServerUtil {

	public static final String BASE_SERVER_URL = "https://dweet.io:443/";
	public static final String CREATE_OR_DWEET = "dweet/for/";
	public static final String GET_DWEET = "get/latest/dweet/for/";

	public static String getCreateOrDweetUrl(String thingName) {
		StringBuilder temp = new StringBuilder(BASE_SERVER_URL);
		temp.append(CREATE_OR_DWEET);
		temp.append(thingName);
		return temp.toString();
	}
	public static String getDweetUrl(String thingName) {
		StringBuilder temp = new StringBuilder(BASE_SERVER_URL);
		temp.append(GET_DWEET);
		temp.append(thingName);
		return temp.toString();
	}
	
}