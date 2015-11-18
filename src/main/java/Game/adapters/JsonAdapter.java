package Game.adapters;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by David on 11/18/2015.
 */
public class JsonAdapter {
    public static String toString(Object obj) {
        return JsonAdapter.toString(obj, Object.class);
    }

    public static String toString(Object object, Type t) {
        return new Gson().toJson(object, t).toString();
    }

    public static Object toObject(String json, Type t) {
        return new Gson().fromJson(json, t);
    }

    public static Object toObject(String json) {
        return new Gson().fromJson(json, Object.class);
    }
}
