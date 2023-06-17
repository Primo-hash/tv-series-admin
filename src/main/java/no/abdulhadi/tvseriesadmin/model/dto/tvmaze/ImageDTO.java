
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
