package com.example.cinema.model;

import com.example.cinema.model.enums.Countries;
import com.example.cinema.model.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Table(name = "films")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Where(clause = "is_deleted = false")
@SequenceGenerator(name = "default_gen", sequenceName = "films_seq", allocationSize = 1, initialValue = 12)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Film extends GenericModel{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_year", nullable = false, length = 4)
    private Short releaseYear;


    @Column(name = "genre", nullable = false)
    @Enumerated
    private Genre genre;


    @Column(name = "country", nullable = false)
    @Enumerated
    private Countries country;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "poster_file_name")
    private String posterFileName;

    @Column(name = "rating_kp")
    private Float ratingKp;

    @ManyToMany()
    @JoinTable(name = "films_film_creators",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "fk_films_film_creators"),
            inverseJoinColumns = @JoinColumn(name = "film_creator_id"), inverseForeignKey = @ForeignKey(name = "fk_film_creators_films"))
    private Set<FilmCreator> filmCreators;

    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    private Set<FilmSession> filmSessions;

    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    private Set<Review> reviews;
}
