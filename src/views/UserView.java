package views;

import java.util.List;

public interface UserView {

    void show();

    static void displayNewMessages(List<String> newMessages) {
        newMessages.forEach(System.out::println);
    }
}
