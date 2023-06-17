
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

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
    "_embedded"
})
public class ShowEmbedEpisodesDTO extends ShowDTO {
    @JsonProperty("_embedded")
    public EmbeddedEpisodesDTO embedded;
}
