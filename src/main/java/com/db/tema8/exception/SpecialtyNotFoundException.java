package com.db.tema8.exception;

public class SpecialtyNotFoundException extends Exception {
    public SpecialtyNotFoundException() {
        super("The specialty is not here bro");
    }
}
