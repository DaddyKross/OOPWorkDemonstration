public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        Person ivan = new Man("Иван");
        Person maria = new Woman("Мария");
        Person anna = new Woman("Анна");
        Person boris = new Man("Борис");
        Person sergey = new Man("Сергей");

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

        FamilyResearch research = new Research(familyTree);

        System.out.println("Дети Ивана: " + research.getChildren("Иван"));
        System.out.println("Родители Анны: " + java.util.Arrays.toString(research.getParents("Анна")));
        System.out.println("Братья и сестры Бориса: " + research.getSiblings("Борис"));
        System.out.println("Пол Ивана: " + ivan.getGender());
        System.out.println("Пол Марии: " + maria.getGender());
    }
}