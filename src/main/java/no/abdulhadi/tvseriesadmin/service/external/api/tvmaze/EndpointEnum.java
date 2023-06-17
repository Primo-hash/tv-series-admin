package no.abdulhadi.tvseriesadmin.service.external.api.tvmaze;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EndpointEnum {
    BASE("https://api.tvmaze.com"),
    SINGLE_SHOW("/singlesearch/shows");
    public final String uri;
}
