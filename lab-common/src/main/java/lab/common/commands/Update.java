package lab.common.commands;

import java.util.Collection;

import lab.common.data.Person;
import lab.common.parsers.BasicParser;
import lab.common.parsers.PersonParser;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

public class Update extends CollectionCommand {

    private final String man = "update id {element} : обновить значение элемента коллекции, id которого равен заданному";

    public Update(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public Update(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Integer id;
        try {
            id = BasicParser.parseInt(arg.replaceAll(" ", ""));
        } catch (Exception e) {
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        Collection<Person> collection = this.getManager().getCollection();
        for (Person i : collection) {
            if (i.getID().equals(id)) {
                IOManager io = this.getIO();
                i.setName(PersonParser.parseName(io));
                i.setCoordinates(PersonParser.parseCoordinates(io));
                i.setHeight(PersonParser.parseHeight(io));
                i.setPassportID(PersonParser.parsePassportID(io));
                i.setEyeColor(PersonParser.parseEyeColor(io));
                i.setNationality(PersonParser.parseNationality(io));
                i.setLocation(PersonParser.parseLocation(io));
                return CommandResponse.SUCCESS;
            }
        }
        return CommandResponse.NO_SUCH_ELEMENT;
    }

    @Override
    public String toString() {
        return "Update";
    }

    @Override
    public String getMan() {
        return man;
    };
}
