package lab.commands;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class Clear extends CollectionCommand {

    public Clear(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public Clear(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Person[] res = getManager().getCollection().stream().toArray(Person[]::new);
        getManager().clear();
        return new CommandResponse(CommandResult.SUCCESS, new Person[0], res);
    }

    @Override
    public String toString() {
        return "Clear";
    }

    public String getMan() {
        return "clear : очистить коллекцию";
    }
}
