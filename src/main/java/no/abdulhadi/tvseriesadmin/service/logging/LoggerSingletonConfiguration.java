package no.abdulhadi.tvseriesadmin.service.logging;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class LoggerSingletonConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Logger loggerSingleton() {
        Logger logger = Logger.getLogger("TV Series Admin Logger");
        CustomLoggerConfiguration.setConfiguration(logger, Level.INFO);
        return logger;
    }
}
