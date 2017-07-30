package javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Sadmiral
 */
public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    @FXML
    private Button lataaButton;
    @FXML
    private Button tallennaButton;
    @FXML
    private TextArea textArea;
    
    void init(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void handleButtonActionL(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Virhe!");
        alert.setHeaderText(null);
        alert.setContentText("Tyhjennä tekstikenttä lukeaksesi uuden tiedoston!");

        if (textArea.getText() == null || textArea.getText().trim().isEmpty()) {
            //lataa tekstitiedostosta teksti fieldiin
            FileChooser fileLoader = new FileChooser();
            fileLoader.setInitialDirectory(new File(System.getProperty("user.home")));
            fileLoader.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            
            File file = fileLoader.showOpenDialog(stage);
            
            ReadAndWriteIO rwIO = new ReadAndWriteIO(file);   
            
            if (file != null)
                textArea.setText(rwIO.readFile(file));
        }
        else
            alert.showAndWait();
    }
    public void handleButtonActionT(ActionEvent event) throws IOException {
        String teksti = textArea.getText();
        teksti = teksti.replaceAll("(?!\\r)\\n", "\r\n"); //normaali tekstinkäsittelyohjelma ei tunnista textArean käyttämiä newlinejä
        
        if (!textArea.getText().trim().isEmpty()){
            //tallenna fieldin teksti tekstitiedostoon
            FileChooser fileSaver = new FileChooser();
            fileSaver.setInitialDirectory(new File(System.getProperty("user.home")));
            fileSaver.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            
            File file = fileSaver.showSaveDialog(stage);
            
            ReadAndWriteIO rwIO = new ReadAndWriteIO(file);
            
            if (file != null) {
                rwIO.writeFile(teksti, file);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
    }    
}
