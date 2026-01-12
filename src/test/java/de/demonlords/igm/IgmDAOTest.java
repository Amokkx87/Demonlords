package de.demonlords.igm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.demonlords.translation.Translation;
import de.demonlords.translation.TranslationRepository;
import de.demonlords.tutorial.Tutorial;
import de.demonlords.tutorial.TutorialRepository;

@SpringBootTest
@ActiveProfiles("test")
class IgmDAOTest {

    @Autowired
    private TutorialRepository tutorialRepo;

    @Autowired
    private TranslationRepository translationRepo;

    @Autowired
    private IgmDAO igmDAO;

    private Tutorial tutorial;
    private Translation translation;

    @BeforeEach
    void setup() {
        tutorial = new Tutorial();
        tutorial.setTutorialtext("TUT_START_01");
        tutorialRepo.save(tutorial);

        translation = new Translation();
        translation.setLangvarName("TUT_START_01");
        translation.setLangvarCountry("de");
        translation.setLangvarContent("Willkommen zum Tutorial!");
        translationRepo.save(translation);
    }

    @Test
    void testResolveIgmTextMitTutorial() {
        Igm igm = new Igm();
        igm.setTutorial(tutorial.getId());

        String result = igmDAO.resolveIgmText(igm, "de");
        assertThat(result).isEqualTo("Willkommen zum Tutorial!");
    }

    @Test
    void testResolveIgmTextOhneTutorial() {
        Igm igm = new Igm();
        igm.setIgmtext("Normale Nachricht");

        String result = igmDAO.resolveIgmText(igm, "de");
        assertThat(result).isEqualTo("Normale Nachricht");
    }
}
