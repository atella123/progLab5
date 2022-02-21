package lab.common.commands;

import lab.common.data.Person;
import lab.common.parsers.PersonParser;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

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
