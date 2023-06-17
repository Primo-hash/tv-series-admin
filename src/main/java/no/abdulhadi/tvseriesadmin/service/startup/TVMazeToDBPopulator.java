package no.abdulhadi.tvseriesadmin.service.startup;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowEmbedEpisodesDTO;
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

@Service
@NoArgsConstructor
@Getter
@Setter
public class TVMazeToDBPopulator {

    private final ShowRepository repository = BeanUtil.getBean(ShowRepository.class);
    private final static String embeddedOption = "episodes";

    @Value("${tvmaze.config.file}")
    private String fileName = "";

    public TVMazeToDBPopulator(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void runStartupRoutine() {
        ArrayList<String> showNames = getFileLines();
        ArrayList<ShowDTO> shows = new ArrayList<>(
                showNames.stream()
                        .map(showName -> ShowService.getShowWithEmbed(showName, embeddedOption, ShowEmbedEpisodesDTO.class))
                        .toList()
        );
        repository.saveAll(shows);
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
