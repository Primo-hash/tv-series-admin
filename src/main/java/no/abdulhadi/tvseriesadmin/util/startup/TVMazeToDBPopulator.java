package no.abdulhadi.tvseriesadmin.util.startup;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.abdulhadi.tvseriesadmin.util.TxtFileParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

@Component
@NoArgsConstructor
@Getter
@Setter
public class TVMazeToDBPopulator {

    @Value("${tvmaze.config.file}")
    private String fileName = "";

    public TVMazeToDBPopulator(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void runStartupRoutine() {
        ArrayList<String> shows = getFileLines();
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
