package edu.sharif.cc.Repository;

import edu.sharif.cc.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> { // Long is the ID type

    Optional<Teacher> findByUsername(String username);

    // List<Teacher> findByCreatedGreaterThan(int created);
    // List<Teacher> findByFollowersGreaterThan(int followers);
}
