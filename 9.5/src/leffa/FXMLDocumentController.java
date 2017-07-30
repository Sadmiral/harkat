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
import javafx.scene.control.ListView;
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
    private ListView<?> listView;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();
        
        for (int i = 0;i<lt.teatteri_array.size();i++)
            comboBox.getItems().add(lt.teatteri_array.get(i).getNimi());
    }
    
    @FXML
    private void handleListausAction(ActionEvent event) {
        LeffaTeatterit lt = LeffaTeatterit.getInstance();
            
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
        
        //Alustetaan ensin oletusarvot (tDay, aloitus, lopetus)
        Date tDay = new Date();
        
        try {
            Date aloitus = simpleTimeFormat.parse("00:01");
            Date lopetus = simpleTimeFormat.parse("23:59");
        
            String id;
            
            textArea.setText("");
            //Jos vain teatteri on valittu, mutta ei päivää
            if (comboBox.getValue() != null & päiväField.getText().isEmpty()) {
                //Jos alkamisaikaa eikä loppumisaikaa olla annettu
                if (alkuField.getText().isEmpty() & päättymisField.getText().isEmpty()) {
                    for (int i = 0;i<lt.teatteri_array.size();i++) {
                        if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                            id = lt.teatteri_array.get(i).getID();

                            lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(tDay), null, aloitus, lopetus);

                            for (int j = 0;j<lt.naytos_array.size();j++)
                                if (j < lt.naytos_array.size()-1)
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                else
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                        }
                    }
                }
                //Kun alkamisaika ja päättymisaika on annettu
                else if (!alkuField.getText().isEmpty() & !päättymisField.getText().isEmpty()) {
                    Date alku = simpleTimeFormat.parse(alkuField.getText());
                    Date pääty = simpleTimeFormat.parse(päättymisField.getText());
                    
                    for (int i = 0;i<lt.teatteri_array.size();i++) {
                        if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                            id = lt.teatteri_array.get(i).getID();

                            lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(tDay), null, alku, pääty);

                            for (int j = 0;j<lt.naytos_array.size();j++)
                                if (j < lt.naytos_array.size()-1)
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                else
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                        }
                    }
                }
                //Jos alkamisaika on annettu, muttei päättymisaikaa
                else if (!alkuField.getText().isEmpty() & päättymisField.getText().isEmpty()) {
                    Date alku = simpleTimeFormat.parse(alkuField.getText());
                    
                    for (int i = 0;i<lt.teatteri_array.size();i++) {
                        if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                            id = lt.teatteri_array.get(i).getID();

                            lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(tDay), null, alku, lopetus);

                            for (int j = 0;j<lt.naytos_array.size();j++)
                                if (j < lt.naytos_array.size()-1)
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                else
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                        }
                    }
                }
                //Jos päättymisaika on annettu, muttei alkamisaikaa
                else if (alkuField.getText().isEmpty() & !päättymisField.getText().isEmpty()) {
                    Date pääty = simpleTimeFormat.parse(päättymisField.getText());
                    
                    for (int i = 0;i<lt.teatteri_array.size();i++) {
                        if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                            id = lt.teatteri_array.get(i).getID();

                            lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(tDay), null, aloitus, pääty);

                            for (int j = 0;j<lt.naytos_array.size();j++)
                                if (j < lt.naytos_array.size()-1)
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                else
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                        }
                    }
                }
            }
            //Jos teatteri ja päivä on valittu
            else if (comboBox.getValue() != null & !päiväField.getText().isEmpty()) {
                Date date = simpleDateFormat.parse(päiväField.getText());
                
                if (päiväField.getText().length() != 10) {
                    textArea.setText("Väärä päivämäärä!\n\nSyötä päivämäärä muodossa dd.mm.yyyy");
                }
                //Jos alkamisaikaa eikä loppumisaikaa olla annettu
                else if (alkuField.getText().isEmpty() & päättymisField.getText().isEmpty()) {
                    for (int i = 0;i<lt.teatteri_array.size();i++) {
                        if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                            id = lt.teatteri_array.get(i).getID();

                            lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), null, aloitus, lopetus);
                            
                            for (int j = 0;j<lt.naytos_array.size();j++)
                                if (j < lt.naytos_array.size()-1) {
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                }
                                else {
                                    textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                                }
                        }
                    }
                }
                else if (!alkuField.getText().isEmpty() & !päättymisField.getText().isEmpty()) {
                    try {
                        Date alku = simpleTimeFormat.parse(alkuField.getText());
                        Date pääty = simpleTimeFormat.parse(päättymisField.getText());
                        
                        for (int i = 0;i<lt.teatteri_array.size();i++) {
                            if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                                id = lt.teatteri_array.get(i).getID();
                                lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), null, alku, pääty);
                                
                                for (int j = 0;j<lt.naytos_array.size();j++)
                                    if (j < lt.naytos_array.size()-1)
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                    else
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Jos alkamisaika on annettu, muttei päättymisaikaa
                else if (!alkuField.getText().isEmpty() && päättymisField.getText().isEmpty()) {
                    try {
                        Date alku = simpleTimeFormat.parse(alkuField.getText());
                        for (int i = 0;i<lt.teatteri_array.size();i++) {
                            if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                                id = lt.teatteri_array.get(i).getID();
                                lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), null, alku, lopetus);
                                
                                for (int j = 0;j<lt.naytos_array.size();j++)
                                    if (j < lt.naytos_array.size()-1)
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                    else
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Jos päättymisaika on annettu, muttei alkamisaikaa
                else if (!päättymisField.getText().isEmpty() && alkuField.getText().isEmpty()) {
                    try {
                        Date pääty = simpleTimeFormat.parse(päättymisField.getText());
                        System.out.println(simpleDateFormat.format(pääty));for (int i = 0;i<lt.teatteri_array.size();i++) {
                            if (comboBox.getValue().equals(lt.teatteri_array.get(i).getNimi())) {
                                id = lt.teatteri_array.get(i).getID();
                                lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + simpleDateFormat.format(date), null, aloitus, pääty);
                                
                                for (int j = 0;j<lt.naytos_array.size();j++)
                                    if (j < lt.naytos_array.size()-1)
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j) + "\n");
                                    else
                                        textArea.setText(textArea.getText() + lt.naytos_array.get(j));
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
            else
                textArea.setText("Valitse teatteri ensin!");
        } catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleHakuAction(ActionEvent event) {
        try {
            LeffaTeatterit lt = LeffaTeatterit.getInstance();
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
            
            Date aloitus = simpleTimeFormat.parse("00:01");
            Date lopetus = simpleTimeFormat.parse("23:59");
            Date date;
            
            
            if (päiväField.getText().length() == 10)
                date = simpleDateFormat.parse(päiväField.getText());
            else
                date = new Date();
            
            String teatteri;
            
            if (nimiField.getText().isEmpty())
                textArea.setText("Syötä elokuvan nimi!");
            
            else {
                textArea.setText(nimiField.getText() + "\n\n");

                for (int i=0;i<lt.teatteri_array.size();i++) {
                    lt.Listaa("http://www.finnkino.fi/xml/Schedule/?area=" + lt.teatteri_array.get(i).getID() + "&dt=" + simpleDateFormat.format(date), nimiField.getText(), aloitus, lopetus);
                    teatteri = lt.teatteri_array.get(i).getNimi();
                    
                    for (int j = 0;j<lt.naytos_array.size();j++) {
                        if (nimiField.getText().equals(lt.naytos_array.get(j))) {

                            if (j < lt.naytos_array.size()) {
                                    textArea.setText(textArea.getText() + teatteri + "\n" + lt.naytos_array.get(j+1) + " - " + lt.naytos_array.get(j+2) + "\n");
                                }
                            else {
                                    textArea.setText(textArea.getText() + teatteri + "\n" + lt.naytos_array.get(j+1) + " - " + lt.naytos_array.get(j+2));
                            }
                            j += 2;
                        }
                    }
                    if (textArea.getText().equals(nimiField.getText() + "\n\n"))
                        textArea.setText("Elokuvaa ei löytynyt!");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}