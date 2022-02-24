package lab.commands;

import java.util.Collection;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
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
            id = Integer.parseInt(arg.replaceAll(" ", ""));
        } catch (Exception e) {
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        Collection<Person> collection = this.getManager().getCollectionCopy();
        for (Person i : collection) {
            if (i.getID().equals(id)) {
                // TODO ADD NEXT ID
                this.getManager().updatePerson(i, PersonParser.parsePerson(0, this.getIO()));
                return CommandResponse.SUCCESS;
            }
        }
        return CommandResponse.NO_SUCH_ELEMENT;
    }

    @Override
    public String toString() {
        return "Update";
    }

    @Override
    public String getMan() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    };
}
