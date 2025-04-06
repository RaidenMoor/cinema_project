package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "film_creators")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted = false")
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "film_creators_seq", allocationSize = 1, initialValue = 14)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class FilmCreator extends GenericModel{

    @Column(name = "full_name", nullable = false)
    private String fullName;

}
