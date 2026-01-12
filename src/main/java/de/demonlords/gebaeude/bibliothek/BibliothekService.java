package de.demonlords.gebaeude.bibliothek;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BibliothekService {

	private final BibliothekRepository repo;

    public BibliothekService(BibliothekRepository repo) {
        this.repo = repo;
    }

    /** Prüft, ob der User einen bestimmten Zauber erforscht hat */
    public boolean isZauberErforscht(int userId, int zauberId) {
        return repo.findByIdUserIdAndIdZauberId(userId, zauberId)
                .map(z -> z.getStufe() > 0)
                .orElse(false);
    }

    /** Alle erforschten Zauber eines Users */
    public Set<Integer> getErforschteZauberIds(int userId) {
        return repo.findByIdUserId(userId).stream()
                .map(z -> z.getId().getZauberId())
                .collect(Collectors.toSet());
    }
}
