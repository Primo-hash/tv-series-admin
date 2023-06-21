package no.abdulhadi.tvseriesadmin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class RecommendationScoreCardDTO {
    @JsonProperty("showId")
    private Integer showId;
    @JsonProperty("networkScore")
    private Integer networkScore = 0;

    @JsonProperty("languageScore")
    private Integer languageScore = 0;

    @JsonProperty("premiere")
    private Integer premiereScore = 0;

    @JsonProperty("runningShowScore")
    private Integer runningShowScore = 0;

    @JsonProperty("genreScore")
    private Integer genreScore = 0;

    @JsonProperty("keywordScore")
    private Integer keywordScore = 0;

    @JsonProperty("runtimeScore")
    private Integer runtimeScore = 0;

    public void addNetworkScore(Integer networkScore) {
        this.networkScore += networkScore;
    }

    public void addLanguageScore(Integer languageScore) {
        this.languageScore += languageScore;
    }

    public void addPremiereScore(Integer premiereScore) {
        this.premiereScore += premiereScore;
    }

    public void addRunningShowScore(Integer runningShowScore) {
        this.runningShowScore += runningShowScore;
    }

    public void addGenreScore(Integer genreScore) {
        this.genreScore += genreScore;
    }

    public void addKeywordScore(Integer keywordScore) {
        this.keywordScore += keywordScore;
    }

    public void addRuntimeScore(Integer runtimeScore) {
        this.runtimeScore += runtimeScore;
    }

    public void addRatingScore(Integer ratingScore) {
        this.ratingScore += ratingScore;
    }

    @JsonProperty("ratingScore")
    private Integer ratingScore = 0;



    public Integer getTotalScore() {
        return networkScore
                + languageScore
                + premiereScore
                + runningShowScore
                + genreScore
                + keywordScore
                + runtimeScore
                + ratingScore;
    }
}
