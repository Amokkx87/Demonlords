package de.demonlords.igm;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import de.demonlords.Util;

@Named
@SessionScoped
public class IgmOrdnerBean implements Serializable {

	private static final long serialVersionUID = -7365744667649714032L;

	@Inject
	private IgmOrdnerDAO ordnerDAO;
	@Autowired
    private ApplicationEventPublisher publisher;

	private List<IgmOrdner> ordnerListe;


	private IgmOrdner ausgewaehlterOrdner;

	@PostConstruct
	public void init() {
		Integer userId = Util.getUserId();

		ordnerListe = new ArrayList<>();

		// 🗂️ 1. Default-Ordner immer hinzufügen
		ordnerListe.add(new IgmOrdner(0, userId, "Posteingang"));
		ordnerListe.add(new IgmOrdner(-1, userId, "Privat"));

		// 🗂️ 2. Benutzerdefinierte Ordner aus DB laden
		if (userId != null) {
			ordnerListe.addAll(ordnerDAO.getOrdnerByUser(userId));
		} else {
			System.err.println("⚠ Kein Benutzer eingeloggt – redirect nötig?");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// 🗂️ 3. Standardauswahl auf "Posteingang"
		ausgewaehlterOrdner = ordnerListe.stream().filter(o -> o.getId() == 0).findFirst().orElse(ordnerListe.get(0));
	}

	public IgmOrdnerDAO getOrdnerDAO() {
		return ordnerDAO;
	}

	public void setOrdnerDAO(IgmOrdnerDAO ordnerDAO) {
		this.ordnerDAO = ordnerDAO;
	}

	public List<IgmOrdner> getOrdnerListe() {
		return ordnerListe;
	}
	
	public void setOrdnerListe(List<IgmOrdner> ordnerListe) {
		this.ordnerListe = ordnerListe;
	}

	public IgmOrdner getAusgewaehlterOrdner() {
		return ausgewaehlterOrdner;
	}

	public void setAusgewaehlterOrdner(IgmOrdner ausgewaehlterOrdner) {
		this.ausgewaehlterOrdner = ausgewaehlterOrdner;
	}

	// Wird aufgerufen, wenn der Benutzer im Frontend den Ordner wechselt
	public void ordnerWechseln() {
		System.out.println("Ordner gewechselt auf: " + ausgewaehlterOrdner.getName());
        Integer userId = Util.getUserId();
        if (userId != null && ausgewaehlterOrdner != null) {
            publisher.publishEvent(new OrdnerWechselnEvent(userId, ausgewaehlterOrdner.getId()));
        }
	}

	public void setPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		
	}
}
