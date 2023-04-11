package com.mozahidone.github.service;

import com.mozahidone.github.dto.Item;
import com.mozahidone.github.dto.OutputDTO;
import com.mozahidone.github.dto.RepositoryDTO;
import com.mozahidone.github.dto.SearchResultDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class StreamService {

    private static final String GITHUB_CODE_SEARCH_API_ENDPOINT = "https://api.github.com";

    private final WebClient webClient;

    public StreamService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(GITHUB_CODE_SEARCH_API_ENDPOINT).build();
    }

    public Flux<OutputDTO> searchCode(String service, String keyword) {

        Flux<OutputDTO> eventFlux = Flux.fromStream(Stream.generate(() -> callGitHubAPI(service, keyword)));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(15));
        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);

    }

    public Mono<OutputDTO> search(String query) {
        return webClient.get()
                .uri("/search/repositories?q={query}", query)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OutputDTO.class);
    }

    private OutputDTO callGitHubAPI(String service, String keyword) {
        System.out.println("Service: " + service + ", keyword: " + keyword);
        Map<String, String> uriVariables = new HashMap<>();

        uriVariables.put("keyword", keyword);
        uriVariables.put("org", service);

        RestTemplate restTemplate = new RestTemplate();
        SearchResultDTO searchResultDTO = restTemplate.getForObject("https://api.github.com/search/code?q={keyword}+org:{org}", SearchResultDTO.class, uriVariables);

        return analyzeCode(searchResultDTO, keyword);
    }

    private OutputDTO analyzeCode(SearchResultDTO searchResultDTO, String keyword) {
        OutputDTO outputDTO = new OutputDTO();
        outputDTO.setTotalCount(searchResultDTO.getTotalCount());
        outputDTO.setItems(new ArrayList<>());

        for(RepositoryDTO item: searchResultDTO.getItems()) {
            //RepositoryDTO repositoryDTO = new RepositoryDTO();

            Item inner = new Item();

            inner.setName(item.getName());
            inner.setHtmlUrl(item.getHtmlUrl());
            inner.setContent(searchKeyword(item.getHtmlUrl(), keyword));
            inner.setScore(item.getScore());
            inner.setForks(item.getForks());
            inner.setLanguage(item.getLanguage());
            outputDTO.getItems().add(inner);
        }
        return outputDTO;
    }

    private String searchKeyword(String githubUrl, String keyword) {
        StringBuffer fileText = new StringBuffer();
        String content = "";
        String foundText = "";
        try {
            URL url = new URL(githubUrl);
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            while ((content = read.readLine()) != null)
                fileText = fileText.append(content);
            read.close();

            int startIndex = fileText.indexOf(keyword);
            foundText = fileText.substring(startIndex, startIndex + keyword.length() + 80);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (Exception e) {
            foundText = keyword;
        }
        return foundText;
    }

}