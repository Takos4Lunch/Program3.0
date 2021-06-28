package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlRepController implements Initializable {

    public Button guardar;
    public Button borrar;
    public Button generar;
    public TextField obs;
    public TextField fechaprobpar;
    public TextField meses;
    public TextField raza;
    public TextField nombre;
    public TextField numero;
    public GridPane Pane1;
    public TextField pr;
    public TextField prenada;
    public TextField vacia;
    public GridPane Pane2;
    public ComboBox dropanimals;
    public File repfile = new File("controlRep.dat");

    FileOutputStream fos;
    ObjectOutputStream oos;
    FileInputStream fis;
    ObjectInputStream ois;

    ArrayList<CRClass> animals =  new ArrayList<CRClass>();;

    public void SiguienteHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlPesaje.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    public void AnteriorHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("RegistroGenealógico.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            load();
        } catch (IOException e) {
            System.out.println("No existe el archivo repcontroller");
        } catch (ClassNotFoundException e) {
            System.out.println("No existe la clase, what");
        }
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

    public void save() throws IOException {
        fos = new FileOutputStream(repfile);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(animals);
        oos.close();
        fos.close();
    }

    public void load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(repfile);
        ois = new ObjectInputStream(fis);
        if(repfile.exists()){
            animals = (ArrayList<CRClass>) ois.readObject();
        }
        for(CRClass c : animals){
            dropanimals.getItems().add(c.placeholder());
        }
    }

    public void LoadOnSheet(ActionEvent actionEvent) {
        //Hora del rock.wav
        String cmp = (String) dropanimals.getValue();
        for(CRClass c:animals){
            if(cmp.equals(c.placeholder())){
                numero.setText(c.getNumero());
                nombre.setText(c.getNombre());
                raza.setText(c.getRaza());
                meses.setText(c.getMeses());
                fechaprobpar.setText(c.getFechaprobpar());
                obs.setText(c.getObs());
                vacia.setText(c.getVacia());
                prenada.setText(c.getPrenada());
                pr.setText(c.getPr());
            }
        }
    }

    public void GuardarAnimal(ActionEvent actionEvent) throws IOException {
        //Aqui hay que agregar una condición
        //Calcular la edad si en vacia = 0
        if(emptyfields()==false){
            String tosave = (String) dropanimals.getValue();
            int toreplace = dropanimals.getItems().indexOf(tosave);
            for(CRClass c : animals){
                if(tosave.equals(c.placeholder())){
                    //Aqui arriba calculamos la fecha probable de parto
                    String thedate = fechaprobpar.getText();
                    if(Integer.parseInt(vacia.getText())==0){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String today = LocalDate.now().format(dtf);
                        LocalDate todaydate = LocalDate.parse(today,dtf);//Ya tenemos la fecha de hoy
                        LocalDate prendate = todaydate.minus(Integer.parseInt(meses.getText()), ChronoUnit.MONTHS);
                        LocalDate birthdate = prendate.plus(9,ChronoUnit.MONTHS);
                        thedate = String.valueOf(birthdate);
                    }
                    //Ya tenemos la fecha probable de parto
                    c.update(numero.getText(),nombre.getText(),raza.getText(),
                            vacia.getText(),prenada.getText(),pr.getText(),
                            meses.getText(),thedate,obs.getText());
                    dropanimals.getItems().set(toreplace,c.placeholder());
                    AlertBox.display("Exito","Se han guardado los datos con exito");
                }
            }
            clearfields(Pane1,Pane2);
        }else{
            AlertBox.display("Error","Hay un campo vacio");
            System.out.println("Hay un campo vacio");
        }
        save();
    }

    public void BorrarAnimal(ActionEvent actionEvent) throws IOException {
        CRClass jhonson = null;
        for(CRClass c: animals){
            String tocomp = (String) dropanimals.getValue();
            if(c.placeholder().equals(tocomp)){
                dropanimals.getItems().remove(tocomp);
                jhonson = c;
            }
        }
        animals.remove(jhonson);
        save();
        AlertBox.display("Exito","Se ha eliminado el elemento exitosamente");
    }

    public void getanimal(String num, String nom, String fechanac) throws IOException {
        animals.add(new CRClass(num,nom,fechanac));
        dropanimals.getItems().add(animals.get(animals.size()-1).placeholder());
        save();
    }

    public boolean emptyfields(){
        boolean hasempty = false;
        for(Node e:Pane1.getChildren()){
            if(e instanceof TextField && e.getId().equals("fechaprobpar")==false && ((TextField) e).getText().equals("")){
                hasempty = true;
            }
        }
        for(Node e:Pane2.getChildren()){
            if(e instanceof TextField && ((TextField) e).getText().equals("")){
                hasempty = true;
            }
        }
        return hasempty;
    }

    public void generate(ActionEvent actionEvent) {
        //Oh boi, here i go, killin' again
        Workbook finalbook = new HSSFWorkbook();//Creamos el libro
        CellStyle style = finalbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        HSSFCell cell = null;
        Row row = null;
        Sheet hoja = finalbook.createSheet("Control Reproductivo");
        //Esta es diferente, en un row deben estar todas las variables
        //Debe haber un contador que indique cuantos animales hay
        //Y hay que contar las vacias, las preñadas y las P.R.
        int ayudarows = 3;
        row = hoja.createRow(ayudarows);
        cell = (HSSFCell) row.createCell(0);
        cell.setCellValue("Item");
        cell.setCellStyle(style);
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
        cell.setCellValue("Vacia");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(5);
        cell.setCellValue("Preñada");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(6);
        cell.setCellValue("P.R.");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(7);
        cell.setCellValue("Meses");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(8);
        cell.setCellValue("Fecha Probable de Parto");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(9);
        cell.setCellValue("Observaciones");
        cell.setCellStyle(style);
        int itemcount = 1;
        for(CRClass c: animals){
            ayudarows++;
            row = hoja.createRow(ayudarows);
            cell = (HSSFCell) row.createCell(0);
            cell.setCellValue(itemcount);
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(1);
            cell.setCellValue(c.getNumero());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(2);
            cell.setCellValue(c.getNombre());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(3);
            cell.setCellValue(c.getRaza());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(4);
            cell.setCellValue(c.getVacia());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(5);
            cell.setCellValue(c.getPrenada());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(6);
            cell.setCellValue(c.getPr());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(7);
            cell.setCellValue(c.getMeses());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(8);
            cell.setCellValue(c.getFechaprobpar());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(9);
            cell.setCellValue(c.getObs());
            cell.setCellStyle(style);
            itemcount++;
        }
        for(int x = 1; x < 10; x++){
            hoja.autoSizeColumn(x);
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Control Reproductivo.xls");
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
        AlertBox.display("Exito","Se ha generado el documento 'Control Reproductivo.xls'");
    }
}
