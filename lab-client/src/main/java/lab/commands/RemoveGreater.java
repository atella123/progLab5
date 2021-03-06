package lab.commands;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.exceptions.StringIsNullException;
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
        Person p;
        try {
            p = PersonParser.parsePerson(this.getIO());
        } catch (StringIsNullException e) {
            return new CommandResponse(CommandResult.END, "Person not parsed");
        }
        return new CommandResponse(CommandResult.SUCCESS, new Person[0],
                getManager().removeIf(person -> p.compareTo(person) < 0).toArray(new Person[0]));
    }

    @Override
    public String toString() {
        return "RemoveGreater";
    }

    @Override
    public String getMan() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}
