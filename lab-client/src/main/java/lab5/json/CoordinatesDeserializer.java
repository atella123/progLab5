package lab5.json;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lab5.common.data.Coordinates;
import lab5.common.exceptions.IllegalFieldValueException;

public final class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {

    /**
     * @param json
     * @param typeOfT
     * @param context
     * @return Coordinates
     * @throws JsonParseException
     */
    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Coordinates coordinates = new Coordinates();
        String arg = json.getAsString();
        Pattern p = Pattern.compile("x: (\\d+.?\\d*), y: (\\d*)");
        Matcher m = p.matcher(arg);
        m.find();
        try {
            coordinates.setX(Float.parseFloat(m.group(1)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invaild value for float x");
        }
        try {
            coordinates.setY(Integer.parseInt(m.group(2)));
        } catch (NumberFormatException | IllegalFieldValueException e) {
            throw new JsonParseException("Invalid value for integer y");
        }
        return coordinates;
    }

}
