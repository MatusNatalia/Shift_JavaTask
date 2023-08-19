package org.shift.service;

import org.shift.utils.Order;
import org.shift.utils.QueueElement;
import org.shift.utils.Type;

import java.io.IOException;
import java.util.*;

public class Sorter {
    private Type type;

    private List<Reader> readers = new ArrayList<>();
    private Writer writer;
    private PriorityQueue<QueueElement> queue;
    private Comparator<QueueElement> comparator;
    public void sort(Type type, Order order, String outputFile, List<String> inputFiles) throws IOException {
        this.type = type;
        writer = new Writer(outputFile);
        for(String inputFile : inputFiles){
            readers.add(new Reader(inputFile));
        }
        if (type == Type.INT) {
            comparator = Comparator.comparingInt(x -> Integer.parseInt(x.getElement()));
        }
        else {
            comparator = Comparator.comparing(QueueElement::getElement);
        }
        if(order == Order.DESC){
            comparator = comparator.reversed();
        }
        queue = new PriorityQueue<>(inputFiles.size(), comparator);
        initQueue(queue);
        mergeFiles();
        writer.flush();
    }

    private void mergeFiles() throws IOException {
        String lastWrittenElement;
        while(!queue.isEmpty()){
            QueueElement element = queue.poll();
            System.out.println(element.getElement());
            writer.writeElement(element.getElement());
            Reader reader = element.getReader();
            String nextElement = reader.getNextElement();
            while(nextElement != null){
                if(isValid(nextElement)){
                    queue.add(new QueueElement(nextElement, reader));
                    break;
                }
                else {
                    System.err.println("Wrong element '" + nextElement + "' in " + reader.getFileName() + ". Will be ignored");
                }
                nextElement = reader.getNextElement();
            }
        }
    }

    private void initQueue(PriorityQueue<QueueElement> queue){
        for (Reader reader : readers) {
            String element = reader.getNextElement();
            while(!isValid(element)){
                element = reader.getNextElement();
            }
            queue.add(new QueueElement(element, reader));
        }
    }

    private boolean isValid(String element){
        if(type == Type.INT){
            try{
                Integer.parseInt(element);
            } catch (NumberFormatException e){
                return false;
            }
        }
        return !element.contains(" ");
    }

}
