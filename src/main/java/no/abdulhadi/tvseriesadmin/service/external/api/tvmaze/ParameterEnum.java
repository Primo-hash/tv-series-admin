package no.abdulhadi.tvseriesadmin.service.external.api.tvmaze;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParameterEnum {
    QUERY("q"),
    EMBED("embed");
    public final String param;
}
