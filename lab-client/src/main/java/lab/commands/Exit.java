package lab.commands;

public final class Exit extends Command {

    @Override
    public CommandResponse execute(String arg) {
        return CommandResponse.END;
    }

    @Override
    public String toString() {
        return "Exit";
    }

    public String getMan() {
        return "exit : завершить программу (без сохранения в файл)";
    };
}
