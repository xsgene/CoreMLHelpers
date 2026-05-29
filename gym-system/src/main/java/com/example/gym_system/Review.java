package com.example.gym_system;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;
    private int rating; // 1-5
    private String comment;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Review() {}

    public Review(String reviewerName, int rating, String comment, Trainer trainer) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
        this.trainer = trainer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }
}
