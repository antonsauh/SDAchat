package views.chat;

import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import chat.Chat;
import service.ChatService;
import service.MessageWatcherThread;
import user.SomeRunnable;
import user.User;
import views.UserView;

public class ChatView implements UserView {

    private ChatService chatService = new ChatService();
    private Chat chat;
    private MessageWatcherThread messageWatcherThread;
    private User user;

    public ChatView() {

    }

    public ChatView(Chat chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    @Override public void show() {
        printChatHistory(chatService.getChatHistory(this.chat));

        new MessageWatcherThread(chat, user).start();
        Thread myThread = new MessageWatcherThread(chat, user);
        myThread.start();

        MessageWatcherThread basicallyTheSameThread = new MessageWatcherThread(chat, user);

        basicallyTheSameThread.start();

        new Thread(new SomeRunnable()).start();


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("exit")){
                Runtime.getRuntime().exit(1000);
            }
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(chat.getPathToFile().toFile(), true);
                fileWriter.write("ton@ton" + " " + input + "\n");
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void printChatHistory(List<String> messageHistory) {
        messageHistory.forEach(message -> System.out.println(message));
    }
}
