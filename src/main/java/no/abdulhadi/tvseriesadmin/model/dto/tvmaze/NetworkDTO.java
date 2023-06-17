
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
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("country")
    public CountryDTO country;
    @JsonProperty("officialSite")
    public Object officialSite;
}
