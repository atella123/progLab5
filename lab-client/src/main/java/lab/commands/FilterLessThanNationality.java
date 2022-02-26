package lab.commands;

import java.util.stream.Collectors;

import lab.common.data.Country;
import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class FilterLessThanNationality extends CollectionCommand {

    public FilterLessThanNationality(IOManager io, PersonCollectionManager manager) {
        super(io, manager);
    }

    public FilterLessThanNationality(PersonCollectionManager manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Country country;
        try {
            country = Country.valueOf(arg.toUpperCase());
        } catch (Exception e) {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument");
        }
        getIO().write(getManager().getCollectionCopy().stream()
                .filter(person -> person.getNationality().compareTo(country) < 0).map(Person::toString)
                .collect(Collectors.joining("\n")));
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "FilterLessThanNationality";
    }

    public String getMan() {
        return "filter_less_than_nationality nationality : вывести элементы, значение поля nationality которых меньше заданного";
    }
}
