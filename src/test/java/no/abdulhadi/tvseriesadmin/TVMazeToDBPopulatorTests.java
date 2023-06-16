package no.abdulhadi.tvseriesadmin;

import no.abdulhadi.tvseriesadmin.util.startup.TVMazeToDBPopulator;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TVMazeToDBPopulatorTests {

    TVMazeToDBPopulator populator = new TVMazeToDBPopulator("tvmaze_config.txt");

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