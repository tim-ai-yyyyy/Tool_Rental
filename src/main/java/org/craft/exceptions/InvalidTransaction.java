package org.craft.exceptions;

public class InvalidTransaction extends Exception {
    @Override
    public String getMessage(){
        return "\nTransaction could not be processed. \nDiscounts have to be set between 0 and 100 and rental days must be greater than 0\n";
    }
}
