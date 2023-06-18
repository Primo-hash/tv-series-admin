package no.abdulhadi.tvseriesadmin.controller;

import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.report.Top10ShowsReport;
import no.abdulhadi.tvseriesadmin.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShowReportController {

    ShowRepository repository;

    @Autowired
    ShowReportController(ShowRepository repository) {
        this.repository = repository;
    };

    @GetMapping("/reports/top10shows")
    public String getUsers(){
        List<ShowDTO> shows = repository.findAll();
        Top10ShowsReport report = new Top10ShowsReport(shows);
        return report.toStringReport();
    }
}
