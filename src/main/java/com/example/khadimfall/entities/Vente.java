package com.example.khadimfall.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.SimpleTimeZone;

public class Vente {
    private final StringProperty id;
    private final StringProperty montant;
    private final StringProperty date;
    public Vente() {
        id= new SimpleStringProperty(this, "id");
        montant= new SimpleStringProperty(this,"montant");
        date= new SimpleStringProperty(this,"date");
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

    public String getMontant() {
        return montant.get();
    }

    public StringProperty montantProperty() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant.set(montant);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}