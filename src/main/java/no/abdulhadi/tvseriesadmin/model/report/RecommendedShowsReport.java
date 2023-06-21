package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.model.dto.RecommendationScoreCardDTO;
import no.abdulhadi.tvseriesadmin.model.dto.ShowRecommendationFilterDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.util.external.api.imdb.EndpointEnum;
import no.abdulhadi.tvseriesadmin.util.injector.DTOInjector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

@Getter
@Log
public class RecommendedShowsReport implements Reportable {
    private List<RecommendationScoreCardDTO> scoreCards;
    private final List<ShowDTO> shows = new ArrayList<>();
    private final ShowRecommendationFilterDTO showFilter;
    private final Integer responselimit;

    public <T extends ShowDTO> RecommendedShowsReport(List<T> shows, String params) {
        this.shows.addAll(shows);
        showFilter = DTOInjector.getDTOFromJSON(params, ShowRecommendationFilterDTO.class);
        responselimit = (showFilter.getLimit() != null) ? showFilter.getLimit() : 1;
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        List<RecommendationScoreCardDTO> unorderedScoreCards = new ArrayList<>();
        for (ShowDTO show : shows) {
            unorderedScoreCards.add(calculateRecommendationScore(show));
        }
        scoreCards = unorderedScoreCards.stream()
                .sorted(Comparator.comparing(RecommendationScoreCardDTO::getTotalScore).reversed())
                .limit(responselimit)
                .toList();
    }

    public String toStringReport() {
        StringBuilder report = new StringBuilder();
        report.append("SHOW_NAME;RATING;GENRES;SUMMARY;IMDB_LINK\n");

        for (RecommendationScoreCardDTO scoreCard : scoreCards) {
            ShowDTO show = shows.stream().filter(selectedShow -> selectedShow.getId().equals(scoreCard.getShowId())).findFirst().orElse(null);
            if (show == null) {continue;}

            report.append(show.getName()).append(";");
            report.append(show.getRating().getAverage()).append(";");
            report.append(String.join(",", show.getGenres())).append(";");
            report.append(show.getSummary()).append(";");
            report.append(EndpointEnum.IMDB_BASE).append(String.format(EndpointEnum.IMDB_SINGLE_SHOW.getUri(), show.getExternals().getImdb())).append("\n");
        }

        return report.toString();
    }

    private RecommendationScoreCardDTO calculateRecommendationScore(ShowDTO show) {
        RecommendationScoreCardDTO scoreCard = new RecommendationScoreCardDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        scoreCard.setShowId(show.getId());

        if (show.getNetwork() != null) {scoreCard.addNetworkScore(getNetworkScore(show.getNetwork().getName(), showFilter.getNetwork()));}
        scoreCard.addLanguageScore(getLanguageScore(show.getLanguage(), showFilter.getLanguage()));

        try {
            scoreCard.addPremiereScore(getPremieredAfterScore(formatter.parse(show.getPremiered()), showFilter.getPremieredAfter()));
            scoreCard.addPremiereScore(getPremieredBeforeScore(formatter.parse(show.getPremiered()), showFilter.getPremieredBefore()));
        } catch (ParseException | NullPointerException e) {
            log.info(e.getMessage());
        }

        scoreCard.addRunningShowScore(getRunningShowScore(show.getStatus(), showFilter.getRunningShow()));
        scoreCard.addGenreScore(getGenreScore(show.getGenres(), showFilter.getGenres()));
        scoreCard.addKeywordScore(getKeyWordScore(show.getName(), show.getSummary(), showFilter.getKeywords()));
        scoreCard.addRuntimeScore(getRuntimeLessThanScore(show.getAverageRuntime(), showFilter.getRuntimeLessThan()));
        scoreCard.addRuntimeScore(getRuntimeGreaterThanScore(show.getAverageRuntime(), showFilter.getRuntimeGreaterThan()));
        scoreCard.addRatingScore(getRatingGreaterThanScore(show.getRating().getAverage(), showFilter.getRatingGreaterThan()));

        return scoreCard;
    }

    private int getRatingGreaterThanScore(Double rating, Double comparator) {
        return (rating != null && comparator != null && comparator > 0 && rating > comparator) ? 1 : 0;
    }

    private int getRuntimeGreaterThanScore(Integer runtime, Integer comparator) {
        return (runtime != null && comparator != null && comparator > 0 && runtime > comparator) ? 1 : 0;
    }

    private int getRuntimeLessThanScore(Integer runtime, Integer comparator) {
        return (runtime != null && comparator != null && comparator > 0 && runtime < comparator) ? 1 : 0;
    }

    private int getKeyWordScore(String showName, String showSummary, List<String> keyWords) {
        int score = 0;
        if (keyWords != null) {
            for (String keyWord : keyWords) {
                score += (showName.toLowerCase().contains(keyWord.toLowerCase())) ? 1 : 0;
                score += (showSummary.toLowerCase().contains(keyWord.toLowerCase())) ? 1 : 0;
            }
        }
        return score;
    }

    private int getGenreScore(List<String> genres, List<String> comparator) {
        int score = 0;
        if (comparator != null) {
            for (String genre : genres) {
                score += (comparator.contains(genre)) ? 1 : 0;
            }
        }
        return score;
    }

    private int getRunningShowScore(String runningStatus, Boolean comparator) {
        if (comparator != null && comparator && runningStatus.equals("Running")) {
            return 1;
        } else if (comparator != null && !comparator && !runningStatus.equals("Running")) {
            return 1;
        }
        return 0;
    }

    private int getPremieredBeforeScore(Date premiered, Date comparator) {
        return (premiered != null && comparator != null && premiered.before(comparator)) ? 1 : 0;
    }

    private int getPremieredAfterScore(Date premiered, Date comparator) {
        return (comparator != null && premiered.after(comparator)) ? 1 : 0;
    }

    private int getLanguageScore(String language, String comparator) {
        return (language.equals(comparator)) ? 1 : 0;
    }

    private int getNetworkScore(String network, String comparator) {
        return (network.equals(comparator)) ? 1 : 0;
    }
}
