package com.example.khadimfall.controllers;

import com.example.khadimfall.util.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    void loadClient(ActionEvent event) throws IOException {
        Outils.loadAndwait(event,"Bienvenu dans votre espace client","/com/example/khadimfall/client.fxml");
    }

    @FXML
    void loadVente(ActionEvent event) throws IOException {
        Outils.loadAndwait(event,"Bienvenu dans votre espace de vente de voitures","/com/example/khadimfall/vente.fxml");
    }

    @FXML
    void loadVoiture(ActionEvent event) throws IOException {
        Outils.loadAndwait(event,"Bienvenu dans votre espace","/com/example/khadimfall/voiture.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
