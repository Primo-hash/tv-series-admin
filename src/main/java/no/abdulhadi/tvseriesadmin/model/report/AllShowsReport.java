package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.EpisodeDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Log
public class AllShowsReport implements Reportable {
    private final List<ShowDTO> shows = new ArrayList<>();

    public <T extends ShowDTO> AllShowsReport(List<T> shows) {
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        this.shows.addAll(shows);
    }

    public String toStringReport() {
        StringBuilder report = new StringBuilder();
        report.append("SHOW_NAME;NETWORK;GENRES;EPISODE_COUNT;RELEASED_EPISODE_COUNT\n");

        for (ShowDTO show: shows) {
            report.append(show.getName()).append(";");

            if ((show.getNetwork() != null)) {
                report.append(show.getNetwork().getName()).append(";"); }
            else {
                report.append(";");
            }

            report.append(String.join(",", show.getGenres())).append(";");
            report.append(show.getEmbedded().getEpisodes().size()).append(";");
            report.append(countReleasedEpisodes(show.getEmbedded().getEpisodes())).append("\n");
        }

        return report.toString().trim();
    }

    private long countReleasedEpisodes(List<EpisodeDTO> episodes) {
        Calendar calendar = Calendar.getInstance();
        return episodes.stream().filter(episode -> episode.getAirdate().before(calendar.getTime())).count();
    }

}
