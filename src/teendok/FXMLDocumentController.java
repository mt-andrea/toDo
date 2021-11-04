/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */
package teendok;
import java.io.File;
import panel.Panel;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Máté Andrea
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private TextField txtUjFeladat;

    @FXML
    private ListView<String> lstFeladatok;

    @FXML
    void elejere() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        if (i>0) {
            String f=lstFeladatok.getItems().get(i);
            lstFeladatok.getItems().remove(i);
            lstFeladatok.getItems().add(0, f);
            lstFeladatok.getSelectionModel().select(0);
        }
        txtUjFeladat.requestFocus();
    }

    @FXML
    void fel() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        if (i>0) {
            String f=lstFeladatok.getItems().get(i);
            lstFeladatok.getItems().remove(i);
            lstFeladatok.getItems().add(i-1, f);
            lstFeladatok.getSelectionModel().select(i-1);
        }
        txtUjFeladat.requestFocus();

    }

    @FXML
    void hozzaad() {
        String feladat=txtUjFeladat.getText();
        if (!feladat.isEmpty()) {
            lstFeladatok.getItems().add(feladat);
            int utolso=lstFeladatok.getItems().size()-1;
            lstFeladatok.getSelectionModel().select(utolso);
        }
        txtUjFeladat.requestFocus();
        txtUjFeladat.selectAll();
    }

    @FXML
    void kilep() {
        if (ment() || Panel.igennem("Kilépés", "Nem tudtam menteni! Ki szeretnél lépni?")) {
            Platform.exit();
        }
    }

    @FXML
    void le() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        int utolso=lstFeladatok.getItems().size()-1;
        if (i>-1 && i<utolso) {
            String f=lstFeladatok.getItems().get(i);
            lstFeladatok.getItems().remove(i);
            lstFeladatok.getItems().add(i+1, f);
            lstFeladatok.getSelectionModel().select(i+1);
        }
        txtUjFeladat.requestFocus();
    }

    @FXML
    void modosit() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        if (i>-1) {
            lstFeladatok.getItems().set(i,txtUjFeladat.getText());
        }
        txtUjFeladat.requestFocus();
        txtUjFeladat.selectAll();
    }

    @FXML
    void torol() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        if (i>-1) {
            lstFeladatok.getItems().remove(i);
        }
        txtUjFeladat.requestFocus();
    }

    @FXML
    void vegere() {
        int i=lstFeladatok.getSelectionModel().getSelectedIndex();
        int utolso=lstFeladatok.getItems().size()-1;
        if (i>-1 && i<utolso) {
            String f=lstFeladatok.getItems().get(i);
            lstFeladatok.getItems().remove(i);
            lstFeladatok.getItems().add(f);
            lstFeladatok.getSelectionModel().select(utolso);
        }
        txtUjFeladat.requestFocus();
    }
    @FXML
    void bill(KeyEvent event) {
        if (event.getCode()==KeyCode.DELETE) {
            torol();
        }
    }
    
    
private boolean ment(){
    try (PrintWriter ki=new PrintWriter("teendok.txt","utf8")){
        for (String f : lstFeladatok.getItems()) {
            ki.println(f);
        }
        return true;
    } catch (IOException e) {
        Panel.hiba("Hiba a mentésnél!",e.getMessage());
        return false;
    }
}

private void betolt(){
    try (Scanner be=new Scanner(new File("teendok.txt"))){
        while (be.hasNextLine()) {            
            lstFeladatok.getItems().add(be.nextLine());
        }
    } catch (IOException e) {
    }
}




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        betolt();
        
        lstFeladatok.getSelectionModel().selectedItemProperty().addListener(
                (v,regi,uj)->{
                    if (uj!=null) {
                        txtUjFeladat.setText(uj);
                    }else{txtUjFeladat.setText("");}
                });
    }    
    
}
