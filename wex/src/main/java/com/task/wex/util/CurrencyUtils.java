package com.task.wex.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyUtils {
    
    public static String round(double value) {
        DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        return df.format(value);
    }


}
