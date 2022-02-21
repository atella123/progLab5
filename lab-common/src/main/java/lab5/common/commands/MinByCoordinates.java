package lab5.common.commands;

import java.util.Collection;

import lab5.common.data.Coordinates;
import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class MinByCoordinates extends CollectionCommand {

    private final String man = "min_by_coordinates : вывести любой объект из коллекции, значение поля coordinates которого является минимальным";

    public MinByCoordinates(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public MinByCoordinates(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Collection<Person> collection = this.getManager().getCollection();
        if (collection.size() > 0) {
            Coordinates minC = new Coordinates(Float.MAX_VALUE, Integer.MAX_VALUE);
            Person minP = null;
            for (Person i : collection) {
                if (i.getCoordinates().compareTo(minC) < 0) {
                    minP = i;
                    minC = i.getCoordinates();
                }
            }
            this.getIO().write(minP.toString());
            return CommandResponse.SUCCESS;
        }
        return CommandResponse.COLLECTION_IS_EMPTY;
    }

    @Override
    public String getMan() {
        return man;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "MinByCoordinates";
    }
}
