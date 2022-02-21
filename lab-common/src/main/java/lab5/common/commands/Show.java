package lab5.common.commands;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class Show extends CollectionCommand {

    private final String man = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

    public Show(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public Show(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getIO().write("Person manager elements:");
        for (Person i : this.getManager().getCollection()) {
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
        return man;
    };
}
