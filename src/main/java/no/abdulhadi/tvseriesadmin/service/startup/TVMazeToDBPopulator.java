package no.abdulhadi.tvseriesadmin.service.startup;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.dto.show_embed_episodes.ShowEmbedEpisodesDTO;
import no.abdulhadi.tvseriesadmin.service.TxtFileParser;
import no.abdulhadi.tvseriesadmin.service.external.api.tvmaze.ShowService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Getter
@Setter
public class TVMazeToDBPopulator {

    private final static String embeddedOption = "episodes";

    @Value("${tvmaze.config.file}")
    private String fileName = "";

    public TVMazeToDBPopulator(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void runStartupRoutine() {
        ArrayList<String> showNames = getFileLines();
        //create dto, create entity, create injector, add api requests, inject json response to list of DTOs
        ArrayList<ShowDTO> shows = new ArrayList<>(
                showNames.stream()
                        .map(showName -> ShowService.getShowWithEmbed(showName, embeddedOption, ShowEmbedEpisodesDTO.class))
                        .toList()
        );
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
