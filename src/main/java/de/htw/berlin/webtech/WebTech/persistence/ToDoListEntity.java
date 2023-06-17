package de.htw.berlin.webtech.WebTech.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ToDo-Liste")
public class ToDoListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "überschrift")
    private String überschrift;

    @Column(name = "aufgabe")
    private String aufgabe;

    @Column(name = "erledigt")
    private Boolean erledigt;

    @Column(name = "datum")
    private Date datum;

    @Column (name= "priorität")
    private String priorität;


    public ToDoListEntity(String überschrift, String aufgabe, Boolean erledigt, Date datum, String priorität) {
        this.überschrift = überschrift;
        this.aufgabe = aufgabe;
        this.erledigt = erledigt;
        this.datum = datum;
        this.priorität= priorität;
    }

    public ToDoListEntity() {

    }

    public long getId() {
        return id;
    }


    public String getÜberschrift() {
        return überschrift;
    }

    public void setÜberschrift(String überschrift) {
        this.überschrift = überschrift;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public Boolean getErledigt() {
        return erledigt;
    }

    public void setErledigt(Boolean erledigt) {
        this.erledigt = erledigt;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getPriorität() {
        return priorität;
    }

    public void setPriorität(String priorität) { this.priorität = priorität;
    }
}
