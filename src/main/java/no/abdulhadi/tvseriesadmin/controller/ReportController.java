package no.abdulhadi.tvseriesadmin.controller;

import jakarta.servlet.http.HttpServletResponse;
import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryEnum;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryProducer;
import no.abdulhadi.tvseriesadmin.model.report.ReportEnum;
import no.abdulhadi.tvseriesadmin.model.report.Reportable;
import no.abdulhadi.tvseriesadmin.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    ShowRepository repository;

    @Autowired
    ReportController(ShowRepository repository) {
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
        String fileName = "top_10_shows.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getTopTenShows(response);
    }

    @GetMapping("/reports/nextweek")
    public String getNextWeekShows(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getReport(ReportEnum.NEXT_WEEK_SHOWS, shows);
        } catch (ReportProducerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Could not create report";
        }
        return report.toStringReport();
    }

    @GetMapping("/reports/nextweek.txt")
    public String getNextWeekShowsTxtFile(HttpServletResponse response){
        String fileName = "next_week.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getNextWeekShows(response);
    }

    @GetMapping("/reports/topnetworks")
    public String getTopNetworks(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getReport(ReportEnum.TOP_NETWORKS, shows);
        } catch (ReportProducerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Could not create report";
        }
        return report.toStringReport();
    }

    @GetMapping("/reports/topnetworks.txt")
    public String getTopNetworksTxtFile(HttpServletResponse response){
        String fileName = "top_networks.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getTopNetworks(response);
    }

    @GetMapping("/reports/allshows")
    public String getAllShows(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getReport(ReportEnum.ALL_SHOWS, shows);
        } catch (ReportProducerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Could not create report";
        }
        return report.toStringReport();
    }

    @GetMapping("/reports/allshows.txt")
    public String getAllShowsTxtFile(HttpServletResponse response){
        String fileName = "all_shows.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getAllShows(response);
    }
}