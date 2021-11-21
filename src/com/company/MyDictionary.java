package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MyDictionary {
    private File newFile = null;
    private BufferedReader bufferedReader;
    private String patternLatin = "[A-Za-z]{4}";
    private String patternNumbers = "[0-9]{5}";
    private String patternRus = "[А-Яа-я]+";
    private String typeDictionary;

    /**
     * @param fileName имя текстового файла словаря
     * @param pattern  true-словарь для латинских буквв, false-словарь для цифр
     * @throws IOException
     */
    public MyDictionary(String fileName, boolean pattern) throws Exception {
        newFile = new File(fileName + ".txt");
        if (pattern)
            typeDictionary = patternLatin;
        else
            typeDictionary = patternNumbers;

        if (newFile.exists()) {
            bufferedReader = new BufferedReader(new FileReader(newFile));
            String str = bufferedReader.readLine();
            if (str != null) {
                if (!str.split("-")[0].matches(typeDictionary) || !str.split("-")[1].matches(patternRus)) {
                    throw new Exception("несоответствие типу словаря");
                }
            }
        }
        else {
            newFile.createNewFile();
        }
    }

    public MyDictionary(String fileName) throws Exception {
        newFile = new File(fileName + ".txt");
        bufferedReader = new BufferedReader(new FileReader(newFile));
        String str = bufferedReader.readLine();
        if (!newFile.exists()) {
            throw new Exception("файл не существует");
        }
        if (str == null) {
            throw new Exception("файл пуст, невозможно определить тип словаря");
        }
        if ((str.split("-")[0].matches(patternLatin) || str.split("-")[0].matches(patternNumbers))
                && str.split("-")[1].matches(patternRus)) {
            if (str.split("-")[0].matches(patternLatin))
                typeDictionary = patternLatin;
            else
                typeDictionary = patternNumbers;
        } else {
            throw new Exception("файл не словарь");
        }
    }

    public void outputDictionary() throws Exception {
        bufferedReader = new BufferedReader(new FileReader(newFile));
        String str = bufferedReader.readLine();
        if (str == null) {
            throw new Exception("словарь пуст");
        }
        while (str != null) {
            System.out.println(str);
            str = bufferedReader.readLine();
        }
    }

    private HashMap<String, String> hashDictionary() throws Exception {
        bufferedReader = new BufferedReader(new FileReader(newFile));
        HashMap<String, String> hashMap = new HashMap<>();
        String str = bufferedReader.readLine();
        if (str == null) {
            throw new Exception("словарь пуст");
        }
        while (str != null) {
            hashMap.put(str.split("-")[0], str.split("-")[1]);
            str = bufferedReader.readLine();
        }
        return hashMap;
    }

    public String searchKey(String key) throws Exception {
        if (!key.matches(typeDictionary)) {
            throw new Exception("ключ не соответствует типу словаря");
        }
        HashMap<String, String> hashMap = hashDictionary();
        if (hashMap.containsKey(key))
            return hashMap.get(key);
        else
            throw new Exception("ключ не найден");
    }

    public void deleteEntry(String key) throws Exception {
        if (!key.matches(typeDictionary)) {
            throw new Exception("ключ не соответствует типу словаря");
        }
        HashMap<String, String> hashMap = hashDictionary();
        System.out.println(hashMap.toString());
        Set<String> list = hashMap.keySet();
        FileWriter fileWriter = new FileWriter(newFile);

        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("");
        for (int i = 0; i < list.size(); i++) {
            for (String str : list) {
                System.out.println(str + "54545");
                if (!str.equals(key)) {
                    printWriter.println(str + "-" + hashMap.get(str));
                }
            }
        }
        printWriter.close();

    }

    public void addEntry(String key, String value) throws Exception {
        if (!key.matches(typeDictionary)) {
            throw new Exception("ключ не соответствует типу словаря");
        }
        HashMap<String, String> hashMap = hashDictionary();
        if (hashMap.containsKey(key)) {
            throw new Exception("данный ключ уже существует");
        } else {
            hashMap.put(key, value);
            FileWriter fw = new FileWriter(newFile, true);
            PrintWriter printWriter = new PrintWriter(fw);
            printWriter.print("\n" + key + "-" + value);
            printWriter.close();
        }
    }
}
