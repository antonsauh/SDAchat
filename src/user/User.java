package user;

import java.util.List;

import chat.Chat;

public class User {

    private String email;
    private List<Chat> userChats;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Chat> getUserChats() {
        return userChats;
    }

    public void setUserChats(List<Chat> userChats) {
        this.userChats = userChats;
    }
}
