package de.demonlords.einheiten;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.demonlords.auth.LoginBean;
import de.demonlords.gebaeude.bibliothek.BibliothekRepository;
import de.demonlords.siedlung.Siedlung;
import de.demonlords.translation.TranslationRepository;
import jakarta.faces.application.FacesMessage;

@ExtendWith(MockitoExtension.class)
public class AusbildenBeanTest {
	
	@Mock LoginBean login;

    @Mock GrundwerteRepository grundwerteRepo;
    @Mock ItemRepository itemRepo;
    @Mock VorlageRepository vorlageRepo;
    @Mock BibliothekRepository bibliothekRepo;
    @Mock TranslationRepository translationRepo;

    private TestableAusbildenBean bean;

    private Siedlung siedlung;

    @BeforeEach
    void setup() {
        bean = new TestableAusbildenBean();

        // "Injects" manuell setzen (weil wir hier keinen Spring Context starten)
        bean.login = login;
        bean.grundwerteRepo = grundwerteRepo;
        bean.itemRepo = itemRepo;
        bean.vorlageRepo = vorlageRepo;
        bean.bibliothekRepo = bibliothekRepo;
        bean.translationRepo = translationRepo;

        siedlung = new Siedlung();
        siedlung.setErz(10_000);
        siedlung.setGold(10_000);
        siedlung.setHolz(10_000);
        siedlung.setNahrung(10_000);
        siedlung.setSilber(10_000);

        when(login.getActiveSiedlung()).thenReturn(siedlung);
        when(login.getUserId()).thenReturn(123);
    }

    @Test
    void init_withUnitId_loadsUnitAndTranslatesName() {
        // Arrange
        bean.params.put("id", "7");

        Grundwerte gw = new Grundwerte();
        gw.setId(7);
        gw.setEinheitVar("UNIT_SWORD");
        gw.setPic("sword.png");
        gw.setAp(10);
        gw.setVp(20);
        gw.setHp(30);
        gw.setMp(40);
        gw.setErz(100);
        gw.setGold(200);
        gw.setHolz(300);
        gw.setNahrung(400);
        gw.setSilber(500);
        gw.setZeit(60);
        gw.setSpecial(0);
        gw.setSpecial2(0);

        when(grundwerteRepo.findById(7)).thenReturn(Optional.of(gw));
        when(translationRepo.translate("UNIT_SWORD")).thenReturn("Schwertkämpfer");

        // Act
        bean.init();

        // Assert
        assertThat(bean.getBaseUnit()).isNotNull();
        assertThat(bean.getDisplayName()).isEqualTo("Schwertkämpfer");
        assertThat(bean.getPic()).isEqualTo("sword.png");

        verify(grundwerteRepo).findById(7);
        verify(translationRepo).translate("UNIT_SWORD");
    }

    @Test
    void init_withTemplateId_loadsTemplateUnitAndItems() {
        // Arrange
        bean.params.put("template", "99");

        Vorlage tpl = new Vorlage();
        tpl.setId(99);
        tpl.setUser(123);
        tpl.setUnit(7);

        tpl.setWaffe(11);
        tpl.setSchild(12);
        tpl.setRuestung(0);
        tpl.setHelm(0);
        tpl.setRing(0);
        tpl.setZauber(0);
        tpl.setZauber2nd(0);

        when(vorlageRepo.findById(99)).thenReturn(Optional.of(tpl));

        Grundwerte gw = new Grundwerte();
        gw.setId(7);
        gw.setEinheitVar("UNIT_SWORD");
        gw.setPic("sword.png");
        gw.setAp(10);
        gw.setVp(10);
        gw.setHp(10);
        gw.setMp(10);
        gw.setSpecial(0);
        gw.setSpecial2(0);
        when(grundwerteRepo.findById(7)).thenReturn(Optional.of(gw));
        when(translationRepo.translate("UNIT_SWORD")).thenReturn("Schwertkämpfer");

        Item w = new Item();
        w.setId(11);
        w.setAp(5);
        w.setItemmask(0);
        when(itemRepo.findById(11)).thenReturn(Optional.of(w));

        Item s = new Item();
        s.setId(12);
        s.setVp(7);
        s.setItemmask(0);
        when(itemRepo.findById(12)).thenReturn(Optional.of(s));

        // Act
        bean.init();

        // Assert
        assertThat(bean.getBaseUnit()).isNotNull();
        assertThat(bean.getDisplayName()).isEqualTo("Schwertkämpfer");

        // Werte inkl. Items prüfen
        assertThat(bean.getFullAP()).isEqualTo(10 + 5);
        assertThat(bean.getFullVP()).isEqualTo(10 + 7);

        verify(vorlageRepo).findById(99);
        verify(grundwerteRepo).findById(7);
        verify(itemRepo).findById(11);
        verify(itemRepo).findById(12);
    }

