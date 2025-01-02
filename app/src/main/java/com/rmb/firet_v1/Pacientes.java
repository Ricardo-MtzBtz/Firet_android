package com.rmb.firet_v1;

public class Pacientes {
    private String nombre;
    private String edad;
    private String peso;
    private String motivo;

    public Pacientes(String nombre, String edad, String peso, String motivo){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.motivo = motivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
