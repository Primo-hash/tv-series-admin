package no.abdulhadi.tvseriesadmin.service.external.api.tvmaze;

import no.abdulhadi.tvseriesadmin.exception.InjectorException;
import no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes.ShowDTO;
import no.abdulhadi.tvseriesadmin.service.external.api.WebFluxConfiguration;
import no.abdulhadi.tvseriesadmin.service.injector.DTOInjector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Service
public class ShowService {
    public static <T extends ShowDTO> ShowDTO getShowWithEmbed(String showName, String embed, Class<T> dtoClass) {
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
                .block();

        try {
            return DTOInjector.getDTOFromJSON(res, dtoClass);
        } catch (InjectorException e) {
            e.printStackTrace();
        }

        return new ShowDTO();
    }

    private static String quoteWrap(String s) {
        return "\"" + s + "\"";
    }
}
