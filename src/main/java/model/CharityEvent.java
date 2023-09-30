package model;

import javax.persistence.*;

@Entity
@Table
public class CharityEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String logoFile;

    private int cost;

    public CharityEvent(String name, String logoFile, int cost) {
        this.name = name;
        this.logoFile = logoFile;
        this.cost = cost;
    }

    public CharityEvent() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
