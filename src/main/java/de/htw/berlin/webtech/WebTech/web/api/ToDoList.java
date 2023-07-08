package de.htw.berlin.webtech.WebTech.web.api;

import java.util.Date;

public class ToDoList {


    private long id;
    private String aufgabentitel;
    private String aufgabe;
    private boolean erledigt;
    private Date datum;
    private String dringlichkeit;

    public ToDoList(long id, String aufgabentitel, String aufgabe, Date datum, boolean erledigt, String dringlichkeit) {
        this.id = id;
        this.aufgabentitel = aufgabentitel ;
        this.aufgabe = aufgabe;
        this.erledigt = erledigt;
        this.datum = datum;
        this.dringlichkeit = dringlichkeit;
    }

    public String getDringlichkeit() {
        return dringlichkeit;
    }

    public void setDringlichkeit(String dringlichkeit) {
        this.dringlichkeit = dringlichkeit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isErledigt() {
        return erledigt;
    }

    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}

