package lab.common.commands;

import lab.common.data.Person;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

public class Info extends CollectionCommand {

    private final String man = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";

    public Info(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public Info(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        StringBuilder builder = new StringBuilder();
        builder.append("Collection type: ");
        builder.append(this.getManager().getCollection().getClass().toString());
        builder.append("\nInit date: ");
        builder.append(this.getManager().getInitDate().toString());
        builder.append("\nElement count: ");
        builder.append(this.getManager().getCollection().size());
        this.getIO().write(builder.toString());
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Info";
    }

    @Override
    public String getMan() {
        return man;
    };
}
