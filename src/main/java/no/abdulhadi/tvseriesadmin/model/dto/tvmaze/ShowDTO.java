package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mongodb.lang.NonNullApi;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@ToString
@JsonPropertyOrder({
        "id",
        "url",
        "name",
        "type",
        "language",
        "genres",
        "status",
        "runtime",
        "averageRuntime",
        "premiered",
        "ended",
        "officialSite",
        "schedule",
        "rating",
        "weight",
        "network",
        "webChannel",
        "dvdCountry",
        "externals",
        "image",
        "summary",
        "updated",
        "_links",
})
@Document( collection = "shows")
public class ShowDTO {
    @Id
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("language")
    private String language;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("status")
    private String status;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("averageRuntime")
    private Integer averageRuntime;
    @JsonProperty("premiered")
    private String premiered;
    @JsonProperty("ended")
    private String ended;
    @JsonProperty("officialSite")
    private String officialSite;
    @JsonProperty("schedule")
    private ScheduleDTO schedule;
    @JsonProperty("rating")
    private RatingDTO rating;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("network")
    private NetworkDTO network;
    @JsonProperty("webChannel")
    private Object webChannel;
    @JsonProperty("dvdCountry")
    private Object dvdCountry;
    @JsonProperty("externals")
    private ExternalsDTO externals;
    @JsonProperty("image")
    private ImageDTO image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("updated")
    private Integer updated;
    @JsonProperty("_links")
    private ShowLinksDTO links;
    @JsonProperty("_embedded")
    private EmbeddedEpisodesDTO embedded;
}
