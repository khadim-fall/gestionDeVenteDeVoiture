package com.example.khadimfall.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final StringProperty id;
    private final StringProperty cni;
    private final StringProperty prenom;
    private final StringProperty nom;
    private final StringProperty  adresse;

    private final StringProperty  sexe;

    public Client()
    {
        id= new SimpleStringProperty(this, "id");
        cni = new SimpleStringProperty(this, "cni");
        prenom = new SimpleStringProperty(this, "prenom");
        nom = new SimpleStringProperty(this, "nom");
        adresse = new SimpleStringProperty(this, "adresse");
        sexe = new SimpleStringProperty(this, "sexe");
    }


    public StringProperty idProperty() {
        return id;
    }
    public String getId() { return id.get(); }

    public void setId(String id) {
        this.id.set(id);
    }


    public StringProperty cniProperty() { return cni; }
    public String getCni() { return cni.get(); }
    public void setCni(String cni) { this.cni.set(cni); }
    //prenom
    public StringProperty prenomProperty() { return prenom; }
    public String getPrenom() { return prenom.get(); }
    public void setPrenom(String prenom) { this.prenom.set(prenom); }
    //nom
    public StringProperty nomProperty() { return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String nom) { this.nom.set(nom); }
    //adresse
    public StringProperty adresseProperty() { return adresse; }
    public String getAdresse() { return adresse.get(); }
    public void setAdresse(String adresse) { this.adresse.set(adresse); }
    //sexe
    public StringProperty sexeProperty() { return sexe; }
    public String getSexe() { return sexe.get(); }
    public void setSexe(String sexe) { this.sexe.set(sexe); }


}
