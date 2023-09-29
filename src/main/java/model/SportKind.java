package model;

import javax.persistence.*;

@Entity
@Table
public class SportKind {

    public SportKind(String name, convertMethod payMethod, String logoFile) {
        this.name = name;
        this.payMethod = payMethod;
        this.logoFile = logoFile;
    }

    public SportKind() {

    }

    public convertMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(convertMethod payMethod) {
        this.payMethod = payMethod;
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

    public enum convertMethod {
        byStep,
        byMinute,
        byDistance,
        byComplexActions
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private convertMethod payMethod;

    private String logoFile;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
