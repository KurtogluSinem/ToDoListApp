package de.htw.berlin.webtech.WebTech.web.api;

import java.util.Date;

public class ToDoListManipulationRequest {

    private long id;
    private String überschrift;
    private String aufgabe;
    private boolean erledigt;
    private Date datum;

    public ToDoListManipulationRequest(long id, String überschrift, String aufgabe, Date datum, boolean erledigt) {
        this.id = id;
        this.überschrift = überschrift;
        this.aufgabe = aufgabe;
        this.erledigt = erledigt;
        this.datum = datum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
