package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chat.Chat;
import user.User;

public class MessageWatcherThread extends Thread {

    private Chat chatToMonitor;
    private User user;
    private ChatService chatService = new ChatService();
    private List<String> oldMessagesList = new ArrayList<>();
    private List<String> newMessagesList = new ArrayList<>();

    public MessageWatcherThread(Chat chat, User user) {
        this.chatToMonitor = chat;
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            if (chatToMonitor.getPathToFile().toFile().canRead()) {
                List<String> extractedNewMessages = new ArrayList<>();
                updateStoredMessagesInfo();
                if (isNewMessagesPresent()) {
                    extractNewMessages(extractedNewMessages);
                    chatService.displayNewMessagesToUser(extractedNewMessages);
                    oldMessagesList = newMessagesList;
                }

            }
        }
    }

    private BufferedReader createBufferedReaderWithPathToChatFile() {
        BufferedReader reader = null;
        File fileToRead = chatToMonitor.getPathToFile().toFile();
        if (fileToRead.canRead()) {
            try {
                reader = new BufferedReader(new FileReader(fileToRead));
            } catch (Exception e) {
                e.printStackTrace();
                createBufferedReaderWithPathToChatFile();
            }
        }

        return reader;
    }

    private List<String> getNewMessagesFromFile() throws IOException {
        BufferedReader reader = createBufferedReaderWithPathToChatFile();
        if (reader != null) {
            List<String> messagesInFile = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null; ) {
                messagesInFile.add(line);
            }
            reader.close();
            return messagesInFile;
        }
        return Collections.emptyList();
    }

    private boolean isNewMessagesPresent() {
        return getNewMessagesList().size() != 0 && getNewMessagesList().size() > oldMessagesList.size();

    }

    private List<String> extractNewMessages(List<String> listOfAddedMessages) {

        int amountOfNewMessages = getNewMessagesList().size() - getOldMessagesList().size(); //2
        for (int i = getNewMessagesList().size() - 1;
             i >= getNewMessagesList().size() - amountOfNewMessages;
             i--) {
            if (!getNewMessagesList().get(i).contains(user.getEmail())) {
                listOfAddedMessages.add(getNewMessagesList().get(i));
            }
        }
        return listOfAddedMessages;
    }

    private void updateStoredMessagesInfo() {

        if (oldMessagesList.isEmpty()) {
            try {
                setOldMessagesList(getNewMessagesFromFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                setNewMessagesList(getNewMessagesFromFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Chat getChatToMonitor() {
        return chatToMonitor;
    }

    public void setChatToMonitor(Chat chatToMonitor) {
        this.chatToMonitor = chatToMonitor;
    }

    public ChatService getChatService() {
        return chatService;
    }

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    public List<String> getOldMessagesList() {
        return oldMessagesList;
    }

    public void setOldMessagesList(List<String> oldMessagesList) {
        this.oldMessagesList = oldMessagesList;
    }

    public void setNewMessagesList(List<String> newMessagesList) {
        this.newMessagesList = newMessagesList;
    }

    public List<String> getNewMessagesList() {
        return newMessagesList;
    }
}
