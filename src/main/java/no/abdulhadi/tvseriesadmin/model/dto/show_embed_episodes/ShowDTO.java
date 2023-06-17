package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;
@Getter
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
public class ShowDTO {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("type")
    public String type;
    @JsonProperty("language")
    public String language;
    @JsonProperty("genres")
    public List<String> genres;
    @JsonProperty("status")
    public String status;
    @JsonProperty("runtime")
    public Integer runtime;
    @JsonProperty("averageRuntime")
    public Integer averageRuntime;
    @JsonProperty("premiered")
    public String premiered;
    @JsonProperty("ended")
    public String ended;
    @JsonProperty("officialSite")
    public String officialSite;
    @JsonProperty("schedule")
    public ScheduleDTO schedule;
    @JsonProperty("rating")
    public RatingDTO rating;
    @JsonProperty("weight")
    public Integer weight;
    @JsonProperty("network")
    public NetworkDTO network;
    @JsonProperty("webChannel")
    public Object webChannel;
    @JsonProperty("dvdCountry")
    public Object dvdCountry;
    @JsonProperty("externals")
    public ExternalsDTO externals;
    @JsonProperty("image")
    public ImageDTO image;
    @JsonProperty("summary")
    public String summary;
    @JsonProperty("updated")
    public Integer updated;
    @JsonProperty("_links")
    public ShowLinksDTO links;
}
