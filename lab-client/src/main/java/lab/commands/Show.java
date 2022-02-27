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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Person manager elements:\n");
        for (Person i : this.getManager().getCollectionCopy()) {
            stringBuilder.append(i.toString())
                    .append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        getIO().write(stringBuilder.toString());
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "Show";
    }

    @Override
    public String getMan() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
