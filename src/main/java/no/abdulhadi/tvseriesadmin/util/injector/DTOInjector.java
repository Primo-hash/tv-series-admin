package no.abdulhadi.tvseriesadmin.util.injector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.abdulhadi.tvseriesadmin.exception.InjectorException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DTOInjector {

    Logger logger = LoggerFactory.getLogger(DTOInjector.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getDTOFromJSON(String json, Class<T> c) {
        try {
            return mapper.readValue(json, c);
        } catch (JsonProcessingException e) {
            throw new InjectorException("Could not map given JSON to DTO. " + json + "\n" + e);
        }
    }
}
