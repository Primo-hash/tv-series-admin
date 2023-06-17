
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "id",
    "name",
    "country",
    "officialSite"
})
public class NetworkDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private CountryDTO country;
    @JsonProperty("officialSite")
    private Object officialSite;
}
