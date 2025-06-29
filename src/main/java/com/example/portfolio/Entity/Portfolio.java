package com.example.portfolio.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="portfolio")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String description;

    @ManyToOne
    @JoinColumn(name= "user_id",nullable = false)
    User user;

    @OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Education> educations;

    @OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Experience> experiences;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Project> projects;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Skill> skills;


}
