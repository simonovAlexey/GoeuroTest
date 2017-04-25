package by.simonov.service;

import by.simonov.model.Suggestion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GoEuroApiClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.suggestionUrlTemplate}")
    private String suggestUrl;

    public List<Suggestion> findSuggestionsByCity(String city) {
        ResponseEntity<Suggestion[]> responseEntity = restTemplate.getForEntity(suggestUrl, Suggestion[].class, ImmutableMap.of(
                "city", city
        ));
        ImmutableList<Suggestion> suggestions = ImmutableList.copyOf(responseEntity.getBody());
        return suggestions;
    }
}
