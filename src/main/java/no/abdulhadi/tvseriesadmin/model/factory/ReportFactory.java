package no.abdulhadi.tvseriesadmin.model.factory;

import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.report.ReportEnum;
import no.abdulhadi.tvseriesadmin.model.report.Reportable;

import java.util.List;

public abstract class ReportFactory {
    public abstract <T extends ShowDTO> Reportable getReport(ReportEnum reportType, List<T> items);
    public abstract <T extends ShowDTO> Reportable getParameterizedReport(ReportEnum reportType, List<T> items, String parameters);
}
