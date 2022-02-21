package lab5.common.commands;

import java.util.HashMap;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class GroupCountingByPassportID extends CollectionCommand {

    private final String man = "group_counting_by_passport_i_d : сгруппировать элементы коллекции по значению поля passportID, вывести количество элементов в каждой группе";

    public GroupCountingByPassportID(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public GroupCountingByPassportID(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        HashMap<String, Integer> groups = new HashMap<>();
        for (Person i : this.getManager().getCollection()) {
            if (!groups.containsKey(i.getPassportID())) {
                groups.put(i.getPassportID(), 0);
            }
            groups.put(i.getPassportID(), groups.get(i.getPassportID()) + 1);
        }
        for (String i : groups.keySet()) {
            this.getIO().write(i + " : " + groups.get(i));
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "GroupCountingByPassportID";
    }

    @Override
    public String getMan() {
        return man;
    }
}
