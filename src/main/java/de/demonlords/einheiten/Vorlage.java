package de.demonlords.einheiten;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unit_template")
public class Vorlage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "`user`")
	private Integer userId;

	@Column(name = "unit")
	private Integer unitId;

	@Column(name = "buildin", length = 15, nullable = false)
	private String buildin;

	@Column(name = "template_name", length = 50, nullable = false)
	private String templateName;

	@Column(name = "Waffe")
	private Integer waffe;

	@Column(name = "Ruestung")
	private Integer ruestung;

	@Column(name = "Schild")
	private Integer schild;

	@Column(name = "Helm")
	private Integer helm;

	@Column(name = "Ring")
	private Integer ring;

	@Column(name = "zauber")
	private Integer zauber;

	@Column(name = "zauber2nd")
	private Integer zauber2nd;

	// --------------------
	// Getter & Setter
	// --------------------

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getBuildin() {
		return buildin;
	}

	public void setBuildin(String buildin) {
		this.buildin = buildin;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getWaffe() {
		return waffe;
	}

	public void setWaffe(Integer waffe) {
		this.waffe = waffe;
	}

	public Integer getRuestung() {
		return ruestung;
	}

	public void setRuestung(Integer ruestung) {
		this.ruestung = ruestung;
	}

	public Integer getSchild() {
		return schild;
	}

	public void setSchild(Integer schild) {
		this.schild = schild;
	}

	public Integer getHelm() {
		return helm;
	}

	public void setHelm(Integer helm) {
		this.helm = helm;
	}

	public Integer getRing() {
		return ring;
	}

	public void setRing(Integer ring) {
		this.ring = ring;
	}

	public Integer getZauber() {
		return zauber;
	}

	public void setZauber(Integer zauber) {
		this.zauber = zauber;
	}

	public Integer getZauber2nd() {
		return zauber2nd;
	}

	public void setZauber2nd(Integer zauber2nd) {
		this.zauber2nd = zauber2nd;
	}
}
