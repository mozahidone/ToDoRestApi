package com.mozahidone.openai.service;

import com.mozahidone.openai.dto.AskChatGPTResponse;
import com.mozahidone.openai.utils.Utils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ChatGPTService {
    private final OpenAiService openAiService;

    public ChatGPTService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public AskChatGPTResponse askChatGpt(String question) {
        if (!Utils.isOk(question)) {
            return new AskChatGPTResponse(false, "Question is required");
        }

        List<String> messages = new ArrayList<>();
        try {
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo-16k-0613")
                    .messages(Collections.singletonList(new ChatMessage("user", question)))
                    .build();
            List<ChatCompletionChoice> choices = openAiService.createChatCompletion(completionRequest).getChoices();
            for (ChatCompletionChoice c : choices) {
                String[] values = c.getMessage().getContent().split("\n");
                if (values.length >0) {
                    messages.addAll(Arrays.asList(values));
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return new AskChatGPTResponse(Collections.singletonList("Internal service error. Please contact with admin"));
        }
        return new AskChatGPTResponse(messages);
    }

}
