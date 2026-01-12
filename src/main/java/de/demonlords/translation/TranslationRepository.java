package de.demonlords.translation;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    Optional<Translation> findByLangvarNameAndLangvarCountry(String langvarName, String langvarCountry);
}