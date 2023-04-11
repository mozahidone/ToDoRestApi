package com.mozahidone.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonPropertyOrder({"total_file", "items"})
public class OutputDTO {

    @JsonProperty("total_file")
    private Integer totalCount;

    private List<Item> items;
}