package lab.commands;

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
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        for (Person i : this.getManager().getCollectionCopy()) {
            if (i.getNationality().compareTo(country) < 0) {
                this.getIO().write(i.toString());
            }
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "FilterLessThanNationality";
    }

    public String getMan() {
        return "filter_less_than_nationality nationality : вывести элементы, значение поля nationality которых меньше заданного";
    };
}
