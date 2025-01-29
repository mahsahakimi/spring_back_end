package edu.sharif.cc.services;

import edu.sharif.cc.Repository.TeacherRepository;
import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
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
}
