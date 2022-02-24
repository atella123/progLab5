package lab.commands;

import java.util.Collection;
import java.util.HashSet;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;
import lab.parsers.PersonParser;

public final class RemoveGreater extends CollectionCommand {

    public RemoveGreater(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public RemoveGreater(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Person p = PersonParser.parsePerson(0, this.getIO());
        Collection<Person> toRemove = new HashSet<>();
        for (Person i : this.getManager().getCollectionCopy()) {
            if (p.compareTo(i) < 0) {
                toRemove.add(i);
            }
        }
        this.getManager().getCollectionCopy().removeAll(toRemove);
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "RemoveGreater";
    }

    @Override
    public String getMan() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    };
}
