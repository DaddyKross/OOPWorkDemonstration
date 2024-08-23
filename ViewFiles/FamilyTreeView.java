package familytree.view;

import familytree.model.Person;
import java.util.List;

public interface FamilyTreeView {
    void displayMenu();
    int getMenuChoice();
    String getInput(String prompt);
    void displayMessage(String message);
    void displayPerson(Person person);
    void displayPersonList(List<Person> people);
}
