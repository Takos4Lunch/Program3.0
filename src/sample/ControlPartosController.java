package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlPartosController implements Initializable {

    public TextField numero;
    public TextField nombre;
    public TextField raza;
    public Button guardar;
    public Button borrar;
    public Button generar;
    public Button agregarchild;
    public Button killchild;
    public TextField fecha;
    public ComboBox tipoparto;
    public ComboBox slotchild;
    public TextField nomchild;
    public Button addparto;
    public Button eliminarparto;
    public ComboBox droparto;
    public ComboBox dropanimals;
    public File repfile = new File("controlPartos.dat");
    public GridPane pane1;
    public GridPane pane2;

    FileOutputStream fos;
    ObjectOutputStream oos;
    FileInputStream fis;
    ObjectInputStream ois;

    ArrayList<CPartClass> animals = new ArrayList<>();

    public void SiguienteHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("RegistroGenealógico.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    public void AnteriorHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlPesaje.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        * A diferencia de los demás, aquí uno de los campos es inicializado manualmente
        * esto se debe a que solo hay 4 tipos de partos, este es mas un campo que le indica
        * a la clase que es lo que va a contener, a diferencia de las anteriores iteraciones
        * donde los parametros de la clase los determina la misma
        * */
        for(int x = 0; x<4;x++){
            tipoparto.getItems().add(x+1);
        }

        try {
            load();
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(repfile);
        ois = new ObjectInputStream(fis);
        if(repfile.exists()){
            animals = (ArrayList<CPartClass>) ois.readObject();
        }
        for(CPartClass c : animals){
            dropanimals.getItems().add(c.placeholder());
        }
    }

    public void clearfields(GridPane... gp){

        for (GridPane gp1 : gp){
            for(javafx.scene.Node c: gp1.getChildren()){
                if(c instanceof TextField){
                    ((TextField) c).clear();
                }
            }
        }
    }

    public void changeparto(ActionEvent actionEvent) throws IOException {
            slotchild.getItems().clear();
            String toload = (String) dropanimals.getValue();
            String toload2 = (String) droparto.getValue();
            /*
             * Aqui, debo cargar la fecha del parto en el campo
             * el nombre del primer animal, y los animales dentro de la lista
             * */
            try{
                for(CPartClass c:animals){
                    if(toload.equals(c.placeholder())){
                        for(Parto x:c.partos){
                            if(toload2.equals(x.placeholder())){
                                for(String y:x.childname){
                                    slotchild.getItems().add(y);
                                }
                            }
                        }
                    }
                }
            }catch(NullPointerException e){System.out.println("Clásico como el campeon cargar partos"); }
    }

    public void loadonsheet(ActionEvent actionEvent) {
        droparto.getItems().clear();
        String toplace = (String) dropanimals.getValue();
        Parto loader = null;
        boolean isfirst = true;
        for(CPartClass c: animals){
            if(toplace.equals(c.placeholder())){
                numero.setText(c.getNumero());
                nombre.setText(c.getNombre());
                raza.setText(c.getRaza());
                for(Parto x:c.partos){
                    if(isfirst){
                        loader = x;
                    }
                    droparto.getItems().add(x.placeholder());
                    isfirst = false;
                }
                /*
                * Ahora es donde se pone difícil, porque hay que cambiar
                * los otros campos en base al parto seleccionado
                * ya tenemos el parto, ahora, debemos llenar los campos con
                * los datos de dicho parto
                * */
                try{
                    for(String s:loader.childname){

                        slotchild.getItems().add(s);
                    }
                }catch (NullPointerException e){
                    System.out.println("aun no existen los children");
                }

            }
        }
    }

    public void Guardar(ActionEvent actionEvent) throws IOException {
        //Ay
        String toreplace = (String) dropanimals.getValue();
        int indextoreplace = dropanimals.getItems().indexOf(toreplace);
        if(!numero.getText().equals("") || !nombre.getText().equals("") || !raza.getText().equals("")){
            for(CPartClass c:animals){
                if(c.placeholder().equals(toreplace)){
                    c.setNumero(numero.getText());
                    c.setNombre(nombre.getText());
                    c.setRaza(raza.getText());
                    dropanimals.getItems().set(indextoreplace,c.placeholder());
                    numero.clear();
                    nombre.clear();
                    raza.clear();
                }
            }
            save();
            AlertBox.display("Exito","Se ha guardado el elemento de forma exitosa");
        }else
        {
            System.out.println("Campo vacio");
            AlertBox.display("Error","Hay un campo vacio");
        }

    }

    public void Delete(ActionEvent actionEvent) throws IOException {
        //no me acordaba
        String todelete = (String) dropanimals.getValue();
        CPartClass deletion = null;
        for(CPartClass c:animals){
            if(c.placeholder().equals(todelete)){
                deletion = c;
                dropanimals.getItems().remove(todelete);
            }
        }
        animals.remove(deletion);
        System.out.println("Elemento eliminado");
        AlertBox.display("Exito","Se ha eliminado el elemento de forma exitosa");
        save();
    }

    public void generate(ActionEvent actionEvent) {
        //de esto kek
        Workbook finalbook = new HSSFWorkbook();//Creamos el libro
        CellStyle style = finalbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        HSSFCell cell = null;
        Row row = null;
        //Aqui habrá una diferencia: Cada animal tendrá su propia hoja
        int ayudarows = 3;
        for(CPartClass c:animals){
            Sheet hoja = finalbook.createSheet(c.placeholder());

            //Headers principales
            row = hoja.createRow(ayudarows);

            cell = (HSSFCell) row.createCell(1);
            cell.setCellValue("Numero");
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(2);
            cell.setCellValue("Nombre");
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(3);
            cell.setCellValue("Raza");
            cell.setCellStyle(style);

            cell = (HSSFCell) row.createCell(4);
            cell.setCellValue("Fecha");
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(5);
            cell.setCellValue("Tipo");
            cell.setCellStyle(style);
            ayudarows++;

            row = hoja.createRow(ayudarows);

            cell = (HSSFCell) row.createCell(1);
            cell.setCellValue(c.getNumero());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(2);
            cell.setCellValue(c.getNombre());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(3);
            cell.setCellValue(c.getRaza());
            cell.setCellStyle(style);

            for(Parto p:c.partos){
                cell = (HSSFCell) row.createCell(4);
                cell.setCellValue(p.getFecha());
                cell.setCellStyle(style);
                cell = (HSSFCell) row.createCell(5);
                cell.setCellValue(typestring(p.getTipo()));
                cell.setCellStyle(style);
                int stringhelper = 6;
                for(String j:p.childname){
                    cell = (HSSFCell) row.createCell(stringhelper);
                    cell.setCellValue(j);
                    cell.setCellStyle(style);
                    stringhelper++;
                }
                ayudarows++;
                row =hoja.createRow(ayudarows);
            }
            ayudarows = 3;
            hoja.autoSizeColumn(1);
            hoja.autoSizeColumn(2);
            hoja.autoSizeColumn(3);
            hoja.autoSizeColumn(4);
            hoja.autoSizeColumn(5);
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Control Partos.xls");
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
        AlertBox.display("Generado","Se ha generado el documento 'Control Partos.xls'");
    }

    public String typestring(int typeint){
        String type = null;
        switch (typeint){
            case 1:
                type="Simple";
                break;
            case 2:
                type="Multiple";
                break;
            case 3:
                type="Triple";
                break;
            case 4:
                type="Cuadruple o mayor";
                break;
        }
        return type;
    }

    public void addanimal(ActionEvent actionEvent) throws IOException {
        /*
        * Aqui debemos:
        * 1- Encontrar el animal correspondiente
        * 2- Encontrar el parto correspondiente
        * 3- Guardar dicho animal dentro de su string correspondiente
        * 4- Guardar todo
        * */
        String baseanimal = (String) dropanimals.getValue();
        String basepart = (String) droparto.getValue();
        for(CPartClass c: animals){
            if(c.placeholder().equals(baseanimal) && !nomchild.getText().equals("")){
                for(Parto p:c.partos){
                    if(p.placeholder().equals(basepart)){
                        p.childname.add(nomchild.getText());
                        slotchild.getItems().add(nomchild.getText());
                        nomchild.clear();
                        save();
                        AlertBox.display("Exito","Se ha agregado el elemento de forma exitosa");
                    }
                }
            }else{
                //campo vacio
                AlertBox.display("Error","Hay un campo vacio");
            }
        }
    }

    public void killanimal(ActionEvent actionEvent) throws IOException {
        String baseanimal = (String) dropanimals.getValue();
        String basepart = (String) droparto.getValue();
        String todelete = (String) slotchild.getValue();
        for(CPartClass c: animals){
            if(c.placeholder().equals(baseanimal)){
                for(Parto p:c.partos){
                    if(p.placeholder().equals(basepart)){
                        p.childname.remove(todelete);
                        slotchild.getItems().remove(todelete);
                        nomchild.clear();
                        save();
                    }
                }
            }
        }
        AlertBox.display("Exito","Se ha eliminado el elemento de forma exitosa");
    }

    public void aggparto(ActionEvent actionEvent) throws IOException {
        //Empezamos por aquí
        String cmp = (String) dropanimals.getValue();
        for(CPartClass c:animals){
            if(c.placeholder().equals(cmp) && !fecha.getText().equals("") && tipoparto.getValue()!=null){
                c.addparto(fecha.getText(),Integer.parseInt(String.valueOf(tipoparto.getValue())));

                droparto.getItems().clear();
                for(Parto x:c.partos){
                    droparto.getItems().add(x.placeholder());
                }
                AlertBox.display("Exito","Se ha agregado el elemento de forma exitosa");
            }else{
                System.out.println("Error");
                AlertBox.display("Error","Hay un campo vacio");
            }
        }
        save();
        fecha.setText("");
    }

    public void elimparto(ActionEvent actionEvent) throws IOException {
        String cmp = (String) dropanimals.getValue();
        String todelete = (String) droparto.getValue();
        for(CPartClass c:animals){
            if(c.placeholder().equals(cmp)){
                c.partos.remove(todelete);

                droparto.getItems().remove(todelete);
            }
        }
        save();
        AlertBox.display("Exito","Se ha eliminado el elemento de forma exitosa");
    }

    public void save() throws IOException {
        fos = new FileOutputStream(repfile);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(animals);
        oos.close();
        fos.close();
    }

    public void getanimal(String num, String nom, String raz) throws IOException {
        animals.add(new CPartClass(num,nom,raz));
        dropanimals.getItems().add(animals.get(animals.size()-1).placeholder());
        save();
    }

    public void editanimal(ActionEvent actionEvent) throws IOException {
        //Let's get this bread
        String tocmp1 = (String) dropanimals.getValue();
        String tocmp2 = (String) droparto.getValue();
        String tocmp3 = (String) slotchild.getValue();
        int replaceindex = slotchild.getItems().indexOf(tocmp3);
        for(CPartClass c: animals){
            if(tocmp1.equals(c.placeholder()) && !nomchild.getText().equals("")){
                for(Parto p:c.partos){
                    if(p.placeholder().equals(tocmp2)){
                        for(String s:p.childname){
                            if(s.equals(tocmp3)){
                                p.childname.set(p.childname.indexOf(tocmp3),nomchild.getText());
                                slotchild.getItems().set(replaceindex,nomchild.getText());
                                nomchild.clear();
                                break;
                            }
                        }
                    }
                }
                save();
                AlertBox.display("Exito","Se ha editado el elemento de forma exitosa");
            }else{
                //Campo necesario para el cambio esta vacío
                System.out.println("Campo vacio");
                AlertBox.display("Error","Hay un campo vacio");
            }
        }
        //Listo, ya funciona
    }

    public void editparto(ActionEvent actionEvent) throws IOException {
        /*
        * We're near
        * */
        String tochange1 = (String) dropanimals.getValue();
        String tochange2 = (String) droparto.getValue();
        for(CPartClass c:animals){
            if(c.placeholder().equals(tochange1) && !fecha.getText().equals("") && tipoparto.getValue()!=null){
                for(Parto p:c.partos){
                    if(p.placeholder().equals(tochange2)){
                        p.edit(fecha.getText(), Integer.parseInt(String.valueOf(tipoparto.getValue())));
                        droparto.getItems().clear();
                        for(Parto x:c.partos){
                            droparto.getItems().add(x.placeholder());
                        }
                        fecha.clear();
                        AlertBox.display("Exito","Se ha editado el elemento de forma exitosa");
                    }
                }
            }else{
                System.out.println("Hay un campo vacio editar partos");
                AlertBox.display("Error","Hay un campo vacio");
            }
        }
        save();
    }

    public void changenombre(ActionEvent actionEvent) {
        String load = (String) slotchild.getValue();
        nomchild.setText(load);
    }
}
