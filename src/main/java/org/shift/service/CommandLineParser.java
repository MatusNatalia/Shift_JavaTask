package org.shift.service;

import org.shift.exceptions.WrongArgumentException;
import org.shift.utils.Order;
import org.shift.utils.Type;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {

    private List<String> inputFiles = new ArrayList<>();
    private String outputFile;
    private Type type = Type.UNDEFINED;
    private Order order = Order.UNDEFINED;

    public void parseCommandLineArguments(String[] args){
        try {
            if (args.length == 0) {
                throw new WrongArgumentException("too few arguments");
            }
            int i;
            for (i = 0; i < args.length; i++) {
                if ('-' == args[i].charAt(0)) {
                    if (args[i].length() != 2) {
                        System.err.println("Unknown flag: " + args[i] + ". Will be ignored");
                        continue;
                    }
                    switch (args[i].charAt(1)) {
                        case 'i':
                            if (type == Type.UNDEFINED) {
                                type = Type.INT;
                                break;
                            } else {
                                throw new WrongArgumentException("type redefinition");
                            }
                        case 's':
                            if (type == Type.UNDEFINED) {
                                type = Type.STRING;
                                break;
                            } else {
                                throw new WrongArgumentException("type redefinition");
                            }
                        case 'a':
                            if (order == Order.UNDEFINED) {
                                order = Order.ASC;
                                break;
                            } else {
                                throw new WrongArgumentException("order redefinition");
                            }
                        case 'd':
                            if (order == Order.UNDEFINED) {
                                order = Order.DESC;
                                break;
                            } else {
                                throw new WrongArgumentException("order redefinition");
                            }
                        default:
                            System.err.println("Unknown flag: " + args[i] + ". Will be ignored.");
                    }
                } else {
                    break;
                }
            }
            if (order == Order.UNDEFINED) {
                order = Order.ASC;
            }
            if (type == Type.UNDEFINED) {
                throw new WrongArgumentException("please define data type");
            }
            if (args.length < i + 3) {
                throw new WrongArgumentException("too few arguments");
            }
            outputFile = args[i++];
            for (int j = i; j < args.length; j++) {
                inputFiles.add(args[j]);
            }
            System.out.println("Arguments:");
            System.out.println("Type: " + type);
            System.out.println("Order: " + order);
            System.out.println("Output: " + outputFile);
            System.out.println("Input: ");
            for (String file : inputFiles) {
                System.out.println(file + " ");
            }
        } catch (WrongArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public List<String> getInputFiles(){
        return inputFiles;
    }

    public String getOutputFile(){
        return outputFile;
    }

    public Type getType(){
        return type;
    }

    public Order getOrder(){
        return order;
    }

}
