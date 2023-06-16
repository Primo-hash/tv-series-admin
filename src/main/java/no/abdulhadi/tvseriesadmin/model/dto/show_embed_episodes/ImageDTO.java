
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "medium",
    "original"
})
public class ImageDTO {
    @JsonProperty("medium")
    public String medium;
    @JsonProperty("original")
    public String original;
}
