package org.shift.utils;

import org.shift.service.Reader;

public class QueueElement {
    private final String element;
    private final Reader reader;

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
