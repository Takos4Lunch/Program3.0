package sample;

import java.io.Serializable;

public class Ovejo extends Cordero implements Serializable {
    String fecharecsem, numlote, porcentaje;
    //Variables femeninas
    String diashab2;

    public String getDiashab2() {
        return diashab2;
    }

    String [] basicfieldsmale = {"fecha330","peso330","fechacast","fechinitmonta","tempmonta","fechasacrif","pesosacrif","fecharecsem","numlote","porcentaje"};
    String [] basicfieldsfemale = {"fecha330","peso330","fechinitmonta","tempmonta","fechasacrif","pesosacrif","tiposerv","fechprimpar","habmat","diashab2"};

    //Male constructor
    public Ovejo(String numero, String nombre, String fechanac,
                 String pesonac, String raza, String color, String prop,
                 String nompadre, String nomadre, String fecha50, String peso50,
                 String fechpred, String pesopred, String fechdespar,
                 String fechvac, String obs, String sexo, String ID,
                 String fechdest, String pesodest, String fechavent,
                 String preciovent, String fecha330, String peso330,
                 String fechacast, String fechinitmonta, String tempmonta,
                 String fechasacrif, String pesosacrif, String fecharecsem,
                 String numlote, String porcentaje) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent, fecha330, peso330, fechacast, fechinitmonta, tempmonta, fechasacrif, pesosacrif);
        this.fecharecsem = fecharecsem;
        this.numlote = numlote;
        this.porcentaje = porcentaje;
    }

    //Female constructor
    public Ovejo(String numero, String nombre, String fechanac, String pesonac,
                 String raza, String color, String prop, String nompadre,
                 String nomadre, String fecha50, String peso50, String fechpred,
                 String pesopred, String fechdespar, String fechvac, String obs,
                 String sexo, String ID, String fechdest, String pesodest,
                 String fechavent, String preciovent, String fecha330,
                 String peso330, String fechinitmonta, String tempmonta,
                 String fechasacrif, String pesosacrif, String tiposerv,
                 String fechprimpar, String habmat, String diashab2,
                 String differential) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent, fecha330, peso330, fechinitmonta, tempmonta, fechasacrif, pesosacrif, tiposerv, fechprimpar, habmat);
        this.diashab2 = diashab2;
    }

    public Ovejo(){
    }

    public Ovejo(String numero, String nombre, String fechanac, String pesonac, String raza, String color, String prop, String nompadre, String nomadre, String fecha50, String peso50, String fechpred, String pesopred, String fechdespar, String fechvac, String obs, String sexo, String id) {
        super(numero,nombre,fechanac,pesonac,raza,color,prop,nompadre,nomadre,fecha50,peso50,fechpred,pesopred,fechdespar,fechvac,obs,sexo,id);
    }

    public Ovejo(String numero, String nombre, String fechanac,
                     String pesonac, String raza, String color, String prop,
                     String nompadre, String nomadre, String fecha50,
                     String peso50, String fechpred, String pesopred,
                     String fechdespar, String fechvac, String obs, String sexo,
                     String ID, String fechdest, String pesodest, String fechavent,
                     String preciovent){
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent);
    }

    public Ovejo(String numero, String nombre, String fechanac, String pesonac, String raza, String color, String prop, String nompadre, String nomadre, String fecha50, String peso50, String fechpred, String pesopred, String fechdespar, String fechvac, String obs, String sexo, String id, String fecha330, String peso330, String fechacast, String fechinitmonta, String tempmonta, String fechasacrif, String pesosacrif, String tiposerv, String fechprimpar, String habmat) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, id);
        this.fecha330 = fecha330;
        this.peso330 = peso330;
        this.fechacast = fechacast;
        this.fechinitmonta = fechinitmonta;
        this.tempmonta = tempmonta;
        this.fechasacrif = fechasacrif;
        this.pesosacrif = pesosacrif;
        this.tiposerv = tiposerv;
        this.fechprimpar = fechprimpar;
        this.habmat = habmat;
    }

    public String getFecharecsem() {
        return fecharecsem;
    }

    public String getNumlote() {
        return numlote;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setFecharecsem(String fecharecsem) {
        this.fecharecsem = fecharecsem;
    }

    public void setNumlote(String numlote) {
        this.numlote = numlote;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setDiashab2(String diashab2) {
        this.diashab2 = diashab2;
    }
}
