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
        StringBuilder result = new StringBuilder();
        for (Command i : commands) {
            result.append(i.getMan())
                    .append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        getIO().write(result.toString());
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "Help";
    }

    @Override
    public String getMan() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((commands == null) ? 0 : commands.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Help other = (Help) obj;
        if (commands == null) {
            if (other.commands != null) {
                return false;
            }
        } else if (!commands.equals(other.commands)) {
            return false;
        }
        return true;
    }
}
