package no.abdulhadi.tvseriesadmin.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportEnum {
    TOP_TEN_SHOWS("Top 10 shows"),
    INVALID("Invalid report");

    private final String name;
}
