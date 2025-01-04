package edu.sharif.cc.models;

import edu.sharif.cc.dtos.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "Student")
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_id_sequence",
            sequenceName = "actual_student_id_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_sequence"
    )
    private Long id;

    @Column(
            name = "username",
            columnDefinition = "TEXT",
            nullable = false,
            unique = true
    )
    private String username;

    @Column(
            name = "name",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_solved_problems",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<Problem> solvedProblems;

    public Student() { // for JPA
    }

    public Student(String username, String name) {
        this.name = name;
        this.username = username;
    }

    public static Student fromDto(StudentDTO studentDto) {
        return new Student(studentDto.getUsername(), studentDto.getName());
    }

    public static StudentDTO toDto(Student student) {

        return new StudentDTO(student.getUsername(), student.getName());
    }
}
