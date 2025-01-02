package com.rmb.firet_v1;
public class Cita {
    private String nombrePaciente;
    private String fecha;
    private String hora;
    private String motivo;
    private String domicilio;

    public Cita(String nombrePaciente, String fecha, String hora, String motivo, String domicilio) {
        this.nombrePaciente = nombrePaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {this.hora = hora;}

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {this.motivo = motivo;}

}
