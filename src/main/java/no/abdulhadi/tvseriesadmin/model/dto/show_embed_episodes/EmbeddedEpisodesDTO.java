
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonPropertyOrder({
    "episodes"
})
public class EmbeddedEpisodesDTO {
    @JsonProperty("episodes")
    public List<EpisodeDTO> episodes;
}
