package by.simonov.service;

import by.simonov.model.Suggestion;
import by.simonov.to.CsvSuggestionTo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CsvSuggestionConverter {

    public CsvSuggestionTo toCsvSuggestionTo(@NonNull Suggestion input) {
        CsvSuggestionTo to = new CsvSuggestionTo();
        to.setId(input.getPositionId());
        to.setName(input.getPrimaryName());
        to.setType(input.getType());
        to.setLatitude(input.getLatitude());
        to.setLongitude(input.getLongitude());
        return to;
    }
}
