package sample;

import java.io.Serializable;

public class CRClass implements Serializable {
    String numero;
    String nombre;
    String raza;
    String vacia;
    String prenada;
    String pr;
    String meses;
    String fechaprobpar;
    String obs;

    public CRClass(String numero, String nombre, String raza) {
        this.numero = numero;
        this.nombre = nombre;
        this.raza = raza;
    }

    public void update(String numero, String nombre, String raza,String vacia, String prenada,
                       String pr, String meses, String fechaprobpar, String obs){
        this.numero = numero;
        this.nombre = nombre;
        this.raza = raza;
        this.vacia = vacia;
        this.prenada = prenada;
        this.pr = pr;
        this.meses = meses;
        this.fechaprobpar = fechaprobpar;
        this.obs = obs;
    }

    public CRClass(String numero, String nombre, String raza,String vacia, String prenada,
                       String pr, String meses, String fechaprobpar, String obs){
        this.numero = numero;
        this.nombre = nombre;
        this.raza = raza;
        this.vacia = vacia;
        this.prenada = prenada;
        this.pr = pr;
        this.meses = meses;
        this.fechaprobpar = fechaprobpar;
        this.obs = obs;
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

    public String getVacia() {
        return vacia;
    }

    public void setVacia(String vacia) {
        this.vacia = vacia;
    }

    public String getPrenada() {
        return prenada;
    }

    public void setPrenada(String preñada) {
        this.prenada = preñada;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getFechaprobpar() {
        return fechaprobpar;
    }

    public void setFechaprobpar(String fechaprobpar) {
        this.fechaprobpar = fechaprobpar;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String placeholder(){
        return getNumero() +" - "+ getNombre();
    }
}
