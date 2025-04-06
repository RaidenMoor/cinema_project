package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*; // Используйте jakarta.persistence.*
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "genres_seq", allocationSize = 1, initialValue = 14)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Genres extends GenericModel {
    @Column(name = "genre_name", nullable = false)
    private String genreName;

}