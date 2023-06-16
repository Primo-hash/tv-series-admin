
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "episodes"
})
public class EmbeddedDTO {
    @JsonProperty("episodes")
    public List<EpisodeDTO> episodes;
}
