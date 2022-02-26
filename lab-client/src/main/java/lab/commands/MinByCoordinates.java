package lab.commands;

import java.util.Collection;

import lab.common.data.Coordinates;
import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class MinByCoordinates extends CollectionCommand {

    public MinByCoordinates(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public MinByCoordinates(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Collection<Person> collection = this.getManager().getCollectionCopy();
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
            return new CommandResponse(CommandResult.SUCCESS);
        }
        return new CommandResponse(CommandResult.ERROR, "Collection is empty");
    }

    @Override
    public String getMan() {
        return "min_by_coordinates : вывести любой объект из коллекции, значение поля coordinates которого является минимальным";
    }

    @Override
    public String toString() {
        return "MinByCoordinates";
    }
}
