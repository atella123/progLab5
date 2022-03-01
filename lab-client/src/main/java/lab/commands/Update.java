package lab.commands;

import java.util.Optional;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.exceptions.StringIsNullException;
import lab.io.IOManager;
import lab.parsers.PersonParser;

public final class Update extends CollectionCommand {

    public Update(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public Update(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        int id;
        try {
            id = Integer.parseInt(arg.replace(" ", ""));
        } catch (NumberFormatException | NullPointerException e) {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument");
        }
        Optional<Person> personToUpdate = getManager().getPersonByID(id);
        if (personToUpdate.isPresent()) {
            try {
                getManager().updatePerson(personToUpdate.get(), PersonParser.parsePerson(getIO()));
                return new CommandResponse(CommandResult.SUCCESS);
            } catch (StringIsNullException e) {
                return new CommandResponse(CommandResult.END, "Person not parsed");
            }
        }
        return new CommandResponse(CommandResult.ERROR, "No element with id (" + id + ") is present");
    }

    @Override
    public String toString() {
        return "Update";
    }

    @Override
    public String getMan() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
