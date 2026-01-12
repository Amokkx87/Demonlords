package de.demonlords.tutorial;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackages = "de.demonlords")
@EnableJpaRepositories(basePackages = "de.demonlords")
public class TutorialRepositoryTest {

	@Autowired
	private TutorialRepository tutorialRepo;

	@Test
	void testTutorialSaveAndFind() {
		Tutorial tut = new Tutorial();
		tut.setTutorialtext("TUT01");
		tutorialRepo.save(tut);

		var found = tutorialRepo.findById(tut.getId());
		assertThat(found).isPresent();
		assertThat(found.get().getTutorialtext()).isEqualTo("TUT01");
	}

}
