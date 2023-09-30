package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
public class ActionGeoPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private double latitude;

    private double longitude;

    @ManyToOne
    @JoinColumn(name = "sport_action_id")
    private SportAction sportAction;

    private LocalDateTime dateTime;

    public ActionGeoPoint(double latitude, double longitude, SportAction sportAction, LocalDateTime dateTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.sportAction = sportAction;
        this.dateTime = dateTime;
    }

    public SportAction getSportAction() {
        return sportAction;
    }

    public void setSportAction(SportAction sportAction) {
        this.sportAction = sportAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
