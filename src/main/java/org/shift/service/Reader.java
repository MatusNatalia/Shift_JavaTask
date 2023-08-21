package org.shift.service;

import java.io.*;

public class Reader {

    private final BufferedReader bufferedReader;
    private final String fileName;

    public Reader(String fileName) throws FileNotFoundException{
        this.fileName = fileName;
        File file = new File(fileName);
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    public String getNextElement() throws IOException{
        return bufferedReader.readLine();
    }

    public String getFileName(){
        return fileName;
    }

}
