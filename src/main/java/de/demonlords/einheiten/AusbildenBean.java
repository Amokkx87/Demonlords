package de.demonlords.einheiten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import de.demonlords.auth.LoginBean;
import de.demonlords.gebaeude.bibliothek.BibliothekRepository;
import de.demonlords.einheiten.Grundwerte;
import de.demonlords.einheiten.GrundwerteRepository;
import de.demonlords.einheiten.Item;
import de.demonlords.einheiten.ItemRepository;
import de.demonlords.siedlung.Siedlung;
import de.demonlords.einheiten.Vorlage;
import de.demonlords.einheiten.VorlageRepository;
import de.demonlords.translation.TranslationRepository;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@ViewScoped
public class AusbildenBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// -----------------------------
	// Injects
	// -----------------------------

	@Inject
	protected LoginBean login;

	@Inject
	protected LoginBean sessionBean;

	@Autowired
	protected GrundwerteRepository grundwerteRepo;

	@Autowired
	protected ItemRepository itemRepo;

	@Autowired
	protected VorlageRepository vorlageRepo;

	@Autowired
	protected BibliothekRepository bibliothekRepo;

	@Autowired
	protected TranslationRepository translationRepo;

	// -----------------------------
	// Model
	// -----------------------------

	protected Integer unitId; // ?id=
	protected Integer templateId; // ?template=

	protected Grundwerte baseUnit; // Grundwerte aus DB
	protected Siedlung siedlung; // aktive Siedlung

	// Ausrüstung
	protected Item waffe;
	protected Item schild;
	protected Item ruestung;
	protected Item helm;
	protected Item ring;

	// Zauber
	protected String zauberName = "Keiner";
	protected String zauber2Name = "Keiner";

	// Spezialfähigkeiten (Icons)
	protected List<String> specialAbilities = new ArrayList<>();

	// Input Menge
	protected int amount = 1;

	// -----------------------------
	// Init
	// -----------------------------

	@PostConstruct
	public void init() {
		loadParams();

		this.siedlung = login.getActiveSiedlung();

		if (templateId != null) {
			loadTemplate(templateId);
		} else if (unitId != null) {
			loadUnit(unitId);
		}
	}

	// statt direkt FacesContext zu benutzen:
	protected Map<String, String> getRequestParams() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	private void loadParams() {
		Map<String, String> params = getRequestParams();

		if (params.containsKey("id"))
			unitId = Integer.parseInt(params.get("id"));

		if (params.containsKey("template"))
			templateId = Integer.parseInt(params.get("template"));
	}

	// -------------------------------------------------
	// LOAD UNIT
	// -------------------------------------------------

	private void loadUnit(int id) {

		baseUnit = grundwerteRepo.findById(id).orElse(null);
		if (baseUnit == null)
			return;

		// DisplayName via Translation
		String lang = sessionBean.getLanguage();
		translationRepo.findByLangvarNameAndLangvarCountry(baseUnit.getEinheitVar(), lang).ifPresentOrElse(
				t -> baseUnit.setDisplayName(t.getLangvarContent()),
				() -> baseUnit.setDisplayName(baseUnit.getEinheitVar()) // fallback
		);

		// Spezialfähigkeiten
		// specialAbilities = SpecialAbilityUtil.extractIcons(baseUnit.getSpecial(),
		// baseUnit.getSpecial2());
	}

	// -------------------------------------------------
	// LOAD TEMPLATE (Unit + Items + Zauber)
	// -------------------------------------------------

	private void loadTemplate(int tplId) {

		Vorlage tpl = vorlageRepo.findById(tplId).orElse(null);
		if (tpl == null)
			return;

		// Grundwerte laden
		baseUnit = grundwerteRepo.findById(tpl.getUnitId()).orElse(null);
		if (baseUnit == null)
			return;

		String lang = sessionBean.getLanguage();
		translationRepo.findByLangvarNameAndLangvarCountry(baseUnit.getEinheitVar(), lang).ifPresentOrElse(
				t -> baseUnit.setDisplayName(t.getLangvarContent()),
				() -> baseUnit.setDisplayName(baseUnit.getEinheitVar()) // fallback
		);

		// Items nachladen
		this.waffe = (tpl.getWaffe() != 0 ? itemRepo.findById(tpl.getWaffe()).orElse(null) : null);
		this.ruestung = (tpl.getRuestung() != 0 ? itemRepo.findById(tpl.getRuestung()).orElse(null) : null);
		this.schild = (tpl.getSchild() != 0 ? itemRepo.findById(tpl.getSchild()).orElse(null) : null);
		this.helm = (tpl.getHelm() != 0 ? itemRepo.findById(tpl.getHelm()).orElse(null) : null);
		this.ring = (tpl.getRing() != 0 ? itemRepo.findById(tpl.getRing()).orElse(null) : null);

		// Zauber
		this.zauberName = resolveZauberName(tpl.getZauber());
		this.zauber2Name = resolveZauberName(tpl.getZauber2nd());

		// Spezial (Einheit + Items)
		// specialAbilities = SpecialAbilityUtil.extractIcons(baseUnit.getSpecial(),
		// baseUnit.getSpecial2());

		// Item-Specials hinzufügen
		addItemSpecials(waffe);
		addItemSpecials(schild);
		addItemSpecials(ruestung);
		addItemSpecials(helm);
		addItemSpecials(ring);
	}

	private void addItemSpecials(Item i) {
		if (i == null)
			return;
		if (i.getItemmask() > 0) {
			// specialAbilities.addAll(SpecialAbilityUtil.extractItemIcons(i.getItemmask()));
		}
	}

	private String resolveZauberName(int id) {
		if (id == 0)
			return "Keiner";
		return "Zauber #" + id; // später über Translation ersetzen
	}

	// =====================================================================
	// WERTE MIT AUSSTATTUNG
	// =====================================================================

	public int getFullAP() {
		return baseUnit.getAp() + sumItem(i -> i.getAp());
	}

	public int getFullVP() {
		return baseUnit.getVp() + sumItem(i -> i.getVp());
	}

	public int getFullHP() {
		return baseUnit.getHp() + sumItem(i -> i.getHp());
	}

	public int getFullMP() {
		return baseUnit.getMp() + sumItem(i -> i.getMp());
	}

	public int getFullTragkraft() {
		return baseUnit.getTragkraft() + sumItem(i -> i.getTragkraft());
	}

	private int sumItem(java.util.function.ToIntFunction<Item> f) {
		int sum = 0;
		if (waffe != null)
			sum += f.applyAsInt(waffe);
		if (schild != null)
			sum += f.applyAsInt(schild);
		if (ruestung != null)
			sum += f.applyAsInt(ruestung);
		if (helm != null)
			sum += f.applyAsInt(helm);
		if (ring != null)
			sum += f.applyAsInt(ring);
		return sum;
	}

	// -----------------------------
	// BONUSSTRINGS (z. B. „(+122)“)
	// -----------------------------

	public String getApBonusString() {
		return bonusString(sumItem(Item::getAp));
	}

	public String getVpBonusString() {
		return bonusString(sumItem(Item::getVp));
	}

	public String getHpBonusString() {
		return bonusString(sumItem(Item::getHp));
	}

	public String getMpBonusString() {
		return bonusString(sumItem(Item::getMp));
	}

	private String bonusString(int bonus) {
		if (bonus == 0)
			return "";
		return " (+" + bonus + ")";
	}

	// =====================================================================
	// KOSTEN (Grundkosten * amount) + Item-Kosten
	// =====================================================================

	public long getCostErz() {
		return cost(baseUnit.getErz(), i -> i.getErz());
	}

	public long getCostGold() {
		return cost(baseUnit.getGold(), i -> i.getGold());
	}

	public long getCostHolz() {
		return cost(baseUnit.getHolz(), i -> i.getHolz());
	}

	public long getCostNahrung() {
		return cost(baseUnit.getNahrung(), i -> i.getNahrung());
	}

	public long getCostSilber() {
		return cost(baseUnit.getSilber(), i -> i.getSilber());
	}

	private long cost(int base, java.util.function.ToIntFunction<Item> f) {
		return (base + sumItem(f)) * amount;
	}

	// =====================================================================
	// BAUDAUER
	// =====================================================================

	public String getDurationFormatted() {

		int sek = baseUnit.getZeit() * amount;

		int m = sek / 60;
		int s = sek % 60;

		return String.format("%02d:%02d", m, s);
	}

	// =====================================================================
	// AKTIONEN
	// =====================================================================

	@Transactional
	public void trainUnit() {

		if (!hasEnough()) {
			error("Nicht genügend Ressourcen!");
			return;
		}

		siedlung.setErz(siedlung.getErz() - getCostErz());
		siedlung.setGold(siedlung.getGold() - getCostGold());
		siedlung.setHolz(siedlung.getHolz() - getCostHolz());
		siedlung.setNahrung(siedlung.getNahrung() - getCostNahrung());
		siedlung.setSilber(siedlung.getSilber() - getCostSilber());

		success("Einheit wird ausgebildet!");
	}

	public void saveTemplate() {

		Vorlage v = new Vorlage();

		v.setUserId(login.getUserId());
		v.setUnitId(baseUnit.getId());
		v.setTemplateName(baseUnit.getDisplayName());

		// Ausrüstung
		v.setWaffe(waffe != null ? waffe.getId() : 0);
		v.setRuestung(ruestung != null ? ruestung.getId() : 0);
		v.setSchild(schild != null ? schild.getId() : 0);
		v.setHelm(helm != null ? helm.getId() : 0);
		v.setRing(ring != null ? ring.getId() : 0);

		vorlageRepo.save(v);

		success("Vorlage gespeichert.");
	}

	// =====================================================================
	// RESOURCE CHECK
	// =====================================================================

	private boolean hasEnough() {

		return getCostErz() <= siedlung.getErz() && getCostGold() <= siedlung.getGold()
				&& getCostHolz() <= siedlung.getHolz() && getCostNahrung() <= siedlung.getNahrung()
				&& getCostSilber() <= siedlung.getSilber();
	}

	protected void addMessage(FacesMessage.Severity severity, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
	}

	protected void success(String msg) {
		addMessage(FacesMessage.SEVERITY_INFO, msg);
	}

	protected void error(String msg) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	// =====================================================================
	// GETTER
	// =====================================================================

	public Grundwerte getBaseUnit() {
		return baseUnit;
	}

	public List<String> getSpecialAbilities() {
		return specialAbilities;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDisplayName() {
		return baseUnit.getDisplayName();
	}

	public String getPic() {
		return baseUnit.getPic();
	}

	public String getZauberName() {
		return zauberName;
	}

	public String getZauber2Name() {
		return zauber2Name;
	}

}
