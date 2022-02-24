package lab.parsers;

import lab.common.data.Color;
import lab.common.data.Coordinates;
import lab.common.data.Country;
import lab.common.data.Location;
import lab.common.data.Person;
import lab.io.IOManager;

public final class PersonParser {

    private PersonParser() {
    }

    public static Person parsePerson(Integer nextID, IOManager io) {
        // TODO FIX ALL USAGES
        String name = parseName(io);
        Coordinates coordinates = parseCoordinates(io);
        int height = parseHeight(io);
        String passportID = parsePassportID(io);
        Color eyeColor = parseEyeColor(io);
        Country nationality = parseNationality(io);
        Location location = parseLocation(io);
        Person p = new Person(name, coordinates, height, passportID, eyeColor, nationality, location);
        p.setID(nextID);
        return p;
    }

    public static String parseName(IOManager io) {
        io.write("Enter person name");
        return BasicParser.readValidString(io, Person.Validator::isValidName, "Person name can't be empty");
    }

    public static Coordinates parseCoordinates(IOManager io) {
        io.write("Enter person coordinates");
        return CoordinatesParser.parseCoordinates(io);
    }

    public static int parseHeight(IOManager io) {
        io.write("Enter person height");
        return BasicParser.readValidObject(io, Integer::parseInt, Person.Validator::isValidHeight,
                "Enter positive value", "Enter valid Integer");
    }

    public static String parsePassportID(IOManager io) {
        io.write("Enter person passportID");
        return BasicParser.readValidString(io, Person.Validator::isValidPassportID,
                "Enter valid passportID");
    }

    public static Color parseEyeColor(IOManager io) {
        io.write("Enter person eyeColor");
        return BasicParser.readEnumValue(io, Color.class);
    }

    public static Country parseNationality(IOManager io) {
        io.write("Enter person nationality");
        return BasicParser.readEnumValue(io, Country.class);
    }

    public static Location parseLocation(IOManager io) {
        io.write("Enter person location");
        return LocationParser.parseLocation(io);
    }

}
