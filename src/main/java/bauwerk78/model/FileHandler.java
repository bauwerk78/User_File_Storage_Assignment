package bauwerk78.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileHandler {

    private final Path homePath;
    private Path currentPath;

    /*Known is that you can't use filenames with spaces which is not a good idea anyway.
     */

    public FileHandler(Path homePath) {
        this.homePath = homePath.normalize();
        this.currentPath = this.homePath;
    }

    //Make sure the user stays within home directory.
    public int checkPath(Path comparatorPath) {
        comparatorPath.normalize();
        return comparatorPath.compareTo(homePath);
    }

    //TODO for all below, check if dir or file and so on, safety checks in other words.
    public void read(String fileName) throws IOException {
        List<String> tempList = new ArrayList<>();
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(fileName);
        if (Files.isRegularFile(tempPath)) {
            tempList = Files.readAllLines(tempPath);
            tempList.forEach(System.out::println);
        } else {
            System.out.println("Could not read file.");
        }
    }

    //TODO for some reason a number of dots qualifies like a proper directory.
    public Path changeDirectory(String input) {
        Path tempPath = currentPath;
        if (input.equals("..")) {
            tempPath = tempPath.getParent();
            if (checkPath(tempPath) >= 0) {
                System.out.println(checkPath(tempPath) + " temp path return value");
                currentPath = tempPath;
                return currentPath;
            }
        } else {
            tempPath = tempPath.resolve(input);
            if (Files.isDirectory(tempPath) && checkPath(tempPath) >= 0) {
                System.out.println(checkPath(tempPath) + " temp path return value step 2");
                currentPath = tempPath;
                return currentPath;
            }
        }
        System.out.println("Failure: Either not a directory or going out of home root.");
        return currentPath;
    }

    public void moveFile(String source, String target) throws IOException {
        Path tempSourcePth = currentPath;
        tempSourcePth = tempSourcePth.resolve(source);
        Path tempTargetPth = currentPath;
        tempTargetPth = tempTargetPth.resolve(target);
        if (Files.isRegularFile(tempSourcePth)) {
            Files.move(tempSourcePth, tempTargetPth, StandardCopyOption.REPLACE_EXISTING);
        } else {
            System.out.println("Failure to move file or you entered a non existent file.");
        }
    }

    public void moveDirectory() {

    }

    public void copyFile(String source, String target) throws IOException {
        Path tempSourcePth = currentPath;
        tempSourcePth = tempSourcePth.resolve(source);
        Path tempTargetPth = currentPath;
        tempTargetPth = tempTargetPth.resolve(target);
        if (Files.isRegularFile(tempSourcePth)) {
            Files.copy(tempSourcePth, tempTargetPth);
        } else {
            System.out.println("Copy of file failed.");
        }

    }

    public void deleteFile(String fileName) throws IOException {
        Path tempSourcePth = currentPath;
        tempSourcePth = tempSourcePth.resolve(fileName);
        if (Files.isRegularFile(tempSourcePth)) {
            Files.delete(tempSourcePth);
        } else {
            System.out.println("This is not a file you are trying to delete.");
        }

    }

    public void deleteDirectory(String directoryName) throws IOException {
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(directoryName);
        if(Files.isDirectory(tempPath)) {
            Files.delete(tempPath);
        }
    }

    public void createDirectory(String path) throws IOException {
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(path);
        Files.createDirectory(tempPath);
    }

    public void createFile(String path) throws IOException {
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(path);
        Files.createFile(tempPath);
    }

    public void writeToFileFresh(String path, String textToWrite) throws IOException {
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(path);
        byte[] byteBuffer = textToWrite.getBytes();
        Files.write(tempPath, byteBuffer, StandardOpenOption.CREATE_NEW);
    }

    public void appendToFile(String path, String textToWrite) throws IOException {
        Path tempPath = currentPath;
        tempPath = tempPath.resolve(path);
        if (checkPath(tempPath) >= 0) {
            byte[] byteBuffer = textToWrite.getBytes();
            Files.write(tempPath, byteBuffer, StandardOpenOption.APPEND);
        }
    }

    //TODO perhaps add filters to show directories and files in different colors.
    public void listCurrentDir(Path userCurrentDir) throws IOException {
        Files.list(userCurrentDir).forEach(System.out::println);
    }

}//End of class.
