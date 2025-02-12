package org.nagesh.springbootbasics.controller;

import java.math.BigDecimal;

public class NumberToWordsConverter {

    private static final String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] thousands = {"", "Thousand", "Lakh", "Crore"};

    public static String convertToIndianCurrency(BigDecimal amount) {
        int rupees = amount.intValue();
        int paisa = amount.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).intValue();

        String rupeePart = (rupees > 0) ? convertNumberToWords(rupees) + " Rupees" : "";
        String paisaPart = (paisa > 0) ? convertNumberToWords(paisa) + " Paise" : "";

        if (rupeePart.isEmpty() && paisaPart.isEmpty()) {
            return "Zero Rupees";
        }

        return rupeePart + (!rupeePart.isEmpty() && !paisaPart.isEmpty() ? " and " : "") + paisaPart;
    }

    private static String convertNumberToWords(int number) {
        if (number == 0) {
            return "Zero";
        }

        String words = "";
        int place = 0;

        while (number > 0) {
            if (number % 1000 != 0) {
                String prefix = convertThreeDigitNumber((number % 1000));
                words = prefix + " " + thousands[place] + " " + words;
            }
            number /= 1000;
            place++;
        }

        return words.trim();
    }

    private static String convertThreeDigitNumber(int number) {
        String word = "";

        if (number / 100 > 0) {
            word += units[number / 100] + " Hundred ";
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                word += units[number];
            } else {
                word += tens[number / 10];
                if (number % 10 > 0) {
                    word += " " + units[number % 10];
                }
            }
        }

        return word.trim();
    }


}