package org.shift.utils;

public enum Order {
    UNDEFINED(0),
    ASC(1),
    DESC(-1);

    private Integer value;
    Order(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
