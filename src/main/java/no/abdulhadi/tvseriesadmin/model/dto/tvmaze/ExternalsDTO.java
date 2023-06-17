
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
    public Integer tvrage;
    @JsonProperty("thetvdb")
    public Integer thetvdb;
    @JsonProperty("imdb")
    public String imdb;
}
