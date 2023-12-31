package no.abdulhadi.tvseriesadmin.util.external.api.tvmaze;

import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.exception.InjectorException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.util.external.api.WebFluxConfiguration;
import no.abdulhadi.tvseriesadmin.util.injector.DTOInjector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Log
@Service
public class ShowService {

    private static final int BACKOFF_STRATEGY = 3;
    private static final int BACKOFF_DURATION_MILLISECONDS = 1500;


    public static ShowDTO getShow(String showName, String embed) {
        WebClient client = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(WebFluxConfiguration.MAX_WEB_BUFFER_SIZE))
                .baseUrl(EndpointEnum.TVMAZE_BASE.getUri())
                .build();

        String res = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EndpointEnum.TVMAZE_SINGLE_SHOW.getUri())
                        .queryParam(ParameterEnum.QUERY.getParam(), showName)
                        .queryParam(ParameterEnum.EMBED.getParam(), embed)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(BACKOFF_STRATEGY, Duration.ofMillis(BACKOFF_DURATION_MILLISECONDS)))
                .block();

        try {
            return DTOInjector.getDTOFromJSON(res, ShowDTO.class);
        } catch (InjectorException e) {
            log.info("Could not parse JSON to ShowDTO");
            log.fine(e.getMessage());
        }

        return new ShowDTO();
    }

    private static String quoteWrap(String s) {
        return "\"" + s + "\"";
    }
}
