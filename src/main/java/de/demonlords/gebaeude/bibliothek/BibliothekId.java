package de.demonlords.gebaeude.bibliothek;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BibliothekId implements Serializable {

	private static final long serialVersionUID = 5935560787802975955L;

	@Column(name="user")
    private int userId;

    @Column(name="zauber")
    private int zauberId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getZauberId() {
		return zauberId;
	}

	public void setZauberId(int zauberId) {
		this.zauberId = zauberId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, zauberId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliothekId other = (BibliothekId) obj;
		return userId == other.userId && zauberId == other.zauberId;
	}
}
