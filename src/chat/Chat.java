package chat;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import user.User;

public class Chat {

    private Path pathToFile;
    private Set<User> users;

    public Chat(){
        users = new HashSet<>();
    }

    public Path getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUserToChat(User user) {
        this.users.add(user);
    }
}
