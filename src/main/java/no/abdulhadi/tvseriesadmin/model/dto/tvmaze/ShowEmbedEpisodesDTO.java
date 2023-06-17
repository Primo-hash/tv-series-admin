
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

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
    "_embedded"
})
public class ShowEmbedEpisodesDTO extends ShowDTO {
    @JsonProperty("_embedded")
    public EmbeddedEpisodesDTO embedded;
}
