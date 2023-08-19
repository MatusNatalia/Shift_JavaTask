package org.shift;

import org.shift.exceptions.WrongArgumentException;
import org.shift.service.CommandLineParser;
import org.shift.service.Sorter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        CommandLineParser parser = new CommandLineParser();
        parser.parseCommandLineArguments(args);
        try {
            Sorter sorter = new Sorter();
            sorter.sort(parser.getType(), parser.getOrder(), parser.getOutputFile(), parser.getInputFiles());
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}