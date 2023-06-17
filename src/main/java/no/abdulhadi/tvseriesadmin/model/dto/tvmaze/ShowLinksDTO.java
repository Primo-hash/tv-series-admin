
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({
    "self",
    "previousepisode"
})
public class ShowLinksDTO {

    @JsonProperty("self")
    private SelfDTO self;
    @JsonProperty("previousepisode")
    private PreviousEpisodeDTO previousEpisode;
    @JsonProperty("nextepisode")
    private NextEpisodeDTO nextepisode;
}
