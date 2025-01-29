package edu.sharif.cc.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Category")
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @SequenceGenerator(name = "problem_id_sequence", sequenceName = "actual_problem_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_id_sequence")
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "category_problems",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<Problem> problems;

    public Category(String name) {
        this.name = name;
    }
}
