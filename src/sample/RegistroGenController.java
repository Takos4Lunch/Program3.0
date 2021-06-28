package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.jxls.transform.poi.PoiTransformer;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

public class RegistroGenController implements Initializable {

    public GridPane PanePrincipal;
    public AnchorPane fullscreen;
    public GridPane pan2;
    public Label tiposervicio;
    public TextField tiposerv;
    public GridPane selectgp;
    ArrayList<Borrego> borregos;
    ArrayList<Corderito> corderitos;
    ArrayList<Cordero> corderos;
    ArrayList<Ovejo> ovejos;
    Borrego listbor = new Borrego();
    Corderito listcord = new Corderito();
    Cordero listcord2 = new Cordero();
    Ovejo listovj = new Ovejo();
    FileOutputStream fos;
    ObjectOutputStream oos;
    ArrayList<Borrego> brg = new ArrayList<Borrego>();
    ArrayList<Corderito> crd = new ArrayList<Corderito>();
    ArrayList<Cordero> crdr = new ArrayList<Cordero>();

    public RadioButton borrego;
    public RadioButton corderito;
    public RadioButton cordero;
    public RadioButton ovejo;
    public RadioButton borrega;
    public RadioButton corderita;
    public RadioButton cordera;
    public RadioButton oveja;
    public Button generar;
    public Button Siguiente;
    public ComboBox dropanimals;
    public Button Guardar;
    public Button editar;
    public Button borrar;
    public TextField Numero;
    public TextField Nombre;
    public TextField Fechanac;
    public TextField Pesonac;
    public TextField raza;
    public TextField color;
    public TextField proposito;
    public TextField nompadre;
    public TextField nommadre;
    public TextField fecha50;
    public TextField peso50;
    public TextField fechapred;
    public TextField pesopred;
    public TextField fechadesp;
    public TextField fechavac;
    public TextField fechadest;
    public TextField pesodest;
    public TextField fecha330;
    public TextField peso330;
    public TextField fechacast;
    public TextField fechinitmont;
    public TextField tempmont;
    public TextField fecharecsem;
    public TextField numlotemuestra;
    public TextField prenes;
    public TextField fechavent;
    public TextField preciovent;
    public TextField obs;
    public TextField fechasacrif;
    public TextField pesosacrif;
    public TextField fechaprimpar;
    public TextField diashab2par;
    public TextField habmat;
    public TextField agropecuaria;
    public TextField municipio;
    public TextField estado;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date1;
    LocalDate date2;

