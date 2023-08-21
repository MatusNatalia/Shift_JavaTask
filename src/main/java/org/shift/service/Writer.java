package org.shift.service;

import java.io.*;

public class Writer {

    private final BufferedWriter bufferedWriter;

    public Writer(String fileName) throws IOException{
        File file = new File(fileName);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public void writeElement(String element) throws IOException{
        bufferedWriter.write(element+"\n");
    }

    public void flush() throws IOException{
        bufferedWriter.flush();
    }

}
