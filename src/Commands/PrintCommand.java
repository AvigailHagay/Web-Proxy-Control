package Commands;

import Validators.Validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class is used to print the list of blocked URLs.
 */
public class PrintCommand implements Command {
    /**
     * Print the list of blocked URLs.
     * @param continuationCommand the command given by the user
     */
    @Override
    public void action(String continuationCommand) {
        try {
            if(!Validation.validateCommandLength(continuationCommand, 0))
                return;
            // read the blocked URLs file
            Path path = Paths.get("blocked_urls.txt");
            List<String> lines = Files.readAllLines(path);
            // print the list of blocked URLs
            if (!lines.isEmpty()) {
                lines.sort(String::compareToIgnoreCase);
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("cannot read blocked.txt");
        }
    }
}