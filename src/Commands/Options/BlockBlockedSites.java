package Commands.Options;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * This class implements the Option interface and is used to block
 * URLs that are in the blocked_urls.txt file.
 */
public class BlockBlockedSites implements Option {
    private static final String BLOCKED_URLS_FILE = "blocked_urls.txt";

    @Override
    public boolean isBlocked(HttpURLConnection connection) {

        // Get the URL object
        URL url = connection.getURL();

        // Get the path of the URL as a string
        String urlPath =  url.toString();

        // Set request header to accept only non-image content types
        File blockedFile = new File(BLOCKED_URLS_FILE);

        try (Scanner scanner = new Scanner(blockedFile)) {
            while (scanner.hasNextLine()) {

                String blockedUrl = scanner.nextLine().trim();
                if (urlPath.startsWith(blockedUrl)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("cannot read blocked.txt");
            return true;
        }

        return false;
    }
}
