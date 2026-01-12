package de.demonlords.tutorial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String typ = "";
	private String specialId = "";
	private Integer level = 0;
	private String betreff = "";
	private String tutorialtext = "";
	private Integer activate = 0;
	private Integer startquest = 0;

	// Getter/Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getBetreff() {
		return betreff;
	}

	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}

	public String getTutorialtext() {
		return tutorialtext;
	}

	public void setTutorialtext(String tutorialtext) {
		this.tutorialtext = tutorialtext;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Integer getStartquest() {
		return startquest;
	}

	public void setStartquest(Integer startquest) {
		this.startquest = startquest;
	}

}