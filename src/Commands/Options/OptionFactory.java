package Commands.Options;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to implement the factory design pattern.
 * It is used to create the appropriate option object based on the user input.
 */
public class OptionFactory {
    /**
     * This map is used to store the option objects.
     */
    private static final Map<String, OptionFactoryInterface> factoryMap = new HashMap<>();

    static {
        factoryMap.put("i", BlockImages::new);
        factoryMap.put("b", BlockBlockedSites::new);
        factoryMap.put("c", BlockCookies::new);
        factoryMap.put("h", BlockHtml::new);
    }

    /**
     * This method is used to create the appropriate option object based on the user input.
     * @param action The user input.
     * @return The appropriate option object.
     */
    public Option createOption(String action) {
        OptionFactoryInterface factory = factoryMap.get(action);
        if (factory == null) {
            throw new IllegalArgumentException("invalid option");
        }
        return factory.create();
    }
}
