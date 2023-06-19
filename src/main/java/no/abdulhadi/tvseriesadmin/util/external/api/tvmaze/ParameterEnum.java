package no.abdulhadi.tvseriesadmin.util.external.api.tvmaze;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParameterEnum {
    QUERY("q"),
    EMBED("embed");
    private final String param;
}
