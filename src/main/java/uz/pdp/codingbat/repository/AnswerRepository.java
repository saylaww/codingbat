package uz.pdp.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbat.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
