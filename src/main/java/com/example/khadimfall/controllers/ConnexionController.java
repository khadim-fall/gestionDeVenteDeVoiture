package com.example.khadimfall.controllers;

import com.example.khadimfall.Db;
import com.example.khadimfall.util.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class ConnexionController implements Initializable {

    @FXML
    private TextField txtlogin;

    @FXML
    private PasswordField txtpassword;
    @FXML
    private ComboBox<String> typeUser;
    @FXML
    private Label lbletat;
    @FXML
    void connectButton(ActionEvent event) throws SQLException {
        Db connectNow = new Db();
        Connection connectDb = connectNow.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        String sql ="select * from user where log =? AND mdp=? AND role=?";



        try{
            pstm = connectDb.prepareStatement(sql);
            pstm.setString(1, txtlogin.getText().toString());

            pstm.setString(2, txtpassword.getText().toString());
            pstm.setString(3,typeUser.getValue().toString());
            rs=pstm.executeQuery();

            /*Statement statement = connectDb.createStatement();
             rs = statement.executeQuery(sql);*/

            if (rs.next()){

                if(typeUser.getValue().equals("admin")){
                    JOptionPane.showMessageDialog(null,"Connexion réuissie .Merci de cliquer sur ok pour aller dans votre espace");
                    Outils.load(event,"Bienvenu dans votre espace","/com/example/khadimfall/user.fxml");
                } if(typeUser.getValue().equals("secretaire")){
                    JOptionPane.showMessageDialog(null,"Connexion réuissie .Merci de cliquer sur ok pour aller dans votre espace");
                    Outils.load(event,"Bienvenu dans votre espace","/com/example/khadimfall/client.fxml");
                    //lbletat.setText("Non conneté");
                }
            }else {
                lbletat.setText("Non conneté");
                JOptionPane.showMessageDialog(null,"Merci de vérifier votre login et/ou mot de pass");
            }
            //lbletat.setText("conneté");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("admin","secretaire");
        typeUser.setItems(list);
    }
}
       /*
                    String sql = "INSERT INTO user (log, mdp) VALUES (?, ?)";
                    PreparedStatement statement = connectDb.prepareStatement(sql);
                    statement.setString(1, valeurColonne1);
                    statement.setString(2, valeurColonne2);
                    statement.setString(3, valeurColonne3);
                    * String sql = "INSERT INTO ma_table (log, mdp) VALUES (?, ?)";

                    // Création de l'objet PreparedStatement
                    PreparedStatement statement = connection.prepareStatement(sql);

                    // Remplissage des paramètres de la requête
                    statement.setString(1, valeurColonne1);
                    statement.setString(2, valeurColonne2);
                    statement.setString(3, valeurColonne3);

                    // Exécution de la requête
                    statement.executeUpdate();
            * */