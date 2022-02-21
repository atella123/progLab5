package lab5.common.commands;

import lab5.common.util.IOManager;

public abstract class Command {

    private IOManager io;

    abstract public CommandResponse execute(String arg);

    public abstract String getMan();

    public Command(IOManager io) {
        this.io = io;
    }

    public Command() {
        io = new IOManager();
    }

    public IOManager getIO() {
        return io;
    }

    public void setIO(IOManager io) {
        this.io = io;
    }

    @Override
    public String toString() {
        return "Command";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((io == null) ? 0 : io.hashCode());
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
        Command other = (Command) obj;
        if (io == null) {
            if (other.io != null)
                return false;
        } else if (!io.equals(other.io)) {
            return false;
        }
        return true;
    }
}
