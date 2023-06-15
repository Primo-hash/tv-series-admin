package no.abdulhadi.tvseriesadmin.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLoggerConfiguration {

    public static void setConfiguration(Logger logger, Level selectedLevel) {
        logger.setUseParentHandlers(false);
        logger.addHandler(CustomConsoleHandler.getConsoleHandler());
        logger.setLevel(selectedLevel);
    }
}
