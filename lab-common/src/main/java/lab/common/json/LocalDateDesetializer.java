package lab.common.json;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateDesetializer implements JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String[] jsonString = json.getAsString().split("-");
        return LocalDate.of(Integer.valueOf(jsonString[0]), Integer.valueOf(jsonString[1]),
                Integer.valueOf(jsonString[2]));
    }

}
