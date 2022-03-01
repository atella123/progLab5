package lab.commands;

import java.util.Objects;
import java.util.Optional;

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
        if (Objects.nonNull(arg)) {
            try {
                id = Integer.parseInt(arg.replace(" ", ""));
            } catch (NumberFormatException e) {
                return new CommandResponse(CommandResult.ERROR, "Illegal argument");
            }
        } else {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument");
        }
        Optional<Person> person = getManager().removePersonByID(id);
        if (person.isPresent()) {
            return new CommandResponse(CommandResult.SUCCESS, new Person[0], new Person[] {person.get()});
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
