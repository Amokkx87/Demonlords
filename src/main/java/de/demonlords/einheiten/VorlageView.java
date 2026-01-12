package de.demonlords.einheiten;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.Transient;

public class VorlageView {

	public int id;
	public String name;
	public String pic;

	// Werte
	public int ap;
	public int vp;
	public int hp;
	public int mp;
	public int kommando;

	// Kosten
	public int erz;
	public int gold;
	public int holz;
	public int nahrung;
	public int silber;

	public Integer waffe;
	public Integer ruestung;
	public Integer schild;
	public Integer helm;
	public Integer ring;

	@Transient
	private Item waffeItem;
	@Transient
	private Item ruestungItem;
	@Transient
	private Item schildItem;
	@Transient
	private Item helmItem;
	@Transient
	private Item ringItem;

	public List<String> specialAbilities;

	public void loadItems(ItemRepository itemRepo) {
		waffeItem = waffe != null && waffe > 0 ? itemRepo.findById(waffe).orElse(null) : null;
		ruestungItem = ruestung != null && ruestung > 0 ? itemRepo.findById(ruestung).orElse(null) : null;
		schildItem = schild != null && schild > 0 ? itemRepo.findById(schild).orElse(null) : null;
		helmItem = helm != null && helm > 0 ? itemRepo.findById(helm).orElse(null) : null;
		ringItem = ring != null && ring > 0 ? itemRepo.findById(ring).orElse(null) : null;
	}

	@Transient
	public int getTotalAP() {
		return this.getAp() + getSafe(waffeItem, Item::getAp) + getSafe(ruestungItem, Item::getAp)
				+ getSafe(schildItem, Item::getAp) + getSafe(helmItem, Item::getAp) + getSafe(ringItem, Item::getAp);
	}

	@Transient
	public int getTotalVP() {
		return this.getVp() + getSafe(waffeItem, Item::getVp) + getSafe(ruestungItem, Item::getVp)
				+ getSafe(schildItem, Item::getVp) + getSafe(helmItem, Item::getVp) + getSafe(ringItem, Item::getVp);
	}

	@Transient
	public int getTotalHP() {
		return this.getHp() + getSafe(waffeItem, Item::getHp) + getSafe(ruestungItem, Item::getHp)
				+ getSafe(schildItem, Item::getHp) + getSafe(helmItem, Item::getHp) + getSafe(ringItem, Item::getHp);
	}

	@Transient
	public int getTotalMP() {
		return this.getMp() + getSafe(waffeItem, Item::getMp) + getSafe(ruestungItem, Item::getMp)
				+ getSafe(schildItem, Item::getMp) + getSafe(helmItem, Item::getMp) + getSafe(ringItem, Item::getMp);
	}

	@Transient
	public int getTotalErz() {
		return this.getErz() + getSafe(waffeItem, Item::getErz) + getSafe(ruestungItem, Item::getErz)
				+ getSafe(schildItem, Item::getErz) + getSafe(helmItem, Item::getErz) + getSafe(ringItem, Item::getErz);
	}

	@Transient
	public int getTotalGold() {
		return this.getGold() + getSafe(waffeItem, Item::getGold) + getSafe(ruestungItem, Item::getGold)
				+ getSafe(schildItem, Item::getGold) + getSafe(helmItem, Item::getGold)
				+ getSafe(ringItem, Item::getGold);
	}

	@Transient
	public int getTotalHolz() {
		return this.getHolz() + getSafe(waffeItem, Item::getHolz) + getSafe(ruestungItem, Item::getHolz)
				+ getSafe(schildItem, Item::getHolz) + getSafe(helmItem, Item::getHolz)
				+ getSafe(ringItem, Item::getHolz);
	}

	@Transient
	public int getTotalNahrung() {
		return this.getNahrung() + getSafe(waffeItem, Item::getNahrung) + getSafe(ruestungItem, Item::getNahrung)
				+ getSafe(schildItem, Item::getNahrung) + getSafe(helmItem, Item::getNahrung)
				+ getSafe(ringItem, Item::getNahrung);
	}

	@Transient
	public int getTotalSilber() {
		return this.getSilber() + getSafe(waffeItem, Item::getSilber) + getSafe(ruestungItem, Item::getSilber)
				+ getSafe(schildItem, Item::getSilber) + getSafe(helmItem, Item::getSilber)
				+ getSafe(ringItem, Item::getSilber);
	}

	private int getSafe(Item i, Function<Item, Integer> fn) {
		return i == null ? 0 : fn.apply(i);
	}

	// Getter/Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public int getKommando() {
		return kommando;
	}

	public void setKommando(int kommando) {
		this.kommando = kommando;
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

	public List<String> getSpecialAbilities() {
		return specialAbilities;
	}

	public void setSpecialAbilities(List<String> specialAbilities) {
		this.specialAbilities = specialAbilities;
	}

}
