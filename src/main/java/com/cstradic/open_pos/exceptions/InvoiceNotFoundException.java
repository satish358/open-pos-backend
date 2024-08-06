package com.cstradic.open_pos.exceptions;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException() {
        super("Invoice not found in database");
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