    public void AnteriorHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlPartos.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    public void SiguienteHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlReproductivo.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Primero, revisamos que exista el archivo
        //Luego, lo cargamos
        borregos = new ArrayList<Borrego>();
        corderitos = new ArrayList<Corderito>();
        corderos = new ArrayList<Cordero>();
        ovejos = new ArrayList<Ovejo>();
        //cargamos
        try {
            load("borregos.dat","corderitos.dat","corderos.dat","ovejos.dat");
        } catch (IOException e) {
            System.out.println("No hay objetos aún");
        } catch (ClassNotFoundException e) {
            System.out.println("No hay objetos aún");
        }
    }

    public void radbuttoncontroller(){
        clearfields(pan2,PanePrincipal);

        if(borrego.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            //Ahora, se deben deshabilitar los campos requeridos para cada uno//Este inhabilita un campo de texto
            settextfields(PanePrincipal,"borrego");
            settextfields(pan2,"borrego");
        }else if(corderito.isFocused()){
            ovejo.setSelected(false);
            borrego.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            //Esto funciona, pero buscaremos una forma mas dinámica de hacerlo
            settextfields(PanePrincipal,"corderito");
            settextfields(pan2,"corderito");
        }else if(cordero.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            borrego.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            settextfields(PanePrincipal,"cordero");
            settextfields(pan2,"cordero");
        }else if(ovejo.isFocused()){
            borrego.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            settextfields(PanePrincipal,"ovejo");
            settextfields(pan2,"ovejo");
        }else if(borrega.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrego.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            settextfields(PanePrincipal,"borrega");
            settextfields(pan2,"borrega");
        }else if(corderita.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            borrego.setSelected(false);
            cordera.setSelected(false);
            oveja.setSelected(false);
            settextfields(PanePrincipal,"corderita");
            settextfields(pan2,"corderita");
        }else if(cordera.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            borrego.setSelected(false);
            oveja.setSelected(false);
            settextfields(PanePrincipal,"cordera");
            settextfields(pan2,"cordera");
        }else if(oveja.isFocused()){
            ovejo.setSelected(false);
            corderito.setSelected(false);
            cordero.setSelected(false);
            borrega.setSelected(false);
            corderita.setSelected(false);
            cordera.setSelected(false);
            borrego.setSelected(false);
            settextfields(PanePrincipal,"oveja");
            settextfields(pan2,"oveja");
        }
        //Ademas se deben establecer los campos que se van a deshabilitar
    }

    public void guardar() throws IOException {
        //Aquí, guardaremos la información de los animales
        String ID = UUID.randomUUID().toString();
        String sexo;
        String nomsave = null, numsave = null, razsave = null;
        boolean send = false;
        if(borrego.isSelected() || borrega.isSelected()){
            //Necesitamos un método para generar el ID

            sexo = (borrego.isSelected()) ? "male" : "female";//sabroso nojoda
            if(emptyfields(PanePrincipal,pan2)==false){
                //Guardamos
                borregos.add(new Borrego(Numero.getText(),Nombre.getText(),
                        Fechanac.getText(),Pesonac.getText(),raza.getText(),
                        color.getText(),proposito.getText(),nompadre.getText(),
                        nommadre.getText(),fecha50.getText(),peso50.getText(),
                        fechapred.getText(),pesopred.getText(),fechadesp.getText(),
                        fechavac.getText(),obs.getText(),sexo,ID));
                dropanimals.getItems().addAll(borregos.get(borregos.size()-1).placeholder());
                /*
                * Ya añadido a la lista, quedan 2 cosas
                * 1) Serializar
                * 2) Limpir los campos
                * */
                calcedad();
                nomsave = Nombre.getText();
                numsave = Numero.getText();
                razsave = raza.getText();
                save("borregos.dat",borregos);
                AlertBox.display("Exito","Se han guardado los datos del animal");
                clearfields(pan2,PanePrincipal);
                send = true;
                //Guardar las variables justo arriba del clearfields
            }else{
                //Notificamos que no hay nada que guardar
                System.out.println("Hay un campo vacio");
                AlertBox.display("Error","Hay un campo vacio");
            }

            System.out.println("Borrego/Borrega");
        }else if(corderito.isSelected() || corderita.isSelected()){
            sexo = (corderito.isSelected()) ? "male" : "female";

            if(emptyfields(PanePrincipal,pan2)==false){
                corderitos.add(new Corderito(Numero.getText(),Nombre.getText(),Fechanac.getText(),
                        Pesonac.getText(),raza.getText(),color.getText(),
                        proposito.getText(),nompadre.getText(),nommadre.getText(),
                        fecha50.getText(),peso50.getText(),fechapred.getText(),
                        pesopred.getText(),fechadesp.getText(),fechavac.getText(),
                        obs.getText(),sexo,ID,fechadest.getText(),pesodest.getText(),
                        fechavent.getText(),preciovent.getText()));

                dropanimals.getItems().addAll(corderitos.get(corderitos.size()-1).placeholder());
                calcedad();
                nomsave = Nombre.getText();
                numsave = Numero.getText();
                razsave = raza.getText();
                save("corderitos.dat",corderitos);
                clearfields(pan2,PanePrincipal);
                send = true;
                AlertBox.display("Exito","Se han guardado los datos del animal");
                //Guardar las variables justo arriba del clearfields
            }else{
                System.out.println("hay un campo vacio");
                AlertBox.display("Error","Hay un campo vacio");
            }

            System.out.println("Corderito/Corderita");
        }else if(cordero.isSelected() || cordera.isSelected()){
            sexo = (cordero.isSelected()) ? "male" : "female";

            if(emptyfields(PanePrincipal,pan2)==false){
                if(sexo.equals("male")){
                    corderos.add(new Cordero(Numero.getText(),Nombre.getText(),
                            Fechanac.getText(),Pesonac.getText(),raza.getText(),
                            color.getText(),proposito.getText(),nompadre.getText(),
                            nommadre.getText(),fecha50.getText(),peso50.getText(),
                            fechapred.getText(),pesopred.getText(),fechadesp.getText(),
                            fechavac.getText(),obs.getText(),sexo,ID,fechadest.getText(),
                            pesodest.getText(),fechavent.getText(),preciovent.getText(),
                            fecha330.getText(),peso330.getText(),fechacast.getText(),fechinitmont.getText(),
                            tempmont.getText(),fechasacrif.getText(),pesosacrif.getText()));
                }else{
                    corderos.add(new Cordero(Numero.getText(),Nombre.getText(),Fechanac.getText(),
                            Pesonac.getText(),raza.getText(),color.getText(),proposito.getText(),
                            nompadre.getText(),nommadre.getText(),fecha50.getText(),
                            peso50.getText(),fechapred.getText(),pesopred.getText(),fechadesp.getText(),
                            fechavac.getText(),obs.getText(),sexo,ID,fechadest.getText(),pesodest.getText(),
                            fechavent.getText(),preciovent.getText(),fecha330.getText(),peso330.getText(),
                            fechinitmont.getText(),tempmont.getText(),fechasacrif.getText(),pesosacrif.getText(),
                            tiposerv.getText(),fechaprimpar.getText(),habmat.getText()));
                }

                dropanimals.getItems().add(corderos.get(corderos.size()-1).placeholder());
                calcedad();
                nomsave = Nombre.getText();
                numsave = Numero.getText();
                razsave = raza.getText();
                save("corderos.dat",corderos);
                clearfields(pan2,PanePrincipal);
                AlertBox.display("Exito","Se han guardado los datos del animal");
                send = true;
                //Guardar las variables justo arriba del clearfields
            }else{
                System.out.println("hay un campo vacio");
                AlertBox.display("Error","Hay un campo vacio");
            }

            System.out.println("Cordero/Cordera");
        }else if(ovejo.isSelected() || oveja.isSelected()){
            sexo = (ovejo.isSelected()) ? "male" : "female";

            if(emptyfields(PanePrincipal,pan2)==false){
                if(sexo.equals("male")){
                    ovejos.add(new Ovejo(Numero.getText(),Nombre.getText(),
                            Fechanac.getText(),Pesonac.getText(),raza.getText(),
                            color.getText(),proposito.getText(),nompadre.getText(),
                            nommadre.getText(),fecha50.getText(),peso50.getText(),
                            fechapred.getText(),pesopred.getText(),fechadesp.getText(),
                            fechavac.getText(),obs.getText(),sexo,ID,fechadest.getText(),
                            pesodest.getText(),fechavent.getText(),preciovent.getText(),
                            fecha330.getText(),peso330.getText(),fechacast.getText(),fechinitmont.getText(),
                            tempmont.getText(),fechasacrif.getText(),pesosacrif.getText(),fecharecsem.getText(),
                            numlotemuestra.getText(),prenes.getText()));
                }else{
                    ovejos.add(new Ovejo(Numero.getText(),Nombre.getText(),
                            Fechanac.getText(),Pesonac.getText(),raza.getText(),
                            color.getText(),proposito.getText(),nompadre.getText(),
                            nommadre.getText(),fecha50.getText(),peso50.getText(),
                            fechapred.getText(),pesopred.getText(),fechadesp.getText(),
                            fechavac.getText(),obs.getText(),sexo,ID,fechadest.getText(),
                            pesodest.getText(),fechavent.getText(),preciovent.getText(),fecha330.getText(),
                            peso330.getText(),fechinitmont.getText(),tempmont.getText(),fechasacrif.getText(),pesosacrif.getText(),
                            tiposerv.getText(),fechaprimpar.getText(),habmat.getText(),diashab2par.getText(),"Epale"));
                    //Aqui es donde se envia dicho objeto a los otros controles
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("ControlReproductivo.fxml"));
                    Parent tableviewparent = load.load();
                    ControlRepController control = load.getController();
                    control.getanimal(Numero.getText(),Nombre.getText(),raza.getText());

                    FXMLLoader load3 = new FXMLLoader();
                    load3.setLocation(getClass().getResource("ControlPartos.fxml"));
                    Parent tableviewparent3 = load3.load();
                    ControlPartosController control3 = load3.getController();
                    control3.getanimal(Numero.getText(),Nombre.getText(),raza.getText());
                    //Aquí tambien debemos enviarlo a la tabla de control de partos
                }

                dropanimals.getItems().add(ovejos.get(ovejos.size()-1).placeholder());
                calcedad();
                nomsave = Nombre.getText();
                numsave = Numero.getText();
                razsave = raza.getText();
                save("ovejos.dat",ovejos);
                clearfields(pan2,PanePrincipal);
                AlertBox.display("Exito","Se han guardado los datos del animal");
                send = true;
                //Guardar las variables justo arriba del clearfields
            }else{
                System.out.println("Hay un campo vacio");
                AlertBox.display("Error","Hay un campo vacio");
            }

            System.out.println("Ovejo/Oveja");
        }

        if(send){
            FXMLLoader load2 = new FXMLLoader();
            load2.setLocation(getClass().getResource("ControlPesaje.fxml"));
            Parent tableviewparent2 = load2.load();
            ControlPesajeController control = load2.getController();
            control.gotanimal(numsave,nomsave,razsave);
        }
    }

    public void settextfields (GridPane gridPane,String type){

        for(javafx.scene.Node c : gridPane.getChildren()){
            if(c instanceof TextField){
                TextField f = (TextField) c;
                c.setDisable(true);
                switch (type){
                    case "borrego":
                        for(String x : listbor.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case"borrega":
                        for(String x : listbor.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "corderito":
                        for(String x : listcord.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "corderita":
                        for(String x : listcord.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "cordero":
                        for(String x : listcord2.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        for(String x : listcord2.basicfieldsmale){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "cordera":
                        for(String x : listcord2.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        for(String x : listcord2.basicfieldsfemale){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "ovejo":
                        for(String x : listovj.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        for(String x : listovj.basicfieldsmale){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                    case "oveja":
                        for(String x : listovj.basicfields){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        for(String x : listovj.basicfieldsfemale){
                            if(x.equals(c.getId())){
                                c.setDisable(false);
                            }
                        }
                        break;
                }
            }
            else if (c instanceof GridPane)
                settextfields((GridPane) c,type);
        }
    }

    public boolean emptyfields(GridPane ... gpane){
        boolean isempty = false;

        for(GridPane gp1 : gpane){
            for(javafx.scene.Node c : gp1.getChildren()){
                if(c instanceof TextField && c.isDisabled()==false && "".equals(((TextField) c).getText())){
                    isempty = true;
                }
            }
        }
        return isempty;
    }

    public void clearfields(GridPane ... gp){

        for (GridPane gp1 : gp){
            for(javafx.scene.Node c: gp1.getChildren()){
                if(c instanceof TextField){
                    ((TextField) c).clear();
                }
            }
        }
    }

    public void save(String e, ArrayList f) throws IOException {
        fos = new FileOutputStream(e);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(f);
        oos.close();
        fos.close();
    }

    public void load(String ... e) throws IOException, ClassNotFoundException {
        for(String z : e){
            FileInputStream fis = new FileInputStream(z);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File check;
            check = new File(z);
            switch (z){
                case "borregos.dat":
                    if(check.exists()){
                        borregos = (ArrayList) ois.readObject();
                        for(Borrego c : borregos){
                            dropanimals.getItems().add(c.placeholder());
                        }
                    }
                    break;
                case "corderitos.dat":
                    if(check.exists()){
                        corderitos = (ArrayList) ois.readObject();
                        for(Corderito c : corderitos){
                            dropanimals.getItems().add(c.placeholder());
                        }
                    }
                    break;
                case "corderos.dat":
                    if(check.exists()){
                        corderos = (ArrayList) ois.readObject();
                        for(Cordero c : corderos){
                            dropanimals.getItems().add(c.placeholder());
                        }
                    }
                    break;
                case "ovejos.dat":
                    if(check.exists()){
                        ovejos = (ArrayList) ois.readObject();
                        for(Ovejo c : ovejos){
                            dropanimals.getItems().add(c.placeholder());
                        }
                    }
                    break;
            }
            ois.close();
            fis.close();
        }
    }

    public void loadonsheet(ActionEvent actionEvent) {
           System.out.println(dropanimals.getValue());
           //Hay que comparar esto con cada elemento de los arraylist
            String compare = (String) dropanimals.getValue();
            String typeclass = null;
            int position;
            clearfields(PanePrincipal,pan2);
            unset(selectgp);
            for(Borrego c : borregos){
                if(c.placeholder().equals(compare)){
                    if(c.getSexo().equals("male")){
                        borrego.setSelected(true);
                    }else{
                        borrega.setSelected(true);
                    }
                    typeclass="borrego";
                    fillfields(typeclass,c);


                }
            }
            for(Corderito c : corderitos){
                if(c.placeholder().equals(compare)){
                    if(c.getSexo().equals("male")){
                        corderito.setSelected(true);
                    }else{
                        corderita.setSelected(true);
                    }
                        typeclass="corderito";
                        fillfields(typeclass,c);
                }
            }
            for(Cordero c : corderos){
                if(c.placeholder().equals(compare)){
                    if(c.sexo.equals("male")){
                        typeclass="cordero";
                        cordero.setSelected(true);
                    }else{
                        typeclass="cordera";
                        cordera.setSelected(true);
                    }
                    fillfields(typeclass,c);
                }
            }
            for(Ovejo c : ovejos){
                if(c.placeholder().equals(compare)){
                    if(c.sexo.equals("male")){
                        typeclass="ovejo";
                        ovejo.setSelected(true);
                    }else{
                        typeclass="oveja";
                        oveja.setSelected(true);
                    }
                    fillfields(typeclass,c);
                }
            }
            try {
                settextfields(PanePrincipal,typeclass);
                settextfields(pan2,typeclass);
            }catch (NullPointerException e){
                System.out.println("meh");
            }

    }

    public void fillfields(String Jhonny,Borrego c){
            Numero.setText(c.getNumero());
            Nombre.setText(c.getNombre());
            Fechanac.setText(c.getFechanac());
            Pesonac.setText(c.getPesonac());
            raza.setText(c.getRaza());
            color.setText(c.getColor());
            proposito.setText(c.getProp());
            nompadre.setText(c.getNompadre());
            nommadre.setText(c.getNomadre());
            fecha50.setText(c.getFecha50());
            peso50.setText(c.getPeso50());
            fechapred.setText(c.getFechpred());
            pesopred.setText(c.getPesopred());
            fechadesp.setText(c.getFechdespar());
            fechavac.setText(c.getFechvac());
            obs.setText(c.getObs());
    }

    public void fillfields(String Jhonny, Corderito c){
        Numero.setText(c.getNumero());
        Nombre.setText(c.getNombre());
        Fechanac.setText(c.getFechanac());
        Pesonac.setText(c.getPesonac());
        raza.setText(c.getRaza());
        color.setText(c.getColor());
        proposito.setText(c.getProp());
        nompadre.setText(c.getNompadre());
        nommadre.setText(c.getNomadre());
        fecha50.setText(c.getFecha50());
        peso50.setText(c.getPeso50());
        fechapred.setText(c.getFechpred());
        pesopred.setText(c.getPesopred());
        fechadesp.setText(c.getFechdespar());
        fechavac.setText(c.getFechvac());
        obs.setText(c.getObs());
        fechadest.setText(c.getFechdest());
        pesodest.setText(c.getPesodest());
        fechavent.setText(c.getFechavent());
        preciovent.setText(c.getPreciovent());
    }

    public void fillfields(String Jhonny, Cordero c){
        switch(Jhonny){
            case "cordero":
                Numero.setText(c.getNumero());
                Nombre.setText(c.getNombre());
                Fechanac.setText(c.getFechanac());
                Pesonac.setText(c.getPesonac());
                raza.setText(c.getRaza());
                color.setText(c.getColor());
                proposito.setText(c.getProp());
                nompadre.setText(c.getNompadre());
                nommadre.setText(c.getNomadre());
                fecha50.setText(c.getFecha50());
                peso50.setText(c.getPeso50());
                fechapred.setText(c.getFechpred());
                pesopred.setText(c.getPesopred());
                fechadesp.setText(c.getFechdespar());
                fechavac.setText(c.getFechvac());
                obs.setText(c.getObs());
                fechadest.setText(c.getFechdest());
                pesodest.setText(c.getPesodest());
                fechavent.setText(c.getFechavent());
                preciovent.setText(c.getPreciovent());
                fecha330.setText(c.getFecha330());
                peso330.setText(c.getPeso330());
                fechinitmont.setText(c.getFechinitmonta());
                tempmont.setText(c.getTempmonta());
                fechasacrif.setText(c.getFechasacrif());
                pesosacrif.setText(c.getPesosacrif());
                fechacast.setText(c.getFechacast());
                break;
            case "cordera":
                Numero.setText(c.getNumero());
                Nombre.setText(c.getNombre());
                Fechanac.setText(c.getFechanac());
                Pesonac.setText(c.getPesonac());
                raza.setText(c.getRaza());
                color.setText(c.getColor());
                proposito.setText(c.getProp());
                nompadre.setText(c.getNompadre());
                nommadre.setText(c.getNomadre());
                fecha50.setText(c.getFecha50());
                peso50.setText(c.getPeso50());
                fechapred.setText(c.getFechpred());
                pesopred.setText(c.getPesopred());
                fechadesp.setText(c.getFechdespar());
                fechavac.setText(c.getFechvac());
                obs.setText(c.getObs());
                fechadest.setText(c.getFechdest());
                pesodest.setText(c.getPesodest());
                fechavent.setText(c.getFechavent());
                preciovent.setText(c.getPreciovent());
                fecha330.setText(c.getFecha330());
                peso330.setText(c.getPeso330());
                fechinitmont.setText(c.getFechinitmonta());
                tempmont.setText(c.getTempmonta());
                fechasacrif.setText(c.getFechasacrif());
                pesosacrif.setText(c.getPesosacrif());
                tiposerv.setText(c.getTiposerv());
                fechaprimpar.setText(c.getFechprimpar());
                habmat.setText(c.getHabmat());
                break;
        }
    }

    public void fillfields(String Jhonny, Ovejo c){
        unset(selectgp);
        switch(Jhonny){
            case "ovejo":
                Numero.setText(c.getNumero());
                Nombre.setText(c.getNombre());
                Fechanac.setText(c.getFechanac());
                Pesonac.setText(c.getPesonac());
                raza.setText(c.getRaza());
                color.setText(c.getColor());
                proposito.setText(c.getProp());
                nompadre.setText(c.getNompadre());
                nommadre.setText(c.getNomadre());
                fecha50.setText(c.getFecha50());
                peso50.setText(c.getPeso50());
                fechapred.setText(c.getFechpred());
                pesopred.setText(c.getPesopred());
                fechadesp.setText(c.getFechdespar());
                fechavac.setText(c.getFechvac());
                obs.setText(c.getObs());
                fechadest.setText(c.getFechdest());
                pesodest.setText(c.getPesodest());
                fechavent.setText(c.getFechavent());
                preciovent.setText(c.getPreciovent());
                fecha330.setText(c.getFecha330());
                peso330.setText(c.getPeso330());
                fechinitmont.setText(c.getFechinitmonta());
                tempmont.setText(c.getTempmonta());
                fechasacrif.setText(c.getFechasacrif());
                pesosacrif.setText(c.getPesosacrif());
                fechacast.setText(c.getFechacast());
                fecharecsem.setText(c.getFecharecsem());
                numlotemuestra.setText(c.getNumlote());
                prenes.setText(c.getPorcentaje());
                ovejo.setSelected(true);
                break;
            case "oveja":
                Numero.setText(c.getNumero());
                Nombre.setText(c.getNombre());
                Fechanac.setText(c.getFechanac());
                Pesonac.setText(c.getPesonac());
                raza.setText(c.getRaza());
                color.setText(c.getColor());
                proposito.setText(c.getProp());
                nompadre.setText(c.getNompadre());
                nommadre.setText(c.getNomadre());
                fecha50.setText(c.getFecha50());
                peso50.setText(c.getPeso50());
                fechapred.setText(c.getFechpred());
                pesopred.setText(c.getPesopred());
                fechadesp.setText(c.getFechdespar());
                fechavac.setText(c.getFechvac());
                obs.setText(c.getObs());
                fechadest.setText(c.getFechdest());
                pesodest.setText(c.getPesodest());
                fechavent.setText(c.getFechavent());
                preciovent.setText(c.getPreciovent());
                fecha330.setText(c.getFecha330());
                peso330.setText(c.getPeso330());
                fechinitmont.setText(c.getFechinitmonta());
                tempmont.setText(c.getTempmonta());
                fechasacrif.setText(c.getFechasacrif());
                pesosacrif.setText(c.getPesosacrif());
                tiposerv.setText(c.getTiposerv());
                fechaprimpar.setText(c.getFechprimpar());
                habmat.setText(c.getHabmat());
                diashab2par.setText(c.getDiashab2());
                oveja.setSelected(true);
                break;
        }
    }

    public void editar(ActionEvent actionEvent) throws IOException {
        /*
        * Primero debemos obtener el objeto
        * luego, modificarlo
        * y por ultimo, llamar al metodo para guardar los cambios
        * */
        String toedit = (String) dropanimals.getValue();
        int toreplace = dropanimals.getItems().indexOf(toedit);
        String tosave;/*
        Hay que implementar el cambio de placeholder que se implementó
        en la otra hoja
        porque si no, no se editaran los elementos correspondientes
        Por eso, creo que era mejor cuando usabamos ID, pero que se le va a hacer
        */

        if(emptyfields(PanePrincipal,pan2)==false){
            for(Borrego c : borregos){
                if(c.placeholder().equals(toedit)){
                    //Aqui adentro se cargara
                    c.setNumero(Numero.getText());
                    c.setNombre(Nombre.getText());
                    c.setFechanac(Fechanac.getText());
                    c.setPesonac(Pesonac.getText());
                    c.setRaza(raza.getText());
                    c.setColor(color.getText());
                    c.setProp(proposito.getText());
                    c.setNompadre(nompadre.getText());
                    c.setNomadre(nommadre.getText());
                    c.setFecha50(fecha50.getText());
                    c.setPeso50(peso50.getText());
                    c.setFechpred(fechapred.getText());
                    c.setPesopred(pesopred.getText());
                    c.setFechdespar(fechadesp.getText());
                    c.setFechvac(fechavac.getText());
                    c.setObs(obs.getText());
                    dropanimals.getItems().set(toreplace,c.placeholder());
                }
            }
            for(Corderito c : corderitos){
                if(c.placeholder().equals(toedit)){
                    c.setNumero(Numero.getText());
                    c.setNombre(Nombre.getText());
                    c.setFechanac(Fechanac.getText());
                    c.setPesonac(Pesonac.getText());
                    c.setRaza(raza.getText());
                    c.setColor(color.getText());
                    c.setProp(proposito.getText());
                    c.setNompadre(nompadre.getText());
                    c.setNomadre(nommadre.getText());
                    c.setFecha50(fecha50.getText());
                    c.setPeso50(peso50.getText());
                    c.setFechpred(fechapred.getText());
                    c.setPesopred(pesopred.getText());
                    c.setFechdespar(fechadesp.getText());
                    c.setFechvac(fechavac.getText());
                    c.setObs(obs.getText());
                    c.setFechdest(fechadest.getText());
                    c.setPesodest(pesodest.getText());
                    c.setFechavent(fechavent.getText());
                    c.setPreciovent(preciovent.getText());
                    dropanimals.getItems().set(toreplace,c.placeholder());
                }
            }
            for(Cordero c : corderos){
                if(c.placeholder().equals(toedit)){
                    if(c.getSexo().equals("male")){
                        c.setNumero(Numero.getText());
                        c.setNombre(Nombre.getText());
                        c.setFechanac(Fechanac.getText());
                        c.setPesonac(Pesonac.getText());
                        c.setRaza(raza.getText());
                        c.setColor(color.getText());
                        c.setProp(proposito.getText());
                        c.setNompadre(nompadre.getText());
                        c.setNomadre(nommadre.getText());
                        c.setFecha50(fecha50.getText());
                        c.setPeso50(peso50.getText());
                        c.setFechpred(fechapred.getText());
                        c.setPesopred(pesopred.getText());
                        c.setFechdespar(fechadesp.getText());
                        c.setFechvac(fechavac.getText());
                        c.setObs(obs.getText());
                        c.setFechdest(fechadest.getText());
                        c.setPesodest(pesodest.getText());
                        c.setFechavent(fechavent.getText());
                        c.setPreciovent(preciovent.getText());
                        c.setFecha330(fecha330.getText());
                        c.setPeso330(peso330.getText());
                        c.setFechacast(fechacast.getText());
                        c.setFechinitmonta(fechinitmont.getText());
                        c.setTempmonta(tempmont.getText());
                        c.setFechasacrif(fechasacrif.getText());
                        c.setPesosacrif(pesosacrif.getText());
                    }else{
                        c.setNumero(Numero.getText());
                        c.setNombre(Nombre.getText());
                        c.setFechanac(Fechanac.getText());
                        c.setPesonac(Pesonac.getText());
                        c.setRaza(raza.getText());
                        c.setColor(color.getText());
                        c.setProp(proposito.getText());
                        c.setNompadre(nompadre.getText());
                        c.setNomadre(nommadre.getText());
                        c.setFecha50(fecha50.getText());
                        c.setPeso50(peso50.getText());
                        c.setFechpred(fechapred.getText());
                        c.setPesopred(pesopred.getText());
                        c.setFechdespar(fechadesp.getText());
                        c.setFechvac(fechavac.getText());
                        c.setObs(obs.getText());
                        c.setFechdest(fechadest.getText());
                        c.setPesodest(pesodest.getText());
                        c.setFechavent(fechavent.getText());
                        c.setPreciovent(preciovent.getText());
                        c.setFecha330(fecha330.getText());
                        c.setPeso330(peso330.getText());
                        c.setFechinitmonta(fechinitmont.getText());
                        c.setTempmonta(tempmont.getText());
                        c.setFechasacrif(fechasacrif.getText());
                        c.setPesosacrif(pesosacrif.getText());
                        c.setTiposerv(tiposerv.getText());
                        c.setFechprimpar(fechaprimpar.getText());
                        c.setHabmat(habmat.getText());
                    }
                    dropanimals.getItems().set(toreplace,c.placeholder());
                }
            }
            for(Ovejo c : ovejos){
                if(c.placeholder().equals(toedit)){
                    if(c.getSexo().equals("male")){
                        c.setNumero(Numero.getText());
                        c.setNombre(Nombre.getText());
                        c.setFechanac(Fechanac.getText());
                        c.setPesonac(Pesonac.getText());
                        c.setRaza(raza.getText());
                        c.setColor(color.getText());
                        c.setProp(proposito.getText());
                        c.setNompadre(nompadre.getText());
                        c.setNomadre(nommadre.getText());
                        c.setFecha50(fecha50.getText());
                        c.setPeso50(peso50.getText());
                        c.setFechpred(fechapred.getText());
                        c.setPesopred(pesopred.getText());
                        c.setFechdespar(fechadesp.getText());
                        c.setFechvac(fechavac.getText());
                        c.setObs(obs.getText());
                        c.setFechdest(fechadest.getText());
                        c.setPesodest(pesodest.getText());
                        c.setFechavent(fechavent.getText());
                        c.setPreciovent(preciovent.getText());
                        c.setFecha330(fecha330.getText());
                        c.setPeso330(peso330.getText());
                        c.setFechacast(fechacast.getText());
                        c.setFechinitmonta(fechinitmont.getText());
                        c.setTempmonta(tempmont.getText());
                        c.setFechasacrif(fechasacrif.getText());
                        c.setPesosacrif(pesosacrif.getText());
                        c.setFecharecsem(fecharecsem.getText());
                        c.setNumlote(numlotemuestra.getText());
                        c.setPorcentaje(prenes.getText());
                    }else{
                        c.setNumero(Numero.getText());
                        c.setNombre(Nombre.getText());
                        c.setFechanac(Fechanac.getText());
                        c.setPesonac(Pesonac.getText());
                        c.setRaza(raza.getText());
                        c.setColor(color.getText());
                        c.setProp(proposito.getText());
                        c.setNompadre(nompadre.getText());
                        c.setNomadre(nommadre.getText());
                        c.setFecha50(fecha50.getText());
                        c.setPeso50(peso50.getText());
                        c.setFechpred(fechapred.getText());
                        c.setPesopred(pesopred.getText());
                        c.setFechdespar(fechadesp.getText());
                        c.setFechvac(fechavac.getText());
                        c.setObs(obs.getText());
                        c.setFechdest(fechadest.getText());
                        c.setPesodest(pesodest.getText());
                        c.setFechavent(fechavent.getText());
                        c.setPreciovent(preciovent.getText());
                        c.setFecha330(fecha330.getText());
                        c.setPeso330(peso330.getText());
                        c.setFechinitmonta(fechinitmont.getText());
                        c.setTempmonta(tempmont.getText());
                        c.setFechasacrif(fechasacrif.getText());
                        c.setPesosacrif(pesosacrif.getText());
                        c.setTiposerv(tiposerv.getText());
                        c.setFechprimpar(fechaprimpar.getText());
                        c.setHabmat(habmat.getText());
                        c.setDiashab2(diashab2par.getText());
                    }
                    dropanimals.getItems().set(toreplace,c.placeholder());
                }
            }
            clearfields(pan2,PanePrincipal);
            calcedad();
            save("borregos.dat",borregos);
            save("corderitos.dat",corderitos);
            save("corderos.dat",corderos);
            save("ovejos.dat",ovejos);
            AlertBox.display("Exito","Se ha editado el elemento seleccionado");
        }else{
            //Mensaje para el usuario
            AlertBox.display("Error","Hay campos vacios");
            System.out.println("Hay campos vacios");
        }

    }

    public void delete(ActionEvent actionEvent) throws IOException {
        String todelete = (String) dropanimals.getValue();
        //Implementar mismo arreglo
        Borrego a = null;
        Corderito b = null;
        Cordero cor = null;
        Ovejo d = null;
        Boolean bor = false, c1 = false, c2 = false, o = false;

        for(Borrego c : borregos){
            if(c.placeholder().equals(todelete)){
                dropanimals.getItems().remove(c.placeholder());
                a = c;
                bor = true;
            }
        }
        for(Corderito c : corderitos){
            if(c.placeholder().equals(todelete)){
                dropanimals.getItems().remove(c.placeholder());
                b = c;
                c1 = true;
            }
        }
        for(Cordero c: corderos){
            if(c.placeholder().equals(todelete)){
                dropanimals.getItems().remove(c.placeholder());
                cor = c;
                c2 = true;
            }
        }
        for(Ovejo c: ovejos){
            if(c.placeholder().equals(todelete)){
                dropanimals.getItems().remove(c.placeholder());
                d = c;
                o = true;
            }
        }

        if(bor){
            borregos.remove(a);
            save("borregos.dat",borregos);
        }else if(c1){
            corderitos.remove(b);
            save("corderitos.dat",corderitos);
        }else if(c2){
            corderos.remove(cor);
            save("corderos.dat",corderos);
        }else if(o){
            ovejos.remove(d);
            save("ovejos.dat",ovejos);
        }
        AlertBox.display("Exito","Se ha eliminado el elemento seleccionado");
    }

    public void generate(ActionEvent actionEvent) throws IOException {
        ArrayList<Borrego> MaleB = new ArrayList<>();
        ArrayList<Borrego> FemaleB = new ArrayList<>();
        ArrayList<Corderito> MaleC = new ArrayList<>();
        ArrayList<Corderito> FemaleC = new ArrayList<>();
        ArrayList<Cordero> MaleCor = new ArrayList<>();
        ArrayList<Cordero> FemaleCor = new ArrayList<>();
        ArrayList<Ovejo> MaleO = new ArrayList<>();
        ArrayList<Ovejo> FemaleO = new ArrayList<>();
        /*
        * Hay que hacer 8 libros
        * los primeros 4 son copy&paste
        * */
        for(Borrego x:borregos){
            if(x.getSexo().equals("male")){
                MaleB.add(x);
            }else{
                FemaleB.add(x);
            }
        }
        for(Corderito x:corderitos){
            if(x.getSexo().equals("male")){
                MaleC.add(x);
            }else{
                FemaleC.add(x);
            }
        }
        for(Cordero x:corderos){
            if(x.getSexo().equals("male")){
                MaleCor.add(x);
            }else{
                FemaleCor.add(x);
            }
        }
        for(Ovejo x:ovejos){
            if(x.getSexo().equals("male")){
                MaleO.add(x);
            }else{
                FemaleO.add(x);
            }
        }
        //Variables inicializadas, empieza el infierno
        Workbook finalbook = new HSSFWorkbook();//Creamos el libro
        CellStyle style = finalbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        HSSFCell cell = null;
        Row row = null;

        /*
        * Muerte cerebral
        * */

        //
        int ayudarows = 3;//Clásico como el campeon
        if(borrego.isSelected()){
            //borrego
            Sheet hoja = finalbook.createSheet("Borregos");

            for(Borrego x:MaleB){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Borregos.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
                 }
            try {
                fileOut.close();
            } catch (IOException ex) {
                 }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
                }
        }else if(borrega.isSelected()){
            //borregas
            Sheet hoja = finalbook.createSheet("Borregas");

            for(Borrego x:FemaleB){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Borregas.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
        }else if(corderito.isSelected()){
            //corderitos
            Sheet hoja = finalbook.createSheet("Corderitos");

            for(Corderito x:MaleC){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Corderitos.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
            //////////////////////////////////////////
        }else if(corderita.isSelected()){
            //corderitas
            Sheet hoja = finalbook.createSheet("Corderitas");
            for(Corderito x:FemaleC){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Corderitas.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
            //////////////////////////////////////////
        }else if(cordero.isSelected()){
            //corderos
            Sheet hoja = finalbook.createSheet("Corderos");
            for(Cordero x:MaleCor){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 330 dias");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 330 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de inicio de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechinitmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Temporada de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTempmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechasacrif());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha castracion");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechacast());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Corderos.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
        }else if(cordera.isSelected()){
            //corderas
            Sheet hoja = finalbook.createSheet("Corderas");
            for(Cordero x:FemaleCor){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 330 dias");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 330 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de inicio de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechinitmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Temporada de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTempmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechasacrif());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Tipo de servicio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTiposerv());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha primer parto");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechprimpar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Habilidad materna");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getHabmat());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Corderas.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
        }else if(ovejo.isSelected()){
            //ovejos
            Sheet hoja = finalbook.createSheet("Ovejos");
            for(Ovejo x:MaleO){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 330 dias");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 330 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de inicio de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechinitmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Temporada de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTempmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechasacrif());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha castracion");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechacast());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de recolección de semen");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecharecsem());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero de lote de muestra");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumlote());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Porcentaje de preñez");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPorcentaje());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Ovejos.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
        }else if(oveja.isSelected()){
            //ovejas
            Sheet hoja = finalbook.createSheet("Ovejas");
            for(Ovejo x:FemaleO){
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Numero");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNumero());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNombre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechanac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al nacimiento");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesonac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Raza");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getRaza());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Color");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getColor());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Proposito");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getProp());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero del padre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNompadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Nombre y numero de la madre");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getNomadre());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 50 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso50());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechpred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso de ingreso al predio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesopred());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de desparasitación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdespar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de vacunación");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechvac());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechdest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al destete");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPesodest());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechavent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Precio de venta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha a los 330 dias");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFecha330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso a los 330 días");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPeso330());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha de inicio de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechinitmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Temporada de monta");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTempmonta());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechasacrif());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Peso al sacrificio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getPreciovent());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Tipo de servicio");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getTiposerv());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Fecha primer parto");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getFechprimpar());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Habilidad materna");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getHabmat());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Dias abiertos al 2do parto");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getDiashab2());
                cell.setCellStyle(style);
                ayudarows++;
                row = hoja.createRow(ayudarows);
                cell = (HSSFCell) row.createCell(0);
                cell.setCellValue("Observaciones");
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(1);
                cell.setCellValue(x.getObs());
                cell.setCellStyle(style);
                ayudarows+=2;
            }
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Registro Genealógico Ovejas.xls");
            } catch (FileNotFoundException ex) {

            }
            try {
                finalbook.write(fileOut);
            } catch (IOException ex) {
            }
            try {
                fileOut.close();
            } catch (IOException ex) {
            }

            try {
                // Closing the workbook
                finalbook.close();
            } catch (IOException ex) {
            }
        }
        AlertBox.display("Exito","Se ha generado el documento de forma exitosa");
    }

    public void unset(GridPane gridPane){
            for(javafx.scene.Node c: gridPane.getChildren()){
                if(c instanceof RadioButton){
                    ((RadioButton) c).setSelected(false);
                }
            }
    }

    public void calcedad(){
        //Revisamos borregos, corderitos, corderos y ovejos
        try{
            String today = LocalDate.now().format(dtf);
            date2 = LocalDate.parse(today,dtf);
            for(Borrego b : borregos){
                date1 = LocalDate.parse(b.getFechanac(),dtf);
                int dias = (int) ChronoUnit.DAYS.between(date1,date2);
                System.out.println(dias);
                upgrade(b,dias);
            }
            for(Corderito c: corderitos){
                date1 = LocalDate.parse(c.getFechanac(),dtf);
                int dias = (int) ChronoUnit.DAYS.between(date1,date2);
                System.out.println(dias);
                upgrade(c,dias);
            }
            for(Cordero c:corderos){
                date1 = LocalDate.parse(c.getFechanac(),dtf);
                int dias = (int) ChronoUnit.DAYS.between(date1,date2);
                System.out.println(dias);
                upgrade(c,dias);
            }
            //Quitamos la eliminación del metodo upgrade, y la hacemos aquí
            borregos.removeAll(brg);
            corderitos.removeAll(crd);
            corderos.removeAll(crdr);
            brg.clear();
            crd.clear();
            crdr.clear();
        }catch(DateTimeParseException e){
            System.out.println("Debe introducir bien la fecha");
        }
    }

    public void upgrade(Borrego b,int age){
        if(age>330){
            ovejos.add(new Ovejo(b.numero,b.nombre,b.fechanac,b.pesonac,
                    b.raza,b.color,b.prop,b.nompadre,b.nomadre,b.fecha50,b.peso50,
                    b.fechpred,b.pesopred,b.fechdespar,b.fechvac,b.obs,b.sexo,b.ID));
            brg.add(b);
        }else if(age>180){
            corderos.add(new Cordero(b.numero,b.nombre,b.fechanac,b.pesonac,
                    b.raza,b.color,b.prop,b.nompadre,b.nomadre,b.fecha50,b.peso50,
                    b.fechpred,b.pesopred,b.fechdespar,b.fechvac,b.obs,b.sexo,b.ID));
            brg.add(b);
        }else if(age>50){
            corderitos.add(new Corderito(b.numero,b.nombre,b.fechanac,b.pesonac,
                    b.raza,b.color,b.prop,b.nompadre,b.nomadre,b.fecha50,b.peso50,
                    b.fechpred,b.pesopred,b.fechdespar,b.fechvac,b.obs,b.sexo,b.ID));
            brg.add(b);
        }
    }

    public void upgrade(Corderito c,int age){
        if(age>330){
            ovejos.add(new Ovejo(c.numero, c.nombre, c.fechanac, c.pesonac,
                    c.raza, c.color, c.prop, c.nompadre, c.nomadre, c.fecha50,
                    c.peso50, c.fechpred, c.pesopred, c.fechdespar, c.fechvac,
                    c.obs, c.sexo, c.ID, c.Fechdest, c.pesodest, c.fechavent,
                    c.preciovent));

        }else if(age>180){
            corderos.add(new Cordero(c.numero, c.nombre, c.fechanac, c.pesonac,
                    c.raza, c.color, c.prop, c.nompadre, c.nomadre, c.fecha50,
                    c.peso50, c.fechpred, c.pesopred, c.fechdespar, c.fechvac,
                    c.obs, c.sexo, c.ID, c.Fechdest, c.pesodest, c.fechavent,
                    c.preciovent));

        }
    }

    public void upgrade(Cordero c,int age){
        if(age>330){
            ovejos.add(new Ovejo(c.numero, c.nombre, c.fechanac, c.pesonac,
                    c.raza, c.color, c.prop, c.nompadre, c.nomadre, c.fecha50,
                    c.peso50, c.fechpred, c.pesopred, c.fechdespar, c.fechvac,
                    c.obs, c.sexo, c.ID, c.fecha330, c.peso330, c.fechacast,
                    c.fechinitmonta, c.tempmonta, c.fechasacrif, c.pesosacrif,
                    c.tiposerv, c.fechprimpar, c.habmat));
        }
    }
}
