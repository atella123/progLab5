package lab.commands;

import java.util.stream.Collectors;

import lab.common.data.Country;
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
        } catch (IllegalArgumentException | NullPointerException e) {
            return new CommandResponse(CommandResult.ERROR, "Illegal argument");
        }
        return new CommandResponse(CommandResult.SUCCESS,
                getManager().filter(person -> person.getNationality().compareTo(country) < 0)
                        .map(Object::toString).collect(Collectors.joining("\n")));
    }

    @Override
    public String toString() {
        return "FilterLessThanNationality";
    }

    public String getMan() {
        return "filter_less_than_nationality nationality : вывести элементы, значение поля nationality которых меньше заданного";
    }
}
