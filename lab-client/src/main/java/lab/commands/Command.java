package lab.commands;

import lab.io.IOManager;
import lab.io.Reader;
import lab.io.Writter;

public abstract class Command {

    private IOManager io;

    public Command(IOManager io) {
        this.io = io;
    }

    public Command() {
        io = new IOManager();
    }

    public abstract CommandResponse execute(String arg);

    public abstract String getMan();

    public IOManager getIO() {
        return io;
    }

    public void setIO(IOManager newIo) {
        io = newIo;
    }

    public void setIO(Reader reader, Writter writter) {
        io.setIO(reader, writter);
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
            if (other.io != null) {
                return false;
            }
        } else if (!io.equals(other.io)) {
            return false;
        }
        return true;
    }
}
