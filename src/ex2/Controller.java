package ex2;

import Commands.Command;
import Commands.CommandFactory;

import java.util.Objects;
import java.util.Scanner;

/**
 * This class is used to implement the controller design pattern.
 * It is used to control the flow of the program.
 */
public class Controller {

    /**
     * This is the command factory object.
     */
    private final CommandFactory factory;

    public Controller() {
        factory = new CommandFactory();
    }

    /**
     * This method is used to run the program.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read user input
            String userInput = scanner.nextLine().trim();

            // Check if the user input is empty
            if(userInput.isEmpty()) {
                continue;
            }

            // Check if the user input is "q"
            String action = userInput.split(" ")[0];
            if(Objects.equals(action, "q")){
                break;
            }

            // Check if the user input is valid
            String continuationCommand = "";
            if (userInput.length() > 1) {
                continuationCommand = userInput.substring(2);
            }
            try {
                Command command = factory.createCommand(action);
                command.action(continuationCommand);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
