package com.example.portfolio.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String projectName;
    String skills;
    String experience;
    String education;
    @ManyToOne
    @JoinColumn(name= "user_id")
    User user;

}
