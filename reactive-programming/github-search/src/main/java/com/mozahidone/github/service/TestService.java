package com.mozahidone.github.service;

import com.mozahidone.github.dto.SearchResultDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

public class TestService {

    private static final String GITHUB_CODE_SEARCH_API_ENDPOINT = "https://api.github.com";

    private final WebClient webClient;

    public TestService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(GITHUB_CODE_SEARCH_API_ENDPOINT).build();
    }

    public Mono<SearchResultDTO> searchCode(String service, String keyword) {

        WebClient.ResponseSpec responseSpec = this.webClient.get().uri("/search/code?q={keyword}+in:file+language:js+repo:jquery/jquery", keyword)
                .retrieve();
        SearchResultDTO responseBody = responseSpec.bodyToMono(SearchResultDTO.class).block();


        return this.webClient.get().uri("/search/code?q={keyword}+in:file+language:js+repo:jquery/jquery", keyword)
                .retrieve().bodyToMono(SearchResultDTO.class);
    }

    public SearchResultDTO searchCode3(String service, String keyword) {

        WebClient.ResponseSpec responseSpec = this.webClient.get().uri("/search/code?q={keyword}+in:file+language:js+repo:jquery/jquery", keyword)
                .retrieve();
        SearchResultDTO responseBody = responseSpec.bodyToMono(SearchResultDTO.class).block();


        return responseBody;
    }

    public Flux<SearchResultDTO> searchCode2(String service, String keyword) {

        /*Flux<SearchResultDTO> eventFlux = Flux.fromStream(Stream.generate(() -> new SearchResultDTO(1, true, null)));

        return this.webClient.get().uri("/search/code?q={keyword}+in:file+language:js+repo:jquery/jquery", keyword)
                .retrieve().bodyToFlux(SearchResultDTO.class);*/


        //Flux<SearchResultDTO> eventFlux = streamService.searchCode2("AWS", "addClass");

        Flux<SearchResultDTO> eventFlux = Flux.fromStream(Stream.generate(() -> new SearchResultDTO(1, true, null)));

        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(15));

        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);

    }

    private void analyzeCode(String service) {

    }

    /*
    @GetMapping("/stream-sse")
    public Flux<SearchResultDTO> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }
*/
    //Flux<SearchResultDTO> eventFlux = streamService.searchCode2("AWS", "addClass");

}