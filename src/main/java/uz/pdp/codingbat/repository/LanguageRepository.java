package uz.pdp.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbat.entity.Language;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
//    boolean existsByName(String name);
    Optional<Language> findByName(String name);

    boolean existsByName(String name);

}
