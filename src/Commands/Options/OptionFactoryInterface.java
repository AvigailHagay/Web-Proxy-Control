package Commands.Options;

/**
 * This interface is used to implement the option design pattern.
 * It is used to block URLs based on the options given by the user.
 */
public interface OptionFactoryInterface {
    Option create();
}
