package br.com.eric.utils.converters;

public class NumberUtils {

    public static Double convertToDouble(String strNumber){
        if(strNumber == null) return 0d;
        String number = strNumber.replaceAll(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber){
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        String regex = "[-+]?[0-9]*\\\\.?[0-9]+";
        return number.matches(regex);
    }
}
