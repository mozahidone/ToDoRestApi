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

    public class Item {
        private String name;
        private String htmlUrl;
        private String content;
        private Double score;
        private Integer forks;
        private String language;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Integer getForks() {
            return forks;
        }

        public void setForks(Integer forks) {
            this.forks = forks;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

}