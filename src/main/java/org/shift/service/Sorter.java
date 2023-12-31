package org.shift.service;

import org.shift.utils.Order;
import org.shift.utils.QueueElement;
import org.shift.utils.Type;

import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sorter {
    private Type type;
    private final List<Reader> readers = new ArrayList<>();
    private Writer writer;
    private PriorityQueue<QueueElement> queue;
    private Comparator<QueueElement> comparator;
    private static final Logger log = LogManager.getLogger(Sorter.class);
    public void sort(Type type, Order order, String outputFile, List<String> inputFiles) {
        log.info("Started sorting");
        log.debug("Type: " + type);
        log.debug("Order: " + order);
        log.debug("Output file: " + outputFile);
        inputFiles.forEach(file -> log.debug("Input file: " + file));
        this.type = type;
        try {
            writer = new Writer(outputFile);
            for (String inputFile : inputFiles) {
                readers.add(new Reader(inputFile));
            }
            if (type == Type.INT) {
                comparator = Comparator.comparingInt(x -> Integer.parseInt(x.getElement()));
            } else {
                comparator = Comparator.comparing(QueueElement::getElement);
            }
            if (order == Order.DESC) {
                comparator = comparator.reversed();
            }
            queue = new PriorityQueue<>(inputFiles.size(), comparator);
            initQueue(queue);
            mergeFiles();
            writer.flush();
            log.info("Sorting completed successfully");
        } catch (IOException e){
            log.error("IO exception: "+e.getMessage());
            log.info("Sorting failed");
        }
    }

    private void mergeFiles() throws IOException {
        QueueElement lastWrittenElement;
        Reader reader;
        while(!queue.isEmpty()){
            QueueElement element = queue.poll();
            writer.writeElement(element.getElement());
            lastWrittenElement = element;
            reader = element.getReader();
            QueueElement nextElement = new QueueElement(reader.getNextElement(), reader);
            while(nextElement.getElement() != null){
                if(!isValid(nextElement)){
                    log.warn("Wrong element '" + nextElement.getElement() + "' in " + reader.getFileName() + ". Ignored");
                    nextElement.setElement(reader.getNextElement());
                }
                else if(isOutOfOrder(nextElement, lastWrittenElement)){
                    log.warn("Element '" + nextElement.getElement() + "' in " + reader.getFileName() + " is out of order. Ignored");
                    nextElement.setElement(reader.getNextElement());
                }
                else {
                    queue.add(nextElement);
                    break;
                }
            }
        }
    }

    private void initQueue(PriorityQueue<QueueElement> queue) throws IOException {
        for (Reader reader : readers) {
            QueueElement element = new QueueElement(reader.getNextElement(), reader);
            while(!isValid(element) && element.getElement() != null){
                log.warn("Wrong element '" + element.getElement() + "' in " + reader.getFileName() + ". Ignored");
                element.setElement(reader.getNextElement());
            }
            if(element.getElement() != null) {
                queue.add(element);
            }
        }
    }

    private boolean isValid(QueueElement element){
        if(type == Type.INT){
            try{
                Integer.parseInt(element.getElement());
            } catch (NumberFormatException e){
                return false;
            }
        }
        return !element.getElement().contains(" ");
    }

    private boolean isOutOfOrder(QueueElement element, QueueElement lastElement){
        return comparator.compare(element, lastElement) < 0;
    }

}
