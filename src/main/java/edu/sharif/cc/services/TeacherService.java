package edu.sharif.cc.services;

import edu.sharif.cc.Repository.ProblemRepository;
import edu.sharif.cc.Repository.StudentRepository;
import edu.sharif.cc.Repository.TeacherRepository;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.dtos.StudentDTO;
import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.exceptions.*;
import edu.sharif.cc.models.Problem;
import edu.sharif.cc.models.Student;
import edu.sharif.cc.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository, ProblemRepository problemRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.problemRepository = problemRepository;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map((teacher) -> Teacher.toDto(teacher))
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherByUsername(String username) throws UserNotFoundException {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));
        return Teacher.toDto(teacher);
    }

    public void saveTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.fromDto(teacherDTO);
        if (teacherRepository.existsByUsername(teacher.getUsername())) {
            throw new UserAlreadyExistsException("Teacher with username " + teacher.getUsername() + "' already exists.");
        }
        teacherRepository.save(teacher);
    }

    public List<ProblemDTO> getCreatedProblems(String username) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));
        return teacher.getCreatedProblems().stream()
                .map((p) -> Problem.toDto(p))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getFollowersStudents(String username) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));
        return studentRepository.findByFollowingsTeachersContaining(teacher).stream()
                .map((s) -> Student.toDto(s))
                .collect(Collectors.toList());
    }

    public List<TeacherDTO> getFollowersTeachers(String username) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));
        return teacher.getFollowersTeachers().stream()
                .map((t) -> Teacher.toDto(t))
                .collect(Collectors.toList());
    }

    public void addCreatedProblem(String username, String title) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));

        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with title: " + title));

        if (problemRepository.existsByTitle(title)) {
            throw new ProblemAlreadyExistsException("Problem already created");
        }

        teacher.getCreatedProblems().add(problem);
        teacherRepository.save(teacher);
    }

    public List<TeacherDTO> getFollowings(String username) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));
        return teacher.getFollowingsTeachers().stream()
                .map((s) -> Teacher.toDto(s))
                .collect(Collectors.toList());
    }

    public void followTeacher(String username, String otherTeacherUsername) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + username));

        Teacher otherTeacher = teacherRepository.findByUsername(otherTeacherUsername)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + otherTeacherUsername));

        if (teacher.getFollowingsTeachers().contains(otherTeacher)) {
            throw new UserAlreadyFollowsException("Teacher already follows this teacher");
        }

        if (!username.equals(otherTeacherUsername)) {
            teacher.getFollowingsTeachers().add(otherTeacher);
            teacherRepository.save(teacher);
        }
    }
}