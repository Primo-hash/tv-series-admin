package no.abdulhadi.tvseriesadmin.model.factory;

import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.report.ReportEnum;
import no.abdulhadi.tvseriesadmin.model.report.Reportable;
import no.abdulhadi.tvseriesadmin.model.report.TopShowsReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowReportFactory extends ReportFactory {


    @Override
    public <T extends ShowDTO> Reportable getReport(ReportEnum reportType, List<T> shows) {
        switch (reportType) {
            case TOP_TEN_SHOWS -> {
                return new TopShowsReport(shows, 10);
            }
            default -> throw new ReportProducerException("Invalid report choice: " + reportType.getName());
        }
    }

    @Override
    public Reportable getReport(ReportEnum reportType) {
        switch (reportType) {
            default -> throw new ReportProducerException("Invalid report choice: " + reportType.getName());
        }
    }
}
