
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
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("timezone")
    private String timezone;
}
