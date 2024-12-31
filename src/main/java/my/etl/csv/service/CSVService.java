package my.etl.csv.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import my.etl.sample.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CSVService {
    private final CSVInsertService csvInsertService;

    public void readCSV(MultipartFile file) {

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));) {

            String[] headers = reader.readNext(); // 첫줄헤더
            List<Map<String, String>> rows = new ArrayList<>(); // 로우

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], nextLine[i]);
                }
                rows.add(row);
            }

            csvInsertService.insertCSV("users", headers, rows);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
