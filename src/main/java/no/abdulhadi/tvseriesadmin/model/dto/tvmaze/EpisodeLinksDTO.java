
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
