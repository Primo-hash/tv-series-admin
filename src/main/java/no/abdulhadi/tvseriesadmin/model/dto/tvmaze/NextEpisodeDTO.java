
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({
    "href"
})
public class NextEpisodeDTO {
    @JsonProperty("href")
    private String href;
}
