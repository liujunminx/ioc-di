package com.ljm.core;

import java.io.InputStream;
import java.util.*;

public class PropertiesReader {

    private Map<String, Object> propertiesMap = new HashMap<>();

    private final String DELIMITER = ":";

    public PropertiesReader(){
        startRead();
    }

    public void startRead() {
        Queue<String> queue = new LinkedList<>();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.yml")) {
            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.startsWith("#"))
                    continue;
                int index = line.indexOf(DELIMITER);
                if (index != -1) {
                    String key = line.substring(0, index).trim();
                    String value = line.substring(index + 1).trim();
                    if (value.isBlank()){
                        queue.add(key);
                    }else {
                        String objKey = "";
                        while (!queue.isEmpty()){
                            if (objKey.isBlank())
                                objKey = queue.poll();
                            else
                                objKey += "." + queue.poll();
                        }
                        objKey += "." + key;
                        propertiesMap.put(objKey, value);
                    }
                }
            }
        } catch (Exception e){
            throw new RuntimeException("read yaml file failed: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> getPropertiesMap() {
        return propertiesMap;
    }

    public static void main(String[] args) {
        PropertiesReader reader = new PropertiesReader();
        System.out.println(reader.propertiesMap);
    }
}
