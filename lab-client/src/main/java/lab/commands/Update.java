package lab.commands;

import java.util.Set;
import java.util.stream.Collectors;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.common.exceptions.StringIsNullException;
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
        Integer id;
        try {
            id = Integer.parseInt(arg.replace(" ", ""));
        } catch (Exception e) {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument:\t" + arg);
        }
        Set<Person> personToUpdate = getManager().getCollectionCopy().stream().filter(p -> p.getID().equals(id))
                .collect(Collectors.toSet());
        if (!personToUpdate.isEmpty()) {
            try {
                personToUpdate.forEach(p -> getManager().updatePerson(p, PersonParser.parsePerson(getIO())));
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
