package lab5.common.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lab5.common.data.Coordinates;

public final class CoordinatesSerealizer implements JsonSerializer<Coordinates> {

    /**
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement
     */
    @Override
    public JsonElement serialize(Coordinates src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive("x: " + src.getX() + ", y: " + src.getY());
    }

}
