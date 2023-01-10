package com.example.khadimfall.controllers;

import com.example.khadimfall.entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientController implements Initializable {

    @FXML
    private TableColumn<Client, String> adresseCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<Client, String> idCol;
    @FXML
    private TableColumn<Client, String> cniCol;

    @FXML
    private TableColumn<Client, String> nomCol;

    @FXML
    private TableColumn<Client, String> prenomCol;

    @FXML
    private TableColumn<Client, String> sexeCol;

    @FXML
    private TableView<Client> table;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtCNI;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtSexe;

    @FXML
    void add(ActionEvent event) {
        String cniClient,nomClient,prenomClient,adresseClient,sexeClient;
        cniClient=txtCNI.getText();
        prenomClient=txtPrenom.getText();
        nomClient=txtNom.getText();
        adresseClient=txtAdresse.getText();
        sexeClient=txtSexe.getText();
        try{
            pst=con.prepareStatement("insert into client (cni,prenom,nom,adresse,sexe) values(?,?,?,?,?)");
            pst.setString(1,cniClient);
            pst.setString(2,prenomClient);
            pst.setString(3,nomClient);
            pst.setString(4,adresseClient);
            pst.setString(5,sexeClient);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test connexion");
            alert.setHeaderText("Enregistrement Client");
            alert.setContentText("ajouté(e)!!");
            alert.showAndWait();

            table();
            txtCNI.setText("");
            txtPrenom.setText("");
            txtNom.setText("");
            txtAdresse.setText("");
            txtSexe.setText("");
            txtCNI.requestFocus();

        } catch (SQLException e) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void table()
    {
        connect();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,cni,prenom,nom,adresse,sexe  from client");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Client client = new Client();
                    client.setId(rs.getString("id"));
                    client.setCni(rs.getString("cni"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setNom(rs.getString("nom"));
                    client.setAdresse(rs.getString("adresse"));
                    client.setSexe(rs.getString("sexe"));

                    clients.add(client);
                }
            }
            table.setItems(clients);
            idCol.setCellValueFactory(f->f.getValue().idProperty());
            cniCol.setCellValueFactory(f -> f.getValue().cniProperty());
            prenomCol.setCellValueFactory(f -> f.getValue().prenomProperty());
            nomCol.setCellValueFactory(f -> f.getValue().nomProperty());
            adresseCol.setCellValueFactory(f -> f.getValue().adresseProperty());
            sexeCol.setCellValueFactory(f->f.getValue().sexeProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory( tv -> {
            TableRow<Client> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();
                    id=Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

                    txtCNI.setText(String.valueOf(table.getItems().get(myIndex).getCni()));
                    txtPrenom.setText(table.getItems().get(myIndex).getPrenom());
                    txtNom.setText(table.getItems().get(myIndex).getNom());
                    txtAdresse.setText(table.getItems().get(myIndex).getAdresse());
                    txtSexe.setText(table.getItems().get(myIndex).getSexe());

                }
            });
            return myRow;
        });


    }

    @FXML
    void delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from client where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'enregistrement");

            alert.setHeaderText("Voulez vous supprimer cet enregistrement?");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    void update(ActionEvent event) {

         String cni,prenom,nom,adresse,sexe;

         myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
            cni=txtCNI.getText();
            prenom = txtPrenom.getText();
            nom = txtNom.getText();
            adresse = txtAdresse.getText();
             sexe=txtSexe.getText();
        try
        {
            pst = con.prepareStatement("update client set cni=?,prenom = ?,nom = ? ,adresse = ? ,sexe = ? where id = ? ");
            pst.setString(1,cni);
            pst.setString(2, prenom);
            pst.setString(3, nom);
            pst.setString(4, adresse);
             pst.setString(5, sexe);
             pst.setInt(6, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Client(e)");

            alert.setHeaderText("Enregistrement Client(e)");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
                table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
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
