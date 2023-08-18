package org.shift.service;

import java.io.*;

public class Writer {

    private BufferedWriter bufferedWriter;

    public Writer(String fileName){
        File file = new File(fileName);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeElement(String element) throws IOException {
        bufferedWriter.write(element+"\n");
    }

    public void flush() throws IOException{
        bufferedWriter.flush();
    }

}
