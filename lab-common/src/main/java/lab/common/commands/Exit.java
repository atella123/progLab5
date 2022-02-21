package lab.common.commands;

public class Exit extends Command {

    private final String man = "exit : завершить программу (без сохранения в файл)";

    /**
     * @param arg
     */
    @Override
    public CommandResponse execute(String arg) {
        return CommandResponse.END;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Exit";
    }

    public String getMan() {
        return man;
    };
}
