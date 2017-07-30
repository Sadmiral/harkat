package leffa;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
        
        Date date = new Date();
        Date alku = new Date();
        Date pääty = new Date();

        String id;
        
        textArea.setText("");
        if (comboBox.getValue() != null & päiväField.getText().isEmpty()) {
            for (int i = 0;i<lt.teatteri_array.size();i++) {
                if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                    id = lt.teatteri_array.get(i).getID();
                    lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), alku, pääty);
                    
                    for (int j = 0;j<lt.naytos_array.size();j++)
                        if (j < lt.naytos_array.size()-1)
                            textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                        else
                            textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                }
            }
        }
        else if (comboBox.getValue() != null & !päiväField.getText().isEmpty() | !alkuField.getText().isEmpty() | !päättymisField.getText().isEmpty()) {
            if (päiväField.getText().length() != 10)
                textArea.setText("Väärä päivämäärä!\n\nSyötä päivämäärä muodossa dd.mm.yyyy");
            else if (!alkuField.getText().isEmpty() & !päättymisField.getText().isEmpty()) {

                try {
                    alku = new SimpleDateFormat("HH:mm").parse(alkuField.getText());
                    pääty = new SimpleDateFormat("HH:mm").parse(päättymisField.getText());
                    System.out.println(simpleTimeFormat.format(alku) + simpleTimeFormat.format(pääty));
                } catch (ParseException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
            else if (!alkuField.getText().isEmpty()) {
                try {
                    alku = new SimpleDateFormat("HH:mm").parse(alkuField.getText());
                    System.out.println(simpleDateFormat.format(alku));
                } catch (ParseException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (!päättymisField.getText().isEmpty()) {
                try {
                    pääty = new SimpleDateFormat("HH:mm").parse(päättymisField.getText());
                    System.out.println(simpleDateFormat.format(pääty));
                } catch (ParseException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Lisää näytökset listaan vaan jos ne täyttävät alkuehdot (aloitus- ja lopetusajat)
                
            }
            else {
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(päiväField.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0;i<lt.teatteri_array.size();i++) {
                    if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                        id = lt.teatteri_array.get(i).getID();
                        lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), alku, pääty);

                        for (int j = 0;j<lt.naytos_array.size();j++)
                            if (j < lt.naytos_array.size()-1)
                                textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                            else
                                textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                    }
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