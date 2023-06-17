package no.abdulhadi.tvseriesadmin.service.startup;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.EpisodeDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowEmbedEpisodesDTO;
import no.abdulhadi.tvseriesadmin.repository.EpisodeRepository;
import no.abdulhadi.tvseriesadmin.repository.ShowRepository;
import no.abdulhadi.tvseriesadmin.service.BeanUtil;
import no.abdulhadi.tvseriesadmin.service.TxtFileParser;
import no.abdulhadi.tvseriesadmin.service.external.api.tvmaze.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Getter
@Setter
public class TVMazeToDBPopulator {

    private final ShowRepository showRepository = BeanUtil.getBean(ShowRepository.class);
    private final EpisodeRepository episodeRepository = BeanUtil.getBean(EpisodeRepository.class);
    private final static String embeddedOption = "episodes";

    @Value("${tvmaze.config.file}")
    private String fileName = "";

    public TVMazeToDBPopulator(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void runStartupRoutine() {
        ArrayList<String> showNames = getFileLines();
        ArrayList<ShowEmbedEpisodesDTO> shows = populateDatabaseWithShows(showNames);
        populateDatabaseWithEpisodes(shows);
    }

    private ArrayList<ShowEmbedEpisodesDTO> populateDatabaseWithShows(ArrayList<String> showNames) {
        ArrayList<ShowEmbedEpisodesDTO> shows =
                showNames.stream()
                        .map(showName -> ShowService.getShowWithEmbed(showName, embeddedOption))
                        .collect(Collectors.toCollection(ArrayList::new));
        showRepository.saveAll(shows);
        return shows;
    }

    private void populateDatabaseWithEpisodes(ArrayList<ShowEmbedEpisodesDTO> shows) {
        ArrayList<EpisodeDTO> episodes = shows.stream()
                .flatMap(show -> show.getEmbedded().getEpisodes().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        episodeRepository.saveAll(episodes);
    }

    public ArrayList<String> getFileLines() {
        ArrayList<String> lines = new ArrayList<>();
        try {
           File file = ResourceUtils.getFile("classpath:"+fileName);
           TxtFileParser txtFileParser = TxtFileParser.builder().fileReader(new FileReader(file.getAbsolutePath())).build();
           lines = txtFileParser.getLinesAsStrings();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
