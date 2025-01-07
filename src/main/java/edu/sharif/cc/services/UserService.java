package edu.sharif.cc.services;

import edu.sharif.cc.Repository.StudentRepository;
import edu.sharif.cc.Repository.TeacherRepository;
import edu.sharif.cc.dtos.*;
import edu.sharif.cc.exceptions.ProblemAlreadyExistsException;
import edu.sharif.cc.exceptions.UserAlreadyExistsException;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.models.Problem;
import edu.sharif.cc.models.Student;
import edu.sharif.cc.models.Teacher;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

//    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    // @Autowired
    public UserService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

//    @Autowired
//    public UserService(UserRepository userRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
//        this.userRepository = userRepository;
//        this.teacherRepository = teacherRepository;
//        this.studentRepository = studentRepository;
//    }

    public TokenDTO createUser(UserDTO user) {
        if (user.getType().equalsIgnoreCase("t")) {
            if (teacherRepository.existsByUsername(user.getUsername())) {
                throw new UserAlreadyExistsException("Teacher with username '" + user.getUsername() + "' already exists.");
            }
            else {
                Teacher teacher = new Teacher(user.getUsername(), user.getName());
                teacherRepository.save(teacher);
            }
        }
        else {
            if (studentRepository.existsByUsername(user.getUsername())) {
                throw new UserAlreadyExistsException("Student with username '" + user.getUsername() + "' already exists.");
            }
            else {
                Student student = new Student(user.getUsername(), user.getName());
                studentRepository.save(student);
            }
        }
        return new TokenDTO(user.getUsername(), user.getPassword(), user.getType());
    }

//    public TokenDTO login() {
//
//    };

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map((teacher) -> Teacher.toDto(teacher))
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherByUsername(String username) throws UserNotFoundException {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("Teacher not found with username: " + username)
                );
        return Teacher.toDto(teacher);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map((student) -> Student.toDto(student))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentByUsername(String username) throws UserNotFoundException {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("Student not found with username: " + username)
                );
        return Student.toDto(student);
    }

    public List<ProblemDTO> getSolvedProblemsByStudent(String username) throws UserNotFoundException {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("Student not found with username: " + username)
                );
        List<Problem> problems = student.getSolvedProblems();
        return problems.stream()
                .map((problem) -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

//    public UserDto createUser(UserDto userDto) throws UserAlreadyExistsException {
//        if (userRepository.existsByUsername(userDto.getUsername())) {
//            throw new UserAlreadyExistsException("User with username '" + userDto.getUsername() + "' already exists.");
//        }
//
//        User user = new User(userDto);
//        user = userRepository.save(user);
//
//        // Check user type and save to corresponding repository
//        if ("t".equals(userDto.getType())) {
//            Teacher teacher = new Teacher();
//            teacher.setUsername(user.getUsername());
//            teacherRepository.save(teacher);
//        } else if ("s".equals(userDto.getType())) {
//            Student student = new Student();
//            student.setUsername(user.getUsername());
//            studentRepository.save(student);
//        }
//
//        return new UserDto(user);
//    }
//
//    public UserDto login(UserDto userDto) throws UserNotFoundException, InvalidCredentialsException {
//        Optional<User> userOptional = userRepository.findByUsernameAndPasswordAndType(userDto.getUsername(), userDto.getPassword(), userDto.getType());
//
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException("Invalid username, password or type");
//        }
//        return new UserDto(userOptional.get());
//    }
}
