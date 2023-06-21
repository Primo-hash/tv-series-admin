
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({
    "average"
})
public class RatingDTO implements Comparable<RatingDTO> {
    @JsonProperty("average")
    private Double average;

    @Override
    public int compareTo(RatingDTO rating) {
        return this.average.compareTo(rating.getAverage());
    }
}
