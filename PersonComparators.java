package familytree.util;

import familytree.model.Person;

import java.util.Comparator;

public class PersonComparators {
    public static Comparator<Person> compareByName = Comparator.comparing(Person::getName);

    public static Comparator<Person> compareByBirthDate = Comparator.comparing(Person::getDateOfBirth);
}