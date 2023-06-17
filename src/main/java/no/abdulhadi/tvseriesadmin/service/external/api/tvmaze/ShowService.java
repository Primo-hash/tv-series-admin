package no.abdulhadi.tvseriesadmin.service.external.api.tvmaze;

import no.abdulhadi.tvseriesadmin.exception.InjectorException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowEmbedEpisodesDTO;
import no.abdulhadi.tvseriesadmin.service.external.api.WebFluxConfiguration;
import no.abdulhadi.tvseriesadmin.service.injector.DTOInjector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class ShowService {

    private static final int BACKOFF_STRATEGY = 3;
    private static final int BACKOFF_DURATION_MILLISECONDS = 1500;


    public static ShowEmbedEpisodesDTO getShowWithEmbed(String showName, String embed) {
        WebClient client = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(WebFluxConfiguration.MAX_WEB_BUFFER_SIZE))
                .baseUrl(EndpointEnum.BASE.uri)
                .build();

        String res = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EndpointEnum.SINGLE_SHOW.uri)
                        .queryParam(ParameterEnum.QUERY.param, showName)
                        .queryParam(ParameterEnum.EMBED.param, embed)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(BACKOFF_STRATEGY, Duration.ofMillis(BACKOFF_DURATION_MILLISECONDS)))
                .block();

        try {
            return DTOInjector.getDTOFromJSON(res, ShowEmbedEpisodesDTO.class);
        } catch (InjectorException e) {
            e.printStackTrace();
        }

        return new ShowEmbedEpisodesDTO();
    }

    private static String quoteWrap(String s) {
        return "\"" + s + "\"";
    }
}