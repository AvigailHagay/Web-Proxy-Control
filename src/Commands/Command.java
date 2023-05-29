package Commands;

/**
 * This interface is used to implement the command design pattern.
 * It is used to execute commands given by the user.
 */
public interface Command {
    void action(String continuationCommand);
}
