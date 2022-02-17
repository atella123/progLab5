package lab5.common.data;

import java.time.LocalDate;
import java.util.Objects;

import lab5.common.exceptions.IllegalFieldValueException;

public class Person implements Comparable<Person> {

    private static Integer lastID = 0;

    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого
                        // поля должно быть уникальным, Значение этого поля должно генерироваться
                        // автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private java.time.LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                              // автоматически
    private int height; // Значение поля должно быть больше 0
    private String passportID; // Длина строки должна быть не меньше 10, Поле может быть null
    private Color eyeColor; // Поле может быть null
    private Country nationality; // Поле не может быть null
    private Location location; // Поле не может быть null

    public Person(String name, Coordinates coordinates, int height, String passportID, Color eyeColor,
            Country nationality, Location location) {
        setID();
        setName(name);
        setCoordinates(coordinates);
        setCreationDate();
        setHeight(height);
        setPassportID(passportID);
        setEyeColor(eyeColor);
        setNationality(nationality);
        setLocation(location);
    }

    public Integer getID() {
        return id;
    }

    private void setID() {
        this.id = lastID++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Validator.isValidName(name)) {
            this.name = name;
        }
        throw new IllegalFieldValueException();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (Validator.isValidCoordinates(coordinates)) {
            this.coordinates = coordinates;
        }
        throw new IllegalFieldValueException();
    }

    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate() {
        creationDate = LocalDate.now();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (Validator.isValidHeight(height)) {
            this.height = height;
        }
        throw new IllegalFieldValueException();
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        if (Validator.isValidPassportID(passportID)) {
            this.passportID = passportID;
        }
        throw new IllegalFieldValueException();
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (Validator.isValidLocation(location)) {
            this.location = location;
        }
        throw new IllegalFieldValueException();
    }

    public static class Validator {
        public static boolean isValidName(String name) {
            if (Objects.nonNull(name)) {
                return name.isEmpty();
            }
            return false;
        }

        public static boolean isValidCoordinates(Coordinates coordinates) {
            return Objects.nonNull(coordinates);
        }

        public static boolean isValidHeight(int height) {
            return height > 0;
        }

        public static boolean isValidPassportID(String passportID) {
            if (Objects.nonNull(passportID)) {
                return passportID.length() >= 10;
            }
            return false;
        }

        public static boolean isValidLocation(Location location) {
            return Objects.nonNull(location);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((eyeColor == null) ? 0 : eyeColor.hashCode());
        result = prime * result + height;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
        result = prime * result + ((passportID == null) ? 0 : passportID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (eyeColor != other.eyeColor)
            return false;
        if (height != other.height)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nationality != other.nationality)
            return false;
        if (passportID == null) {
            if (other.passportID != null)
                return false;
        } else if (!passportID.equals(other.passportID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [coordinates=" + coordinates + ", creationDate=" + creationDate + ", eyeColor=" + eyeColor
                + ", height=" + height + ", id=" + id + ", location=" + location + ", name=" + name + ", nationality="
                + nationality + ", passportID=" + passportID + "]";
    }

    @Override
    public int compareTo(Person person) {
        return id - person.id;
    }
}
