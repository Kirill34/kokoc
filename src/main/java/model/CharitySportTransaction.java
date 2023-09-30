package model;

import javax.persistence.*;

@Entity
@Table
public class CharitySportTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "charity_event_id")
    private CharityEvent charityEvent;

    @ManyToOne
    @JoinColumn(name = "sport_action_id")
    private SportAction sportAction;

    public SportAction getSportAction() {
        return sportAction;
    }

    public void setSportAction(SportAction sportAction) {
        this.sportAction = sportAction;
    }

    public CharityEvent getCharityEvent() {
        return charityEvent;
    }

    public void setCharityEvent(CharityEvent charityEvent) {
        this.charityEvent = charityEvent;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
