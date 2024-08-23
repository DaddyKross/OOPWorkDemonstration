package familytree.presenter;

import familytree.model.FamilyTree;
import familytree.model.Person;
import familytree.view.FamilyTreeView;
import familytree.service.FamilyResearch;
import familytree.service.FileOperations;

import java.time.LocalDate;

public class FamilyTreePresenter {
    private FamilyTree<Person> model;
    private FamilyTreeView view;
    private FamilyResearch research;
    private FileOperations fileOps;

    public FamilyTreePresenter(FamilyTree<Person> model, FamilyTreeView view) {
        this.model = model;
        this.view = view;
        this.research = new FamilyResearch(model);
        this.fileOps = new FileOperations();
    }

    public void run() {
        while (true) {
            view.displayMenu();
            int choice = view.getMenuChoice();

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
                    model.sortByName();
                    view.displayMessage("Отсортировано по имени");
                    break;
                case 5:
                    model.sortByBirthDate();
                    view.displayMessage("Отсортировано по дате рождения");
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
                    view.displayMessage("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addPerson() {
        String name = view.getInput("Введите имя: ");
        String genderInput = view.getInput("Введите пол (М/Ж): ");
        String gender = genderInput.trim().equalsIgnoreCase("М") ? "Мужской" : "Женский";
        String birthDateInput = view.getInput("Введите дату рождения (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(birthDateInput);

        Person person = new Person(name, gender, birthDate);
        model.addMember(person);
        view.displayMessage("Человек добавлен в семейное дерево.");
    }

    private void findPerson() {
        String name = view.getInput("Введите имя для поиска: ");
        Person person = model.findPerson(name);
        if (person != null) {
            view.displayMessage("Найдено:");
            view.displayPerson(person);
        } else {
            view.displayMessage("Человек не найден.");
        }
    }

    private void showAllMembers() {
        view.displayMessage("Все члены семьи:");
        view.displayPersonList(model.getMembers());
    }

    private void saveToFile() {
        String fileName = view.getInput("Введите имя файла для сохранения: ");
        try {
            fileOps.saveToFile(fileName, model);
            view.displayMessage("Семейное дерево сохранено в файл.");
        } catch (Exception e) {
            view.displayMessage("Ошибка при сохранении: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        String fileName = view.getInput("Введите имя файла для загрузки: ");
        try {
            model = (FamilyTree<Person>) fileOps.loadFromFile(fileName);
            research = new FamilyResearch(model);
            view.displayMessage("Семейное дерево загружено из файла.");
        } catch (Exception e) {
            view.displayMessage("Ошибка при загрузке: " + e.getMessage());
        }
    }
}
