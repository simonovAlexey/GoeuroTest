package by.simonov.service;

import by.simonov.model.Suggestion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class GoEuroApiClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.suggestionUrlTemplate}")
    private String suggestUrl;

    public List<Suggestion> findSuggestionsByCity(String city) throws IOException {
        try {
            ResponseEntity<Suggestion[]> responseEntity = restTemplate.getForEntity(suggestUrl, Suggestion[].class, ImmutableMap.of(
                    "city", city
            ));
            return ImmutableList.copyOf(responseEntity.getBody());
        }catch (HttpClientErrorException httpClientErrorException){
            HttpStatus statusCode = httpClientErrorException.getStatusCode();
            String url = suggestUrl.replace("{city}",city);
            System.out.println("HttpClientErrorException: " + httpClientErrorException.getMessage()+ " in: " + url);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());

        }
        throw new IOException("Error in findSuggestionsByCity");

    }
}
