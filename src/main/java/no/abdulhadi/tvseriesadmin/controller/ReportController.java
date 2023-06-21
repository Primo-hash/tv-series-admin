package no.abdulhadi.tvseriesadmin.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryEnum;
import no.abdulhadi.tvseriesadmin.model.factory.FactoryProducer;
import no.abdulhadi.tvseriesadmin.model.report.ReportEnum;
import no.abdulhadi.tvseriesadmin.model.report.Reportable;
import no.abdulhadi.tvseriesadmin.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
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
        Reportable report = getReport(ReportEnum.TOP_TEN_SHOWS, shows, response);
        if (report == null) return "Could not create report";
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
        Reportable report = getReport(ReportEnum.NEXT_WEEK_SHOWS, shows, response);
        if (report == null) return "Could not create report";
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
        Reportable report = getReport(ReportEnum.TOP_NETWORKS, shows, response);
        if (report == null) return "Could not create report";
        return report.toStringReport();
    }

    @GetMapping("/reports/topnetworks.txt")
    public String getTopNetworksTxtFile(HttpServletResponse response){
        String fileName = "top_networks.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getTopNetworks(response);
    }

    @GetMapping("/reports/summary")
    public String getAllShows(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report = getReport(ReportEnum.ALL_SHOWS, shows, response);
        if (report == null) return "Could not create report";
        return report.toStringReport();
    }

    @GetMapping("/reports/summary.txt")
    public String getAllShowsTxtFile(HttpServletResponse response){
        String fileName = "summary.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getAllShows(response);
    }

    @GetMapping("/reports/bestepisodes")
    public String getBestEpisodes(HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report = getReport(ReportEnum.BEST_EPISODES, shows, response);
        if (report == null) return "Could not create report";
        return report.toStringReport();
    }

    @GetMapping("/reports/bestepisodes.txt")
    public String getBestEpisodesTxtFile(HttpServletResponse response){
        String fileName = "best_episodes.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return getBestEpisodes(response);
    }

    @PostMapping("/reports/recommendedshows")
    public String postRecommendedShows(@RequestBody String filters, HttpServletResponse response){
        List<ShowDTO> shows = repository.findAll();
        Reportable report = getParameterizedReport(ReportEnum.RECOMMENDED_SHOWS, shows, filters, response);
        if (report == null) return "Could not create report";
        return report.toStringReport();
    }

    @PostMapping("/reports/recommendedshows.txt")
    public String postRecommendedShowsTxtFile(@RequestBody String filters, HttpServletResponse response){
        String fileName = "best_episodes.txt";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return postRecommendedShows(filters, response);
    }

    private static Reportable getReport(ReportEnum reportType, List<ShowDTO> shows, HttpServletResponse response) {
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getReport(reportType, shows);
        } catch (ReportProducerException e) {
            log.info("Could not create report of type " + reportType.getName());
            log.fine(e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return report;
    }

    private static Reportable getParameterizedReport(ReportEnum reportType, List<ShowDTO> shows, String parameters, HttpServletResponse response) {
        Reportable report;
        try {
            report = FactoryProducer.getReportFactory(FactoryEnum.SHOW_REPORT).getParameterizedReport(reportType, shows, parameters);
        } catch (ReportProducerException e) {
            log.info("Could not create report of type " + reportType.getName());
            log.fine(e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return report;
    }
}
