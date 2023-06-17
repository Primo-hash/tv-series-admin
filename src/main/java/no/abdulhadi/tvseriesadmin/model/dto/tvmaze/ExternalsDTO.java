
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({
    "tvrage",
    "thetvdb",
    "imdb"
})
public class ExternalsDTO {
    @JsonProperty("tvrage")
    private Integer tvrage;
    @JsonProperty("thetvdb")
    private Integer thetvdb;
    @JsonProperty("imdb")
    private String imdb;
}
