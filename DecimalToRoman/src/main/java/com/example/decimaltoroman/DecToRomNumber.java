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


        int[] values = {1000,500,100,50,10,5,1};


        //V -> 5
        //number:5
        //s: V
        //40 -> XL
        //3 -> III
        //5 -> V
        //926 ->
        //926 -500 = 426    D
        //426 - 100 = 326   C
        //326 -100

        //40 - 10 = 30 X
        //30 - 10 = 20 X
        //20 -10 = 10 X
        //10 - 10  = 0 X

        //99 -> XCIX
        //99 - 50 = L
        //49 - 10 = X
        //39 - 10 = X
        //29 - 10 = X
        //19 - 10 = X
        // LXXXX- > XC
        // 9 -5 = VIIII -> IX

        //1500 -> M
        //500 -> D
        //0

        String s = "";

        while(number != 0) {

            /*for (int arr: values) {
                if (number >= arr) {
                    number -= arr;
                    s += translations.get(arr);
                    break;
                }
            }*/

            if (number >= 1000) {
                number -= 1000;
                s += translations.get(1000);
            }
            else if (number >= 500) {
                number -= 500;
                s += translations.get(500);
            }
            else if (number >= 100) {
                number -= 100;
                s += translations.get(100);
            }
            else if (number >= 50) {
                number -= 50;
                s += translations.get(50);
            }
            else if (number >= 10) {
                number -= 10;
                s += translations.get(10);
            }
            else if (number >= 5) {
                number -= 5;
                s += translations.get(5);
            }
            else if (number >= 1) {
                number -= 1;
                s += translations.get(1);
            }
        }
//99 -> LXXXXVIIII -> XCVIIII -> XCIX
//99 -> LXXXXVIV
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
