package de.demonlords.igm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class IgmBeanTest {

	@Mock
    private IgmDAO igmDAO;

    @Mock
    private IgmOrdnerBean ordnerBean;

    @InjectMocks
    private IgmBean igmBean;

    private Igm nachricht1;
    private Igm nachricht2;

    @BeforeEach
    void setup() {
        nachricht1 = new Igm();
        nachricht1.setId(1);
        nachricht1.setIgmmask(0);

        nachricht2 = new Igm();
        nachricht2.setId(2);
        nachricht2.setIgmmask(0);

        List<Igm> liste = new ArrayList<>();
        liste.add(nachricht1);
        liste.add(nachricht2);
        igmBean.setMarkierteNachrichten(liste);
    }

    // --------------------------------------------------
    // 🔹 Einzeloperationen
    // --------------------------------------------------

    @Test
    void testLoescheEinzeln() {
        List<Igm> nachrichten = new ArrayList<>();
        nachrichten.add(nachricht1);
        igmBean.setMarkierteNachrichten(nachrichten);
        igmBean.setNachrichten(new ArrayList<>(nachrichten));

        igmBean.loescheEinzeln(nachricht1);

        verify(igmDAO).deleteById(1);
        assertThat(igmBean.getNachrichten()).doesNotContain(nachricht1);
    }

    @Test
    void testSetEinzelnGesichert_AktiviertUndDeaktiviert() {
        nachricht1.setIgmmask(0);

        igmBean.setEinzelnGesichert(nachricht1, true);
        assertThat(nachricht1.getIgmmask() & IgmFlags.GESICHERT_USER).isNotZero();

        igmBean.setEinzelnGesichert(nachricht1, false);
        assertThat(nachricht1.getIgmmask() & IgmFlags.GESICHERT_USER).isZero();

        verify(igmDAO, atLeastOnce()).update(any());
    }

    // --------------------------------------------------
    // 🔹 Mehrfachoperationen (markierte Nachrichten)
    // --------------------------------------------------

    @Test
    void testMarkiereGelesen_SetztUndEntferntBit() {
        igmBean.markiereGelesen(true);
        for (Igm msg : igmBean.getMarkierteNachrichten()) {
            assertThat(msg.getIgmmask() & IgmFlags.GELESEN).isNotZero();
        }

        igmBean.markiereGelesen(false);
        for (Igm msg : igmBean.getMarkierteNachrichten()) {
            assertThat(msg.getIgmmask() & IgmFlags.GELESEN).isZero();
        }

        verify(igmDAO, atLeastOnce()).update(any());
    }

    @Test
    void testSetAutoloeschen_AktivUndInaktiv() {
        nachricht1.setIgmmask(IgmFlags.GESICHERT_USER);

        igmBean.setAutoloeschen(true);
        assertThat(nachricht1.getIgmmask() & IgmFlags.GESICHERT_USER).isZero();

        igmBean.setAutoloeschen(false);
        assertThat(nachricht1.getIgmmask() & IgmFlags.GESICHERT_USER).isNotZero();

        verify(igmDAO, atLeastOnce()).update(any());
    }

    @Test
    void testLoescheNachrichten() {
        igmBean.setNachrichten(new ArrayList<>(igmBean.getMarkierteNachrichten()));
        igmBean.loescheNachrichten();

        verify(igmDAO, times(2)).deleteById(anyInt());
        assertThat(igmBean.getNachrichten()).isEmpty();
        assertThat(igmBean.getMarkierteNachrichten()).isEmpty();
    }

    @Test
    void testVerschiebeNachrichten_AendertUserordner() {
        IgmOrdner ziel = new IgmOrdner(99, 1, "Testordner");
        igmBean.setNachrichten(new ArrayList<>(igmBean.getMarkierteNachrichten()));

        igmBean.verschiebeNachrichten(ziel);

        for (Igm msg : igmBean.getMarkierteNachrichten()) {
            assertThat(msg.getUserordner()).isEqualTo(99);
        }

        verify(igmDAO, atLeastOnce()).update(any());
        assertThat(igmBean.getNachrichten()).isEmpty();
    }

    // --------------------------------------------------
    // 🔹 Event & Text-Resolver
    // --------------------------------------------------

    @Test
    void testOnOrdnerWechsel_LädtNeueNachrichten() {
        OrdnerWechselnEvent event = new OrdnerWechselnEvent(1, 42);
        when(igmDAO.getNachrichtenByUser(1, 42)).thenReturn(List.of(nachricht1));

        igmBean.onOrdnerWechsel(event);

        assertThat(igmBean.getNachrichten()).containsExactly(nachricht1);
        verify(igmDAO).getNachrichtenByUser(1, 42);
    }

    @Test
    void testResolveIgmText_RuftDAO() {
        when(igmDAO.resolveIgmText(nachricht1, "de")).thenReturn("Hallo Welt");
        String result = igmBean.resolveIgmText(nachricht1);
        assertThat(result).isEqualTo("Hallo Welt");
        verify(igmDAO).resolveIgmText(nachricht1, "de");
    }
}