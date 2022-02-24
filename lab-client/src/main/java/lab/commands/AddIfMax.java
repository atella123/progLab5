package lab.commands;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;
import lab.parsers.PersonParser;

public final class AddIfMax extends CollectionCommand {

    public AddIfMax(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public AddIfMax(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Person p = PersonParser.parsePerson(this.getIO());
        boolean isMax = true;
        for (Person i : this.getManager().getCollectionCopy()) {
            isMax = p.compareTo(i) > 0;
        }
        if (isMax) {
            this.getManager().add(p);
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "AddIfMax";
    }

    @Override
    public String getMan() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
