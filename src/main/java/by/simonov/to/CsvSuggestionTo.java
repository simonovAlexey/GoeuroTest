package by.simonov.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CsvSuggestionTo {
    @JsonProperty("_id")
    private long id;
    private String name;
    private String type;
    private double latitude;
    private double longitude;
}
