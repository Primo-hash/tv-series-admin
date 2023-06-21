package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.EpisodeDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@Getter
public class BestRatedEpisodeReport implements Reportable {

    private final HashMap<EpisodeDTO, ShowDTO> bestRatedEpisodes;

    public <T extends ShowDTO> BestRatedEpisodeReport(List<T> shows) {
        bestRatedEpisodes = new HashMap<>();
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        for (ShowDTO show : shows) {
            mapBestEpisode(show);
        }
    }

    private void mapBestEpisode(ShowDTO show) {
        List<EpisodeDTO> validEpisodes = show.getEmbedded().getEpisodes().stream()
                .filter(episode -> episode.getRating().getAverage() != null)
                .toList();
        if (!validEpisodes.isEmpty()) {
            bestRatedEpisodes.put(
                    Collections.max(validEpisodes,
                            Comparator.comparing(episode -> episode.getRating().getAverage())),
                    show
            );
        }
    }

    public String toStringReport() {

        StringBuilder report = new StringBuilder();
        report.append("SHOW_NAME;NETWORK;GENRES;SEASON_NUMBER;EPISODE_NUMBER;EPISODE_NAME;RATING;\n");

        bestRatedEpisodes.forEach((episode, show) -> {
            report.append(show.getName()).append(";");
            if ((show.getNetwork() != null)) {
                report.append(show.getNetwork().getName()).append(";"); }
            else {
                report.append(";");
            }
            report.append(String.join(",", show.getGenres())).append(";");
            report.append(episode.getSeason()).append(";");
            report.append(episode.getNumber()).append(";");
            report.append(episode.getName()).append(";");
            report.append(episode.getRating().getAverage()).append("\n");
        });

        return report.toString().trim();
    }





}
