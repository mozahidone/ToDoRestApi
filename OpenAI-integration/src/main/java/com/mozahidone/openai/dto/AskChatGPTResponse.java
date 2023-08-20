package com.mozahidone.openai.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AskChatGPTResponse extends ServiceResponse implements Serializable {

    List<String> answers;

    public AskChatGPTResponse(boolean status, String message) {
        super(status, message);
    }

    public AskChatGPTResponse(List<String> answers) {
        super();
        this.answers = answers;
    }
}
