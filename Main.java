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

        IFileOperations fileOps = new FileOperations(familyTree);

        try {
            fileOps.saveToFile("family_tree.ser");
            System.out.println("Семейное дерево сохранено в файл.");

            fileOps.loadFromFile("family_tree.ser");
            System.out.println("Семейное дерево загружено из файла.");

            FamilyTree loadedTree = ((FileOperations) fileOps).getFamilyTree();
            IFamilyResearch loadedResearch = new FamilyResearch(loadedTree);

            System.out.println("Проверка загруженных данных:");
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