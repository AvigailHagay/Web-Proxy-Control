package Commands.Options;

import java.net.HttpURLConnection;

/**
 * This interface is used to implement the option design pattern.
 * It is used to block URLs based on the options given by the user.
 */
public interface Option {
    boolean isBlocked(HttpURLConnection connection);
}
