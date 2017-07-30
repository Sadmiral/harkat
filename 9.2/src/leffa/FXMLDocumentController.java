package leffa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button listausButton;
    @FXML
    private Button hakuButton;
    @FXML
    private TextField päiväField;
    @FXML
    private TextField alkuField;
    @FXML
    private TextField päättymisField;
    @FXML
    private TextField nimiField;
    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<String> comboBox;

    
    @FXML
    private void handleListausAction(ActionEvent event) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();
        for (int i = 0;i<lt.teatteri_array.size();i++)
            comboBox.getItems().add(lt.teatteri_array.get(i).getNimi());
    }

    @FXML
    private void handleHakuAction(ActionEvent event) {
    }
}
