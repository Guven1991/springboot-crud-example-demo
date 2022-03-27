package com.example.springbootcrudexampledemo.error;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException{
    public BookUnSupportedFieldPatchException(Set<Double> keys){
        super("Field" + keys.toString() + " update is not allow.");
    }
}
