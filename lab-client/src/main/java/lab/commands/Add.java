package lab.commands;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;
import lab.parsers.PersonParser;

public final class Add extends CollectionCommand {

    public Add(PersonCollectionManager manager) {
        super(manager);
    }

    public Add(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        this.getManager().getCollectionCopy().add(PersonParser.parsePerson(0, this.getIO()));
        return CommandResponse.SUCCESS;
    }

    @Override
    public String getMan() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

}
