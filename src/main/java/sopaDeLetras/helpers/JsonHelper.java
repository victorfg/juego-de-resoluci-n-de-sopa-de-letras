package sopaDeLetras.helpers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonHelper {
	public static String toJSON(ArrayList<?> list) {	 
		Type listType = new TypeToken<ArrayList<?>>() {}.getType();
	    Gson gson = new Gson();
	    //String encodedString = Base64.getEncoder().encodeToString(gson.toJson(list, listType).getBytes());
	    return gson.toJson(list, listType);
	}
}
