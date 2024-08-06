import java.util.List;

public interface FamilyResearch {
    List<Person> getChildren(String name);
    Person[] getParents(String name);
    List<Person> getSiblings(String name);
}