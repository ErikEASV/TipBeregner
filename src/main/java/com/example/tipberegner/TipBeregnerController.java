package com.example.tipberegner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipBeregnerController {

    private static final NumberFormat valuta =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat procent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15);

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

    @FXML
    void beregnKnapTrykket(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(beløbTekstfelt.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTekstfelt.setText(valuta.format(tip));
            totalTekstfelt.setText(valuta.format(total));
        }
        catch (NumberFormatException ex) {
            beløbTekstfelt.setText("Indtast beløb");
            beløbTekstfelt.selectAll();
            beløbTekstfelt.requestFocus();
        }

    }

    public void initialize() {
        valuta.setRoundingMode(RoundingMode.HALF_UP);

        tipProcentSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                       Number oldValue, Number newValue) {
                       tipPercentage =
                          BigDecimal.valueOf(newValue.intValue() / 100.0);
                       tipProcentLabel.setText(procent.format(tipPercentage));
                       beregnKnapTrykket(null);

                    }
                }
        );
    }

}
