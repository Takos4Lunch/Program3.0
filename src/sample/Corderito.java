package sample;

import java.io.Serializable;

public class Corderito extends Borrego implements Serializable {
    String Fechdest, pesodest, fechavent, preciovent;

    String[] basicfields = {"numero","nombre","fechanac","pesonac", "raza", "color", "prop", "nompadre", "nomadre",
            "fecha50", "peso50", "fechpred", "pesopred", "fechdespar", "fechvac", "obs","Fechdest", "pesodest", "fechavent","preciovent"};

    public Corderito(String numero, String nombre, String fechanac,
                     String pesonac, String raza, String color, String prop,
                     String nompadre, String nomadre, String fecha50,
                     String peso50, String fechpred, String pesopred,
                     String fechdespar, String fechvac, String obs, String sexo,
                     String ID, String fechdest, String pesodest, String fechavent,
                     String preciovent) {
        super(numero, nombre, fechanac, pesonac, raza, color, prop, nompadre, nomadre, fecha50, peso50, fechpred, pesopred, fechdespar, fechvac, obs, sexo, ID);
        Fechdest = fechdest;
        this.pesodest = pesodest;
        this.fechavent = fechavent;
        this.preciovent = preciovent;
    }

    public Corderito() {

    }

    public Corderito(String numero, String nombre, String fechanac, String pesonac, String raza, String color, String prop, String nompadre, String nomadre, String fecha50, String peso50, String fechpred, String pesopred, String fechdespar, String fechvac, String obs, String sexo, String id) {
        super(numero,nombre,fechanac,pesonac,raza,color,prop,nompadre,nomadre,fecha50,peso50,fechpred,pesopred,fechdespar,fechvac,obs,sexo,id);
    }

    public String getFechdest() {
        return Fechdest;
    }

    public String getPesodest() {
        return pesodest;
    }

    public String getFechavent() {
        return fechavent;
    }

    public String getPreciovent() {
        return preciovent;
    }

    public void setFechdest(String fechdest) {
        Fechdest = fechdest;
    }

    public void setPesodest(String pesodest) {
        this.pesodest = pesodest;
    }

    public void setFechavent(String fechavent) {
        this.fechavent = fechavent;
    }

    public void setPreciovent(String preciovent) {
        this.preciovent = preciovent;
    }
}
