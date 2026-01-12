package de.demonlords.translation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class TranslationRepositoryTest {

	@Autowired
	private TranslationRepository translationRepo;

	@Test
	void testTranslationSaveAndFind() {
		Translation t = new Translation();
		t.setLangvarName("HELLO");
		t.setLangvarCountry("de");
		t.setLangvarContent("Hallo Welt");
		translationRepo.save(t);

		var found = translationRepo.findByLangvarNameAndLangvarCountry("HELLO", "de");
		assertThat(found).isPresent();
		assertThat(found.get().getLangvarContent()).isEqualTo("Hallo Welt");
	}

}
