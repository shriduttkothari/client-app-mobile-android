package io.clientapp.mobile.android;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json parser utility singleton class.
 * 
 * @author shridutt.kothari
 * 
 */
public class JxsonUtil {
	private static JxsonUtil instance;
	private static ObjectMapper objectMapper = null;

	
	private JsonFactory jsonFactory = null;
	private JsonParser jp = null;
	

	/**
	 * private constructor
	 */
	private JxsonUtil() {
		objectMapper = new ObjectMapper();
		 jsonFactory = new JsonFactory();
	}

	/**
	 * Provides the singleton instance of JxsonUtil
	 * 
	 * @return instance of JxsonUtil
	 */
	public static JxsonUtil getInstance() {
		if (null == instance) {
			instance = new JxsonUtil();
		}
		return instance;
	}

	public <T> T parseJsonData(String jsonResponse, Class<T> resultClass) throws JsonParseException,
			JsonMappingException, IOException {
		jp = jsonFactory.createJsonParser(jsonResponse);
		T jsonDataModel = objectMapper.readValue(jp, resultClass);
		return (T) jsonDataModel;
	}

	public String buildJson(Object object)
			throws IOException {
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getJSONObject(Object object)
			throws JsonGenerationException, JsonMappingException, IOException,
			JSONException {
		JSONObject jsonObj = new JSONObject(
				objectMapper.writeValueAsString(object));
		return jsonObj;
	}
}
