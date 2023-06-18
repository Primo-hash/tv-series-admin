package no.abdulhadi.tvseriesadmin.controller;

import jakarta.servlet.http.HttpServletResponse;
import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryEnum;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryProducer;
import no.abdulhadi.tvseriesadmin.model.report.ReportEnum;
import no.abdulhadi.tvseriesadmin.model.report.Reportable;
import no.abdulhadi.tvseriesadmin.model.report.TopShowsReport;
import no.abdulhadi.tvseriesadmin.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowReportController {

    ShowRepository repository;

    @Autowired
    ShowReportController(ShowRepository repository) {
        this.repository = repository;
    };

    @GetMapping("/reports/top10shows")
    public String getTopTenShows(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getReport(ReportEnum.TOP_TEN_SHOWS, shows);
        } catch (ReportProducerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Could not create report";
        }
        return report.toStringReport();
    }

    @GetMapping("/reports/top10shows.txt")
    public String getTopTenShowsTxtFile(HttpServletResponse response){
        String fileName = "top10shows.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getTopTenShows(response);
    }
}
