package lab.common.commands;

import java.util.Collection;

import lab.common.util.IOManager;

public class Help extends Command {

    private final String man = "help : вывести справку по доступным командам";
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
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Help";
    }

    @Override
    public String getMan() {
        return man;
    };
}
