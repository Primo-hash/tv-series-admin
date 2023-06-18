package no.abdulhadi.tvseriesadmin.model.report;

import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;

import java.util.List;

public interface Reportable {
    <T extends ShowDTO> void generateReport(List<T> items);

    String toStringReport();
}
