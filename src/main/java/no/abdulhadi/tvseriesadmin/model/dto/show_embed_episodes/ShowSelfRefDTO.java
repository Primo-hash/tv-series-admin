
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "href"
})
public class ShowSelfRefDTO {
    @JsonProperty("href")
    public String href;
}
