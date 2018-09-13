package chat;

import java.io.IOException;
import java.util.List;

import user.User;

public interface ChatInteraction {

    List<String> getChatHistory(Chat chat);

    Chat createNewChat(User user) throws IOException;

}
