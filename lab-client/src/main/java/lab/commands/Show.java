package lab.commands;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class Show extends CollectionCommand {

    public Show(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public Show(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getIO().write("Person manager elements:");
        for (Person i : this.getManager().getCollectionCopy()) {
            this.getIO().write(i.toString());
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Show";
    }

    @Override
    public String getMan() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    };
}
