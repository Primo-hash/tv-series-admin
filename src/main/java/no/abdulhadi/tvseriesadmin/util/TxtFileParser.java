package no.abdulhadi.tvseriesadmin.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxtFileParser {
    FileReader fileReader;

    public ArrayList<String> getLinesAsStrings() {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            String currentLine;

            while((currentLine = bufferedReader.readLine()) != null) {
                lines.add(currentLine.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
