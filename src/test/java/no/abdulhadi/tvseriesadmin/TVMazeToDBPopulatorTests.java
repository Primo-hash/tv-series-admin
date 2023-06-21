package no.abdulhadi.tvseriesadmin;

import no.abdulhadi.tvseriesadmin.model.dto.ShowRecommendationFilterDTO;
import no.abdulhadi.tvseriesadmin.repository.EpisodeRepository;
import no.abdulhadi.tvseriesadmin.util.BeanUtil;
import no.abdulhadi.tvseriesadmin.util.injector.DTOInjector;
import no.abdulhadi.tvseriesadmin.util.startup.TVMazeToDBPopulator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class TVMazeToDBPopulatorTests {

    @Autowired
    TVMazeToDBPopulator populator;

    @Test
    @DisplayName("Test that sample data is the same as the file data")
    void testSampleDataIsSameAsFileReaderLines() {
        ArrayList<String> sampleData = new ArrayList<>(Arrays.asList(
                "Real Time with Bill Maher",
                "Wynonna Earp",
                "Masters of Illusion",
                "Whistleblower",
                "Insatiable"
        ));

        populator.setFileName("tvmaze_config.txt");

        assertThat(populator.getFileLines(), is(sampleData));
    }

    @Test
    @DisplayName("Test that no sample data returns an empty array list from file data")
    void testNoSampleDataIsSameAsFileReaderLines() {
        ArrayList<String> sampleData = new ArrayList<>();
        populator.setFileName("");

        assertThat(populator.getFileLines(), is(sampleData));
    }
}
