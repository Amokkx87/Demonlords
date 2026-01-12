package de.demonlords.gebaeude.kaserne;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import de.demonlords.auth.LoginBean;
import de.demonlords.einheiten.Grundwerte;
import de.demonlords.einheiten.GrundwerteRepository;
import de.demonlords.einheiten.ItemRepository;
import de.demonlords.einheiten.Vorlage;
import de.demonlords.einheiten.VorlageRepository;
import de.demonlords.einheiten.VorlageView;
import de.demonlords.gebaeude.bibliothek.BibliothekService;
import de.demonlords.translation.TranslationRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class KaserneBean implements Serializable {

	private static final long serialVersionUID = -58645491361081365L;

	@Inject
	private GrundwerteRepository grundwerteRepository;
	@Inject
	private VorlageRepository vorlageRepository;
	@Inject
	private TranslationRepository translationRepository;
	@Inject
	private ItemRepository itemRepo;

	@Autowired
	private BibliothekService bibliothekService;

	@Inject
	private LoginBean sessionBean; // enthält activeSiedlung

	private List<Grundwerte> einheiten;
	private List<VorlageView> vorlagen;
	private Grundwerte ausgewaehlt;
	private String renameBuffer;

	@PostConstruct
	public void init() {
		loadEinheiten();
		loadVorlagen();
	}
	
	public String openBuildMenuForUnit(int unitId) {
	    return "/pages/ausbilden.xhtml?id=" + unitId + "&faces-redirect=true";
	}

	public String openBuildMenuForTemplate(int templateId) {
	    return "/pages/ausbilden.xhtml?template=" + templateId + "&faces-redirect=true";
	}
	
	public void deleteVorlage(Vorlage v) {
	    vorlageRepository.delete(v);
	    loadVorlagen(); // neu laden
	}
	
	public void renameVorlage(Vorlage v) {
	    v.setTemplateName(renameBuffer);
	    vorlageRepository.save(v);
	    loadVorlagen();
	}

	private void loadEinheiten() {
		int userId = sessionBean.getUserId();
		int kaserneLevel = sessionBean.getActiveSiedlung().getKaserne();

		Set<Integer> erforschteZauber = bibliothekService.getErforschteZauberIds(userId);

		// 1) Alle Grundwerte der Kaserne laden
		List<Grundwerte> roh = grundwerteRepository.findByGenre("Kaserne").stream().filter(u -> u.getHide() == 0)
				.filter(u -> u.getMinlevel() <= kaserneLevel).filter(u -> {
					// basedon=0 → immer erlaubt
					if (u.getBasedon() == 0)
						return true;

					// wenn basedon gesetzt → Zauber muss erforscht sein
					return erforschteZauber.contains(u.getBasedon());
				}).collect(Collectors.toList());

		// 2) Duplikate entfernen → gruppieren nach einheit_var
		Map<String, Grundwerte> unique = roh.stream().collect(Collectors.toMap(Grundwerte::getEinheitVar, // KEY:
																											// eindeutiger
																											// technischer
																											// Name
				u -> u, // VALUE
				(a, b) -> a // falls doppelt → erstes behalten
		));

		// 3) final sortiert zurückgeben
		this.einheiten = unique.values().stream().sorted((a, b) -> {
			// 1. basedOn = 0 zuerst
			int cmp1 = Integer.compare(a.getBasedon() == 0 ? 0 : 1, b.getBasedon() == 0 ? 0 : 1);
			if (cmp1 != 0)
				return cmp1;

			// 2. Innerhalb sortieren nach minLevel
			return Integer.compare(a.getMinlevel(), b.getMinlevel());
		}).collect(Collectors.toList());

		String lang = sessionBean.getLanguage();

		this.einheiten.forEach(u -> {
			translationRepository.findByLangvarNameAndLangvarCountry(u.getEinheitVar(), lang).ifPresentOrElse(
					t -> u.setDisplayName(t.getLangvarContent()), () -> u.setDisplayName(u.getEinheitVar()) // fallback
			);
		});
	}

	private void loadVorlagen() {

		int userId = sessionBean.getUserId();

		List<Vorlage> raw = vorlageRepository.findByUserIdAndBuildin(userId, "Kaserne");

		this.vorlagen = raw.stream().map(v -> buildVorlageView(v)).toList();
	}

	private VorlageView buildVorlageView(Vorlage tpl) {

		Grundwerte gw = grundwerteRepository.findById(tpl.getUnitId()).orElse(null);

		if (gw == null)
			return null;

		VorlageView view = new VorlageView();

		view.id = tpl.getId();

		String lang = sessionBean.getLanguage();

		view.name = tpl.getTemplateName();

		if (view.name == null)
			// Name über Translation-Service / einheit_var
			translationRepository.findByLangvarNameAndLangvarCountry(gw.getEinheitVar(), lang)
					.ifPresentOrElse(t -> view.name = t.getLangvarContent(), () -> view.name = gw.getEinheitVar() // fallback
					);

		// Bild
		view.pic = gw.getPic();

		// Werte aus Grundwerte
		view.ap = gw.getAp();
		view.vp = gw.getVp();
		view.hp = gw.getHp();
		view.mp = gw.getMp();
		view.kommando = gw.getKommando();

		// Kosten
		view.erz = gw.getErz();
		view.gold = gw.getGold();
		view.holz = gw.getHolz();
		view.nahrung = gw.getNahrung();
		view.silber = gw.getSilber();

		view.waffe = tpl.getWaffe();
		view.ruestung = tpl.getRuestung();
		view.schild = tpl.getSchild();
		view.helm = tpl.getHelm();
		view.ring = tpl.getRing();

		// Spezialfähigkeiten aus bitmasken
		view.specialAbilities = gw.getSpecialAbilities();

		view.loadItems(itemRepo);

		return view;
	}

	public void selectUnit(Grundwerte gw) {
		this.ausgewaehlt = gw;
	}

	public List<Grundwerte> getEinheiten() {
		return einheiten;
	}

	public List<VorlageView> getVorlagen() {
		return vorlagen;
	}

	public Grundwerte getAusgewaehlt() {
		return ausgewaehlt;
	}

	public String getRenameBuffer() {
		return renameBuffer;
	}

	public void setRenameBuffer(String renameBuffer) {
		this.renameBuffer = renameBuffer;
	}
}
