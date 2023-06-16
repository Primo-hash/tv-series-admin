
package no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
