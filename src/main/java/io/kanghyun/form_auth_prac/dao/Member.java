package io.kanghyun.form_auth_prac.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    private String email;

    private String role = "MEMBER";

    private LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
