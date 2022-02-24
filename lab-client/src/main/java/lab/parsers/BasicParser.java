package lab.parsers;

import java.util.function.Predicate;

import lab.common.util.StringConverter;
import lab.io.IOManager;
import lab.util.EnumUtil;

public final class BasicParser {

    private BasicParser() {

    }

    public static String readValidString(IOManager io, Predicate<String> predicate,
            String illegalValueMessage) {
        String s = io.readLine();
        while (!predicate.test(s)) {
            io.write(illegalValueMessage);
            s = io.readLine();
        }
        return s;
    }

    public static <T> T readValidObject(IOManager io, StringConverter<T> converter, Predicate<T> predicate,
            String illegalValueMessage, String convertMessage) {
        T t = readObjectFromString(io, converter, illegalValueMessage);
        while (!predicate.test(t)) {
            io.write(illegalValueMessage);
            t = readObjectFromString(io, converter, convertMessage);
        }
        return t;
    }

    public static <T> T readObjectFromString(IOManager io, StringConverter<T> converter, String illegalValueMessage) {
        T t;
        String s;
        while (true) {
            s = io.readLine();
            try {
                t = converter.convert(s);
                return t;
            } catch (IllegalArgumentException e) {
                io.write(illegalValueMessage);
            }
        }
    }

    public static <E extends Enum<E>> E readEnumValue(IOManager io, Class<E> e) {
        String s;
        do {
            EnumUtil.printEnumValues(io, e);
            s = io.readLine().toUpperCase();
        } while (!EnumUtil.isEnumValue(s, e));
        return Enum.valueOf(e, s);
    }
}
