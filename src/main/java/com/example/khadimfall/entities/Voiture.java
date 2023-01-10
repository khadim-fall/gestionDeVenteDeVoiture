package com.example.khadimfall.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Voiture {
    private final StringProperty id;
    private final StringProperty marque;
    private final StringProperty modele;
    private final StringProperty kilometrage;
    private StringProperty annee;

    public Voiture() {
        id=new SimpleStringProperty(this,"id");
        marque = new SimpleStringProperty(this, "marque");
        modele = new SimpleStringProperty(this, "modele");
        kilometrage = new SimpleStringProperty(this, "kilometrage");
        annee = new SimpleStringProperty(this, "annee");

    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMarque() {
        return marque.get();
    }

    public StringProperty marqueProperty() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque.set(marque);
    }

    public String getModele() {
        return modele.get();
    }

    public StringProperty modeleProperty() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele.set(modele);
    }

    public String getKilometrage() {
        return kilometrage.get();
    }

    public StringProperty kilometrageProperty() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage.set(kilometrage);
    }

    public String getAnnee() {
        return annee.get();
    }

    public StringProperty anneeProperty() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee.set(annee);
    }
}
