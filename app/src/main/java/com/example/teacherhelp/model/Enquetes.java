package com.example.teacherhelp.model;

public class Enquetes {

    private String resumo;
    private String titulo;
    private String chave;
    private String autor;

    public Enquetes(String titulo, String resumo,String autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.autor = autor;
    }

    public Enquetes() {
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getChave() {
        return chave;
    }

    public String setChave(String chave) {
        this.chave = chave;
        return chave;
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


