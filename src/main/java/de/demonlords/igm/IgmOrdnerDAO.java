package de.demonlords.igm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IgmOrdnerDAO {

    @Autowired
    private IgmOrdnerRepository ordnerRepository;

    public List<IgmOrdner> getOrdnerByUser(Integer userId) {
        return ordnerRepository.findAllByUser(userId);
    }

    public IgmOrdner save(IgmOrdner ordner) {
        return ordnerRepository.save(ordner);
    }

    public void deleteById(Integer id) {
        ordnerRepository.deleteById(id);
    }
}
