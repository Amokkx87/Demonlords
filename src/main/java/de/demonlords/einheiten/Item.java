package de.demonlords.einheiten;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String typ;
    private Integer tier;

    private Integer ap;
    private Integer vp;
    private Integer hp;
    private Integer mp;
    private Integer tragkraft;

    private Integer erz;
    private Integer gold;
    private Integer holz;
    private Integer nahrung;
    private Integer silber;

    private Integer zeit;
    private Integer opferpunkte;
    private Integer kraft;

    private Integer distanz;
    private Integer element;
    private Long itemmask;

    private String einheit_var_requirements;
    private Integer specialrequirements;
    private Integer specialrequirements2;

    private String beschreibung;

    @Column(name = "blacksmith_level")
    private Integer blacksmithLevel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	public Integer getAp() {
		return ap;
	}

	public void setAp(Integer ap) {
		this.ap = ap;
	}

	public Integer getVp() {
		return vp;
	}

	public void setVp(Integer vp) {
		this.vp = vp;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getMp() {
		return mp;
	}

	public void setMp(Integer mp) {
		this.mp = mp;
	}

	public Integer getTragkraft() {
		return tragkraft;
	}

	public void setTragkraft(Integer tragkraft) {
		this.tragkraft = tragkraft;
	}

	public Integer getErz() {
		return erz;
	}

	public void setErz(Integer erz) {
		this.erz = erz;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getHolz() {
		return holz;
	}

	public void setHolz(Integer holz) {
		this.holz = holz;
	}

	public Integer getNahrung() {
		return nahrung;
	}

	public void setNahrung(Integer nahrung) {
		this.nahrung = nahrung;
	}

	public Integer getSilber() {
		return silber;
	}

	public void setSilber(Integer silber) {
		this.silber = silber;
	}

	public Integer getZeit() {
		return zeit;
	}

	public void setZeit(Integer zeit) {
		this.zeit = zeit;
	}

	public Integer getOpferpunkte() {
		return opferpunkte;
	}

	public void setOpferpunkte(Integer opferpunkte) {
		this.opferpunkte = opferpunkte;
	}

	public Integer getKraft() {
		return kraft;
	}

	public void setKraft(Integer kraft) {
		this.kraft = kraft;
	}

	public Integer getDistanz() {
		return distanz;
	}

	public void setDistanz(Integer distanz) {
		this.distanz = distanz;
	}

	public Integer getElement() {
		return element;
	}

	public void setElement(Integer element) {
		this.element = element;
	}

	public Long getItemmask() {
		return itemmask;
	}

	public void setItemmask(Long itemmask) {
		this.itemmask = itemmask;
	}

	public String getEinheit_var_requirements() {
		return einheit_var_requirements;
	}

	public void setEinheit_var_requirements(String einheit_var_requirements) {
		this.einheit_var_requirements = einheit_var_requirements;
	}

	public Integer getSpecialrequirements() {
		return specialrequirements;
	}

	public void setSpecialrequirements(Integer specialrequirements) {
		this.specialrequirements = specialrequirements;
	}

	public Integer getSpecialrequirements2() {
		return specialrequirements2;
	}

	public void setSpecialrequirements2(Integer specialrequirements2) {
		this.specialrequirements2 = specialrequirements2;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Integer getBlacksmithLevel() {
		return blacksmithLevel;
	}

	public void setBlacksmithLevel(Integer blacksmithLevel) {
		this.blacksmithLevel = blacksmithLevel;
	}

    // Getter/Setter …
}