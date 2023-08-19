package org.shift.service;

import org.shift.exceptions.WrongElementException;

import java.io.*;

public class Reader {

    private BufferedReader bufferedReader;
    private String fileName;

    public Reader(String fileName){
        this.fileName = fileName;
        File file = new File(fileName);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public String getNextElement(){
        String nextElement = null;
        try {
            nextElement = bufferedReader.readLine();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return nextElement;
    }

    public String getFileName(){
        return fileName;
    }

}
