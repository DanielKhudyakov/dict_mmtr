package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
//            MyDictionary2 myDictionary2 = new MyDictionary2("file1234.txt", MyDictionary2.patternLatin);
//            myDictionary2.printDictionary();
//            myDictionary2.addEntry("oooo","оывалофлдаовлфдалждул");
//            myDictionary2.addEntry("wwww","оывалофлд");
//            myDictionary2.deleteEntry("oooo");

            MyDictionary2 myDictionary2 = null;
            String command = "";
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите команду ");
                command=scanner.nextLine();
                switch (command) {
                    case "create","open":
                        System.out.println("Введите имя файла ");
                        String file=scanner.nextLine();
                        System.out.println("Введите паттерн l-латинские буквы n-цифры ");
                        String pattern = scanner.nextLine();
                        switch (pattern) {
                            case "l" -> myDictionary2 = new MyDictionary2(file, MyDictionary2.patternLatin);
                            case "n" -> myDictionary2 = new MyDictionary2(file, MyDictionary2.patternNumbers);
                            default -> System.out.println("неверный паттерн");
                        }
                        break;
                    case "add":
                        if (myDictionary2 != null) {
                            System.out.println("Введите ключ");
                            String key = scanner.nextLine();
                            System.out.println("Введите значение ");
                            String value = scanner.nextLine();
                            myDictionary2.addEntry(key, value);
                        }
                        break;
                    case "delete":
                        if (myDictionary2 != null) {
                            System.out.println("Введите ключ");
                            String key = scanner.nextLine();
                            myDictionary2.deleteEntry(key);
                        }
                        break;
                    case "print":
                        if (myDictionary2 != null) {
                            myDictionary2.printDictionary();
                        }
                        break;
                    case "end":
                        System.out.println("Конец работы со словарем");
                        return;
                    default:
                        System.out.println("неверная команда");
                }

            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
