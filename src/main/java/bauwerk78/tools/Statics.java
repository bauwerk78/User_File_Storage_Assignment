package bauwerk78.tools;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public interface Statics {

    Scanner scanner = new Scanner(System.in);


    static String requestInput(String description) {
        System.out.print(description);
        return scanner.nextLine();
    }

    static Path convertInputToPath(String input) {
        return Paths.get(input);
    }



}
