package de.demonlords.igm;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "igm_ordner")
public class IgmOrdner implements Serializable {

	private static final long serialVersionUID = -3397428571712348336L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "`user`")
    private Integer user;
    private String name;
    private Integer foldermask;
    private Integer lastactivity;
    
    public IgmOrdner() {}

    public IgmOrdner(Integer id, Integer user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUser() { return user; }
    public void setUser(Integer user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getFoldermask() { return foldermask; }
    public void setFoldermask(Integer foldermask) { this.foldermask = foldermask; }

    public Integer getLastactivity() { return lastactivity; }
    public void setLastactivity(Integer lastactivity) { this.lastactivity = lastactivity; }
}