package com.mozahidone.github.controller;

import com.mozahidone.github.dto.OutputDTO;
import com.mozahidone.github.service.StreamService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {

    private final StreamService streamService;

    @Value("${key1}")
    private String url;

    @Value("${key2}")
    private String content;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(value = "search", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OutputDTO> searchCode(@RequestParam String service, @RequestParam String keyword) {
        return streamService.searchCode(service, keyword);
    }

}
