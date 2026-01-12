package de.demonlords.gebaeude.bibliothek;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothekRepository extends JpaRepository<Bibliothek, BibliothekId> {

	List<Bibliothek> findByIdUserId(int userId);

	Optional<Bibliothek> findByIdUserIdAndIdZauberId(int userId, int zauberId);
}