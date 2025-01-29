package edu.sharif.cc.Repository;

import edu.sharif.cc.models.Student;
import edu.sharif.cc.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUsername(String username);

    boolean existsByUsername(String username);

    List<Student> findByFollowingsStudentsContaining(Student student);
    List<Student> findByFollowingsTeachersContaining(Teacher teacher);
}
