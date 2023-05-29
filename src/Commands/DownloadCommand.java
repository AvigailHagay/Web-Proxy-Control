package Commands;

import Commands.Options.Option;
import Commands.Options.OptionFactory;
import Validators.Validation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to download a file from a given URL.
 */
public class DownloadCommand implements Command {

    private final OptionFactory factory;

    public DownloadCommand() {
        factory = new OptionFactory();
    }
    /**
     * This method is used to download a file from a given URL.
     * @param continuationCommand the command given by the user
     */
    @Override
    public void action(String continuationCommand) {

        String[] parts = continuationCommand.split("\\s+");

        // validate command
        if (!ValidDownloadCommandLength(parts)) { return; }

        // parse command
        String options = (parts.length == 3) ? parts[0] : "";
        String urlStr = parts[parts.length - 2];
        String outFileStr = parts[parts.length - 1];

        URL url = Validation.validateURL(urlStr);
        if (url == null) { return; }

        /** Check if the file already exists.
         * If it does, ask the user if they want to overwrite it.
         */
        try {
            HttpURLConnection connection = openConnection(url);
            if (connection == null) {
                return;
            }

            int statusCode = connection.getResponseCode();

            if(!checkOptions(options, connection, statusCode))
                return;

            downloadFile(connection, url, outFileStr);

        } catch (IOException e) {
            System.out.println("invalid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to validate the command given by the user.
     * @param parts the command given by the user
     * @return true if the command is valid, false otherwise
     */
    private boolean ValidDownloadCommandLength(String[] parts)
    {
        if (parts.length < 2 || parts.length > 3) {
            System.out.println("invalid command");
            return false;
        }
        else if((parts.length == 2 && parts[0].charAt(0) == '-')||
                (parts.length == 3 && parts[0].charAt(0) != '-')) {
            System.out.println("invalid command");
            return false;
        }
        return true;
    }

    /**
     * This method is used to open a connection to the given URL.
     * @param url the URL to connect to
     * @return the connection to the URL
     */
    private HttpURLConnection openConnection(URL url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("invalid URL");
            connection = null;
        }
        return connection;
    }
    /**
     * This method is used to check if the options given by the user are valid.
     * @param options the options given by the user
     * @param connection the connection to the URL
     * @return true if the options are valid, false otherwise
     */
    private boolean checkOptions(String options, HttpURLConnection connection, int statusCode) {

        Option[] arrayOption = new Option[options.length()];
        for (int i = 1; i < options.length(); i++) {
            Option option = factory.createOption(options.substring(i, i + 1));
            arrayOption[i-1] = option;
        }
        for (int i = 1; i < options.length(); i++) {
            if (arrayOption[i-1].isBlocked(connection)) {
                System.out.println("denied");
                return false;
            }
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.out.println(statusCode);
                return false;
            }
        }
        return true;
    }
    /**
     * This method is used to download the file from the given URL.
     * @param connection the connection to the URL
     * @param url the URL to download from
     * @param outFileStr the name of the file to download to
     */
    private void downloadFile(HttpURLConnection connection, URL url, String outFileStr) {
        try {
            File outFile = new File(outFileStr);
            this.download(connection, url, outFile);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
    /**
     * This method is used to download the file from the given URL.
     * @param connection the connection to the URL
     * @param url the URL to download from
     * @param outFile the file to download to
     */
    private void download(HttpURLConnection connection, URL url, File outFile) throws IOException {

        // download file
        try (InputStream inputStream = connection.getInputStream()) {
            try (OutputStream outputStream = new FileOutputStream(outFile)) {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                System.out.println("cannot write output file");
                throw e;
            }
        }
    }
}


