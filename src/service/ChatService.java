package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import chat.Chat;
import chat.ChatInteraction;
import user.User;
import views.UserView;

public class ChatService implements ChatInteraction {

    private FileService fileService = new FileService();

    public List<String> getChatHistory(Chat chat){
        if(fileService.chatExists(chat.getPathToFile())){
            try {
                return Files.readAllLines(chat.getPathToFile());
            }catch (Exception e){
                e.printStackTrace();
                return Collections.emptyList();
            }
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public Chat createNewChat(User user) throws IOException {
        Chat chatToBeCreated = new Chat();
        chatToBeCreated.addUserToChat(user);
        Path pathToCreatedChat = fileService.createNewChatFile(chatToBeCreated);
        chatToBeCreated.setPathToFile(pathToCreatedChat);
        return chatToBeCreated;
    }

    public void displayNewMessagesToUser(List<String> newMessages){
        UserView.displayNewMessages(newMessages);
    }
}
