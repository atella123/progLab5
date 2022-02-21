package lab.common.util;

import java.util.Arrays;

import lab.common.exceptions.IsNotEnumClass;

public final class EnumUtil {
    private EnumUtil() {
    }

    public static <E extends Enum<E>> boolean isEnumValue(String s, Class<E> e) {
        E[] constants = e.getEnumConstants();
        if (e.isEnum()) {
            return Arrays.stream(constants).map(x -> x.toString()).anyMatch(x -> x.equals(s));
        }
        throw new IsNotEnumClass();
    }

    public static <E extends Enum<E>> void printEnumValues(IOManager io, Class<E> e) {
        for (E i : e.getEnumConstants()) {
            io.write(i.toString());
        }
    }
}