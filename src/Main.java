import java.nio.file.Paths;
import java.util.Collections;

import chat.Chat;
import user.User;
import views.UserView;
import views.chat.ChatView;

public class Main {

    public static void main(String[] args) {
        /*
        Here we are creating the users
         */
        // this is the user i am receiving messages as
        User user1 = new User();
        user1.setEmail("ant@ant");
        // this the user i will be sending message as
        User user2 = new User();
        user2.setEmail("ton@ton");
        // here I create a new chat instance
        Chat chat = new Chat();
        //here i set the path to the chat file, where the messages appear
        chat.setPathToFile(Paths.get("./chats/ant@antton@ton.txt"));
        //add this chat to the both of the users
        user1.setUserChats(Collections.singletonList(chat));
        user2.setUserChats(Collections.singletonList(chat));
        // new Chat view, so the new chat window, which gets the information about the
        // created chat and about the current user
        UserView view = new ChatView(chat, user1);

        view.show();

    }
}