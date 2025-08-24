package com.example;

public class Midia {
    private int id;
    private String titulo;
    private String tipo;
    private String franquia;
    private Integer nota;

    public Midia(int id, String titulo, String tipo, String franquia, int nota) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.franquia = franquia;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getTipo() {
        return tipo;
    }

    public String getFranquia() {
        return franquia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
public String toString() {
    return  "ID: " + id +
            " | Título: " + titulo +
            " | Tipo: " + tipo +
            " | Franquia: " + franquia +
            " | Nota: " + nota;
}

}