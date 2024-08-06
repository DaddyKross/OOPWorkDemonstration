import java.util.List;

public class Research {
    private FamilyTree familyTree;

    public Research(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    public List<Person> getChildren(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            return person.getChildren();
        }
        return null;
    }

    public Person[] getParents(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            return new Person[]{person.getFather(), person.getMother()};
        }
        return null;
    }

    public List<Person> getSiblings(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            Person father = person.getFather();
            Person mother = person.getMother();
            if (father != null) {
                return father.getChildren().stream()
                        .filter(child -> !child.equals(person))
                        .toList();
            } else if (mother != null) {
                return mother.getChildren().stream()
                        .filter(child -> !child.equals(person))
                        .toList();
            }
        }
        return null;
    }
}