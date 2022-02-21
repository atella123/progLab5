package lab5.common.commands;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public abstract class CollectionCommand extends Command {

    private CollectionManager<Person> manager;

    public CollectionCommand(CollectionManager<Person> manager) {
        super();
        this.manager = manager;
    }

    public CollectionCommand(IOManager io, CollectionManager<Person> manager) {
        super(io);
        this.manager = manager;
    }

    public CollectionManager<Person> getCollection() {
        return manager;
    }

    public void setCollection(CollectionManager<Person> manager) {
        this.manager = manager;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((manager == null) ? 0 : manager.hashCode());
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
        CollectionCommand other = (CollectionCommand) obj;
        if (manager == null) {
            if (other.manager != null) {
                return false;
            }
        } else if (!manager.equals(other.manager)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CollectionCommand";
    }

    public CollectionManager<Person> getManager() {
        return manager;
    }

    public void setManager(CollectionManager<Person> manager) {
        this.manager = manager;
    }

}
