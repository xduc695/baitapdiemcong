package com.__Nguyenxuanduc.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_year")
    private String birthYear;

    @Column(name = "school")
    private String school;

    @Column(name = "role")
    private String role;

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;
}