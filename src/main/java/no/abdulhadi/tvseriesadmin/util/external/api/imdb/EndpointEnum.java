package no.abdulhadi.tvseriesadmin.util.external.api.imdb;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndpointEnum {
    IMDB_BASE("https://www.imdb.com"),
    IMDB_SINGLE_SHOW("/title/%s");
    private final String uri;
}
