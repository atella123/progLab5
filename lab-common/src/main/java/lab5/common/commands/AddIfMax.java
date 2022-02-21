package lab5.common.commands;

import lab5.common.data.Person;
import lab5.common.parsers.PersonParser;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class AddIfMax extends CollectionCommand {

    private final String man = "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";

    public AddIfMax(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public AddIfMax(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Person p = PersonParser.parsePerson(this.getIO());
        boolean isMax = true;
        for (Person i : this.getManager().getCollection()) {
            isMax = p.compareTo(i) > 0;
        }
        if (isMax) {
            this.getManager().getCollection().add(p);
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "AddIfMax";
    }

    @Override
    public String getMan() {
        return man;
    }
}