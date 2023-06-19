package no.abdulhadi.tvseriesadmin.util.external.api.tvmaze;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndpointEnum {
    BASE("https://api.tvmaze.com"),
    SINGLE_SHOW("/singlesearch/shows");
    private final String uri;
}
