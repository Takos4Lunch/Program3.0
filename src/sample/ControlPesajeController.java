package sample;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class ControlPesajeController implements Initializable {
    public TextField p1;
    public TextField p2;
    public TextField p3;
    public TextField p4;
    public TextField numero;
    public TextField nombre;
    public TextField raza;
    public ComboBox dropanimals;
    public GridPane gp2;
    public GridPane gp1;

    FileOutputStream fos;
    ObjectOutputStream oos;
    FileInputStream fis;
    ObjectInputStream ois;

    public File pesajefile = new File("controlPesaje.dat");
    ArrayList<CPClass> animals = new ArrayList<CPClass>();

    public void SiguienteHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlPartos.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    public void AnteriorHoja(ActionEvent actionEvent) throws IOException {
        Parent TableViewParent = FXMLLoader.load(getClass().getResource("ControlReproductivo.fxml"));
        Scene ControlReprodScene = new Scene(TableViewParent);
        //Aqui obtengo la información del stage
        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(ControlReprodScene);
        window.show();
    }

    public void save() throws IOException {
        fos = new FileOutputStream(pesajefile);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(animals);
        oos.close();
        fos.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Aqui debemos cargar la droplist
        try {
            load();
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(pesajefile);
        ois = new ObjectInputStream(fis);
        if(pesajefile.exists()){
            animals = (ArrayList<CPClass>) ois.readObject();
        }
        for(CPClass c : animals){
            dropanimals.getItems().add(c.placeholder());
        }
    }

    public void guardar(ActionEvent actionEvent) throws IOException {
        String toupdate = (String) dropanimals.getValue();
        int toreplace = dropanimals.getItems().indexOf(toupdate);
        if(emptyfields()==false){
            for(CPClass c:animals){
                if(c.placeholder().equals(toupdate)){
                    c.update(numero.getText(),nombre.getText(),raza.getText(),
                            p1.getText(),p2.getText(),p3.getText(), p4.getText());
                    //Hay que hacer el cambio de placeholder
                    dropanimals.getItems().set(toreplace,c.placeholder());
                }
            }
            save();
            clearfields(gp1,gp2);
        }else{
            AlertBox.display("Error","Hay un campo vacío");
        }
    }

    public void borrar(ActionEvent actionEvent) {
        String todelete = (String) dropanimals.getValue();
        CPClass jhonny = null;
        for(CPClass c:animals){
            if(c.placeholder().equals(todelete)){
                jhonny = c;
            }
        }
        dropanimals.getItems().remove(todelete);
        animals.remove(jhonny);
        AlertBox.display("Notificación","El elemento ha sido borrado exitosamente");
    }

    public void generar(ActionEvent actionEvent) {
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
        cell.setCellValue("1er pesaje");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(5);
        cell.setCellValue("2do pesaje");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(6);
        cell.setCellValue("3er pesaje");
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(7);
        cell.setCellValue("4to pesaje");
        cell.setCellStyle(style);
        int itemcount = 1;
        float p1 = 0, p2= 0, p3= 0, p4= 0;
        float ptotal= 0;
        for(CPClass x:animals){
            ayudarows++;
            row = hoja.createRow(ayudarows);
            cell = (HSSFCell) row.createCell(0);
            cell.setCellValue(itemcount);
            itemcount++;
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(1);
            cell.setCellValue(x.getNumero());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(2);
            cell.setCellValue(x.getNombre());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(3);
            cell.setCellValue(x.getRaza());
            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(4);
            cell.setCellValue(x.getP1());
            try{
                p1+=Float.parseFloat( x.getP1());
                ptotal+=Float.parseFloat( x.getP1());
            }catch (NullPointerException e){
                System.out.println("Valor invalido en campo 1");
            }

            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(5);
            cell.setCellValue(x.getP2());

            try{
                p2+=Float.parseFloat( x.getP2());
                ptotal+=Float.parseFloat( x.getP2());
            }catch (NullPointerException e){
                System.out.println("Valor invalido en campo 2");
            }

            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(6);
            cell.setCellValue(x.getP3());

            try{
                p3+=Float.parseFloat( x.getP3());
                ptotal+=Float.parseFloat( x.getP3());
            }catch (NullPointerException e){
                System.out.println("Valor invalido en campo 3");
            }


            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(7);
            cell.setCellValue(x.getP4());
            try{
                p4+=Float.parseFloat( x.getP4());
                ptotal+=Float.parseFloat( x.getP4());
            }catch (NullPointerException e){
                System.out.println("Valor invalido en campo 4");
            }

            cell.setCellStyle(style);
            cell = (HSSFCell) row.createCell(8);
            cell.setCellValue(ptotal);
            ptotal=0;
            cell.setCellStyle(style);
        }

        row = hoja.createRow(ayudarows+1);

        cell = (HSSFCell) row.createCell(4);
        cell.setCellValue(p1);
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(5);
        cell.setCellValue(p2);
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(6);
        cell.setCellValue(p3);
        cell.setCellStyle(style);
        cell = (HSSFCell) row.createCell(7);
        cell.setCellValue(p4);
        cell.setCellStyle(style);


        for(int x = 1; x < 9; x++){
            hoja.autoSizeColumn(x);
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Control Pesaje.xls");
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
        AlertBox.display("Generado","Se ha generado el documento 'Control Pesaje.xls'");
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

    public void loadonsheet(ActionEvent actionEvent) {
        clearfields(gp1,gp2);
        String cmp = (String) dropanimals.getValue();
        for(CPClass c:animals){
            if(c.placeholder().equals(cmp)){
                numero.setText(c.getNumero());
                nombre.setText(c.getNombre());
                raza.setText(c.getRaza());
                p1.setText(c.getP1());
                p2.setText(c.getP2());
                p3.setText(c.getP3());
                p4.setText(c.getP4());
            }
        }
    }

    public void gotanimal(String numero, String nombre, String raza) throws IOException {
        animals.add(new CPClass(numero,nombre,raza));
        dropanimals.getItems().add(animals.get(animals.size()-1).placeholder());
        save();
    }

    public boolean emptyfields(){
        boolean hasempty = false;
        for(Node e:gp1.getChildren()){
            if(e instanceof TextField && e.getId().equals("fechaprobpar")==false && ((TextField) e).getText().equals("")){
                hasempty = true;
            }
        }
        for(Node e:gp2.getChildren()){
            if(e instanceof TextField && ((TextField) e).getText().equals("")){
                hasempty = true;
            }
        }
        return hasempty;
    }
}
