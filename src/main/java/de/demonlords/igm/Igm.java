package de.demonlords.igm;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "igm")
public class Igm implements Serializable {

	private static final long serialVersionUID = -4698553583495079038L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "`user`")
    private Integer user;
    private String cacheusername;
    private Integer userordner;
    private Integer sender;
    private String cachesendername;
    private Integer senderordner;
    private Integer datum;
    private String betreff;

    @Column(columnDefinition = "TEXT")
    private String igmtext;

    private Integer kampfbericht;
    private Integer tutorial;
    private Integer igmmask;
    
    public boolean isGelesen() {
        return (igmmask & 1) != 0;
    }

    public boolean isErstmaligGelesen() {
        return (igmmask & 2) != 0;
    }

    public boolean isUngelesen() {
    	return (igmmask & IgmFlags.GELESEN) == 0;
    }

    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUser() { return user; }
    public void setUser(Integer user) { this.user = user; }

    public String getCacheusername() { return cacheusername; }
    public void setCacheusername(String cacheusername) { this.cacheusername = cacheusername; }

    public Integer getUserordner() { return userordner; }
    public void setUserordner(Integer userordner) { this.userordner = userordner; }

    public Integer getSender() { return sender; }
    public void setSender(Integer sender) { this.sender = sender; }

    public String getCachesendername() { return cachesendername; }
    public void setCachesendername(String cachesendername) { this.cachesendername = cachesendername; }

    public Integer getSenderordner() { return senderordner; }
    public void setSenderordner(Integer senderordner) { this.senderordner = senderordner; }

    public Integer getDatum() { return datum; }
    public void setDatum(Integer datum) { this.datum = datum; }

    public String getBetreff() { return betreff; }
    public void setBetreff(String betreff) { this.betreff = betreff; }

    public String getIgmtext() { return igmtext; }
    public void setIgmtext(String igmtext) { this.igmtext = igmtext; }

    public Integer getKampfbericht() { return kampfbericht; }
    public void setKampfbericht(Integer kampfbericht) { this.kampfbericht = kampfbericht; }

    public Integer getTutorial() { return tutorial; }
    public void setTutorial(Integer tutorial) { this.tutorial = tutorial; }

    public Integer getIgmmask() { return igmmask; }
    public void setIgmmask(Integer igmmask) { this.igmmask = igmmask; }
    
    @Override
    public String toString() {
    	String string = "";
    	string = "ID: " + this.id +  " | Betreff:" + this.betreff;
    	return string;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Igm other = (Igm) obj;
        if(other.id != this.id) {
        	return false;
        }
        return true;
    }
}
