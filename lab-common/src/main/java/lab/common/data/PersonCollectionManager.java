package lab.common.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonCollectionManager {

    private LocalDate initDate;
    private Collection<Person> collection;
    private final TreeSet<Integer> idSet = new TreeSet<>();

    public PersonCollectionManager() {
        idSet.add(0);
        initDate = LocalDate.now();
    }

    public PersonCollectionManager(Collection<Person> collection) {
        initDate = LocalDate.now();
        this.collection = collection;
        idSet.add(0);
        idSet.addAll(collection.stream().map(person -> person.getID()).collect(Collectors.toSet()));
    }

    public void add(Person person) {
        if (idSet.contains(person.getID())) {
            person.setID(idSet.last() + 1);
        }
        idSet.add(person.getID());
        this.collection.add(person);
    }

    public Integer nextID() {
        return idSet.last() + 1;
    }

    public void remove(Person person) {
        idSet.remove(person.getID());
        collection.remove(person);
    }

    public void removeIf(Predicate<Person> filter) {
        collection.removeIf(filter);
        idSet.clear();
        idSet.addAll(collection.stream().map(person -> person.getID()).collect(Collectors.toSet()));
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
        return collection.stream().filter(p -> p.getID().equals(id)).findFirst().get();
    }

    public Collection<Person> getIf(Predicate<Person> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    @SuppressWarnings("rawtypes")
    public Class<? extends Collection> getCollectionType() {
        return collection.getClass();
    }

    public void clear() {
        collection.clear();
        idSet.clear();
        idSet.add(0);
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
