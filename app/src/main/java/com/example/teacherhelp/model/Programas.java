package com.example.teacherhelp.model;

import java.io.Serializable;

public class Programas implements Serializable {

    String tituloPrograma, resumoPrograma,linkPrograma;
    String descricaoPrograma;


    int imageprograma;

    @Override
    public String toString() {
        return "Programas{" +
                "tituloPrograma='" + tituloPrograma + '\'' +
                ", resumoPrograma='" + resumoPrograma + '\'' +
                ", linkPrograma='" + linkPrograma + '\'' +
                ", descricaoPrograma='" + descricaoPrograma + '\'' +
                ", imageprograma=" + imageprograma +
                '}';
    }

    public Programas(String tituloPrograma, String resumoPrograma, int imageprograma, String linkPrograma,String descricaoPrograma) {
        this.tituloPrograma = tituloPrograma;
        this.resumoPrograma = resumoPrograma;
        this.imageprograma = imageprograma;
        this.linkPrograma = linkPrograma;
        this.descricaoPrograma = descricaoPrograma;
    }

    public String getDescricaoPrograma() {
        return descricaoPrograma;
    }

    public void setDescricaoPrograma(String descricaoPrograma) {
        this.descricaoPrograma = descricaoPrograma;
    }

    public String getLinkPrograma() {
        return linkPrograma;
    }

    public void setLinkPrograma(String linkPrograma) {
        this.linkPrograma = linkPrograma;
    }

    public String getTituloPrograma() {
        return tituloPrograma;
    }

    public void setTituloPrograma(String tituloPrograma) {
        this.tituloPrograma = tituloPrograma;
    }

    public String getResumoPrograma() {
        return resumoPrograma;
    }

    public void setResumoPrograma(String resumoPrograma) {
        this.resumoPrograma = resumoPrograma;
    }

    public int getImageprograma() {
        return imageprograma;
    }

    public void setImageprograma(int imageprograma) {
        this.imageprograma = imageprograma;
    }
}
