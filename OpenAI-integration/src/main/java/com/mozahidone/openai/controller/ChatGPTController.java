package com.mozahidone.openai.controller;


import com.mozahidone.openai.dto.AskChatGPTResponse;
import com.mozahidone.openai.service.ChatGPTService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat-gpt")
public class ChatGPTController {

    private final ChatGPTService service;

    public ChatGPTController(ChatGPTService service) {
        this.service = service;
    }

    @GetMapping("/ask/{message}")
    public @ResponseBody AskChatGPTResponse askChatGPT(@PathVariable(value = "message") String message) {
        return this.service.askChatGpt(message);
    }
}
