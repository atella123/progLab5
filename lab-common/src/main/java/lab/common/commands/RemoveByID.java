package lab.common.commands;

import lab.common.data.Person;
import lab.common.parsers.BasicParser;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

public class RemoveByID extends CollectionCommand {

    private final String man = "remove_by_id id : удалить элемент из коллекции по его id";

    public RemoveByID(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public RemoveByID(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Integer id;
        try {
            id = BasicParser.parseInt(arg.replaceAll(" ", ""));
        } catch (Exception e) {
            this.getIO().write("Enter correct value for integer");
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        for (Person i : this.getManager().getCollection()) {
            if (i.getID().equals(id)) {
                this.getManager().getCollection();
                return CommandResponse.SUCCESS;
            }
        }
        return CommandResponse.NO_SUCH_ELEMENT;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "RemoveByID";
    }

    @Override
    public String getMan() {
        return man;
    };
}
