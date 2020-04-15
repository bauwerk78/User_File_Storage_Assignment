package bauwerk78.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private Path tempPath;
    private String rootPath;

    public FileHandler(String userName) {
        rootPath = "UserHome/" + userName;
    }

    //Make sure the user stays within home directory.
    private int checkPath(Path userPath, Path homePath) {
        return userPath.compareTo(homePath);
    }

    public void read(String path, String fileName) throws IOException {
        List<String> tempList = new ArrayList<>();
        tempPath = Paths.get(path);
        tempList = Files.readAllLines(tempPath);
        tempList.forEach(System.out::println);
    }

    public void move(String source, String target) throws IOException {
        Path sourcePth = Paths.get(source);
        Path destinationPth = Paths.get(target);

        Files.move(sourcePth, destinationPth, StandardCopyOption.REPLACE_EXISTING);
    }

    public void copy(String source, String target) throws IOException {
        Path sourcePth = Paths.get(source);
        Path destinationPth = Paths.get(target);

        Files.copy(sourcePth, destinationPth);
    }

    public void delete(String fileName) throws IOException {
        tempPath = Paths.get(fileName);
        Files.delete(tempPath);
    }

    public void createDirectory(String path) throws IOException {
        tempPath = Paths.get(path);
        Files.createDirectory(tempPath);
    }

    public void createFile(String path) throws IOException {
        tempPath = Paths.get(path);
        Files.createFile(tempPath);
    }

    public void writeToFileFresh(String path, String textToWrite) throws IOException {
        tempPath = Paths.get(path);
        byte[] byteBuffer = textToWrite.getBytes();
        Files.write(tempPath, byteBuffer, StandardOpenOption.CREATE_NEW);
    }

    public void appendToFile(String path, String textToWrite) throws IOException {
        tempPath = Paths.get(path);
        byte[] byteBuffer = textToWrite.getBytes();
        Files.write(tempPath, byteBuffer, StandardOpenOption.APPEND);
    }


}//End of class.
