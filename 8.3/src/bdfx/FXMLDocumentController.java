package bdfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
//import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label rahaLabel;
    @FXML
    private TextField limsaInput;
    @FXML
    private TextArea infoLabel;
    @FXML
    private Slider rahaSlider;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BottleDispenser bd = BottleDispenser.getInstance();
        int i, x = 0;
        for (i = 1; i <= bd.bottles; i++) {
                infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getName() + " " + bd.getObj(x).getKoko() + " " + bd.getObj(x).getHinta() + "€" + "\n");
                x++;
            }
    }    
    @FXML
    private void handleOstoButtonAction(ActionEvent event) {
        BottleDispenser bd = BottleDispenser.getInstance();
        int i, x;
        
        if (limsaInput.getText().trim().isEmpty()) {
            infoLabel.setText("");
            x = 0;
            for (i = 1; i <= bd.bottles; i++) {
                infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getName() + " " + bd.getObj(x).getKoko() + " " + bd.getObj(x).getHinta() + "€" + "\n");
                x++;
            }
            infoLabel.setText(infoLabel.getText() + "\nSyötä valintasi numerokenttään.");
        }
        if (!limsaInput.getText().trim().isEmpty() && bd.bottles > 0){
            int val = Integer.parseInt(limsaInput.getText());
            if (val > 0 && val <= bd.bottles) {
                infoLabel.setText("");
                if (bd.money > bd.getObj(val-1).getHinta()) {
                    String b = bd.getObj(val-1).getName();
                    bd.buyBottle(val);
                    x = 0;
                    for(i = 1; i <= bd.bottles; i++) {
                        infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getName() + " " + bd.getObj(x).getKoko() + " " +bd.getObj(x).getHinta() + "€" + "\n");
                        x++;
                    }
                    infoLabel.setText(infoLabel.getText() + "\nKACHUNK! " + b + " tipahti masiinasta");
                }
                else {
                    x = 0;
                    for(i = 1; i <= bd.bottles; i++) {
                        infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getName() + " " + bd.getObj(x).getKoko() + " " +bd.getObj(x).getHinta() + "€" + "\n");
                        x++;
                    }
                    infoLabel.setText(infoLabel.getText() + "\nSyötä lisää rahaa!");
                }
                rahaLabel.setText(String.valueOf(bd.money) + "€");
            }
            else
                infoLabel.setText(infoLabel.getText() + "\nVäärä syöte!");
        }
    }
    @FXML
    private void handleLisausButtonAction(ActionEvent event) {
        BottleDispenser bd = BottleDispenser.getInstance();
        bd.addMoney(rahaSlider.getValue());
        rahaSlider.setValue(0);
        rahaLabel.setText(String.valueOf((double)Math.round(bd.money * 10000d) / 10000d) + "€");
    }
    @FXML
    private void handleUlostusButtonAction(ActionEvent event) {
        BottleDispenser bd = BottleDispenser.getInstance();
        infoLabel.setText("Klink klink. Rahaa tuli ulos " + bd.returnMoney());
        rahaLabel.setText("0.0€");
    }
}