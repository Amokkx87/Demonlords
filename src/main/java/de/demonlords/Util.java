package de.demonlords;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Util {

	// ThreadLocal nur für Tests (nicht persistent!)
	private static final ThreadLocal<Integer> userIdThreadLocal = new ThreadLocal<>();

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static Integer getUserId() {

		// 1️⃣ Test-ID zuerst prüfen
		Integer testId = userIdThreadLocal.get();
		if (testId != null) {
			return testId;
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null) {
			return null;
		}
		HttpSession session = getSession();
		if (session != null)
			return (Integer) session.getAttribute("userId");
		else
			return null;
	}

	/**
	 * Für Tests: Setzt die User-ID ohne FacesContext.
	 */
	public static void setTestUserId(Integer id) {
		userIdThreadLocal.set(id);
	}

	/**
	 * Für Tests: Entfernt den gesetzten Testwert.
	 */
	public static void clearTestUserId() {
		userIdThreadLocal.remove();
	}

	public static String md5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : messageDigest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
