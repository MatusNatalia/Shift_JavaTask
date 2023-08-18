package org.shift.service;

import java.io.*;

public class Reader {

    private BufferedReader bufferedReader;

    public Reader(String fileName){
        File file = new File(fileName);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public String getNextElement() throws IOException {
        return bufferedReader.readLine();
    }

}
