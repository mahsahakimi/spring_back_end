package edu.sharif.cc.Repository;

import edu.sharif.cc.models.Student;
import edu.sharif.cc.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> { // Long is the ID type

    Optional<Teacher> findByUsername(String username);

    boolean existsByUsername(String username);
    
    List<Teacher> findByFollowingsTeachersContaining(Teacher teacher);
}
