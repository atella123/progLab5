package lab5.common.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lab5.common.data.Color;
import lab5.common.data.Coordinates;
import lab5.common.data.Country;
import lab5.common.data.Location;
import lab5.common.data.Person;

public final class PersonDeserializer implements JsonDeserializer<Person> {

    /**
     * @param json
     * @param typeOfT
     * @param context
     * @return Person
     * @throws JsonParseException
     */
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Person p = new Person();
        p.setName(jsonObject.get("name").getAsString());
        p.setCoordinates(context.deserialize(jsonObject.get("coordinates"), Coordinates.class));
        p.setHeight(jsonObject.get("height").getAsInt());
        p.setPassportID(jsonObject.get("passportID").getAsString());
        p.setEyeColor(Color.valueOf(jsonObject.get("eyeColor").getAsString()));
        p.setNationality(Country.valueOf(jsonObject.get("nationality").getAsString()));
        p.setLocation(context.deserialize(jsonObject.get("location"), Location.class));
        return p;
    }

}
