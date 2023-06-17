
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "name",
    "code",
    "timezone"
})
public class CountryDTO {
    @JsonProperty("name")
    public String name;
    @JsonProperty("code")
    public String code;
    @JsonProperty("timezone")
    public String timezone;
}
