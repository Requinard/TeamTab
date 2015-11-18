package Game.adapters;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by David on 11/18/2015.
 */
public class JsonAdapter {
    /**
     * Converts an object to it's json representatioon
     * author: David
     *
     * @param obj Objects that converts to json
     * @return A json representation of this object
     */
    public static String toString(Object obj) {
        return JsonAdapter.toString(obj, Object.class);
    }

    /**
     * Converts an object to it's json representation
     * author: David
     *
     * @param object Object that gets converted to json
     * @param t      Type of the object
     * @return Json representation of the object
     */
    public static String toString(Object object, Type t) {
        return new Gson().toJson(object, t).toString();
    }

    /**
     * Converts a json string to a specific object
     * author: David
     *
     * @param json String that is json formatted
     * @param t    Type of the object that it needs to get converted to
     * @return Objectified JSON
     */
    public static Object toObject(String json, Type t) {
        return new Gson().fromJson(json, t);
    }

    /**
     * Converts a Json string to a generic object
     * author: David
     *
     * @param json Json string that needs conversion
     * @return generic object representation of the JSON
     */
    public static Object toObject(String json) {
        return new Gson().fromJson(json, Object.class);
    }
}
