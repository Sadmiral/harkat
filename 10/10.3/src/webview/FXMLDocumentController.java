package webview;

import java.lang.reflect.InvocationTargetException;
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
    @FXML
    private Button käskyButton;
    @FXML
    private Button alkupButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        web.getEngine().load("http://www.google.com");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        String osoite = osoiteField.getText();
        String check;
        
        if(osoite.equals("index.html")) {
            web.getEngine().load(getClass().getResource("index.html").toExternalForm());
        }
        else if(osoite.length() > 7) {
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

    @FXML
    private void handleKäskyAction(ActionEvent event) {
        web.getEngine().executeScript("document.shoutOut()");
    }

    @FXML
    private void handleAlkupAction(ActionEvent event) throws Exception {
        web.getEngine().executeScript("initialize()");
    }
}
