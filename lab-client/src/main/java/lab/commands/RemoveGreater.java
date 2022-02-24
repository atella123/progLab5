package lab.commands;

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
        this.getManager().removeIf(person -> PersonParser.parsePerson(this.getIO()).compareTo(person) < 0);
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
