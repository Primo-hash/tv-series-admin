package no.abdulhadi.tvseriesadmin.model.report;

import lombok.Getter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.EpisodeDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;

import java.text.SimpleDateFormat;
import java.util.*;

@Getter
public class NextWeekReport implements Reportable {
    private HashMap<String, List<EpisodeDTO>> showsNextWeek = new HashMap<>();
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();

    public <T extends ShowDTO> NextWeekReport(List<T> shows) {
        generateReport(shows);
    }

    public <T extends ShowDTO> void generateReport(List<T> shows) {

        configureCalendar();

        calendar.add(Calendar.DATE, 1);
        Date startDateNextWeek = calendar.getTime();

        calendar.add(Calendar.DATE, 6);
        Date endDateNextWeek = calendar.getTime();

        this.showsNextWeek = mapSchedule(shows, startDateNextWeek, endDateNextWeek);;
    }

    private <T extends ShowDTO> HashMap<String, List<EpisodeDTO>> mapSchedule(List<T> shows, Date startDateNextWeek, Date endDateNextWeek) {
        HashMap<String, List<EpisodeDTO>> showsNextWeek = new HashMap<>();

        for (ShowDTO show : shows) {
            List<EpisodeDTO> episodesNextWeek = new ArrayList<>();
            for (EpisodeDTO episode : show.getEmbedded().getEpisodes()) {
                Date airdate = episode.getAirdate();
                if (airdate.after(startDateNextWeek) && airdate.before(endDateNextWeek)) {
                    episodesNextWeek.add(episode);
                }
            }
            if (episodesNextWeek.size() != 0) {
                showsNextWeek.put(show.getName(), episodesNextWeek);
            }
        }
        return showsNextWeek;
    }

    public String toStringReport() {
        return formatShowings();
    }

    private void configureCalendar() {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    private String formatShowings() {
        final HashMap<Integer, String> dayShowings = new HashMap<>();
        StringBuilder report = new StringBuilder();
        report.append("SHOW_NAME;MONDAY;TUESDAY;WEDNESDAY;THURSDAY;FRIDAY;SATURDAY;SUNDAY\n");

        showsNextWeek.forEach((showName, episodes) -> {
            report.append(showName).append(";");

            for (EpisodeDTO episode : episodes) {
                dayShowings.putAll(getShowingsByDay(episode));
            }

            report.append(dayShowings.get(Calendar.MONDAY)).append(";");
            report.append(dayShowings.get(Calendar.TUESDAY)).append(";");
            report.append(dayShowings.get(Calendar.WEDNESDAY)).append(";");
            report.append(dayShowings.get(Calendar.THURSDAY)).append(";");
            report.append(dayShowings.get(Calendar.FRIDAY)).append(";");
            report.append(dayShowings.get(Calendar.SATURDAY)).append(";");
            report.append(dayShowings.get(Calendar.SUNDAY)).append("\n");
        });

        return report.toString().trim();
    }

    private HashMap<Integer, String> getShowingsByDay(EpisodeDTO episode) {
        HashMap<Integer, String> dayShowings = new HashMap<>();
        for (int day = 1; day < Calendar.DAY_OF_WEEK; day++) {
            calendar.setTime(episode.getAirdate());
            if (calendar.get(Calendar.DAY_OF_WEEK) == day) {
                dayShowings.put(day, "S" + episode.getSeason() + "E" + episode.getNumber());
                break;
            }
        }
        return dayShowings;
    }

}
