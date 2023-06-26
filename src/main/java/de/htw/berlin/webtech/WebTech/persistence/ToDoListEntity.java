package de.htw.berlin.webtech.WebTech.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ToDo-Liste")
public class  ToDoListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "Aufgabentitel")
    private String aufgabentitel;

    @Column(name = "Aufgabe")
    private String aufgabe;

    @Column(name = "Erledigt")
    private Boolean erledigt;

    @Column(name = "Datum")
    private Date datum;

    @Column(name = "Dringlichkeit")
    private boolean dringlichkeit;

    public ToDoListEntity(String aufgabentitel, String aufgabe, Boolean erledigt, Date datum, boolean dringlichkeit) {
        this.aufgabentitel = aufgabentitel;
        this.aufgabe = aufgabe;
        this.erledigt = erledigt;
        this.datum = datum;
        this.dringlichkeit = dringlichkeit;
    }

    public void setDringlichkeit(boolean dringlichkeit) {
        this.dringlichkeit = dringlichkeit;
    }

    public ToDoListEntity() {

    }

    public long getId() {
        return id;
    }


    public String getAufgabentitel() {
        return aufgabentitel;
    }

    public void setAufgabentitel(String aufgabentitel) {
        this.aufgabentitel = aufgabentitel;
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

    public boolean getDringlichkeit() {
        return dringlichkeit;
    }
}

