package lab.common.commands;

import lab.common.util.CommandRunner;

public class History extends Command {

    private final String man = "history : вывести последние 11 команд (без их аргументов)";
    private CommandRunner commands;

    public History(CommandRunner commands) {
        super();
        this.commands = commands;
    }

    @Override
    public CommandResponse execute(String arg) {
        for (Command i : commands.getHistory()) {
            this.getIO().write(i.toString());
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "History";
    }

    public String getMan() {
        return man;
    };
}
