package com.example.khadimfall.controllers;

import com.example.khadimfall.entities.Voiture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoitureController implements Initializable {
    @FXML
    private TableColumn<Voiture, String> idCol;
    @FXML
    private TableColumn<Voiture, String> colAnnee;

    @FXML
    private TableColumn<Voiture, String> colKilometrage;

    @FXML
    private TableColumn<Voiture, String> colMarque;

    @FXML
    private TableColumn<Voiture, String> colModel;

    @FXML
    private TableView<Voiture> tableVoiture;

    @FXML
    private DatePicker txtannee;

    @FXML
    private TextField txtkilometre;

    @FXML
    private TextField txtmarque;

    @FXML
    private TextField txtmodele;


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    ObservableList<Voiture> voitures = FXCollections.observableArrayList();
    @FXML
    void ajouter(ActionEvent event) {

        String marque=txtmarque.getText();
        String modele=txtmodele.getText();
        String kilometrage= txtkilometre.getText();
        String annee= String.valueOf(txtannee.getValue());
        if(marque.isEmpty() || modele.isEmpty() || kilometrage.isEmpty() || annee.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir les champs!");
            alert.showAndWait();
        }
        try{
            pst=con.prepareStatement("insert into voiture (marque,modele,kilometrage,annee) values(?,?,?,?)");
            pst.setString(1,marque);
            pst.setString(2,modele);
            pst.setDouble(3, Double.parseDouble(kilometrage));
            pst.setDate(4, Date.valueOf(annee));
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test connexion");
            alert.setHeaderText("Enregistrement Client");
            alert.setContentText("ajouté(e)!!");
            alert.showAndWait();

            tableVoiture();
            txtmarque.setText("");
            txtmodele.setText("");
            txtkilometre.setText("");
            txtannee.setDayCellFactory(null);
            txtmarque.requestFocus();

        } catch (SQLException e) {
            Logger.getLogger(VoitureController.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void tableVoiture() {
        connect();

        try
        {
            pst = con.prepareStatement("select id,marque,modele,kilometrage,annee from voiture");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Voiture auto = new Voiture();
                    auto.setId(rs.getString("id"));
                    auto.setMarque(rs.getString("marque"));
                    auto.setModele(rs.getString("modele"));
                    auto.setKilometrage(rs.getString("kilometrage"));
                    auto.setAnnee(rs.getString("annee"));

                    voitures.add(auto);
                }
            }
            tableVoiture.setItems(voitures);
            idCol.setCellValueFactory(f->f.getValue().idProperty());
            colMarque.setCellValueFactory(f->f.getValue().marqueProperty());
            colModel.setCellValueFactory(f-> f.getValue().modeleProperty());
            colKilometrage.setCellValueFactory(f->f.getValue().kilometrageProperty());
            colAnnee.setCellValueFactory(f->f.getValue().anneeProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(VoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableVoiture.setRowFactory( tv -> {
            TableRow<Voiture> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableVoiture.getSelectionModel().getSelectedIndex();
                    id=Integer.parseInt(String.valueOf(tableVoiture.getItems().get(myIndex).getId()));
                    txtmarque.setText(tableVoiture.getItems().get(myIndex).getMarque());
                    txtmodele.setText(tableVoiture.getItems().get(myIndex).getModele());
                    txtkilometre.setText(tableVoiture.getItems().get(myIndex).getKilometrage());
                }
            });
            return myRow;
        });
    }
    @FXML
    void modifier(ActionEvent event) {
        String marque,modele,kilometrage,annee;

        myIndex = tableVoiture.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableVoiture.getItems().get(myIndex).getId()));
        marque=txtmarque.getText();
        modele = txtmodele.getText();
        kilometrage = txtkilometre.getText();
        annee = String.valueOf(txtannee.getValue());
        try
        {
            pst = con.prepareStatement("update voiture set marque=?,modele = ?,kilometrage = ? ,annee = ? where id = ? ");
            pst.setString(1,marque);
            pst.setString(2, modele);
            pst.setString(3, kilometrage);
            pst.setString(4, annee);
            pst.setInt(5, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Client(e)");

            alert.setHeaderText("Enregistrement Client(e)");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            tableVoiture();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(VoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void supprimer(ActionEvent event) {
        myIndex = tableVoiture.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tableVoiture.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from voiture where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'enregistrement");

            alert.setHeaderText("Voulez vous supprimer cet enregistrement?");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            tableVoiture();
            txtmarque.setText("");
            txtmodele.setText("");
            txtkilometre.setText("");
            txtannee.setDayCellFactory(null);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(VoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void connect(){
        String databaseName="gestion_voiture";
        String databaseUser="root";
        String databasePassword="";
        String url="jdbc:mysql://localhost/" +databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect();

        tableVoiture();
    }
}
