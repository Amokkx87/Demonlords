package de.demonlords.translation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "translations")
public class Translation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer langvarId;

	private String langvarName;
	private String langvarCountry;
	private String langvarContent;

	// Getter/Setter
	public String getLangvarName() {
		return langvarName;
	}

	public void setLangvarName(String langvarName) {
		this.langvarName = langvarName;
	}

	public String getLangvarCountry() {
		return langvarCountry;
	}

	public void setLangvarCountry(String langvarCountry) {
		this.langvarCountry = langvarCountry;
	}

	public String getLangvarContent() {
		return langvarContent;
	}

	public void setLangvarContent(String langvarContent) {
		this.langvarContent = langvarContent;
	}
}
