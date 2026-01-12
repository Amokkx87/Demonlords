package de.demonlords.igm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.demonlords.translation.Translation;
import de.demonlords.translation.TranslationRepository;
import de.demonlords.tutorial.Tutorial;
import de.demonlords.tutorial.TutorialRepository;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class IgmDAO {

    @Autowired
    private IgmRepository igmRepository;
    
    @Autowired
    private TutorialRepository tutorialRepository;
    
    @Autowired
    private TranslationRepository translationRepository;
    
    public String resolveIgmText(Igm igm, String country) {
        // 1️⃣ kein Tutorial? → direkt IGM-Text
        if (igm.getTutorial() == null || igm.getTutorial() == 0) {
            return igm.getIgmtext();
        }

        // 2️⃣ Tutorial finden
        Optional<Tutorial> tutOpt = tutorialRepository.findById(igm.getTutorial());
        if (tutOpt.isEmpty()) {
            return igm.getIgmtext(); // Fallback
        }

        String langKey = tutOpt.get().getTutorialtext();

        // 3️⃣ Übersetzung holen
        Optional<Translation> trOpt = translationRepository
            .findByLangvarNameAndLangvarCountry(langKey, country != null ? country : "de");

        return trOpt.map(Translation::getLangvarContent)
                    .orElse("[Fehlende Übersetzung: " + langKey + "]");
    }
    

    public List<Igm> getNachrichtenByUser(Integer userId, Integer ordnerId) {
        return igmRepository.findAllByUserAndOrdner(userId, ordnerId);
    }
    
    public void update(Igm msg) {
    	igmRepository.save(msg);
    }

    public Igm save(Igm message) {
        return igmRepository.save(message);
    }

    public void deleteById(Integer id) {
        igmRepository.deleteById(id);
    }
}
