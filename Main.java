package familytree;

import familytree.model.FamilyTree;
import familytree.model.Person;
import familytree.service.FamilyResearch;
import familytree.service.FileOperations;
import familytree.service.IFamilyResearch;
import familytree.service.IFileOperations;

import java.time.LocalDate;
import java.time.Period;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        Person ivan = new Person("Иван", "Мужской", LocalDate.of(1980, 6, 15));
        Person maria = new Person("Мария", "Женский", LocalDate.of(1982, 3, 20));
        Person anna = new Person("Анна", "Женский", LocalDate.of(2005, 11, 10));
        Person boris = new Person("Борис", "Мужской", LocalDate.of(2007, 7, 5));
        Person sergey = new Person("Сергей", "Мужской", LocalDate.of(2009, 9, 1));

        ivan.setMother(maria);
        anna.setFather(ivan);
        anna.setMother(maria);
        boris.setFather(ivan);
        boris.setMother(maria);
        sergey.setFather(ivan);
        sergey.setMother(maria);

        ivan.addChild(anna);
        ivan.addChild(boris);
        ivan.addChild(sergey);
        maria.addChild(anna);
        maria.addChild(boris);
        maria.addChild(sergey);

        familyTree.addPerson(ivan);
        familyTree.addPerson(maria);
        familyTree.addPerson(anna);
        familyTree.addPerson(boris);
        familyTree.addPerson(sergey);

        IFamilyResearch research = new FamilyResearch(familyTree);

        System.out.println("Дети Ивана: " + research.getChildren("Иван"));
        System.out.println("Родители Анны: " + java.util.Arrays.toString(research.getParents("Анна")));
        System.out.println("Братья и сестры Бориса: " + research.getSiblings("Борис"));
        System.out.println("Информация об Иване: " + ivan);
        System.out.println("Информация о Марии: " + maria);
        System.out.println("Возраст Ивана: " + calculateAge(ivan.getDateOfBirth()) + " лет");
        System.out.println("Возраст Марии: " + calculateAge(maria.getDateOfBirth()) + " лет");

        System.out.println("\nСортировка по имени:");
        familyTree.sortByName();
        for (Person person : familyTree) {
            System.out.println(person);
        }

        System.out.println("\nСортировка по дате рождения:");
        familyTree.sortByBirthDate();
        for (Person person : familyTree) {
            System.out.println(person);
        }

        IFileOperations fileOps = new FileOperations();

        try {
            fileOps.saveToFile("family_tree.ser", familyTree);
            System.out.println("\nСемейное дерево сохранено в файл.");

            FamilyTree loadedTree = fileOps.loadFromFile("family_tree.ser");
            System.out.println("Семейное дерево загружено из файла.");

            IFamilyResearch loadedResearch = new FamilyResearch(loadedTree);

            System.out.println("\nПроверка загруженных данных:");
            System.out.println("Дети Ивана: " + loadedResearch.getChildren("Иван"));
            System.out.println("Родители Анны: " + java.util.Arrays.toString(loadedResearch.getParents("Анна")));
            System.out.println("Братья и сестры Бориса: " + loadedResearch.getSiblings("Борис"));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}