package webview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private javafx.scene.web.WebView web;
    @FXML
    private TextField osoiteField;
    @FXML
    private Button refreshButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        web.getEngine().load("http://www.google.com");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        String osoite = osoiteField.getText();
        String check;
        
        if(osoite.length() > 7) {
            check = osoite.substring(0,7);

            if(!check.equals("http://")) {
                web.getEngine().load("http://" + osoite);
            }
            else
                web.getEngine().load(osoite);
        }
        else
            web.getEngine().load(osoite);
    }
    @FXML
    private void handleRefreshAction(ActionEvent event) {
        web.getEngine().reload();
    }
    
    
}
