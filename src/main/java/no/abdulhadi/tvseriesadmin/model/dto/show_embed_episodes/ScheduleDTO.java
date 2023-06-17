
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

import java.util.List;
@Getter
@JsonPropertyOrder({
    "time",
    "days"
})
public class ScheduleDTO {
    @JsonProperty("time")
    public String time;
    @JsonProperty("days")
    public List<String> days;
}
