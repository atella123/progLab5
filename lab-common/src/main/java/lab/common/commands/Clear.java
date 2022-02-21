package lab.common.commands;

import lab.common.data.Person;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

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
