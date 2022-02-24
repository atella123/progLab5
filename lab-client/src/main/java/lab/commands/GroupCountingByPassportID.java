package lab.commands;

import java.util.HashMap;

import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class GroupCountingByPassportID extends CollectionCommand {

    public GroupCountingByPassportID(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public GroupCountingByPassportID(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        HashMap<String, Integer> groups = new HashMap<>();
        for (Person i : this.getManager().getCollectionCopy()) {
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
        return "group_counting_by_passport_i_d : сгруппировать элементы коллекции по значению поля passportID, вывести количество элементов в каждой группе";
    }
}
