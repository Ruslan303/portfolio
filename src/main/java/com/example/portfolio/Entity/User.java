package com.example.portfolio.Entity;

import com.example.portfolio.Entity.enums.UserRole;
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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,  length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)// burda olmalıdır
    private String lastName;


    @Column(nullable = false, unique = true, length = 50)
    String username;

    @Column(nullable = false, length = 60) /**/
    private String password;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.MEMBER;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    List<Portfolio> portfolios;


}
