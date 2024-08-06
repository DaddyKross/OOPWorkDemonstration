public class Man extends Person {
    public Man(String name) {
        super(name);
    }

    @Override
    public String getGender() {
        return "Мужской";
    }
}