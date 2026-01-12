package de.demonlords.igm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IgmRepository extends JpaRepository<Igm, Integer> {

    @Query("SELECT i FROM Igm i WHERE i.user = :userId AND i.userordner = :ordnerId ORDER BY i.datum DESC")
    List<Igm> findAllByUserAndOrdner(Integer userId, Integer ordnerId);
}