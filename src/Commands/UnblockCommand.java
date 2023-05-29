package Commands;

import Validators.Validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unblock a URL by removing it from the blocked_urls.txt file.
 */
public class UnblockCommand implements Command{
    /**
     * Unblock a URL by removing it from the blocked_urls.txt file.
     * @param urlStr the URL to unblock
     */
    @Override
    public void action(String urlStr) {
        try {
            if(!Validation.validateCommandLength(urlStr, 1))
                return;

            // read the file into a list of strings
            Path path = Paths.get("blocked_urls.txt");
            List<String> lines = Files.readAllLines(path);

            if (lines.contains(urlStr)) {
                // remove the specified URL from the list
                List<String> updatedLines = lines.stream()
                        .filter(line -> !line.equals(urlStr))
                        .collect(Collectors.toList());

                // write the updated list back to the file
                try {
                    Files.write(path, updatedLines);
                } catch (IOException e) {
                    System.out.println("cannot write blocked.txt");
                }
            }
        } catch (IOException e) {
            System.out.println("cannot read blocked.txt");
        }
    }
}
