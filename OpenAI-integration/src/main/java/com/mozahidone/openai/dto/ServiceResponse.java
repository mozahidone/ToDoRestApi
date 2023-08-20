package com.mozahidone.openai.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse implements Serializable {
    boolean operationStatus;
    String message;

    public ServiceResponse() {
        this.operationStatus = true;
    }

    public ServiceResponse(boolean status, String message) {
        this.operationStatus = status;
        this.message = message;
    }
}
