package no.abdulhadi.tvseriesadmin.model.factory;

import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.report.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
@Log
public class ShowReportFactory extends ReportFactory {

    @Override
    public <T extends ShowDTO> Reportable getReport(ReportEnum reportType, List<T> shows) {
        switch (reportType) {
            case TOP_TEN_SHOWS -> {
                return new TopShowsReport(shows, 10);
            }
            case NEXT_WEEK_SHOWS -> {
                return new NextWeekReport(shows);
            }
            case TOP_NETWORKS -> {
                return new TopNetworksReport(shows);
            }
            case ALL_SHOWS -> {
                return new AllShowsReport(shows);
            }
            case BEST_EPISODES -> {
                return new BestRatedEpisodeReport(shows);
            }
            default -> throw new ReportProducerException("Invalid report choice: " + reportType.getName());
        }
    }

    @Override
    public <T extends ShowDTO> Reportable getParameterizedReport(ReportEnum reportType, List<T> shows, String parameters) {
        switch (reportType) {
            case RECOMMENDED_SHOWS -> {
                return new RecommendedShowsReport(shows, parameters);
            }
            default -> throw new ReportProducerException("Invalid report choice: " + reportType.getName());
        }
    }
}
