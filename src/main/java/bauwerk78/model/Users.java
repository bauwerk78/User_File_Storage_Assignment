package bauwerk78.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Users {
    //Use a linkedset instead?
    private ArrayList<String[]> userList = new ArrayList<>();

    public Users() {
        init();
    }

    private void init() {
        try {
            importUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importUsers() throws IOException {
        List<String> tempList = new ArrayList<String>();
        Path path = Paths.get("Resources/Users/users.txt");
        tempList = Files.readAllLines(path);
        for (String line : tempList) {
            String[] tempArray = line.split(",");
            userList.add(tempArray);
        }
    }

    public String[] getUserLogin(String userName) {
        for (String[] strings : userList) {
            if(strings[0].contains(userName)) {
                return strings;
            }
        }
        return null;
    }
}
