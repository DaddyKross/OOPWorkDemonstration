package familytree.ui;

import familytree.model.FamilyTree;
import familytree.model.Person;
import familytree.service.FamilyResearch;
import familytree.service.FileOperations;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    private FamilyTree<Person> familyTree;
    private FamilyResearch research;
    private FileOperations fileOps;
    private Scanner scanner;

    public UserInterface() {
        this.familyTree = new FamilyTree<>();
        this.research = new FamilyResearch(familyTree);
        this.fileOps = new FileOperations();
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    public void start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить человека");
            System.out.println("2. Найти человека");
            System.out.println("3. Показать всех членов семьи");
            System.out.println("4. Сортировать по имени");
            System.out.println("5. Сортировать по дате рождения");
            System.out.println("6. Сохранить дерево в файл");
            System.out.println("7. Загрузить дерево из файла");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    findPerson();
                    break;
                case 3:
                    showAllMembers();
                    break;
                case 4:
                    familyTree.sortByName();
                    System.out.println("Отсортировано по имени");
                    break;
                case 5:
                    familyTree.sortByBirthDate();
                    System.out.println("Отсортировано по дате рождения");
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    loadFromFile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addPerson() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите пол (М/Ж):");
        String genderInput = scanner.nextLine().trim().toUpperCase();
        String gender = genderInput.equals("М") ? "Мужской" : "Женский";
        System.out.println("Введите дату рождения (ГГГГ-ММ-ДД):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        Person person = new Person(name, gender, birthDate);
        familyTree.addMember(person);
        System.out.println("Человек добавлен в семейное дерево.");
    }
    private void findPerson() {
        System.out.println("Введите имя для поиска:");
        String name = scanner.nextLine();
        Person person = familyTree.findPerson(name);
        if (person != null) {
            System.out.println("Найдено: " + person);
        } else {
            System.out.println("Человек не найден.");
        }
    }

    private void showAllMembers() {
        System.out.println("Все члены семьи:");
        for (Person person : familyTree) {
            System.out.println(person);
        }
    }

    private void saveToFile() {
        System.out.println("Введите имя файла для сохранения:");
        String fileName = scanner.nextLine();
        try {
            fileOps.saveToFile(fileName, (FamilyTree<?>) familyTree);
            System.out.println("Семейное дерево сохранено в файл.");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        System.out.println("Введите имя файла для загрузки:");
        String fileName = scanner.nextLine();
        try {
            familyTree = (FamilyTree<Person>) fileOps.loadFromFile(fileName);
            research = new FamilyResearch(familyTree);
            System.out.println("Семейное дерево загружено из файла.");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
        }
    }
}