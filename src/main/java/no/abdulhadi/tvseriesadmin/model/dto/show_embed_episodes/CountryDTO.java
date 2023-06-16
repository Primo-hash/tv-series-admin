
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
