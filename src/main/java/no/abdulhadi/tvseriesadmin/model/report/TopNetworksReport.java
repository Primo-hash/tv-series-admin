package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Getter
public class TopNetworksReport implements Reportable {
    private Map<String, List<ShowDTO>> showsByNetwork;

    public <T extends ShowDTO> TopNetworksReport(List<T> shows) {
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {
        saveShowsByNetwork(shows);
    }

    private <T extends ShowDTO> void saveShowsByNetwork(List<T> shows) {
        showsByNetwork = shows.stream()
                .filter(show -> show.getNetwork() != null && show.getRating().getAverage() != null)
                .collect(Collectors.groupingBy(show -> show.getNetwork().getName()));
    }

    public String toStringReport() {

        return formatNetworkReport();
    }

    private String formatNetworkReport() {
        ConcurrentSkipListMap<String, StringBuilder> sortedReportParts = new ConcurrentSkipListMap<>();

        showsByNetwork.forEach((network, shows) -> {
            StringBuilder reportPart = new StringBuilder();
            ShowDTO topShow = getTopShow(shows);
            String averageNetworkRating = getAverageRating(shows);

            reportPart.append(averageNetworkRating).append(";");
            reportPart.append(network).append(";");
            reportPart.append(topShow.getName()).append(";");
            reportPart.append(topShow.getRating().getAverage()).append(";");
            reportPart.append(shows.size()).append("\n");
            sortedReportParts.put(averageNetworkRating, reportPart);
        });

        StringBuilder report = new StringBuilder();
        report.append("AVERAGE_RATING;NETWORK;TOP_RATED_SHOW;TOP_RATING;SHOW_COUNT\n");
        sortedReportParts.descendingMap().forEach((averageRating, reportPart) -> report.append(reportPart));

        return report.toString().trim();
    }

    private <T extends ShowDTO> String getAverageRating(List<T> shows) {
        DecimalFormat decimalFormatter = new DecimalFormat("#.#");
        decimalFormatter.setRoundingMode(RoundingMode.CEILING);

        return decimalFormatter.format(shows.stream()
                .mapToDouble(show -> show.getRating().getAverage())
                .average()
                .orElse(Double.NaN));
    }

    private <T extends ShowDTO> T getTopShow(List<T> shows) {
        return Collections.max(shows, Comparator.comparing(show -> show.getRating().getAverage()));
    }
}
