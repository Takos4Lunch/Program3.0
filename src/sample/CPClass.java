package sample;

import java.io.Serializable;

public class CPClass implements Serializable {//Clase del control reproductivo
    String numero, nombre, raza, p1, p2, p3, p4;

    public CPClass(String numero, String nombre, String raza) {
        this.numero = numero;
        this.nombre = nombre;
        this.raza = raza;
    }

    public void update(String numero, String nombre, String raza, String p1, String p2, String p3, String p4) {
        this.numero = numero;
        this.nombre = nombre;
        this.raza = raza;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public String placeholder(){
        return getNumero() +" - "+getNombre();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }
}
