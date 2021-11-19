package com.example.teacherhelp.model;

import com.example.teacherhelp.config.ConfiguracaoFireBase;
import com.google.firebase.database.DatabaseReference;

public class Registro {

    private String email;
    private String senha;
    private String nome;
    private String idUsuario;

    public Registro() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFireBase.getFirebaseDataBase();
        firebase.child("Usuarios")
                .child(this.idUsuario)
                .setValue( this );
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
