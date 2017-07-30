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
    @FXML
    private Button käskyButton;
    @FXML
    private Button alkupButton;
    @FXML
    private Button edellinenButton;
    @FXML
    private Button seuraavaButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackForward bf = BackForward.getInstance();
        web.getEngine().load("http://www.google.com");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        BackForward bf = BackForward.getInstance();
        
        String osoite = osoiteField.getText();
        String check;
        
        if(osoite.equals("index.html")) {
            web.getEngine().load(getClass().getResource("index.html").toExternalForm());
        }
        else if(osoite.length() > 7) {
            check = osoite.substring(0,7);
            
            if(!check.equals("http://")) {
                bf.osoitteet.add("http://" + osoite);
                bf.itr += 1;
                web.getEngine().load("http://" + osoite);
                System.out.println(bf.itr);
            }
            else {
                bf.osoitteet.add(osoite);
                bf.itr += 1;
                web.getEngine().load(osoite);
                System.out.println(bf.itr);
            }
        }
        else {
            bf.osoitteet.add(osoite);
            bf.itr += 1;
            web.getEngine().load(osoite);
            System.out.println(bf.itr);
        }
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

    @FXML
    private void handleEdellinenAction(ActionEvent event) {
        BackForward bf = BackForward.getInstance();
        if (bf.itr > 0) {
            web.getEngine().load(bf.osoitteet.get(bf.itr-1));
            bf.itr -= 1;
            System.out.println(bf.itr);
        }
    }

    @FXML
    private void handleSeuraavaAction(ActionEvent event) {
        BackForward bf = BackForward.getInstance();
        if (bf.itr < bf.osoitteet.size()) {
            web.getEngine().load(bf.osoitteet.get(bf.itr+1));
            bf.itr += 1;
            System.out.println(bf.itr);
        }
    }
}
