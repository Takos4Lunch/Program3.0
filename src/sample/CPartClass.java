package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class CPartClass implements Serializable {
    //Lets get this bread
    String Numero, Nombre, Raza;
    ArrayList<Parto> partos = new ArrayList<>();

    public CPartClass(String numero, String nombre, String raza) {
        Numero = numero;
        Nombre = nombre;
        Raza = raza;
    }

    public void addparto(String fecha,int tipo){
        partos.add(new Parto(fecha,tipo));
    }

    public String placeholder(){
        return getNumero() + " - " + getNombre();
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }
}
