package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println("Hello! Welcome to BadMaths.");
        System.out.println("How can I help you?");

        Scanner in = new Scanner(System.in);
        //continuously check for user inputs
        while(in.hasNext()){
            String userInput = in.nextLine();
            Parser parser = new Parser(userInput);
        }
    }
}
