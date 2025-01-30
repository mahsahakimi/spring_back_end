package edu.sharif.cc.Repository;

import edu.sharif.cc.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findByTitle(String title);

    boolean existsByTitle(String title);

    @Query(value = "SELECT p FROM Problem p WHERE p.author = ?1")
    List<Problem> findAllByAuthor(String author);
}
