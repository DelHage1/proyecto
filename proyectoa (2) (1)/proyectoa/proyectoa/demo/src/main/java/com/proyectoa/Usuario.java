package com.proyectoa;

import java.time.LocalDate;

public class Usuario {
    private int idusuario;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Descripcion;
    private String prioridad;
    private LocalDate fechadevencimiento;  // Cambiado a LocalDate

    // Constructor que toma LocalDate
    public Usuario(int idusuario, String nombre, String apellido, String email, String descripcion, String prioridad,
            LocalDate fechadevencimiento) {
        this.idusuario = idusuario;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Email = email;
        this.Descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechadevencimiento = fechadevencimiento;
    }

    // Constructor original
    public Usuario(int i, String string, String string2) {
    }

    // Métodos getter y setter

    public Usuario(int idusuario2, String nombre2, String descripcion2, String descripcion3, String descripcion4,
            String descripcion5, String descripcion6) {
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechadevencimiento() {
        return fechadevencimiento;
    }

    public void setFechadevencimiento(LocalDate fechadevencimiento) {
        this.fechadevencimiento = fechadevencimiento;
    }

    // Métodos equals y hashCode

    @Override
    public String toString() {
        return "Usuario [idusuario=" + idusuario + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Email=" + Email
                + ", Descripcion=" + Descripcion + ", prioridad=" + prioridad + ", fechadevencimiento="
                + fechadevencimiento + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idusuario;
        result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
        // Resto del código...
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // Resto del código...
        return true;
    }
}
