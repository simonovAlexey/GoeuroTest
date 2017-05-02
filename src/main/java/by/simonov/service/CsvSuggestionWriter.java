package by.simonov.service;

import by.simonov.to.CsvSuggestionTo;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@Component
public class CsvSuggestionWriter {

    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema schema = csvMapper.schemaFor(CsvSuggestionTo.class)
            .withHeader()
            .sortedBy("_id", "name", "type", "latitude", "longitude");

    public void write(@NonNull String city, @NonNull String file, @NonNull List<CsvSuggestionTo> data) {
        try (Writer writer = new PrintWriter(new FileWriter(file))) {
            if (data.size() == 0) {
                writeError(writer,city);
            } else {
                dowrite(writer, data);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing data");
        }

    }

    private void writeError(@NonNull Writer writer, @NonNull String city ) throws IOException {
        writer.write("Not found information about city: " + city);
    }

    private void dowrite(@NonNull Writer writer, @NonNull List<CsvSuggestionTo> data) throws IOException {
        csvMapper.writer(schema).writeValues(writer).write(data);
    }
}
