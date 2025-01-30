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
public class StudentService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ProblemRepository problemRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.problemRepository = problemRepository;
        this.teacherRepository = teacherRepository;
    }

    public void saveStudent(StudentDTO studentDTO) {
        Student student = Student.fromDto(studentDTO);
        if (studentRepository.existsByUsername(student.getUsername())) {
            throw new UserAlreadyExistsException("Student with username " + student.getUsername() + "' already exists.");
        }
        studentRepository.save(student);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map((student) -> Student.toDto(student))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentByUsername(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));
        return Student.toDto(student);
    }

    public List<StudentDTO> getFollowers(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));
        return studentRepository.findByFollowingsStudentsContaining(student).stream()
                .map((s) -> Student.toDto(s))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getFollowingsStudents(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));
        return student.getFollowingsStudents().stream()
                .map((s) -> Student.toDto(s))
                .collect(Collectors.toList());
    }

    public List<TeacherDTO> getFollowingsTeachers(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));
        return student.getFollowingsTeachers().stream()
                .map((s) -> Teacher.toDto(s))
                .collect(Collectors.toList());
    }

    public void followStudent(String username, String otherStudentUsername) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));

        Student otherStudent = studentRepository.findByUsername(otherStudentUsername)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + otherStudentUsername));

        if (student.getFollowingsStudents().contains(otherStudent)) {
            throw new UserAlreadyFollowsException("Student already follows this student");
        }

        if (!username.equals(otherStudentUsername)) {
            student.getFollowingsStudents().add(otherStudent);
            studentRepository.save(student);
        }
    }

    public void followTeacher(String username, String teacherUsername) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));

        Teacher teacher = teacherRepository.findByUsername(teacherUsername)
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with username: " + teacherUsername));

        if (student.getFollowingsTeachers().contains(teacher)) {
            throw new UserAlreadyFollowsException("Student already follows this teacher");
        }

        student.getFollowingsTeachers().add(teacher);
        studentRepository.save(student);
    }

    public List<ProblemDTO> getSolvedProblems(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));
        return student.getSolvedProblems().stream()
                .map((p) -> Problem.toDto(p))
                .collect(Collectors.toList());
    }

    public void addSolvedProblem(String username, String title) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Student not found with username: " + username));

        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with title: " + title));

        if (student.getSolvedProblems().contains(problem)) {
            throw new ProblemAlreadySolvesException("Student already solved this problem");
        }

        student.getSolvedProblems().add(problem);
        studentRepository.save(student);
    }
}
