package lab.parsers;

import lab.common.data.Coordinates;
import lab.io.IOManager;
import lab.util.DataReader;

public final class CoordinatesParser {

    private CoordinatesParser() {
    }

    public static Coordinates parseCoordinates(IOManager io) {
        Float x = parseX(io);
        Integer y = parseY(io);
        return new Coordinates(x, y);
    }

    public static Float parseX(IOManager io) {
        io.write("Enter x coordinate");
        return DataReader.readStringAsObject(io, Float::parseFloat, "Enter valid Float", false);
    }

    public static Integer parseY(IOManager io) {
        io.write("Enter y coordinate");
        return DataReader.readStringAsValidObject(io, Integer::parseInt, Coordinates.Validator::isValidY,
                "Enter valid Integer", "y must be bigger than -322", false);
    }
}
