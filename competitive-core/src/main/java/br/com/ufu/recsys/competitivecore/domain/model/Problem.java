package br.com.ufu.recsys.competitivecore.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Problem {
    private final UUID id;
    private final String cfProblemId;
    private final String title;
    private final Integer rating;
    private final List<String> tags;

    public Problem(UUID id, String cfProblemId, String title, Integer rating, List<String> tags) {
        this.id = id;
        this.cfProblemId = cfProblemId;
        this.title = title;
        this.rating = rating;
        this.tags = tags != null ? Collections.unmodifiableList(tags) : Collections.emptyList();
    }

    public UUID getId() {
        return id;
    }

    public String getCfProblemId() {
        return cfProblemId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getRating() {
        return rating;
    }

    public List<String> getTags() {
        return tags;
    }
}
