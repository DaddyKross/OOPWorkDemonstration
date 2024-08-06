public class Woman extends Person {
    public Woman(String name) {
        super(name);
    }

    @Override
    public String getGender() {
        return "Женский";
    }
}