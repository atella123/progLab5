package lab.util;

import java.util.Objects;
import java.util.function.Predicate;

import lab.common.exceptions.StringIsNullException;
import lab.io.IOManager;

public final class DataReader {

    private DataReader() {

    }

    public static String readString(IOManager io) {
        String s = io.readLine();
        if (Objects.isNull(s)) {
            throw new StringIsNullException();
        }
        if ("".equals(s)) {
            return null;
        }
        return s;
    }

    public static String readValidString(IOManager io, Predicate<String> predicate,
            String illegalValueMessage) {
        String s = readString(io);
        while (!predicate.test(s)) {
            io.write(illegalValueMessage);
            s = readString(io);
        }
        return s;
    }

    public static <T> T readStringAsObject(IOManager io, StringConverter<T> converter, String illegalValueMessage) {
        T t;
        String s;
        while (true) {
            s = readString(io);
            try {
                t = converter.convert(s);
                return t;
            } catch (IllegalArgumentException | NullPointerException e) {
                io.write(illegalValueMessage);
            }
        }
    }

    public static <T> T readStringAsValidObject(IOManager io, StringConverter<T> converter, Predicate<T> predicate,
            String illegalValueMessage, String convertMessage) {
        T t = readStringAsObject(io, converter, illegalValueMessage);
        while (!predicate.test(t)) {
            io.write(illegalValueMessage);
            t = readStringAsObject(io, converter, convertMessage);
        }
        return t;
    }

    public static <E extends Enum<E>> E readEnumValue(IOManager io, Class<E> e) {
        String s;
        do {
            EnumUtil.printEnumValues(io, e);
            s = readString(io).toUpperCase();
        } while (!EnumUtil.isEnumValue(s, e));
        return Enum.valueOf(e, s);
    }
}
