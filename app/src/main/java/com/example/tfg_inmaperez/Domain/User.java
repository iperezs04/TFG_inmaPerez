package com.example.tfg_inmaperez.Domain;

public class User {

    private Long idUser;
    private String email;

    public User(Long idUser, String email) {
        this.idUser = idUser;
        this.email = email;
    }

    public User() {

    }

    public User(String email) {
        this.email = email;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
