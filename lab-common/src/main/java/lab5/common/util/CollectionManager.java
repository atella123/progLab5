package lab5.common.util;

import java.time.LocalDate;
import java.util.Collection;

public class CollectionManager<T> {

    private LocalDate initDate;
    private Collection<T> collection;

    public CollectionManager() {
        initDate = LocalDate.now();
    }

    public CollectionManager(Collection<T> collection) {
        initDate = LocalDate.now();
        this.collection = collection;
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }

    public LocalDate getInitDate() {
        return initDate;
    }
}
