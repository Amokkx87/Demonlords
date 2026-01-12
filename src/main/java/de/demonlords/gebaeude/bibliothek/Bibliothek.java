package de.demonlords.gebaeude.bibliothek;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bibliothek")
public class Bibliothek {

    @EmbeddedId
    private BibliothekId id;

    private int stufe;

    @Column(name="all_subcategories_found")
    private int allSubcategoriesFound;

    @Column(name="hide_questionmark")
    private int hideQuestionmark;

    @Column(name="assured_successes")
    private int assuredSuccesses;

	public BibliothekId getId() {
		return id;
	}

	public void setId(BibliothekId id) {
		this.id = id;
	}

	public int getStufe() {
		return stufe;
	}

	public void setStufe(int stufe) {
		this.stufe = stufe;
	}

	public int getAllSubcategoriesFound() {
		return allSubcategoriesFound;
	}

	public void setAllSubcategoriesFound(int allSubcategoriesFound) {
		this.allSubcategoriesFound = allSubcategoriesFound;
	}

	public int getHideQuestionmark() {
		return hideQuestionmark;
	}

	public void setHideQuestionmark(int hideQuestionmark) {
		this.hideQuestionmark = hideQuestionmark;
	}

	public int getAssuredSuccesses() {
		return assuredSuccesses;
	}

	public void setAssuredSuccesses(int assuredSuccesses) {
		this.assuredSuccesses = assuredSuccesses;
	}

    // getters + setters
    
}