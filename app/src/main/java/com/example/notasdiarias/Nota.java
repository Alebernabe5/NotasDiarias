package com.example.notasdiarias;

public class Nota {


    protected  int id;
    protected String nota;

    public Nota(int i, String n)
    {
        this.id=1;
        this.nota=n;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
