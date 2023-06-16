
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
