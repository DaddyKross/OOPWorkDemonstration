package familytree.model;

import familytree.util.PersonComparators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FamilyTree implements Serializable, Iterable<Person> {
    private static final long serialVersionUID = 1L;
    private List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person findPerson(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public void sortByName() {
        Collections.sort(people, PersonComparators.compareByName);
    }

    public void sortByBirthDate() {
        Collections.sort(people, PersonComparators.compareByBirthDate);
    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(person).append("\n");
        }
        return sb.toString();
    }
}