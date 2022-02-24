package lab.commands;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class Info extends CollectionCommand {

    public Info(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public Info(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        StringBuilder builder = new StringBuilder();
        builder.append("Collection type: ");
        builder.append(this.getManager().getCollectionType());
        builder.append("\nInit date: ");
        builder.append(this.getManager().getInitDate().toString());
        builder.append("\nElement count: ");
        builder.append(this.getManager().getCollectionCopy().size());
        this.getIO().write(builder.toString());
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Info";
    }

    @Override
    public String getMan() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    };
}
