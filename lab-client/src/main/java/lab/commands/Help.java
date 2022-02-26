package lab.commands;

import java.util.Collection;

import lab.io.IOManager;

public final class Help extends Command {

    private Collection<Command> commands;

    public Help(IOManager io, Collection<Command> commands) {
        super(io);
        this.commands = commands;
    }

    public Help(Collection<Command> commands) {
        this.commands = commands;
    }

    @Override
    public CommandResponse execute(String arg) {
        for (Command i : commands) {
            this.getIO().write(i.getMan());
        }
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "Help";
    }

    @Override
    public String getMan() {
        return "help : вывести справку по доступным командам";
    };
}
