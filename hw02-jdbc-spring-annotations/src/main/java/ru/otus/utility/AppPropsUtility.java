package ru.otus.utility;

import org.springframework.core.env.Environment;

public class AppPropsUtility {
    private AppPropsUtility() {
        throw new AssertionError("No instances");
    }

    public static String getParam(Environment env, String paramName, String errorMessage) {
        String paramValue = env.getProperty(paramName);
        if (paramValue == null) {
            throw new Hw2MissedPropertiesException(errorMessage);
        }
        return paramValue;
    }
}
