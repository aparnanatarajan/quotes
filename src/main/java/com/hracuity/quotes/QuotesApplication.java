package com.hracuity.quotes;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hracuity.quotes.model.Quote;
import com.hracuity.quotes.service.QuotesService;

@SpringBootApplication
public class QuotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(QuotesService service) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            TypeReference<List<Quote>> typeReference = new TypeReference<List<Quote>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream(
            								"/json/ShortDb.json");
            try {
                List<Quote> quotes = mapper.readValue(inputStream,typeReference);
                service.createQuotes(quotes);
                System.out.println ("Saved Quotes");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
