package com.example.decimaltoroman;

import java.util.HashMap;
import java.util.Map;

public class DecToRomNumber {
    public static String converter(int number) {

        Map<Integer, String> translations = new HashMap<>();


        translations.put(1, "I");
        translations.put(5, "V");
        translations.put(10, "X");
        translations.put(50, "L");
        translations.put(100, "C");
        translations.put(500, "D");
        translations.put(1000, "M");


        int[] values = {1000, 500, 100, 50, 10, 5, 1};


        String s = "";

        while (number != 0) {

            for (int arr : values) {
                if (number >= arr) {
                    number -= arr;
                    s += translations.get(arr);
                    break;
                }
            }

        }

        s = s.replace("DCCCC", "CM");
        s = s.replace("CCCC", "CD");
        s = s.replace("LXXXX", "XC");
        s = s.replace("XXXX", "XL");
        s = s.replace("VIIII", "IX");
        s = s.replace("IIII", "IV");


        return s;

    }

    private static int handleValue(String s, int number, Map<Integer, String> translations, int value) {
        if (number >= value) {
            s += translations.get(number);
        }
        return number - value;
    }


}
