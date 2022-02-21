package lab.common.json;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lab.common.data.Location;
import lab.common.exceptions.IllegalFieldValueException;

public final class LocationDeserealizer implements JsonDeserializer<Location> {

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Location location = new Location();
        String arg = json.getAsString();
        Pattern p = Pattern.compile("(\\w+) at x: (\\d+.?\\d*), y: (\\d+)");
        Matcher m = p.matcher(arg);
        m.find();
        int field = 1;
        try {
            location.setName(m.group(field++));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invaild value for location name");
        }
        try {
            location.setX(Float.parseFloat(m.group(field++)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invaild value for float x");
        }
        try {
            location.setY(Integer.parseInt(m.group(field++)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invalid value for integer y");
        }
        return location;
    }
}
