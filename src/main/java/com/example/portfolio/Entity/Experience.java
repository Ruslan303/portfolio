package com.example.portfolio.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String company;
    String position;
    LocalDate startDate;
    LocalDate endDate;
    @Column(length = 2000)
    String description;
    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    Portfolio portfolio;
}
