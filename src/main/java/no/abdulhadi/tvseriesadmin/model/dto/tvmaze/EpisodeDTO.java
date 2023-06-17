
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@JsonPropertyOrder({
    "id",
    "url",
    "name",
    "season",
    "number",
    "type",
    "airdate",
    "airtime",
    "airstamp",
    "runtime",
    "rating",
    "image",
    "summary",
    "_links"
})
@Document( collection = "episodes")
public class EpisodeDTO {
    @Id
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("season")
    public Integer season;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("type")
    public String type;
    @JsonProperty("airdate")
    public String airdate;
    @JsonProperty("airtime")
    public String airtime;
    @JsonProperty("airstamp")
    public String airstamp;
    @JsonProperty("runtime")
    public Integer runtime;
    @JsonProperty("rating")
    public RatingDTO rating;
    @JsonProperty("image")
    public ImageDTO image;
    @JsonProperty("summary")
    public String summary;
    @JsonProperty("_links")
    public EpisodeLinksDTO links;
}
