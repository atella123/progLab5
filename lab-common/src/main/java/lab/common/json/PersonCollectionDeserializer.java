package lab.common.json;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
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
        JsonArray jsonArray = json.getAsJsonArray();
        Collection<Person> result = new HashSet<>();

        for (Integer i = 0; i < jsonArray.size(); i++) {
            result.add(context.deserialize(jsonArray.get(i), Person.class));
        }

        return result;
    }

}
