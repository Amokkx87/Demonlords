package de.demonlords.auth;

import java.io.Serializable;
import java.util.Optional;

import de.demonlords.Util;
import de.demonlords.entity.Account;
import de.demonlords.siedlung.Siedlung;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String password;
	private String uname;

	@Inject
	private LoginDAO loginDAO;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String loginProject() {
		String passwordHashed = Util.md5(password);
		Optional<Account> accountOpt = loginDAO.login(uname, passwordHashed);
		if (accountOpt.isPresent()) {
			HttpSession session = Util.getSession();
			Account acc = accountOpt.get();
			session.setAttribute("username", acc.getName());
			session.setAttribute("userId", acc.getId());
			return "pages/inbox.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please try again!"));
			return "login.xhtml?faces-redirect=true";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login.xhtml?faces-redirect=true";
	}

	public Siedlung getActiveSiedlung() {
		Siedlung siedlung = new Siedlung();
		siedlung.setKaserne(13);
		return siedlung;
	}

	public int getUserId() {
		return Util.getUserId();
	}

	public String getLanguage() {
		// TODO Auto-generated method stub
		return "de";
	}
}