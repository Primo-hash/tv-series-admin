package no.abdulhadi.tvseriesadmin.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Top10ShowsReport extends Report {
    private static final int MAX_SHOWS = 10;
    private List<ShowDTO> topTenShows;

    public <T extends ShowDTO> Top10ShowsReport(List<T> shows) {
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        topTenShows = shows.stream()
                .filter(show -> show.getRating().getAverage() != null)
                .sorted(Comparator.comparing(show -> show.getRating().getAverage()))
                .limit(MAX_SHOWS)
                .collect(Collectors.toList());
    }

    public String toStringReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < topTenShows.size(); i++) {
            report.append(i+1).append(". ").append(topTenShows.get(i).getName()).append("\n");
        }
        return report.toString().trim();
    }

}
