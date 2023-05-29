package Commands;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to implement the factory design pattern.
 * It is used to create the appropriate command object based on the user input.
 */
public class CommandFactory {
    /**
     * This map is used to store the command objects.
     */
    private static final Map<String, CommandFactoryInterface> factoryMap = new HashMap<>();

    static {
        factoryMap.put("b", BlockCommand::new);
        factoryMap.put("u", UnblockCommand::new);
        factoryMap.put("p", PrintCommand::new);
        factoryMap.put("d", DownloadCommand::new);
    }

    /**
     * This method is used to create the appropriate command object based on the user input.
     * @param action The user input.
     * @return The appropriate command object.
     */
    public Command createCommand(String action) {
        CommandFactoryInterface factory = factoryMap.get(action);
        if (factory == null) {
            throw new IllegalArgumentException("invalid command");
        }
        return factory.create();
    }
}

