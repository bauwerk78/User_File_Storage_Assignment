package bauwerk78;

import bauwerk78.model.FileHandler;
import bauwerk78.model.Login;
import bauwerk78.model.User;
import bauwerk78.model.Users;
import bauwerk78.tools.Statics;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainProgram implements Statics {

    private final Path rootPathOfHomeDirectories = Paths.get("UserHome/");
    private FileHandler fileHandler;
    private boolean running;
    private Login login;
    private Users users;
    private User currentUser;

    private Path userHomeDirectory;
    private Path userCurrentDirectory;
    //private int userRoot = userHomeDirectory.getNameCount();

    private String[] userInput;

    public MainProgram() {
        init();
    }

    private void init() {
        running = true;
        users = new Users();
        String[] loginAttempt = new Login().getUserLogin();
        String[] userCheck = users.getUserLogin(loginAttempt[0]);
        if (userCheck != null && loginAttempt[1].equals(userCheck[1])) {
            System.out.println("Login successful! ");
            currentUser = new User(loginAttempt[0], loginAttempt[1]);
            userHomeDirectory = currentUser.getHomePath(rootPathOfHomeDirectories);
            userCurrentDirectory = userHomeDirectory;
            fileHandler = new FileHandler(userHomeDirectory);
        } else {
            //Exit after one failed login attempt so you can't spam dictionary attacks and so on.
            System.out.println("Failed login. Exiting.");
            running = false;
        }


    }

    public void runProgram() {
        while (running) {
            userInput = splitUserInput();
            main(userInput);
        }
    }

    private String[] splitUserInput() {
        String tempString = Statics.requestInput("Enter your command: ");
        return tempString.split(" ", 4);
    }

    //TODO add commands that is available. Low priority.
    private void help() {
        System.out.println("Commands available: ");
        System.out.println("");
    }

    private void main(String[] input) {
        Path tempPath;
        try {
            switch (input[0].toLowerCase()) {
                case "ls":
                case "dir":
                    fileHandler.listCurrentDir(userCurrentDirectory);
                    break;
                case "cd":
                    if (input.length == 2) {
                        userCurrentDirectory = fileHandler.changeDirectory(input[1]);
                        System.out.println(userCurrentDirectory);
                    } else {
                        System.out.println("Not enough parameters for command to work.");
                    }
                    break;
                case "rm":
                case "delete":
                case "del":
                    if(input.length == 2) {
                        fileHandler.deleteFile(input[1]);
                    }
                    break;
                case "mv":
                case "move":
                    if(input.length == 3) {
                        fileHandler.moveFile(input[1], input[2]);
                    }
                    break;
                case "cp":
                case "copy":
                    if(input.length == 3) {
                        fileHandler.copyFile(input[1], input[2]);
                    }
                    break;
                case "rename":
                    break;
                case "mkdir":
                    if(input.length == 2) {
                        fileHandler.createDirectory(input[1]);
                    }
                    break;
                case "rmdir":
                    if(input.length == 2) {
                        fileHandler.deleteDirectory(input[1]);
                    }
                    break;
                case "touch":
                    if(input.length == 2) {
                        fileHandler.createFile(input[1]);
                    }
                    break;
                case "edit":
                    System.out.println(input.length);
                    if (input.length == 4) {
                        if (input[1].toLowerCase().equals("append")) {
                            fileHandler.appendToFile(input[2], input[3]);
                        } else if (input[1].toLowerCase().equals("new")) {
                            fileHandler.writeToFileFresh(input[2], input[3]);
                        } else {
                            System.out.println("Unknown use of command.");
                        }
                    } else {
                        System.out.println("Not enough parameters for command to work.");
                    }
                    break;
                case "read":
                    if(input.length == 2) {
                        fileHandler.read(input[1]);
                    } else {
                        System.out.println("Not enough parameters.");
                    }
                    break;
                case "exit":
                case "quit":
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                case "help":
                    help();
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRunning() {
        return running;
    }


}
