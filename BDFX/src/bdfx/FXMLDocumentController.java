package bdfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label rahaLabel;
    @FXML
    private TextArea infoLabel;
    @FXML
    private Slider rahaSlider;
    @FXML
    private ChoiceBox<String> limuChoice;
    @FXML
    private ChoiceBox<String> kokoChoice;
    @FXML
    private Button kuittiButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        BottleDispenser bd = BottleDispenser.getInstance();
        
        limuChoice.getItems().addAll("Pepsi Max", "Coca-Cola Zero", "Fanta Zero");
        kokoChoice.getItems().addAll("0.5", "1.5");
        int i, x = 0;
        for (i = 1; i <= bd.bottles; i++) {
                infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getNimi() + " " + bd.getObj(x).getKoko() + " " + bd.getObj(x).getHinta() + "€" + "\n");
                x++;
            }
    }    
    @FXML
    private void handleOstoButtonAction(ActionEvent event) throws IOException {
        BottleDispenser bd = BottleDispenser.getInstance(); 
        
        int i, x, val;
        val = getVal();
        if (val == 0) {
            infoLabel.setText("");
            x = 0;
            for (i = 1; i <= bd.bottles; i++) {
                infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getNimi() + " " + bd.getObj(x).getKoko() + " " + bd.getObj(x).getHinta() + "€" + "\n");
                x++;
            }
            infoLabel.setText(infoLabel.getText() + "\nVäärä syöte! Valitse limu ja koko.");
        }
        if (val > 0 && val < 6 && bd.bottles > 0){
            if (val > 0 && val <= bd.bottles) {
                infoLabel.setText("");
                
                if (bd.money > bd.getObj(val-1).getHinta()) {
                    String b = bd.getObj(val-1).getNimi();
                    bd.ostetut_array.add(new Bottle(bd.getObj(val-1).getNimi(), bd.getObj(val-1).getKoko(), bd.getObj(val-1).getHinta()));
                    
                    bd.buyBottle(val);
                    x = 0;
                    for(i = 1; i <= bd.bottles; i++) {
                        infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getNimi() + " " + bd.getObj(x).getKoko() + " " +bd.getObj(x).getHinta() + "€" + "\n");
                        x++;
                    }
                    infoLabel.setText(infoLabel.getText() + "\nKACHUNK! " + b + " tipahti masiinasta");
                }
                else {
                    x = 0;
                    for(i = 1; i <= bd.bottles; i++) {
                        infoLabel.setText(infoLabel.getText() + i + ") " + bd.getObj(x).getNimi() + " " + bd.getObj(x).getKoko() + " " +bd.getObj(x).getHinta() + "€" + "\n");
                        x++;
                    }
                    infoLabel.setText(infoLabel.getText() + "\nSyötä lisää rahaa!");
                }
                rahaLabel.setText(String.valueOf((double)Math.round(bd.money * 10000d) / 10000d) + "€");
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
        rahaLabel.setText(String.valueOf(bd.money) + "€");
    }
    @FXML
    private void handleUlostusButtonAction(ActionEvent event) {
        BottleDispenser bd = BottleDispenser.getInstance();
        infoLabel.setText("Klink klink. Rahaa tuli ulos " + bd.returnMoney());
        rahaLabel.setText("0.0€");
    }
    @FXML
    private void kuittiButtonAction(ActionEvent event) throws IOException {
        tulostaKuitti();
    }
    private void tulostaKuitti() throws IOException {
        BottleDispenser bd = BottleDispenser.getInstance();
        ReadAndWriteIO rwIO = new ReadAndWriteIO("kuitti.txt");
        
        rwIO.writeFile(bd.ostetut_array.get(bd.ostetut_array.size()-1));
    }
    private int getVal() {
        BottleDispenser bd = BottleDispenser.getInstance();

        int i, val = 0;

        if ("Pepsi Max".equals(limuChoice.getValue()) && "0.5".equals(kokoChoice.getValue())) {
            for (i = 0;i < bd.bottles; i++) {
                if ("Pepsi Max".equals(bd.getObj(i).getNimi()) && "0.5".equals(Double.toString(bd.getObj(i).getKoko()))) {
                    val = i+1;
                    return val;
                }
            }
        }
        if ("Pepsi Max".equals(limuChoice.getValue()) && "1.5".equals(kokoChoice.getValue())) {
            for (i = 0;i < bd.bottles; i++) {
                if ("Pepsi Max".equals(bd.getObj(i).getNimi()) && "1.5".equals(Double.toString(bd.getObj(i).getKoko()))) {
                    val = i+1;
                    return val;
                }
            }
        }
        if ("Coca-Cola Zero".equals(limuChoice.getValue()) && "0.5".equals(kokoChoice.getValue())) {
            for (i = 0;i < bd.bottles; i++) {
                if ("Coca-Cola Zero".equals(bd.getObj(i).getNimi()) && "0.5".equals(Double.toString(bd.getObj(i).getKoko()))) {
                    val = i+1;
                    return val;
                }
            }
        }
        if ("Coca-Cola Zero".equals(limuChoice.getValue()) && "1.5".equals(kokoChoice.getValue())) {
            for (i = 0;i < bd.bottles; i++) {
                if ("Coca-Cola Zero".equals(bd.getObj(i).getNimi()) && "1.5".equals(Double.toString(bd.getObj(i).getKoko()))) {
                    val = i+1;
                    return val;     
                }
            }
        }
        if ("Fanta Zero".equals(limuChoice.getValue()) && "0.5".equals(kokoChoice.getValue())) {
            for (i = 0;i < bd.bottles; i++) {
                if ("Fanta Zero".equals(bd.getObj(i).getNimi())) {
                    val = i+1;
                    return val;
                }
            }
        }

    return val;
    }
}