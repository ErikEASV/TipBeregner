package com.example.tipberegner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class Tip {
    private static final NumberFormat valuta =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat procent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15);
    private BigDecimal total;

    public Tip() {
        valuta.setRoundingMode(RoundingMode.HALF_UP);
    }

    public String beregn(String beløb) {
        BigDecimal amount = new BigDecimal(beløb);
        BigDecimal tip = amount.multiply(tipPercentage);
        total = amount.add(tip);
        return valuta.format(tip).toString();
    }

    public String hentTotal() {
        return valuta.format(total).toString();
    }

    public String sætProcent(double p) {
        tipPercentage = BigDecimal.valueOf(p/100);
        return procent.format(tipPercentage);
    }
}
