package Validators;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is used to validate user input.
 */
public class Validation {
    /**
     * This method is used to validate the URL.
     * @param urlStr the URL given by the user
     * @return the URL if it is valid, null otherwise
     */
    public static URL validateURL(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        }
        return url;
    }

    /**
     * This method is used to validate the length of the command.
     * @param command the command given by the user
     * @param length the length of the command
     * @return true if the command is valid, false otherwise
     */
    public static boolean validateCommandLength(String command, int length) {
        if (command.split("\\s+").length != length && command.length() != 0 ||
                (command.length() == 0 && length != 0)) {
            System.out.println("Invalid command");
            return false;
        }
        return true;
    }
}
