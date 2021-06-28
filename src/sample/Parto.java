package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Parto implements Serializable {
    String fecha;
    int tipo;
    ArrayList<String> childname = new ArrayList<>();

    public Parto(String fecha, int tipo) {
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public void edit(String fecha, int tipo){
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getchild(int index){
        return childname.get(index);
    }

    public String placeholder(){
        return getFecha() + " - " + getTipo();
    }
}
