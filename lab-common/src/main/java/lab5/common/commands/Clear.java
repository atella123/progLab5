package lab5.common.commands;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class Clear extends CollectionCommand {

    private final String man = "clear : очистить коллекцию";

    public Clear(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public Clear(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getManager().getCollection().clear();
        return CommandResponse.SUCCESS;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Clear";
    }

    public String getMan() {
        return man;
    }
}
