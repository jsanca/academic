package com.github.jsanca.mapping;

public interface FieldMapper<T> {

    public  T toObject(Object input, Mapping mapping);
}
