package Commands.Options;

import java.net.HttpURLConnection;

/**
 * This interface is used to implement the option design pattern.
 * It is used to block URLs based on the options given by the user.
 */
public class BlockHtml implements Option{
    @Override
    public boolean isBlocked(HttpURLConnection connection) {

        // Check response headers for html content type
        String contentType = connection.getContentType();
        return contentType != null && contentType.startsWith("text/html");

    }
}
