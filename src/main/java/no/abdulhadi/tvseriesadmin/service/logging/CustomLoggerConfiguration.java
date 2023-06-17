package no.abdulhadi.tvseriesadmin.service.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLoggerConfiguration {

    public static void setConfiguration(Logger logger, Level selectedLevel) {
        logger.setUseParentHandlers(false);
        logger.addHandler(CustomConsoleHandler.getConsoleHandler());
        logger.setLevel(selectedLevel);
    }
}
