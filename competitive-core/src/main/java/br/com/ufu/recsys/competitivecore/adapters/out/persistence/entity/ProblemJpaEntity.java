package br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problems")
public class ProblemJpaEntity {

    @Id
    private UUID id;

    @Column(name = "cf_problem_id", unique = true, nullable = false)
    private String cfProblemId; // Ex: "158A"

    private String title;
    private Integer rating;

    @ElementCollection
    @CollectionTable(name = "problem_tags", joinColumns = @JoinColumn(name = "problem_id"))
    @Column(name = "tag")
    private List<String> tags;

    protected ProblemJpaEntity() {}

    public ProblemJpaEntity(UUID id, String cfProblemId, String title, Integer rating, List<String> tags) {
        this.id = id;
        this.cfProblemId = cfProblemId;
        this.title = title;
        this.rating = rating;
        this.tags = tags;
    }

    public UUID getId() { return id; }
    public String getCfProblemId() { return cfProblemId; }
    public String getTitle() { return title; }
    public Integer getRating() { return rating; }
    public List<String> getTags() { return tags; }
}