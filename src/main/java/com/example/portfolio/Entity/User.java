package com.example.portfolio.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    Long id;
    @Column(unique=true,nullable=false)
    String username;
    String password;
    @OneToMany(mappedBy = "user",orphanRemoval = true)
    List<Portfolio> portfolio;
}
