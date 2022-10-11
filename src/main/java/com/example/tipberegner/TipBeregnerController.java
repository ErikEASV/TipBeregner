package com.example.tipberegner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipBeregnerController {

    @FXML
    private TextField beløbTekstfelt;

    @FXML
    private Button beregnKnap;

    @FXML
    private Label tipProcentLabel;

    @FXML
    private Slider tipProcentSlider;

    @FXML
    private TextField tipTekstfelt;

    @FXML
    private TextField totalTekstfelt;

    // Modelklasse
    private Tip tip = new Tip();

    @FXML
    void beregnKnapTrykket(ActionEvent event) {
        try {
            String beløb = beløbTekstfelt.getText();
            tipTekstfelt.setText(tip.beregn(beløb));
            totalTekstfelt.setText(tip.hentTotal());
        }
        catch (NumberFormatException ex) {
            beløbTekstfelt.setText("Indtast beløb");
            beløbTekstfelt.selectAll();
            beløbTekstfelt.requestFocus();
        }

    }

    public void initialize() {
        tipProcentSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                       Number oldValue, Number newValue) {

                           tipProcentLabel.setText(tip.sætProcent(newValue.intValue()));
                           beregnKnapTrykket(null);

                    }
                }
        );
    }

}
