package lab.common.commands;

import lab.common.data.Country;
import lab.common.data.Person;
import lab.common.util.CollectionManager;
import lab.common.util.IOManager;

public class FilterLessThanNationality extends CollectionCommand {

    private final String man = "filter_less_than_nationality nationality : вывести элементы, значение поля nationality которых меньше заданного";

    public FilterLessThanNationality(IOManager io, CollectionManager<Person> manager) {
        super(io, manager);
    }

    public FilterLessThanNationality(CollectionManager<Person> manager) {
        super(manager);
    }

    @Override
    public CommandResponse execute(String arg) {
        Country country;
        try {
            country = Country.valueOf(arg.toUpperCase());
        } catch (Exception e) {
            this.getIO().write("Enter correct value  for nationality");
            for (Country i : Country.values()) {
                this.getIO().write("\t" + i.toString());
            }
            return CommandResponse.ILLEGAL_ARGUMENT;
        }
        for (Person i : this.getManager().getCollection()) {
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
        return man;
    };
}
