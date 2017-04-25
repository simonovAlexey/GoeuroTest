package by.simonov;

import by.simonov.service.CsvSuggestionConverter;
import by.simonov.service.CsvSuggestionWriter;
import by.simonov.service.GoEuroApiClient;
import by.simonov.to.CsvSuggestionTo;
import com.google.common.collect.ImmutableList;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class GoeurotestApplication implements CommandLineRunner {

    @Autowired
    private CsvSuggestionWriter csvSuggestionWriter;
    @Autowired
    private GoEuroApiClient goEuroApiClient;
    @Autowired
    private CsvSuggestionConverter csvSuggestionConverter;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("Args not defined!");
            return;
        }
        new SpringApplicationBuilder(GoeurotestApplication.class).
                bannerMode(Banner.Mode.OFF).
                run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        String city = args[0];
        String file = city + ".csv";

        ImmutableList<CsvSuggestionTo> data = goEuroApiClient.findSuggestionsByCity(city).stream()
                .map(csvSuggestionConverter::toCsvSuggestionTo)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));

        if (data.size() == 0) {
            System.out.println("Not found information about: " + city);
        }
        csvSuggestionWriter.write(city, file, data);
    }
}
