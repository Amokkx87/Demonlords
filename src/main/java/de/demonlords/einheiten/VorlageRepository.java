package de.demonlords.einheiten;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VorlageRepository extends JpaRepository<Vorlage, Integer> {

    List<Vorlage> findByUserId(Integer userId);
    
    List<Vorlage> findByUserIdAndBuildin(Integer userId, String buildin);

    List<Vorlage> findByUserIdAndUnitId(Integer userId, Integer unitId);
}