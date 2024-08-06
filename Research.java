import java.util.List;

public class Research implements FamilyResearch {
    private FamilyTree familyTree;

    public Research(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    @Override
    public List<Person> getChildren(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            return person.getChildren();
        }
        return null;
    }

    @Override
    public Person[] getParents(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            return new Person[]{person.getFather(), person.getMother()};
        }
        return null;
    }

    @Override
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