    @Test
    void trainUnit_whenEnoughResources_subtractsAndShowsSuccess() {
        // Arrange
        bean.params.put("id", "7");

        Grundwerte gw = new Grundwerte();
        gw.setId(7);
        gw.setEinheitVar("UNIT_SWORD");
        gw.setErz(100);
        gw.setGold(200);
        gw.setHolz(300);
        gw.setNahrung(400);
        gw.setSilber(500);
        gw.setZeit(60);
        gw.setSpecial(0);
        gw.setSpecial2(0);

        when(grundwerteRepo.findById(7)).thenReturn(Optional.of(gw));
        when(translationRepo.translate("UNIT_SWORD")).thenReturn("Schwertkämpfer");

        bean.init();
        bean.setAmount(2); // x2

        long erzBefore = siedlung.getErz();
        long goldBefore = siedlung.getGold();

        // Act
        bean.trainUnit();

        // Assert
        assertThat(siedlung.getErz()).isEqualTo(erzBefore - 200);
        assertThat(siedlung.getGold()).isEqualTo(goldBefore - 400);

        assertThat(bean.lastMessageSeverity).isEqualTo(FacesMessage.SEVERITY_INFO);
        assertThat(bean.lastMessageText).contains("ausgebildet");
    }

    @Test
    void trainUnit_whenNotEnoughResources_doesNotSubtractAndShowsError() {
        // Arrange
        bean.params.put("id", "7");

        Grundwerte gw = new Grundwerte();
        gw.setId(7);
        gw.setEinheitVar("UNIT_SWORD");
        gw.setErz(50_000);
        gw.setGold(0);
        gw.setHolz(0);
        gw.setNahrung(0);
        gw.setSilber(0);
        gw.setZeit(60);
        gw.setSpecial(0);
        gw.setSpecial2(0);

        when(grundwerteRepo.findById(7)).thenReturn(Optional.of(gw));
        when(translationRepo.translate("UNIT_SWORD")).thenReturn("Schwertkämpfer");

        bean.init();

        long erzBefore = siedlung.getErz();

        // Act
        bean.trainUnit();

        // Assert
        assertThat(siedlung.getErz()).isEqualTo(erzBefore);

        assertThat(bean.lastMessageSeverity).isEqualTo(FacesMessage.SEVERITY_ERROR);
        assertThat(bean.lastMessageText).contains("Nicht genügend Ressourcen");
    }

    // ------------------------------------------------------------
    // Testable Bean (überschreibt JSF-Abhängigkeiten)
    // ------------------------------------------------------------
    static class TestableAusbildenBean extends AusbildenBean {
        Map<String, String> params = new HashMap<>();

        FacesMessage.Severity lastMessageSeverity;
        String lastMessageText;

        @Override
        protected Map<String, String> getRequestParams() {
            return params;
        }

        @Override
        protected void addMessage(FacesMessage.Severity severity, String msg) {
            this.lastMessageSeverity = severity;
            this.lastMessageText = msg;
        }
    }

}
