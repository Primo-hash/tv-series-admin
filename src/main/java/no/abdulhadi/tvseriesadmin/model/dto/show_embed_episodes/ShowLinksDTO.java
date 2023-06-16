
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "self",
    "previousepisode"
})
public class ShowLinksDTO {

    @JsonProperty("self")
    public SelfDTO self;
    @JsonProperty("previousepisode")
    public PreviousEpisodeDTO previousEpisode;
}
