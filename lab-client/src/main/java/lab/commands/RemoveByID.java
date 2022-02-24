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
            id = Integer.parseInt(arg.replaceAll(" ", ""));
        } catch (Exception e) {
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        for (Person i : this.getManager().getCollectionCopy()) {
            if (i.getID().equals(id)) {
                this.getManager().getCollectionCopy();
                return CommandResponse.SUCCESS;
            }
        }
        return CommandResponse.NO_SUCH_ELEMENT;
    }

    @Override
    public String toString() {
        return "RemoveByID";
    }

    @Override
    public String getMan() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    };
}
