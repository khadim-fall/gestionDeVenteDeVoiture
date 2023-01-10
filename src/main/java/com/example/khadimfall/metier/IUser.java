package com.example.khadimfall.metier;

import com.example.khadimfall.entities.User;

public interface IUser {
    public void createUser(User user);
    public User readUser(String prenom);
    public void updateUser(User user);
    public void deleteUser(String prenom);

}
