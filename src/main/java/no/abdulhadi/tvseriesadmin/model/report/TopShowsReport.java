package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopShowsReport implements Reportable {
    private final int TOP_SHOWS_COUNT;
    private List<ShowDTO> topTenShows;

    public <T extends ShowDTO> TopShowsReport(List<T> shows, int topShowsCount) {
        this.TOP_SHOWS_COUNT = topShowsCount;
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        topTenShows = shows.stream()
                .filter(show -> show.getRating().getAverage() != null)
                .sorted(Comparator.comparing((ShowDTO show) -> show.getRating().getAverage()).reversed())
                .limit(TOP_SHOWS_COUNT)
                .collect(Collectors.toList());
        System.out.println("df");
    }

    public String toStringReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < topTenShows.size(); i++) {
            report.append(topTenShows.get(i).getName());
            if (i == topTenShows.size() - 1) {
                report.append("\n");
            } else {
                report.append(";");
            }
        }
        return report.toString().trim();
    }

}
