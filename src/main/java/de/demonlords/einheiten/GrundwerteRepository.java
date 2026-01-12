package de.demonlords.einheiten;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GrundwerteRepository extends JpaRepository<Grundwerte, Integer> {

    List<Grundwerte> findByGenreAndHideAndMinlevelLessThanEqualOrderByTierAsc(
            String genre, int hide, int minlevel
    );
    
    List<Grundwerte> findByGenre(
            String genre
    );
}
