package no.abdulhadi.tvseriesadmin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowRecommendationFilterDTO {
    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("network")
    private String network;

    @JsonProperty("language")
    private String language;

    @JsonProperty("premieredAfter")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date premieredAfter;

    @JsonProperty("premieredBefore")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date premieredBefore;

    @JsonProperty("runningShow")
    private Boolean runningShow;

    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("keywords")
    private List<String> keywords;

    @JsonProperty("runtimeLessThan")
    private Integer runtimeLessThan;

    @JsonProperty("runtimeGreaterThan")
    private Integer runtimeGreaterThan;

    @JsonProperty("ratingGreaterThan")
    private Double ratingGreaterThan;
}
