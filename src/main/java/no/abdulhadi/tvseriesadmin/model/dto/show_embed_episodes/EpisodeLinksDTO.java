
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "self",
    "show"
})
public class EpisodeLinksDTO {
    @JsonProperty("self")
    public SelfDTO self;
    @JsonProperty("show")
    public ShowSelfRefDTO show;
}
