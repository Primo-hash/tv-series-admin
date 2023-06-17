
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
@JsonPropertyOrder({
    "episodes"
})
public class EmbeddedEpisodesDTO {
    @JsonProperty("episodes")
    private List<EpisodeDTO> episodes;
}
