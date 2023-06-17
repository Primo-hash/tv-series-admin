
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
