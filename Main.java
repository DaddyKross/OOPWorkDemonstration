package familytree;

import familytree.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");
        UserInterface ui = new UserInterface();
        ui.start();
    }
}