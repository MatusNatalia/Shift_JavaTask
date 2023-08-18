package org.shift.service;

import org.shift.utils.Order;
import org.shift.utils.QueueElement;
import org.shift.utils.Type;

import java.io.IOException;
import java.util.*;

public class Sorter {

    private List<Reader> readers = new ArrayList<>();
    private Writer writer;
    private PriorityQueue<QueueElement> queue;
    public void sort(Type type, Order order, String outputFile, List<String> inputFiles) throws IOException {
        writer = new Writer(outputFile);
        for(String inputFile : inputFiles){
            readers.add(new Reader(inputFile));
        }
        Comparator<QueueElement> comparator = null;
        switch (type) {
            case INT -> comparator = (x, y) -> order.getValue()*Integer.compare(Integer.parseInt(x.getElement()), Integer.parseInt(y.getElement()));
            case STRING -> comparator = (x, y) -> order.getValue() * x.getElement().compareTo(y.getElement());
        }
        queue = new PriorityQueue<>(inputFiles.size(), comparator);
        mergeFiles();
        writer.flush();
    }

    private void mergeFiles() throws IOException {
        for (Reader reader : readers) {
            queue.add(new QueueElement(reader.getNextElement(), reader));
        }
        while(!queue.isEmpty()){
            QueueElement element = queue.poll();
            System.out.println(element.getElement());
            writer.writeElement(element.getElement());
            String nextElement = element.getReader().getNextElement();
            if(nextElement != null){
                queue.add(new QueueElement(nextElement, element.getReader()));
            }
        }
    }

}
