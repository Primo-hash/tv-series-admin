
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
    public SelfDTO self;
    @JsonProperty("previousepisode")
    public PreviousEpisodeDTO previousEpisode;
    @JsonProperty("nextepisode")
    public NextEpisodeDTO nextepisode;
}
