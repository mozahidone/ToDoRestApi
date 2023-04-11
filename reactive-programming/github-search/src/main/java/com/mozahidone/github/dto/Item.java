package com.mozahidone.github.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Item {
    private String name;
    private String htmlUrl;
    private String content;
    private Double score;
    private Integer forks;
    private String language;
}
