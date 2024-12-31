package my.etl.csv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CSVInsertService {
    private final JdbcTemplate jdbcTemplate;

    public void insertCSV(String tableName, String[] headers, List<Map<String, String>> rows) {
        String columns = String.join(", ", headers);
        String valuePlaceholders = String.join(", ", headers).replaceAll("[^,]+", "?");

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, valuePlaceholders);

        for (Map<String, String> row : rows) {
            String[] values = new String[headers.length];
            for (int i = 0; i < headers.length; i++) {
                values[i] = row.get(headers[i]);
            }
            jdbcTemplate.update(sql, values); // 데이터 삽입
        }

    }

}
