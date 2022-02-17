package lab5.common.commands;

public abstract class Command {
    private String man;

    abstract CommandResponse execute(String arg);

    public String getMan() {
        return man;
    }

    @Override
    public String toString() {
        return "Command";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((man == null) ? 0 : man.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Command other = (Command) obj;
        if (man == null) {
            if (other.man != null)
                return false;
        } else if (!man.equals(other.man))
            return false;
        return true;
    }
}
