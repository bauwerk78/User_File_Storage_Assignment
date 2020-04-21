package bauwerk78.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUser() {
        return username;
    }

    public Path getHomePath(Path rootPathOfHomeDirectories) {
        return Paths.get(rootPathOfHomeDirectories + "/" + username + "/");
    }

}
