package lab.common.json;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lab.common.data.Person;

public final class PersonCollectionDeserializer implements JsonDeserializer<Collection<Person>> {

    /**
     * @param json
     * @param typeOfT
     * @param context
     * @return Collection<Person>
     * @throws JsonParseException
     */
    @Override
    public Collection<Person> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Collection<Person> result = new HashSet<>();

        for (String key : jsonObject.keySet()) {
            result.add(context.deserialize(jsonObject.get(key), Person.class));
        }

        return result;
    }

}
