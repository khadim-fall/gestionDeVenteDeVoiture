package com.example.khadimfall.entities;

public class User {
    private int idU;
    private String login;
    private String password;
    private String prenom;
    private String nom;
    private String sexe;
    private Role role;

    public User() {
    }

    public User(int idU, String login, String password, String prenom, String nom, String sexe, Role role) {
        this.idU = idU;
        this.login = login;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.role = role;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "idU=" + idU +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", role=" + role +
                '}';
    }
}
