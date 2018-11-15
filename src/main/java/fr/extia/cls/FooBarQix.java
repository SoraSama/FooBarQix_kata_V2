package fr.extia.cls;
/**
 * Author:andelai.S
 * Date:14/11/2018
 */

import fr.extia.inter.FooBarQixInterface;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FooBarQix implements FooBarQixInterface {

    private int max;
    private int min;

    private Map<Integer, String> dict_division;
    private Map<Integer, String> dict_contains;

    public void init(int min, int max) {
        if(min >max)
            throw new IllegalArgumentException("The min value must be less than max");
        this.min = min;
        this.max = max;
/**
 * "LinkedHashMap" is used to structure the replacement dictionary
 *  insert the pair of key and value in order of frequency of replacement, in order to have the best performance
 */
        //dictionary division
        dict_division = new LinkedHashMap();
        dict_division.put(3, "Foo");
        dict_division.put(5, "Bar");
        //dictionary contains
        dict_contains = new LinkedHashMap();
        dict_contains.put(3, "Foo");
        dict_contains.put(5, "Bar");
        dict_contains.put(7, "Qix");
    }
    // print result 1-100
    public void print() {
        IntStream.range(1, 100)
                .forEach(i -> System.out.println(replace(i)));
    }

    public String replace(int number) {
        // consistency check
        if (number < this.min || number > this.max) {
            throw new IllegalArgumentException(number + " is out of bounds (" + min + " to " + max + ")");
        }
        //  build string
        StringBuilder res = new StringBuilder();
        // rule division
        dict_division.forEach((k,v) ->{ if(number % k == 0) res.append(v); });

        // rule constains
        String str_number = String.valueOf(number);
        char[] values = str_number.toCharArray();
        for (char c :values) {
            dict_contains.forEach((k,v) ->{ if(v.charAt(0)==c) res.append(v);});
        }

        // Output result number and String

        if(res.length() == 0)
            return str_number;
        else
            return res.toString();
    }

}
