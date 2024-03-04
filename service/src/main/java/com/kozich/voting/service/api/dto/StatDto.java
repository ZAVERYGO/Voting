package com.kozich.voting.service.api.dto;

import java.util.List;
import java.util.Map;

public class StatDto {
    private List<Map.Entry<String, Integer>> score;


    public StatDto() {
    }

    public StatDto(List<Map.Entry<String, Integer>> score) {
        this.score = score;
    }

    public List<Map.Entry<String, Integer>> getScore() {
        return score;
    }

    public void setScore(List<Map.Entry<String, Integer>> score) {
        this.score = score;
    }
}
