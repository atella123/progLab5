package lab.commands;

import lab.util.CommandRunner;

public final class History extends Command {

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
        return "history : вывести последние 11 команд (без их аргументов)";
    };
}
