package lab5.common.commands;

import lab5.common.data.Person;
import lab5.common.parsers.PersonParser;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class Add extends CollectionCommand {

    private final String man = "add {element} : добавить новый элемент в коллекцию";

    public Add(CollectionManager<Person> manager) {
        super(manager);
    }

    public Add(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getManager().getCollection().add(PersonParser.parsePerson(this.getIO()));
        return CommandResponse.SUCCESS;
    }

    @Override
    public String getMan() {
        return man;
    }

}
