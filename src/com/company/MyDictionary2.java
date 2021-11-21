package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class MyDictionary2 {
    private File file;
    static final String patternLatin = "[A-Za-z]{4}";
    static final String patternNumbers = "[0-9]{5}";
    final String patternRus = "[А-Яа-я]+";
    private String typeDictionary=null;
    private HashMap<String, String> dict=null;

    public MyDictionary2 (String fileName, String pattern ) throws Exception {
        switch (pattern) {
            case patternLatin -> typeDictionary = patternLatin;
            case patternNumbers -> typeDictionary = patternNumbers;
            default -> throw new Exception("неизвестный паттерн");
        }
        file = new File(fileName);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new Exception("файл "+fileName+" невозможно создать ");
            }
        }
        readDict();
    }


    private void readDict() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = bufferedReader.readLine();
        dict = new HashMap<>();
        String[] arr;
        while (str != null) {
            arr = str.split("-");
            if (arr[0].matches(typeDictionary)&& arr[1].matches(patternRus) && arr.length==2) {
                dict.put(arr[0], arr[1]);
            }
            else {
                throw new Exception(Arrays.toString(arr) + " не соответствует паттерну "+ typeDictionary+"-"+patternRus);
            }
            str = bufferedReader.readLine();
        }
    }

    public void printDictionary() {
        for (String key: dict.keySet()) {
            System.out.println(key+"-"+dict.get(key));
        }
    }

    public void addEntry (String key, String value) throws Exception {
        if (key.matches(typeDictionary) && value.matches(patternRus)) {
            if (dict.containsKey(key)) {
                return;
            }
            dict.put(key, value);
        }
        else {
            throw new Exception("пара "+key+"-"+value+ " не соответствует паттерну "+ typeDictionary+"-"+patternRus);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
        if (!(dict.size()-1 == 0)){
            bufferedWriter.append("\n");
        }
        bufferedWriter.append(key+"-"+value);
        bufferedWriter.close();

    }

    public void deleteEntry(String key) throws Exception {
        if (!key.matches(typeDictionary)) {
            throw new Exception(key+"несоотвествует паттерну"+typeDictionary);
        }
        dict.remove(key);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (String k: dict.keySet()) {
            bufferedWriter.append(k+"-"+dict.get(k));
        }
        bufferedWriter.close();
    }
}
