package lab.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
        StringBuilder result = new StringBuilder();
        Map<String, Long> groupCounting = getManager().getCollectionCopy().stream()
                .collect(Collectors.groupingBy(Person::getPassportID, Collectors.counting()));
        for (Entry<String, Long> entry : groupCounting.entrySet()) {
            result.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        getIO().write(result.toString());
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "GroupCountingByPassportID";
    }

    @Override
    public String getMan() {
        return "group_counting_by_passport_id : сгруппировать элементы коллекции по значению поля passportID, вывести количество элементов в каждой группе";
    }
}
