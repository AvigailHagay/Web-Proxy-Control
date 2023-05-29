package Commands.Options;


import java.net.HttpURLConnection;

/**
 * This interface is used to implement the option design pattern.
 * It is used to block URLs based on the options given by the user.
 */
public class BlockCookies implements Option{
    @Override
    public boolean isBlocked(HttpURLConnection connection) {

        // Check response headers for cookies content type
        String cookies = connection.getHeaderField("Set-Cookie");
        return cookies != null;

    }
}
