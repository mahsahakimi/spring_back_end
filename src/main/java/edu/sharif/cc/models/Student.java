package edu.sharif.cc.models;

import edu.sharif.cc.dtos.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Student")
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
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
            name = "name",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String name;

    private String score;

    @Column(
            name = "username",
            columnDefinition = "TEXT",
            nullable = false,
            unique = true
    )
    private String username;

    private String followers;

    private String followings;

    @Transient
    private List<String> solved;

    public List<String> getSolved() {
        if (solvedProblems == null) {
            return List.of();
        }
        return solvedProblems.stream()
                .map((problem -> problem.getTitle()))
                .collect(Collectors.toList());
    }

    @ManyToMany
    @JoinTable(
            name = "student_solved_problems",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<Problem> solvedProblems;

    public Student(String followings, String followers, String username, String score, String name) {
        this.followings = followings;
        this.followers = followers;
        this.username = username;
        this.score = score;
        this.name = name;
    }

    public static Student fromDto(StudentDTO studentDto) {
        return new Student(studentDto.getFollowings(), studentDto.getFollowers(), studentDto.getUsername(), studentDto.getScore(), studentDto.getName());
    }

    public static StudentDTO toDto(Student student) {

        return new StudentDTO(student.getSolved(), student.getFollowings(), student.getFollowers(), student.getUsername(), student.getScore(), student.getName());
    }
}
