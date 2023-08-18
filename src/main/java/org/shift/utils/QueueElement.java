package org.shift.utils;

import org.shift.service.Reader;

public class QueueElement {
    private String element;
    private Reader reader;

    public QueueElement(String element, Reader reader) {
        this.element = element;
        this.reader = reader;
    }

    public String getElement() {
        return element;
    }

    public Reader getReader() {
        return reader;
    }
}
