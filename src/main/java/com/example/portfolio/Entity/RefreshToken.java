package com.example.portfolio.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens", indexes = {
        @Index(name = "idx_refresh_tokens_token", columnList = "token", unique = true)
})
@Getter
@Setter
@ToString(callSuper = true, exclude = "user") /**/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 512) /**/
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public boolean isExpired() {
        return expiryDate.isBefore(Instant.now());
    }
}
