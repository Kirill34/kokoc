package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class SportAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "sport_kind_id")
    private SportKind sportKind;

    private LocalDateTime startAction;

    private LocalDateTime finishAction;

    private float distance;

    private float complex1;

    private float complex2;

    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public SportAction(Employee employee, SportKind sportKind, LocalDateTime startAction, LocalDateTime finishAction, float distance, float complex1, float complex2) {
        this.employee = employee;
        this.sportKind = sportKind;
        this.startAction = startAction;
        this.finishAction = finishAction;
        this.distance = distance;
        this.complex1 = complex1;
        this.complex2 = complex2;
    }

    public SportAction() {

    }


    public SportKind getSportKind() {
        return sportKind;
    }

    public void setSportKind(SportKind sportKind) {
        this.sportKind = sportKind;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartAction() {
        return startAction;
    }

    public void setStartAction(LocalDateTime startAction) {
        this.startAction = startAction;
    }

    public LocalDateTime getFinishAction() {
        return finishAction;
    }

    public void setFinishAction(LocalDateTime finishAction) {
        this.finishAction = finishAction;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getComplex1() {
        return complex1;
    }

    public void setComplex1(float complex1) {
        this.complex1 = complex1;
    }

    public float getComplex2() {
        return complex2;
    }

    public void setComplex2(float complex2) {
        this.complex2 = complex2;
    }
}
