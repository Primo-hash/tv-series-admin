
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
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("season")
    private Integer season;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("type")
    private String type;
    @JsonProperty("airdate")
    private String airdate;
    @JsonProperty("airtime")
    private String airtime;
    @JsonProperty("airstamp")
    private String airstamp;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("rating")
    private RatingDTO rating;
    @JsonProperty("image")
    private ImageDTO image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("_links")
    private EpisodeLinksDTO links;
}
