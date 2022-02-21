package lab5.common.commands;

public enum CommandResponse {
    SUCCESS("Successfully executed command"),
    ILLEGAL_ARGUMENT("Illegal argument"),
    FILE_NOT_FOUND("File not found"),
    CANT_USE_FILE("Can't read/write from/to file"),
    COLLECTION_IS_EMPTY("Collection is empty"),
    NO_SUCH_ELEMENT("No such element in collection"),
    END("Successfully finished running");

    private final String message;

    CommandResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
