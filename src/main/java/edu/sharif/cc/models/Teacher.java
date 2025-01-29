package edu.sharif.cc.models;

import java.util.List;

import edu.sharif.cc.dtos.TeacherDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Teacher")
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacher_id_sequence", sequenceName = "actual_teacher_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_id_sequence")
    private Long id;

    @Column(name = "username", columnDefinition = "TEXT", nullable = false, unique = true)
    private String username;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(name = "created", columnDefinition = "INTEGER DEFAULT 0")
    private Integer created;

    public Integer getCreated() {
        return createdProblems.size();
    }

    @OneToMany(mappedBy = "author")
    private List<Problem> createdProblems;


    public Teacher(String username, String name) {
        this.name = name;
        this.username = username;
    }

    public static Teacher fromDto(TeacherDTO teacherDto) {
        return new Teacher(teacherDto.getUsername(), teacherDto.getName());
    }

    public static TeacherDTO toDto(Teacher teacher) {
        return new TeacherDTO(teacher.getUsername(), teacher.getName(), teacher.getCreated());
    }
}
