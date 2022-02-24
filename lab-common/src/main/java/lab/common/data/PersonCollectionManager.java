package lab.common.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Predicate;

public class PersonCollectionManager {

    private LocalDate initDate;
    private Collection<Person> collection;
    private final TreeSet<Integer> idSet = new TreeSet<>();

    public PersonCollectionManager() {
        initDate = LocalDate.now();
    }

    public PersonCollectionManager(Collection<Person> collection) {
        initDate = LocalDate.now();
        this.collection = collection;
    }

    public void add(Person person) {
        if (idSet.contains(person.getID())) {
            person.setID(idSet.last() + 1);
        }
        idSet.add(person.getID());
        this.collection.add(person);
    }

    public void remove(Person person) {
        this.collection.remove(person);
    }

    public void removeIf(Predicate<Person> filter) {
        this.collection.removeIf(filter);
    }

    public ArrayList<Person> getCollectionCopy() {
        return new ArrayList<>(collection);
    }

    public void updatePerson(Person oldPerson, Person newPerson) {
        collection.remove(oldPerson);
        newPerson.setID(oldPerson.getID());
        newPerson.setCreationDate(oldPerson.getCreationDate());
        collection.add(newPerson);
    }

    public Person getPersonByID(Integer id) {
        for (Person i : collection) {
            if (i.getID().equals(id)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public void setCollection(Collection<Person> collection) {
        this.collection = collection;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public Integer getNextID() {
        return null;
    }
}
