package de.demonlords.igm;

import jakarta.faces.application.Application;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseStream;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.lifecycle.Lifecycle;
import jakarta.faces.render.RenderKit;

import java.util.Iterator;

import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
public class TestFacesContext extends FacesContext {

    private ExternalContext externalContext;

    protected TestFacesContext() {
        setCurrentInstance(this);
    }

    public static TestFacesContext create() {
        return new TestFacesContext();
    }

    public void setExternalContext(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    @Override
    public ExternalContext getExternalContext() {
        return externalContext;
    }

    // --- Pflicht-Methoden, minimal implementiert ---

    @Override
    public Application getApplication() {
        return null;
    }

    @Override
    public Iterator<String> getClientIdsWithMessages() {
        return null;
    }

    @Override
    public FacesMessage.Severity getMaximumSeverity() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages(String clientId) {
        return null;
    }

    @Override
    public RenderKit getRenderKit() {
        return null;
    }

    @Override
    public boolean getRenderResponse() {
        return false;
    }

    @Override
    public boolean getResponseComplete() {
        return false;
    }

    @Override
    public ResponseStream getResponseStream() {
        return null;
    }

    @Override
    public void setResponseStream(ResponseStream responseStream) {
    }

    @Override
    public ResponseWriter getResponseWriter() {
        return null;
    }

    @Override
    public void setResponseWriter(ResponseWriter responseWriter) {
    }

    @Override
    public UIViewRoot getViewRoot() {
        return null;
    }

    @Override
    public void setViewRoot(UIViewRoot root) {
    }

    @Override
    public void addMessage(String clientId, FacesMessage message) {
    }

    @Override
    public void release() {
        setCurrentInstance(null);
    }

	@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renderResponse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void responseComplete() {
		// TODO Auto-generated method stub
		
	}
}
