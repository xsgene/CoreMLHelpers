package com.example.gym_system;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Trainer() {}

    public Trainer(String name, String specialty, Gym gym) {
        this.name = name;
        this.specialty = specialty;
        this.gym = gym;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public Gym getGym() { return gym; }
    public void setGym(Gym gym) { this.gym = gym; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
