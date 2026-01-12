package de.demonlords.igm;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.EventListener;

import de.demonlords.Util;

@Named("igmBean")
@SessionScoped
public class IgmBean implements Serializable {

	private static final long serialVersionUID = -5828059303643430372L;

	@Inject
	private IgmDAO igmDAO;

	@Inject
	private IgmOrdnerBean ordnerBean;
	private List<Igm> nachrichten;
	private Igm ausgewaehlteNachricht;

	private List<Igm> markierteNachrichten = new ArrayList<>();

	@PostConstruct
	public void initAfterView() {
		System.out.println("IgmBean INIT");
		Integer userId = Util.getUserId();
		if (userId != null && ordnerBean.getAusgewaehlterOrdner() != null) {
			nachrichten = igmDAO.getNachrichtenByUser(userId, ordnerBean.getAusgewaehlterOrdner().getId());
		}
	}

	@EventListener
	public void onOrdnerWechsel(OrdnerWechselnEvent event) {
		System.out.println("📨 Ordnerwechsel-Event empfangen: " + event.getOrdnerId());
		nachrichten = igmDAO.getNachrichtenByUser(event.getUserId(), event.getOrdnerId());
	}

//	public void onRowSelect(SelectEvent<Igm> event) {
//		System.out.println("onRowSelect");
//		markiereAlsGelesen(ausgewaehlteNachricht);
//	}
//
//	public void onRowUnselect() {
//		ausgewaehlteNachricht = null;
//	}
	
	public String resolveIgmText(Igm msg) {
	    // später aus der Session (z. B. User-Locale)
	    String country = "de";
	    return igmDAO.resolveIgmText(msg, country);
	}

	// 🔹 Einzelne Nachricht löschen
	public void loescheEinzeln(Igm msg) {
		igmDAO.deleteById(msg.getId());
		nachrichten.remove(msg);
	}

	// 🔹 Einzelne Nachricht sichern / Autolöschung aktivieren
	public void setEinzelnGesichert(Igm msg, boolean sichern) {
		int mask = msg.getIgmmask();

		if (sichern) {
			mask |= IgmFlags.GESICHERT_USER;
		} else {
			mask &= ~IgmFlags.GESICHERT_USER;
		}

		msg.setIgmmask(mask);
		igmDAO.update(msg);
	}

	public void markiereGelesen(boolean gelesen) {
		for (Igm msg : markierteNachrichten) {
			int mask = msg.getIgmmask();
			if (gelesen) {
				mask |= IgmFlags.GELESEN;
			} else {
				mask &= ~IgmFlags.GELESEN;
			}
			msg.setIgmmask(mask);
			igmDAO.update(msg);
		}
	}

	// 🔹 Nachricht vor Autolöschung schützen / wieder aktivieren
	public void setAutoloeschen(boolean aktiv) {
		for (Igm msg : markierteNachrichten) {
			int mask = msg.getIgmmask();
			if (aktiv) {
				mask &= ~IgmFlags.GESICHERT_USER;
			} else {
				mask |= IgmFlags.GESICHERT_USER;
			}
			msg.setIgmmask(mask);
			igmDAO.update(msg);
		}
	}

	// 🔹 Nachricht löschen
	public void loescheNachrichten() {
		for (Igm msg : markierteNachrichten) {
			igmDAO.deleteById(msg.getId());
		}
		nachrichten.removeAll(markierteNachrichten);
		markierteNachrichten.clear();
	}

	// 🔹 Nachricht verschieben in einen anderen Ordner
	public void verschiebeNachrichten(IgmOrdner ziel) {
		for (Igm msg : markierteNachrichten) {
			msg.setUserordner(ziel.getId());
			igmDAO.update(msg);
		}
		nachrichten.removeAll(markierteNachrichten);
		markierteNachrichten.clear();
	}

//	private void markiereAlsGelesen(Igm msg) {
//		int mask = msg.getIgmmask();
//
//		// prüfen, ob gelesen- oder erstmalig_gelesen-Bit schon gesetzt ist
//		boolean gelesen = (mask & 0b0001) != 0;
//		boolean erstmalig = (mask & 0b0010) != 0;
//
//		if (!gelesen && !erstmalig) {
//			// Erstmalig geöffnet → setze Bit 2
//			mask |= 0b0010;
//		} else if (!gelesen) {
//			// Bereits einmal geöffnet, aber Bit 1 fehlt → setze Bit 1
//			mask |= 0b0001;
//		}
//
//		msg.setIgmmask(mask);
//		igmDAO.update(msg);
//	}

	public List<Igm> getNachrichten() {
		return nachrichten;
	}
	
	public void setNachrichten(List<Igm> nachrichten) {
		this.nachrichten = nachrichten;
	}

	public Igm getAusgewaehlteNachricht() {
		return ausgewaehlteNachricht;
	}

	public void setAusgewaehlteNachricht(Igm ausgewaehlteNachricht) {
		this.ausgewaehlteNachricht = ausgewaehlteNachricht;
	}

	public List<Igm> getMarkierteNachrichten() {
		return markierteNachrichten;
	}

	public void setMarkierteNachrichten(List<Igm> markierteNachrichten) {
		this.markierteNachrichten = markierteNachrichten;
	}
}
