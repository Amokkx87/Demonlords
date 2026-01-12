package de.demonlords.igm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IgmOrdnerRepository extends JpaRepository<IgmOrdner, Integer> {

    @Query("SELECT o FROM IgmOrdner o WHERE o.user = :userId ORDER BY o.lastactivity DESC")
    List<IgmOrdner> findAllByUser(Integer userId);
}
