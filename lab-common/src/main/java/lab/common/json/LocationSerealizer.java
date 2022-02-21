package lab.common.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lab.common.data.Location;

public final class LocationSerealizer implements JsonSerializer<Location> {

    /**
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement
     */
    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName() + " at x: " + src.getX() + ", y: " + src.getY());

    }

}
