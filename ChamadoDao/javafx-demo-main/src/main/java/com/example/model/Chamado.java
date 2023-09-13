package com.example.model;

public record Chamado (String usuario, String codEquipamento, String categoria, String status) {

    public String toString(){
        return usuario + " - " + codEquipamento + " - " + categoria + " - " + status + " - ";
    }
}
