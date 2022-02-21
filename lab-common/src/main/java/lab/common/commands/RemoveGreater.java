package lab.common.commands;

import java.util.Collection;
import java.util.HashSet;

import lab.common.data.Person;
import lab.common.parsers.PersonParser;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

public class RemoveGreater extends CollectionCommand {

    private final String man = "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";

    public RemoveGreater(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public RemoveGreater(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Person p = PersonParser.parsePerson(this.getIO());
        Collection<Person> toRemove = new HashSet<>();
        for (Person i : this.getManager().getCollection()) {
            if (p.compareTo(i) < 0) {
                toRemove.add(i);
            }
        }
        for (Person i : toRemove) {
            this.getManager().getCollection().remove(i);
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "RemoveGreater";
    }

    @Override
    public String getMan() {
        return man;
    };
}
