package sample;

import java.io.Serializable;

public class Borrego implements Serializable {
    String numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre,
    fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID;

    String[] basicfields = {"numero","nombre","fechanac","pesonac", "raza", "color", "prop", "nompadre", "nomadre",
            "fecha50", "peso50", "fechpred", "pesopred", "fechdespar", "fechvac", "obs"};

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public void setPesonac(String pesonac) {
        this.pesonac = pesonac;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setNompadre(String nompadre) {
        this.nompadre = nompadre;
    }

    public void setNomadre(String nomadre) {
        this.nomadre = nomadre;
    }

    public void setFecha50(String fecha50) {
        this.fecha50 = fecha50;
    }

    public void setPeso50(String peso50) {
        this.peso50 = peso50;
    }

    public void setFechpred(String fechpred) {
        this.fechpred = fechpred;
    }

    public void setPesopred(String pesopred) {
        this.pesopred = pesopred;
    }

    public void setFechdespar(String fechdespar) {
        this.fechdespar = fechdespar;
    }

    public void setFechvac(String fechvac) {
        this.fechvac = fechvac;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Borrego(){
    }

    public Borrego(String numero, String nombre, String fechanac,
                   String pesonac, String raza, String color, String prop,
                   String nompadre, String nomadre, String fecha50,
                   String peso50, String fechpred, String pesopred,
                   String fechdespar, String fechvac, String obs,
                   String sexo, String ID) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechanac = fechanac;
        this.pesonac = pesonac;
        this.raza = raza;
        this.color = color;
        this.prop = prop;
        this.nompadre = nompadre;
        this.nomadre = nomadre;
        this.fecha50 = fecha50;
        this.peso50 = peso50;
        this.fechpred = fechpred;
        this.pesopred = pesopred;
        this.fechdespar = fechdespar;
        this.fechvac = fechvac;
        this.obs = obs;
        this.sexo = sexo;
        this.ID = ID;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechanac() {
        return fechanac;
    }

    public String getPesonac() {
        return pesonac;
    }

    public String getRaza() {
        return raza;
    }

    public String getColor() {
        return color;
    }

    public String getProp() {
        return prop;
    }

    public String getNompadre() {
        return nompadre;
    }

    public String getNomadre() {
        return nomadre;
    }

    public String getFecha50() {
        return fecha50;
    }

    public String getPeso50() {
        return peso50;
    }

    public String getFechpred() {
        return fechpred;
    }

    public String getPesopred() {
        return pesopred;
    }

    public String getFechdespar() {
        return fechdespar;
    }

    public String getFechvac() {
        return fechvac;
    }

    public String getObs() {
        return obs;
    }

    public String placeholder(){
        return numero + " - " +  nombre;
    }

    public String getSexo() {
        return sexo;
    }
}
