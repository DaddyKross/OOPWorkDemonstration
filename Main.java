package familytree;

import familytree.model.FamilyTree;
import familytree.model.Person;
import familytree.view.ConsoleFamilyTreeView;
import familytree.presenter.FamilyTreePresenter;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");

        FamilyTree<Person> model = new FamilyTree<>();
        ConsoleFamilyTreeView view = new ConsoleFamilyTreeView();
        FamilyTreePresenter presenter = new FamilyTreePresenter(model, view);

        presenter.run();
    }
}