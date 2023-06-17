
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "self",
    "previousepisode"
})
public class ShowLinksDTO {

    @JsonProperty("self")
    public SelfDTO self;
    @JsonProperty("previousepisode")
    public PreviousEpisodeDTO previousEpisode;
    @JsonProperty("nextepisode")
    public NextEpisodeDTO nextepisode;
}
