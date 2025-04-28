package com.example.portfolio.Entity;

import com.example.portfolio.Entity.enums.DegreeLevel;
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
@Table(name="education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String institution;

    @Enumerated(EnumType.STRING)
    DegreeLevel degree;


    String description;
    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}
