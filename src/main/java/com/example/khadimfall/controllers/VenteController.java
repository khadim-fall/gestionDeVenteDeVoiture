package com.example.khadimfall.controllers;

import com.example.khadimfall.entities.Client;
import com.example.khadimfall.entities.Vente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenteController implements Initializable {
    @FXML
    private TableView<Vente> venteTable;
    @FXML
    private TableColumn<Vente, String> idColumn;
    @FXML
    private TableColumn<Vente, String> montantColumn;
    @FXML
    private TableColumn<Vente, String> dateColumn;
    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtMontant;


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    ObservableList<Vente> ventes = FXCollections.observableArrayList();

    @FXML
    void ajouter(ActionEvent event) {
        String montant,date;
        montant=txtMontant.getText();
        date= String.valueOf(txtDate.getValue());

        try{
            pst=con.prepareStatement("insert into vente (montant,date) values(?,?)");
            pst.setString(1,montant);
            pst.setString(2,date);

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test connexion");
            alert.setHeaderText("Enregistrement Vente");
            alert.setContentText("Vente ajouté(e)!!");
            alert.showAndWait();

            table();
            txtMontant.setText("");
            txtDate.setDayCellFactory(null);

            txtMontant.requestFocus();

        } catch (SQLException e) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    @FXML
    void modifier(ActionEvent event) {
        //String montant,date;
        String montant;
        LocalDate date;

        myIndex = venteTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(venteTable.getItems().get(myIndex).getId()));
        montant=txtMontant.getText();
        date = txtDate.getValue();

        try
        {
            pst = con.prepareStatement("update vente set montant=?,date = ? where id = ? ");
            pst.setString(1,montant);
            pst.setDate(2, Date.valueOf(date));
            pst.setInt(3, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update vente");

            alert.setHeaderText("Enregistrement vente");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            table();
            txtMontant.setText("");
            txtDate.setDayCellFactory((Callback<DatePicker, DateCell>) new DateCell());
        }
        catch (SQLException ex)
        {
            Logger.getLogger(VenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void supprimer(ActionEvent event) {
        myIndex = venteTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(venteTable.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from vente where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'enregistrement");

            alert.setHeaderText("Voulez vous supprimer cet enregistrement?");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();
            /*txtMontant.setText("");
            txtDate.setDayCellFactory(null);*/

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void table()
    {
        connect();
        ObservableList<Vente> ventes = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,montant,date from vente");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Vente vente = new Vente();
                    vente.setId(rs.getString("id"));
                    vente.setMontant(rs.getString("montant"));
                    vente.setDate(String.valueOf(rs.getDate("date")));

                    ventes.add(vente);
                }
            }
            venteTable.setItems(ventes);
            idColumn.setCellValueFactory(f->f.getValue().idProperty());
            montantColumn.setCellValueFactory(f -> f.getValue().montantProperty());
            dateColumn.setCellValueFactory(f->f.getValue().dateProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(VenteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        venteTable.setRowFactory( tv -> {
            TableRow<Vente> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  venteTable.getSelectionModel().getSelectedIndex();
                    id=Integer.parseInt(String.valueOf(venteTable.getItems().get(myIndex).getId()));

                    txtMontant.setText(String.valueOf(venteTable.getItems().get(myIndex).getMontant()));
                    //txtDate.setDayCellFactory(datePicker -> venteTable.getItems().get(myIndex).getDate());
                    txtDate.setDayCellFactory((Callback<DatePicker, DateCell>) Date.valueOf( venteTable.getItems().get(myIndex).getDate()));

                }
            });
            return myRow;
        });


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
        table();
    }
}
