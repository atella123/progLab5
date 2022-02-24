package lab.commands;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class Clear extends CollectionCommand {

    public Clear(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public Clear(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getManager().getCollectionCopy().clear();
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Clear";
    }

    public String getMan() {
        return "clear : очистить коллекцию";
    }
}
