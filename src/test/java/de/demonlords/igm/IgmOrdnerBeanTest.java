package de.demonlords.igm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import de.demonlords.Util;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class IgmOrdnerBeanTest {

    //@Mock
    private IgmOrdnerDAO ordnerDAO;

    @Mock
    private ApplicationEventPublisher publisher;

    //@InjectMocks
    private IgmOrdnerBean ordnerBean;

    @BeforeEach
    void setup() {
        
    	// 1. FacesContext mocken
    	TestFacesContext context = TestFacesContext.create();

        ExternalContext external = mock(ExternalContext.class);
        
        // 3. Mock in unser TestFacesContext injizieren
        context.setExternalContext(external);
      
        // 2. DAO mocken
        ordnerDAO = mock(IgmOrdnerDAO.class);

        // 3. Bean initialisieren
        ordnerBean = new IgmOrdnerBean();
        ordnerBean.setOrdnerDAO(ordnerDAO);
        
     // Event Publisher mocken
        publisher = mock(ApplicationEventPublisher.class);
        ordnerBean.setPublisher(publisher);
        
    }

    // --------------------------------------------------
    // 🔹 Test: init() mit gültigem Benutzer
    // --------------------------------------------------
    @Test
    void testInitMitUserLaedtOrdnerUndSetztStandard() {
        // Arrange
        Util.setTestUserId(42); // temporär simuliert den eingeloggten Benutzer
        when(ordnerDAO.getOrdnerByUser(42))
            .thenReturn(List.of(new IgmOrdner(5, 42, "Kampfberichte")));

        // Act
        ordnerBean.init();

        // Assert
        assertThat(ordnerBean.getOrdnerListe())
            .extracting(IgmOrdner::getName)
            .contains("Posteingang", "Privat", "Kampfberichte");

        assertThat(ordnerBean.getAusgewaehlterOrdner().getName())
            .isEqualTo("Posteingang");

        verify(ordnerDAO).getOrdnerByUser(42);
    }

    // --------------------------------------------------
    // 🔹 Test: init() ohne Benutzer → Redirect
    // --------------------------------------------------
    @Test
    void testInitOhneUserFuehrtRedirectAus() throws IOException {
        Util.setTestUserId(null);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Act
        ordnerBean.init();

        // Assert
        verify(externalContext, atLeastOnce()).redirect("login.xhtml");
    }

    // --------------------------------------------------
    // 🔹 Test: ordnerWechseln() publiziert Event
    // --------------------------------------------------
    @Test
    void testOrdnerWechselnPubliziertEvent() {
        Util.setTestUserId(7);
        IgmOrdner ordner = new IgmOrdner(99, 7, "Archiv");
        ordnerBean.setAusgewaehlterOrdner(ordner);

        ordnerBean.ordnerWechseln();

        ArgumentCaptor<OrdnerWechselnEvent> captor = ArgumentCaptor.forClass(OrdnerWechselnEvent.class);
        verify(publisher).publishEvent(captor.capture());

        OrdnerWechselnEvent event = captor.getValue();
        assertThat(event.getUserId()).isEqualTo(7);
        assertThat(event.getOrdnerId()).isEqualTo(99);
    }

    // --------------------------------------------------
    // 🔹 Test: Getter / Setter
    // --------------------------------------------------
    @Test
    void testGetterUndSetter() {
        IgmOrdner ordner = new IgmOrdner(1, 1, "Test");
        ordnerBean.setAusgewaehlterOrdner(ordner);
        assertThat(ordnerBean.getAusgewaehlterOrdner()).isEqualTo(ordner);

        List<IgmOrdner> liste = List.of(ordner);
        ordnerBean.setOrdnerListe(liste);
        assertThat(ordnerBean.getOrdnerListe()).isEqualTo(liste);
    }
}