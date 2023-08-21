package org.shift;

import org.shift.exceptions.WrongArgumentException;
import org.shift.service.CommandLineParser;
import org.shift.service.Sorter;

public class Main {
    public static void main(String[] args) {
        try {
            CommandLineParser parser = new CommandLineParser();
            parser.parseCommandLineArguments(args);
            Sorter sorter = new Sorter();
            sorter.sort(parser.getType(), parser.getOrder(), parser.getOutputFile(), parser.getInputFiles());
        } catch (WrongArgumentException e){
            System.err.println(e.getMessage());
        }
    }
}