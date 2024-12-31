package my.etl.csv.controller;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import my.etl.csv.service.CSVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/csv")
@RequiredArgsConstructor
public class CSVController {
    private final CSVService csvService;

    @PostMapping
    public ResponseEntity<String> uploadCSV(@RequestParam("file")MultipartFile file) throws CsvValidationException, IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        csvService.readCSV(file);

        return ResponseEntity.ok("success");
    }
}
