
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href"
})
public class PreviousEpisodeDTO {
    @JsonProperty("href")
    public String href;
}
