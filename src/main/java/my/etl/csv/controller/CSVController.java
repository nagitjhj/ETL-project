package my.etl.csv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class CSVController {
    @PostMapping
    public ResponseEntity<String> uploadCSV(@RequestParam("file")MultipartFile file) {

        return ResponseEntity.ok("success");
    }
}
