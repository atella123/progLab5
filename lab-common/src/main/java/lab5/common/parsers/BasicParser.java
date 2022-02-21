package lab5.common.parsers;

import java.util.regex.Pattern;
import java.util.function.Predicate;
import java.util.regex.Matcher;

import lab5.common.util.StringConverter;
import lab5.common.util.EnumUtil;
import lab5.common.util.IOManager;
import lab5.common.util.Reader;

public class BasicParser {
    public static int parseInt(Reader reader) {
        return parseInt(reader.readLine());
    }

    public static int parseInt(String s) {
        if (isNotBaseTenInt(s)) {
            Pattern p = Pattern.compile("(0[x|b]?)([0-9a-fA-F]+)");
            Matcher m = p.matcher(s);
            m.find();
            s = m.group(2);
            switch (m.group(1)) {
                case "0b":
                    return Integer.parseInt(s, 2);
                case "0":
                    return Integer.parseInt(s, 8);
                case "0x":
                    return Integer.parseInt(s, 16);
            }
            throw new NumberFormatException();
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
            String IllegalValueMessage) {
        String s;
        while (!predicate.test(s = io.readLine())) {
            io.write(IllegalValueMessage);
        }
        return s;
    }

    public static <T> T readValidObject(IOManager io, StringConverter<T> converter, Predicate<T> predicate,
            String IllegalValueMessage, String ConvertMessage) {
        T t;
        while (!predicate.test(t = readObjectFromString(io, converter, IllegalValueMessage))) {
            io.write(IllegalValueMessage);
        }
        return t;
    }

    public static <T> T readObjectFromString(IOManager io, StringConverter<T> converter, String IllegalValueMessage) {
        T t;
        String s;
        while (true) {
            s = io.readLine();
            try {
                t = converter.convert(s);
                return t;
            } catch (IllegalArgumentException e) {
                io.write(IllegalValueMessage);
            }
        }
    }

    public static <E extends Enum<E>> E readEnumValue(IOManager io, Class<E> e) {
        String s;
        do {
            EnumUtil.printEnumValues(io, e);
        } while (!EnumUtil.isEnumValue(s = io.readLine().toUpperCase(), e));
        return Enum.valueOf(e, s);
    }
}
