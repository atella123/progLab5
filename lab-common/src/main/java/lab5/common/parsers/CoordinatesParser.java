package lab5.common.parsers;

import lab5.common.data.Coordinates;
import lab5.common.util.IOManager;

public class CoordinatesParser {

    public static Coordinates parseCoordinates(IOManager io) {
        Float x = parseX(io);
        Integer y = parseY(io);
        return new Coordinates(x, y);
    }

    public static Float parseX(IOManager io) {
        io.write("Enter x coordinate");
        return BasicParser.readValidObject(io, BasicParser::parseFloat, Coordinates.Validator::isValidX,
                "Enter valid Float", "x can't be null");
    }

    public static Integer parseY(IOManager io) {
        io.write("Enter y coordinate");
        return BasicParser.readValidObject(io, BasicParser::parseInt, Coordinates.Validator::isValidY,
                "Enter valid Integer", "y must be bigger than -322");
    }
}
