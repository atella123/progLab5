package lab.common.data;

import java.util.Objects;

import lab.common.exceptions.IllegalFieldValueException;

public class Coordinates implements Comparable<Coordinates> {
    private Float x; // Поле не может быть null
    private Integer y; // Значение поля должно быть больше -322, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        if (!Validator.isValidX(x)) {
            throw new IllegalFieldValueException();
        }
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (!Validator.isValidY(y)) {
            throw new IllegalFieldValueException();
        }
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Coordinates other = (Coordinates) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinates [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        return (Float.floatToIntBits(this.x) + this.y) - (Float.floatToIntBits(coordinates.x) + coordinates.y);
    }

    public static class Validator {
        private static final int MIN_Y = -322;

        public static boolean isValidX(Float x) {
            return Objects.nonNull(x);
        }

        public static boolean isValidY(Integer y) {
            return Objects.nonNull(y) && y > MIN_Y;
        }
    }
}
