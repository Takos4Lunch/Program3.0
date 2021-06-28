package sample;

import java.io.Serializable;

public class Cordero extends Corderito implements Serializable {
    String fecha330;
    String peso330;
    String fechacast;
    String fechinitmonta;
    String tempmonta;
    String fechasacrif;
    String pesosacrif;

    String [] basicfieldsmale = {"fecha330","peso330","fechacast","fechinitmonta","tempmonta","fechasacrif","pesosacrif"};
    String [] basicfieldsfemale = {"fecha330","peso330","fechinitmonta","tempmonta","tiposerv","fechprimpar","habmat","fechasacrif","pesosacrif"};
    //Variables femeninas
    String tiposerv, fechprimpar, habmat;

    public String getTiposerv() {
        return tiposerv;
    }

    public String getFechprimpar() {
        return fechprimpar;
    }

    public String getHabmat() {
        return habmat;
    }

    public Cordero(String numero, String nombre, String fechanac, String pesonac, String raza, String color, String prop, String nompadre, String nomadre, String fecha50, String peso50, String fechpred, String pesopred, String fechdespar, String fechvac, String obs, String sexo, String id, String fecha330, String peso330, String fechacast, String fechinitmonta, String tempmonta, String fechasacrif, String pesosacrif, String tiposerv, String fechprimpar, String habmat) {
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

    //Male constructor
    public Cordero(String numero, String nombre, String fechanac,
                   String pesonac, String raza, String color, String prop,
                   String nompadre, String nomadre, String fecha50, String peso50,
                   String fechpred, String pesopred, String fechdespar,
                   String fechvac, String obs, String sexo, String ID,
                   String fechdest, String pesodest, String fechavent,
                   String preciovent, String fecha330, String peso330,
                   String fechacast, String fechinitmonta, String tempmonta,
                   String fechasacrif, String pesosacrif) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent);
        this.fecha330 = fecha330;
        this.peso330 = peso330;
        this.fechacast = fechacast;
        this.fechinitmonta = fechinitmonta;
        this.tempmonta = tempmonta;
        this.fechasacrif = fechasacrif;
        this.pesosacrif = pesosacrif;
    }

    //Female constructor
    public Cordero(String numero, String nombre, String fechanac,
                   String pesonac, String raza, String color, String prop,
                   String nompadre, String nomadre, String fecha50, String peso50,
                   String fechpred, String pesopred, String fechdespar,
                   String fechvac, String obs, String sexo, String ID,
                   String fechdest, String pesodest, String fechavent,
                   String preciovent, String fecha330, String peso330,
                   String fechinitmonta, String tempmonta, String fechasacrif,
                   String pesosacrif, String tiposerv, String fechprimpar,
                   String habmat) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent);
        this.fecha330 = fecha330;
        this.peso330 = peso330;
        this.fechinitmonta = fechinitmonta;
        this.tempmonta = tempmonta;
        this.fechasacrif = fechasacrif;
        this.pesosacrif = pesosacrif;
        this.tiposerv = tiposerv;
        this.fechprimpar = fechprimpar;
        this.habmat = habmat;
    }

    public Cordero(String numero, String nombre, String fechanac, String pesonac, String raza, String color, String prop, String nompadre, String nomadre, String fecha50, String peso50, String fechpred, String pesopred, String fechdespar, String fechvac, String obs, String sexo, String id) {
        super(numero,nombre,fechanac,pesonac,raza,color,prop,nompadre,nomadre,fecha50,peso50,fechpred,pesopred,fechdespar,fechvac,obs,sexo,id);
    }

    public Cordero(String numero, String nombre, String fechanac,
                     String pesonac, String raza, String color, String prop,
                     String nompadre, String nomadre, String fecha50,
                     String peso50, String fechpred, String pesopred,
                     String fechdespar, String fechvac, String obs, String sexo,
                     String ID, String fechdest, String pesodest, String fechavent,
                     String preciovent){
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID, fechdest, pesodest, fechavent, preciovent);
    }

    public Cordero() {
        super();
    }

    public String getFecha330() {
        return fecha330;
    }

    public String getPeso330() {
        return peso330;
    }

    public String getFechacast() {
        return fechacast;
    }

    public String getFechinitmonta() {
        return fechinitmonta;
    }

    public String getTempmonta() {
        return tempmonta;
    }

    public String getFechasacrif() {
        return fechasacrif;
    }

    public String getPesosacrif() {
        return pesosacrif;
    }

    public void setFecha330(String fecha330) {
        this.fecha330 = fecha330;
    }

    public void setPeso330(String peso330) {
        this.peso330 = peso330;
    }

    public void setFechacast(String fechacast) {
        this.fechacast = fechacast;
    }

    public void setFechinitmonta(String fechinitmonta) {
        this.fechinitmonta = fechinitmonta;
    }

    public void setTempmonta(String tempmonta) {
        this.tempmonta = tempmonta;
    }

    public void setFechasacrif(String fechasacrif) {
        this.fechasacrif = fechasacrif;
    }

    public void setPesosacrif(String pesosacrif) {
        this.pesosacrif = pesosacrif;
    }

    public void setTiposerv(String tiposerv) {
        this.tiposerv = tiposerv;
    }

    public void setFechprimpar(String fechprimpar) {
        this.fechprimpar = fechprimpar;
    }

    public void setHabmat(String habmat) {
        this.habmat = habmat;
    }
}
