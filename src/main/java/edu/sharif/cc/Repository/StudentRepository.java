package edu.sharif.cc.Repository;

import edu.sharif.cc.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUsername(String username);

    // List<Student> findByScoreGreaterThan(int score);
    // List<Student> findByFollowersGreaterThan(int followers);
}
