package lab.commands;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class RemoveByID extends CollectionCommand {

    public RemoveByID(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public RemoveByID(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Integer id;
        try {
            id = Integer.parseInt(arg.replace(" ", ""));
        } catch (Exception e) {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument");
        }
        if (this.getManager().getCollectionCopy().stream().anyMatch(person -> person.getID().equals(id))) {
            Person person = getManager().getPersonByID(id);
            getManager().remove(person);
            return new CommandResponse(CommandResult.SUCCESS, new Person[0], new Person[] {person});
        }
        return new CommandResponse(CommandResult.ERROR, "No such element");

    }

    @Override
    public String toString() {
        return "RemoveByID";
    }

    @Override
    public String getMan() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
