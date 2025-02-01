package edu.sharif.cc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sharif.cc.Repository.StudentRepository;
import edu.sharif.cc.Repository.TeacherRepository;
import edu.sharif.cc.dtos.SignUpRequestDTO;
import edu.sharif.cc.dtos.SignInRequestDTO;
import edu.sharif.cc.models.Student;
import edu.sharif.cc.models.Teacher;
import edu.sharif.cc.utility.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthenticationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String studentSignUp(SignUpRequestDTO signUpRequest) {
        if (studentRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Student student = new Student();
        student.setUsername(signUpRequest.getUsername());
        student.setPassword(hashedPassword);
        student.setName(signUpRequest.getName());
        studentRepository.save(student);  

        return jwtUtil.generateToken(student.getUsername());
    }

    public String teacherSignUp(SignUpRequestDTO signUpRequest) {
        if (teacherRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Teacher teacher = new Teacher();
        teacher.setUsername(signUpRequest.getUsername());
        teacher.setPassword(hashedPassword);
        teacher.setName(signUpRequest.getName());
        teacherRepository.save(teacher);  

        return jwtUtil.generateToken(teacher.getUsername());
    }

    public String studentSignIn(SignInRequestDTO signInRequest) {
        Student student = studentRepository.findByUsername(signInRequest.getUsername()).orElse(null);

        if (student == null) {
            throw new RuntimeException("Invalid username or password");
        }

        String storedPassword = null;
        if (student != null) {
            storedPassword = student.getPassword();
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), storedPassword)) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(signInRequest.getUsername());
    }

    public String teacherSignIn(SignInRequestDTO signInRequest) {
        Teacher teacher = teacherRepository.findByUsername(signInRequest.getUsername()).orElse(null);

        if (teacher == null) {
            throw new RuntimeException("Invalid username or password");
        }

        String storedPassword = null;
        if (teacher != null) {
            storedPassword = teacher.getPassword();
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), storedPassword)) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(signInRequest.getUsername());
    }
}
