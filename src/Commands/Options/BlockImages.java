package Commands.Options;

import java.net.HttpURLConnection;

/**
 * This interface is used to implement the option design pattern.
 * It is used to block URLs based on the options given by the user.
 */
public class BlockImages implements Option {
    @Override
    public boolean isBlocked(HttpURLConnection connection) {

        // Check response headers for image content type
        String contentType = connection.getHeaderField("Content-Type");
        return contentType != null && contentType.startsWith("image/");
    }
}
