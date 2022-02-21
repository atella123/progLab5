package lab.common.parsers;

import java.util.regex.Pattern;
import java.util.function.Predicate;
import java.util.regex.Matcher;

import lab.common.util.StringConverter;
import lab.common.util.EnumUtil;
import lab.common.util.IOManager;
import lab.common.util.Reader;

public final class BasicParser {

    private BasicParser() {

    }

    public static int parseInt(Reader reader) {
        return parseInt(reader.readLine());
    }

    public static int parseInt(String s) {
        if (isNotBaseTenInt(s)) {
            Pattern p = Pattern.compile("(0[x|b]?)([0-9a-fA-F]+)");
            Matcher m = p.matcher(s);
            m.find();
            switch (m.group(1)) {
                case "0b":
                    final int bin = 2;
                    return Integer.parseInt(m.group(2), bin);
                case "0":
                    final int oct = 8;
                    return Integer.parseInt(m.group(2), oct);
                case "0x":
                    final int hex = 16;
                    return Integer.parseInt(m.group(2), hex);
                default:
                    throw new NumberFormatException();
            }
        }
        return Integer.parseInt(s);
    }

    public static boolean isNotBaseTenInt(String s) {
        return s.matches("(0[x|b]?)[0-9a-fA-F]+");
    }

    public static boolean isValidFloat(String s) {
        return s.matches("\\d+(\\.\\d+)?[f|F]?") || isNotBaseTenInt(s);
    }

    public static float parseFloat(Reader reader) {
        return parseFloat(reader.readLine());
    }

    public static float parseFloat(String s) {
        if (isValidFloat(s) && !isNotBaseTenInt(s)) {
            return Float.parseFloat(s);
        }
        return (float) parseInt(s);
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
            t = readObjectFromString(io, converter, illegalValueMessage);
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
