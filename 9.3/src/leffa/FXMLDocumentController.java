package leffa;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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
        
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        
        String id;
        if (comboBox.getValue() != null) {
            for (int i = 0;i<lt.teatteri_array.size();i++) {
                if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                    id = lt.teatteri_array.get(i).getID();
                    lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + dateFormat.format(date));
                    
                    for (int j = 0;j<lt.naytos_array.size();j++) 
                        if (j < lt.naytos_array.size()-1)
                            textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                        else
                            textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                }
            }
        }
        else
            textArea.setText("Valitse teatteri ensin!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();
        
        for (int i = 0;i<lt.teatteri_array.size();i++)
            comboBox.getItems().add(lt.teatteri_array.get(i).getNimi());
    }

    @FXML
    private void handleHakuAction(ActionEvent event) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();
        
    }
}
// http://www.finnkino.fi/xml/Schedule/?area=<teatterinID>&dt=<päivämäärä pp.kk.vvvv>