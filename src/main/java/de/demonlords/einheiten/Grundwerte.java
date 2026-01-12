package de.demonlords.einheiten;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "grundwerte")
public class Grundwerte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int baseunittype;
	private int tier;
	private int hide;

	private String name;
	private String typ;
	private String typ_var;
	@Column(name = "einheit_var")
	private String einheitVar;

	private int ap;
	private int vp;
	private int hp;
	private int mp;
	private int lives;

	private int erz;
	private int gold;
	private int holz;
	private int nahrung;
	private int silber;

	private int zeit;
	private int element;
	private int kraft;
	private int tragkraft;

	private int max_potions;
	private String genre;

	private String pic;
	private int onlinepic;

	private int basedon;
	private int minlevel;
	private int maxlevel;

	private int kommando;

	private int multi_unit_kill;
	private int atts_per_round;

	private int drei_max;
	private int fsei_max;
	private int doei_max;
	private int frei_max;
	private int feei_max;

	private String ei;
	private String ei_name;

	private String potion_restriction;

	private int special;
	private int special2;
	private int expires_after;
	
	@Transient
	private String displayName;

	public String getDisplayName() {
	    return displayName;
	}

	public void setDisplayName(String name) {
	    this.displayName = name;
	}
	
	@Transient
	public List<String> getSpecialAbilities() {
	    List<String> icons = new ArrayList<>();

	    long s1 = this.special;  // aus DB
	    long s2 = this.special2; // aus DB

	    // ---- SPECIAL 1 MASKS ----
	    if ((s1 & 64) > 0) icons.add("icons/20x20/distanz.png");
	    if ((s1 & 128) > 0) icons.add("icons/20x20/spionage.png");
	    if ((s1 & 256) > 0) icons.add("icons/20x20/checktroops.png");
	    if ((s1 & 512) > 0) icons.add("icons/20x20/drei.png");
	    if ((s1 & 1024) > 0) icons.add("icons/20x20/magie.png");
	    if ((s1 & 2048) > 0) icons.add("icons/20x20/zahl2.png");
	    if ((s1 & 4096) > 0) icons.add("icons/20x20/zahl3.png");
	    if ((s1 & 8192) > 0) icons.add("icons/20x20/schwert.png");
	    if ((s1 & 16384) > 0) icons.add("icons/20x20/buch.png");
	    if ((s1 & 32768) > 0) icons.add("icons/20x20/gem_white.png");
	    if ((s1 & 65536) > 0) icons.add("icons/20x20/drache.png");
	    //if ((s1 & 1048576) > 0) icons.add("in_siege.png");
	    if ((s1 & 4194304) > 0) icons.add("icons/20x20/daemon.png");
	    if ((s1 & 8388608) > 0) icons.add("icons/20x20/distanz_resistenz.png");
	    if ((s1 & 16777216) > 0) icons.add("icons/20x20/magie_resistenz.png");
	    if ((s1 & 33554432) > 0) icons.add("icons/20x20/zahl3durchgestrichen.png");
	    if ((s1 & 536870912) > 0) icons.add("icons/20x20/human.png");
	    if ((s1 & 1073741824) > 0) icons.add("icons/20x20/undead.png");

	    // ---- SPECIAL 2 MASKS ----
	    if ((s2 & 1) > 0) icons.add("icons/20x20/magie2.png");
	    if ((s2 & 2) > 0) icons.add("icons/20x20/gruppenspionage.png");
	    
	    // ---- Elemente ----
	    if((element & 1) > 0) icons.add("icons/20x20/feuer.png");
	    if((element & 2) > 0) icons.add("icons/20x20/eis.png");
	    if((element & 4) > 0) icons.add("icons/20x20/wind.png");
	    if((element & 8) > 0) icons.add("icons/20x20/erde.png");

	    return icons;
	}

	// GETTER/SETTER

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBaseunittype() {
		return baseunittype;
	}

	public void setBaseunittype(int baseunittype) {
		this.baseunittype = baseunittype;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getHide() {
		return hide;
	}

	public void setHide(int hide) {
		this.hide = hide;
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

	public String getTyp_var() {
		return typ_var;
	}

	public void setTyp_var(String typ_var) {
		this.typ_var = typ_var;
	}

	public String getEinheitVar() {
		return einheitVar;
	}

	public void setEinheitVar(String einheitVar) {
		this.einheitVar = einheitVar;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	public int getVp() {
		return vp;
	}

	public void setVp(int vp) {
		this.vp = vp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getErz() {
		return erz;
	}

	public void setErz(int erz) {
		this.erz = erz;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getHolz() {
		return holz;
	}

	public void setHolz(int holz) {
		this.holz = holz;
	}

	public int getNahrung() {
		return nahrung;
	}

	public void setNahrung(int nahrung) {
		this.nahrung = nahrung;
	}

	public int getSilber() {
		return silber;
	}

	public void setSilber(int silber) {
		this.silber = silber;
	}

	public int getZeit() {
		return zeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	public int getElement() {
		return element;
	}

	public void setElement(int element) {
		this.element = element;
	}

	public int getKraft() {
		return kraft;
	}

	public void setKraft(int kraft) {
		this.kraft = kraft;
	}

	public int getTragkraft() {
		return tragkraft;
	}

	public void setTragkraft(int tragkraft) {
		this.tragkraft = tragkraft;
	}

	public int getMax_potions() {
		return max_potions;
	}

	public void setMax_potions(int max_potions) {
		this.max_potions = max_potions;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getOnlinepic() {
		return onlinepic;
	}

	public void setOnlinepic(int onlinepic) {
		this.onlinepic = onlinepic;
	}

	public int getBasedon() {
		return basedon;
	}

	public void setBasedon(int basedon) {
		this.basedon = basedon;
	}

	public int getMinlevel() {
		return minlevel;
	}

	public void setMinlevel(int minlevel) {
		this.minlevel = minlevel;
	}

	public int getMaxlevel() {
		return maxlevel;
	}

	public void setMaxlevel(int maxlevel) {
		this.maxlevel = maxlevel;
	}

	public int getKommando() {
		return kommando;
	}

	public void setKommando(int kommando) {
		this.kommando = kommando;
	}

	public int getMulti_unit_kill() {
		return multi_unit_kill;
	}

	public void setMulti_unit_kill(int multi_unit_kill) {
		this.multi_unit_kill = multi_unit_kill;
	}

	public int getAtts_per_round() {
		return atts_per_round;
	}

	public void setAtts_per_round(int atts_per_round) {
		this.atts_per_round = atts_per_round;
	}

	public int getDrei_max() {
		return drei_max;
	}

	public void setDrei_max(int drei_max) {
		this.drei_max = drei_max;
	}

	public int getFsei_max() {
		return fsei_max;
	}

	public void setFsei_max(int fsei_max) {
		this.fsei_max = fsei_max;
	}

	public int getDoei_max() {
		return doei_max;
	}

	public void setDoei_max(int doei_max) {
		this.doei_max = doei_max;
	}

	public int getFrei_max() {
		return frei_max;
	}

	public void setFrei_max(int frei_max) {
		this.frei_max = frei_max;
	}

	public int getFeei_max() {
		return feei_max;
	}

	public void setFeei_max(int feei_max) {
		this.feei_max = feei_max;
	}

	public String getEi() {
		return ei;
	}

	public void setEi(String ei) {
		this.ei = ei;
	}

	public String getEi_name() {
		return ei_name;
	}

	public void setEi_name(String ei_name) {
		this.ei_name = ei_name;
	}

	public String getPotion_restriction() {
		return potion_restriction;
	}

	public void setPotion_restriction(String potion_restriction) {
		this.potion_restriction = potion_restriction;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public int getSpecial2() {
		return special2;
	}

	public void setSpecial2(int special2) {
		this.special2 = special2;
	}

	public int getExpires_after() {
		return expires_after;
	}

	public void setExpires_after(int expires_after) {
		this.expires_after = expires_after;
	}

}
