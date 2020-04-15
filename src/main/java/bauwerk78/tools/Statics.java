package bauwerk78.tools;

import java.util.Scanner;

public interface Statics {

    Scanner scanner = new Scanner(System.in);


    static String requestInput(String description) {
        System.out.print(description);
        return scanner.nextLine();
    }



}
