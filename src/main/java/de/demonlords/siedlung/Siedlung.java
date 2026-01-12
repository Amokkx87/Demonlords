package de.demonlords.siedlung;

import jakarta.persistence.*;

@Entity
@Table(name = "siedlungen")
public class Siedlung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer user;
	private Integer punkte;

	private String siedlung;
	private Integer x;
	private Integer y;

	private Integer gott;
	private Integer god_bar;

	private Long gruendungszeit;

	// Ressourcen
	private Long erz;
	private Long gold;
	private Long holz;
	private Long nahrung;
	private Long silber;

	private Long erz_storage;
	private Long gold_storage;
	private Long holz_storage;
	private Long nahrung_storage;
	private Long silber_storage;

	private Long storage_limit;

	private Integer erzprodbonus;
	private Integer goldprodbonus;
	private Integer holzprodbonus;
	private Integer nahrungprodbonus;
	private Integer silberprodbonus;

	private Float prodmodifier;

	// Gem-Limits
	private Integer drei;
	private Integer fsei;
	private Integer doei;
	private Integer frei;
	private Integer feei;

	private Integer gem_white;
	private Integer gem_red;
	private Integer gem_blue;
	private Integer gem_green;
	private Integer gem_yellow;

	private Long no_gems_until;
	private Long godbar_fixed_until;

	// Produktionsgebäude
	private Integer erzmine;
	private Integer goldmine;
	private Integer saegewerk;
	private Integer farm;
	private Integer silbermine;

	// Hauptgebäude
	private Integer Haupthaus;
	private Integer Bibliothek;
	private Integer Kaserne;
	private Integer Speicher;
	private Integer Tempel;
	private Integer Friedhof;
	private Integer Drachenhof;
	private Integer Verteidigung;
	private Integer Schattenportal;
	private Integer Hexenkueche;
	private Integer Malus;
	private Integer Arsenal;
	private Integer Blacksmith;
	private Integer Questbuilding1;
	private Integer magier;

	// Belagerungen
	private Long besieged_since;
	private Integer besieged_by;

	private Integer settlemask;
	private Integer envobjcount;

	// --- GETTER & SETTER ---

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getPunkte() {
		return punkte;
	}

	public void setPunkte(Integer punkte) {
		this.punkte = punkte;
	}

	public String getSiedlung() {
		return siedlung;
	}

	public void setSiedlung(String siedlung) {
		this.siedlung = siedlung;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getGott() {
		return gott;
	}

	public void setGott(Integer gott) {
		this.gott = gott;
	}

	public Integer getGod_bar() {
		return god_bar;
	}

	public void setGod_bar(Integer god_bar) {
		this.god_bar = god_bar;
	}

	public Long getGruendungszeit() {
		return gruendungszeit;
	}

	public void setGruendungszeit(Long gruendungszeit) {
		this.gruendungszeit = gruendungszeit;
	}

	public Long getErz() {
		return erz;
	}

	public void setErz(Long erz) {
		this.erz = erz;
	}

	public Long getGold() {
		return gold;
	}

	public void setGold(Long gold) {
		this.gold = gold;
	}

	public Long getHolz() {
		return holz;
	}

	public void setHolz(Long holz) {
		this.holz = holz;
	}

	public Long getNahrung() {
		return nahrung;
	}

	public void setNahrung(Long nahrung) {
		this.nahrung = nahrung;
	}

	public Long getSilber() {
		return silber;
	}

	public void setSilber(Long silber) {
		this.silber = silber;
	}

	public Long getErz_storage() {
		return erz_storage;
	}

	public void setErz_storage(Long erz_storage) {
		this.erz_storage = erz_storage;
	}

	public Long getGold_storage() {
		return gold_storage;
	}

	public void setGold_storage(Long gold_storage) {
		this.gold_storage = gold_storage;
	}

	public Long getHolz_storage() {
		return holz_storage;
	}

	public void setHolz_storage(Long holz_storage) {
		this.holz_storage = holz_storage;
	}

	public Long getNahrung_storage() {
		return nahrung_storage;
	}

	public void setNahrung_storage(Long nahrung_storage) {
		this.nahrung_storage = nahrung_storage;
	}

	public Long getSilber_storage() {
		return silber_storage;
	}

	public void setSilber_storage(Long silber_storage) {
		this.silber_storage = silber_storage;
	}

	public Long getStorage_limit() {
		return storage_limit;
	}

	public void setStorage_limit(Long storage_limit) {
		this.storage_limit = storage_limit;
	}

	public Integer getErzprodbonus() {
		return erzprodbonus;
	}

	public void setErzprodbonus(Integer erzprodbonus) {
		this.erzprodbonus = erzprodbonus;
	}

	public Integer getGoldprodbonus() {
		return goldprodbonus;
	}

	public void setGoldprodbonus(Integer goldprodbonus) {
		this.goldprodbonus = goldprodbonus;
	}

	public Integer getHolzprodbonus() {
		return holzprodbonus;
	}

	public void setHolzprodbonus(Integer holzprodbonus) {
		this.holzprodbonus = holzprodbonus;
	}

	public Integer getNahrungprodbonus() {
		return nahrungprodbonus;
	}

	public void setNahrungprodbonus(Integer nahrungprodbonus) {
		this.nahrungprodbonus = nahrungprodbonus;
	}

	public Integer getSilberprodbonus() {
		return silberprodbonus;
	}

	public void setSilberprodbonus(Integer silberprodbonus) {
		this.silberprodbonus = silberprodbonus;
	}

	public Float getProdmodifier() {
		return prodmodifier;
	}

	public void setProdmodifier(Float prodmodifier) {
		this.prodmodifier = prodmodifier;
	}

	public Integer getDrei() {
		return drei;
	}

	public void setDrei(Integer drei) {
		this.drei = drei;
	}

	public Integer getFsei() {
		return fsei;
	}

	public void setFsei(Integer fsei) {
		this.fsei = fsei;
	}

	public Integer getDoei() {
		return doei;
	}

	public void setDoei(Integer doei) {
		this.doei = doei;
	}

	public Integer getFrei() {
		return frei;
	}

	public void setFrei(Integer frei) {
		this.frei = frei;
	}

	public Integer getFeei() {
		return feei;
	}

	public void setFeei(Integer feei) {
		this.feei = feei;
	}

	public Integer getGem_white() {
		return gem_white;
	}

	public void setGem_white(Integer gem_white) {
		this.gem_white = gem_white;
	}

	public Integer getGem_red() {
		return gem_red;
	}

	public void setGem_red(Integer gem_red) {
		this.gem_red = gem_red;
	}

	public Integer getGem_blue() {
		return gem_blue;
	}

	public void setGem_blue(Integer gem_blue) {
		this.gem_blue = gem_blue;
	}

	public Integer getGem_green() {
		return gem_green;
	}

	public void setGem_green(Integer gem_green) {
		this.gem_green = gem_green;
	}

	public Integer getGem_yellow() {
		return gem_yellow;
	}

	public void setGem_yellow(Integer gem_yellow) {
		this.gem_yellow = gem_yellow;
	}

	public Long getNo_gems_until() {
		return no_gems_until;
	}

	public void setNo_gems_until(Long no_gems_until) {
		this.no_gems_until = no_gems_until;
	}

	public Long getGodbar_fixed_until() {
		return godbar_fixed_until;
	}

	public void setGodbar_fixed_until(Long godbar_fixed_until) {
		this.godbar_fixed_until = godbar_fixed_until;
	}

	public Integer getErzmine() {
		return erzmine;
	}

	public void setErzmine(Integer erzmine) {
		this.erzmine = erzmine;
	}

	public Integer getGoldmine() {
		return goldmine;
	}

	public void setGoldmine(Integer goldmine) {
		this.goldmine = goldmine;
	}

	public Integer getSaegewerk() {
		return saegewerk;
	}

	public void setSaegewerk(Integer saegewerk) {
		this.saegewerk = saegewerk;
	}

	public Integer getFarm() {
		return farm;
	}

	public void setFarm(Integer farm) {
		this.farm = farm;
	}

	public Integer getSilbermine() {
		return silbermine;
	}

	public void setSilbermine(Integer silbermine) {
		this.silbermine = silbermine;
	}

	public Integer getHaupthaus() {
		return Haupthaus;
	}

	public void setHaupthaus(Integer haupthaus) {
		Haupthaus = haupthaus;
	}

	public Integer getBibliothek() {
		return Bibliothek;
	}

	public void setBibliothek(Integer bibliothek) {
		Bibliothek = bibliothek;
	}

	public Integer getKaserne() {
		return Kaserne;
	}

	public void setKaserne(Integer kaserne) {
		Kaserne = kaserne;
	}

	public Integer getSpeicher() {
		return Speicher;
	}

	public void setSpeicher(Integer speicher) {
		Speicher = speicher;
	}

	public Integer getTempel() {
		return Tempel;
	}

	public void setTempel(Integer tempel) {
		Tempel = tempel;
	}

	public Integer getFriedhof() {
		return Friedhof;
	}

	public void setFriedhof(Integer friedhof) {
		Friedhof = friedhof;
	}

	public Integer getDrachenhof() {
		return Drachenhof;
	}

	public void setDrachenhof(Integer drachenhof) {
		Drachenhof = drachenhof;
	}

	public Integer getVerteidigung() {
		return Verteidigung;
	}

	public void setVerteidigung(Integer verteidigung) {
		Verteidigung = verteidigung;
	}

	public Integer getSchattenportal() {
		return Schattenportal;
	}

	public void setSchattenportal(Integer schattenportal) {
		Schattenportal = schattenportal;
	}

	public Integer getHexenkueche() {
		return Hexenkueche;
	}

	public void setHexenkueche(Integer hexenkueche) {
		Hexenkueche = hexenkueche;
	}

	public Integer getMalus() {
		return Malus;
	}

	public void setMalus(Integer malus) {
		Malus = malus;
	}

	public Integer getArsenal() {
		return Arsenal;
	}

	public void setArsenal(Integer arsenal) {
		Arsenal = arsenal;
	}

	public Integer getBlacksmith() {
		return Blacksmith;
	}

	public void setBlacksmith(Integer blacksmith) {
		Blacksmith = blacksmith;
	}

	public Integer getQuestbuilding1() {
		return Questbuilding1;
	}

	public void setQuestbuilding1(Integer questbuilding1) {
		Questbuilding1 = questbuilding1;
	}

	public Integer getMagier() {
		return magier;
	}

	public void setMagier(Integer magier) {
		this.magier = magier;
	}

	public Long getBesieged_since() {
		return besieged_since;
	}

	public void setBesieged_since(Long besieged_since) {
		this.besieged_since = besieged_since;
	}

	public Integer getBesieged_by() {
		return besieged_by;
	}

	public void setBesieged_by(Integer besieged_by) {
		this.besieged_by = besieged_by;
	}

	public Integer getSettlemask() {
		return settlemask;
	}

	public void setSettlemask(Integer settlemask) {
		this.settlemask = settlemask;
	}

	public Integer getEnvobjcount() {
		return envobjcount;
	}

	public void setEnvobjcount(Integer envobjcount) {
		this.envobjcount = envobjcount;
	}
}
