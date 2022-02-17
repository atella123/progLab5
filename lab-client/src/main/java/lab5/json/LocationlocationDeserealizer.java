package lab5.json;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lab5.common.data.Location;
import lab5.common.exceptions.IllegalFieldValueException;

public final class LocationlocationDeserealizer implements JsonDeserializer<Location> {

    /**
     * @param json
     * @param typeOfT
     * @param context
     * @return Location
     * @throws JsonParseException
     */
    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Location location = new Location();
        String arg = json.getAsString();
        Pattern p = Pattern.compile("(\\w+) at x: (\\d+.?\\d*), y: (\\d*)");
        Matcher m = p.matcher(arg);
        m.find();
        try {
            location.setName(m.group(1));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invaild value for location name");
        }
        try {
            location.setX(Float.parseFloat(m.group(2)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invaild value for float x");
        }
        try {
            location.setY(Integer.parseInt(m.group(3)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invalid value for integer y");
        }
        return location;
    }
}