package Commands;

import Validators.Validation;

import java.io.*;

/**
 * Block a URL by adding it to the blocked_urls.txt file.
 */
public class BlockCommand implements Command {

    private static final String BLOCKED_URLS_FILE = "blocked_urls.txt";

    /**
     * Block a URL by adding it to the blocked_urls.txt file.
     * @param urlStr the URL to block
     */
    @Override
    public void action(String urlStr) {
        try {
            if(!Validation.validateCommandLength(urlStr, 1))
                return;
            if (Validation.validateURL(urlStr) == null) { return; }

            File file = new File(BLOCKED_URLS_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }

            /**
             * Check if the URL is already in the file.
             * If it is, don't add it again.
             */
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean alreadyExists = false;
                while ((line = reader.readLine()) != null) {
                    if (line.equals(urlStr)) {
                        alreadyExists = true;
                        break;
                    }
                }

                /**
                 * If the URL is not in the file, add it.
                 */
                if (!alreadyExists) {
                    try (Writer writer = new FileWriter(file, true)) {
                        writer.write(urlStr + "\n");
                    } catch (IOException ex) {
                        System.out.println("cannot write blocked.txt");
                    }
                }
            } catch (IOException ex) {
                System.out.println("cannot read blocked.txt");
            }
        } catch (IOException e) {
            System.out.println("Error blocking URL: " + urlStr);
            e.printStackTrace();
        }

    }
}
