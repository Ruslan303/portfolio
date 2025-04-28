package com.example.portfolio.Entity;

import com.example.portfolio.Entity.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    SkillLevel skillLevel;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    Portfolio portfolio;
}
