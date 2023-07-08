package de.htw.berlin.webtech.WebTech.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ToDoListe")
public class  ToDoListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "aufgabentitel")
    private String aufgabentitel;

    @Column(name = "aufgabe")
    private String aufgabe;

    @Column(name = "datum")
    private Date datum;

    @Column(name = "dringlichkeit")
    @Enumerated(value = EnumType.STRING)
    private Dringlichkeit dringlichkeit;

    @Column(name = "erledigt")
    private Boolean erledigt;




    public ToDoListEntity(String aufgabentitel, String aufgabe, boolean erledigt, Date datum, Dringlichkeit dringlichkeit) {
        this.aufgabentitel = aufgabentitel;
        this.aufgabe = aufgabe;
        this.erledigt = erledigt;
        this.datum = datum;
        this.dringlichkeit = dringlichkeit;
    }


    public void setDringlichkeit(Dringlichkeit dringlichkeit) {
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

    public Dringlichkeit getDringlichkeit() {
        return dringlichkeit;
    }
}


