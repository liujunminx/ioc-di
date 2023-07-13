package com.ljm.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationReader {

    private Map<String, Object> propertiesMap = new HashMap<>();

    public ApplicationReader(){
        startRead();
    }

    public void startRead() {
        File file = new File("application.yml");
        try {
            Scanner scanner = new Scanner(file);
            String currentKey = null;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        ApplicationReader reader = new ApplicationReader();
    }
}
