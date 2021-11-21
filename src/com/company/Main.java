package com.company;

import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
            MyDictionary2 myDictionary2 = new MyDictionary2("file1234.txt", MyDictionary2.patternLatin);
            myDictionary2.printDictionary();
            myDictionary2.addEntry("oooo","оывалофлдаовлфдалждул");
            myDictionary2.addEntry("wwww","оывалофлд");
            myDictionary2.deleteEntry("oooo");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
