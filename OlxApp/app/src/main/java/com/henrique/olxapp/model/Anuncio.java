package com.henrique.olxapp.model;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by enriq on 09/02/2018.
 */

@Entity
public class Anuncio {

    @Id
    private long id;
    private String descricao;
    private String valor;
    private String lugar;

    public Anuncio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
