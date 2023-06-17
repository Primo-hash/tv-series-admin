
package no.abdulhadi.tvseriesadmin.model.dto.tvmaze;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
@JsonPropertyOrder({
    "time",
    "days"
})
public class ScheduleDTO {
    @JsonProperty("time")
    private String time;
    @JsonProperty("days")
    private List<String> days;
}
