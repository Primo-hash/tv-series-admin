
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
