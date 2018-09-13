package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import chat.Chat;
import user.User;

public class FileService {

    public Path createNewChatFile(Chat chat) throws IOException {
        Path directory = Paths.get("./chats");
        StringBuilder chatName = new StringBuilder();
        chat.getUsers().forEach(user -> chatName.append(user.getEmail()));
        Path newChatPath = directory.resolve(chat.getUsers().toString() + ".txt");
        Files.createFile(newChatPath);
        return newChatPath;
    }

    public synchronized void writeNewMessageToChat(String message, Chat chat) throws IOException {
        File chatFile = chat.getPathToFile().toFile();
        FileOutputStream fileOutputStream = new FileOutputStream(chatFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(message + "\n");

        bufferedWriter.close();
    }

    public void writeNewUserToFile(User user) throws IOException {
        Path pathToUsersFile = Paths.get("./user/users.txt");
        File usersFile = pathToUsersFile.toFile();
        FileOutputStream fileOutputStream = new FileOutputStream(usersFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(user.getEmail() + "\n");

        bufferedWriter.close();

    }

    public boolean chatExists(Path path) {
        File chatFile = path.toFile();
        return chatFile.exists();
    }

}
