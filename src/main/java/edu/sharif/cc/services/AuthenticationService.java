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

    // SignUp: Register student or teacher and return JWT token
    public String signUp(SignUpRequestDTO signUpRequest) {
        // Check if username exists in either Student or Teacher repository
        if (studentRepository.existsByUsername(signUpRequest.getUsername()) || teacherRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        // Hash the password before storing
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        // Assuming you're signing up a student or teacher (You can also add role or type)
        Student student = new Student();
        student.setUsername(signUpRequest.getUsername());
        student.setPassword(hashedPassword);
        studentRepository.save(student);  // You can decide if it's a Student or Teacher

        // Generate JWT token after successful registration
        return jwtUtil.generateToken(student.getUsername());
    }

    // SignIn: Authenticate student or teacher and return JWT token
    public String signIn(SignInRequestDTO signInRequest) {
        // Check if username exists in either Student or Teacher repository
        Student student = studentRepository.findByUsername(signInRequest.getUsername()).orElse(null);
        Teacher teacher = teacherRepository.findByUsername(signInRequest.getUsername()).orElse(null);

        if (student == null && teacher == null) {
            throw new RuntimeException("Invalid username or password");
        }

        String storedPassword = null;
        if (student != null) {
            storedPassword = student.getPassword();
        } else if (teacher != null) {
            storedPassword = teacher.getPassword();
        }

        // Compare password with stored hashed password
        if (!passwordEncoder.matches(signInRequest.getPassword(), storedPassword)) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT token after successful login
        return jwtUtil.generateToken(signInRequest.getUsername());
    }
}
