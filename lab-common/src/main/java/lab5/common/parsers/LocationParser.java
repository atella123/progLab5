package lab5.common.parsers;

import lab5.common.data.Location;
import lab5.common.util.IOManager;

public class LocationParser {

    public static Location parseLocation(IOManager io) {
        Float x = parseX(io);
        long y = parseY(io);
        String name = parseName(io);
        return new Location(x, y, name);
    }

    public static Float parseX(IOManager io) {
        io.write("Enter x coordinate for location");
        return BasicParser.readValidObject(io, BasicParser::parseFloat, Location.Validator::isValidX,
                "Enter valid Float", "x can't be null");
    }

    public static long parseY(IOManager io) {
        io.write("Enter y coordinate for location");
        return BasicParser.readObjectFromString(io, Long::valueOf, "Enter valid Long");
    }

    public static String parseName(IOManager io) {
        io.write("Enter location name");
        return BasicParser.readValidString(io, Location.Validator::isValidName, "Enter valid location name");
    }
}
