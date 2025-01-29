package edu.sharif.cc.models;

import edu.sharif.cc.dtos.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Student")
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "student_id_sequence", sequenceName = "actual_student_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_sequence")
    private Long id;

    @Column(name = "username", columnDefinition = "TEXT", nullable = false, unique = true)
    private String username;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(name = "score", columnDefinition = "INTEGER DEFAULT 0")
    private Integer score;

    public Integer getScore() {
        return solvedProblems.size();
    }

    @ManyToMany
    @JoinTable(
        name = "student_followers",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<Student> followingsStudents;

    @ManyToMany
    @JoinTable(
            name = "student_teacher_followers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> followingsTeachers;

    @ManyToMany
    @JoinTable(
            name = "student_solved_problems",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<Problem> solvedProblems;

    public Student(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public static Student fromDto(StudentDTO studentDto) {
        return new Student(studentDto.getUsername(), studentDto.getName());
    }

    public static StudentDTO toDto(Student student) {

        return new StudentDTO(student.getUsername(), student.getName(), student.getScore());
    }
}
