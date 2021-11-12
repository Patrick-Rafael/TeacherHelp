package com.example.teacherhelp.model;

public class Programas {

    private String resumo;
    private String titulo;

    public Programas(String titulo, String resumo) {
        this.titulo = titulo;
        this.resumo = resumo;
    }

    public Programas() {
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}


