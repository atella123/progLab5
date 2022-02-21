package lab5.common.json;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lab5.common.data.Person;

public final class PersonCollectionSereailzer implements JsonSerializer<Collection<Person>> {

    /**
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement
     */
    @Override
    public JsonElement serialize(Collection<Person> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        for (Person i : src) {
            result.add(i.getID().toString(), context.serialize(i));
        }

        return result;
    }

}
