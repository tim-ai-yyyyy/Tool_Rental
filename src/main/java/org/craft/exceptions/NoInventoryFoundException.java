package org.craft.exceptions;

public class NoInventoryFoundException extends Exception {
    @Override
    public String getMessage(){
        return "The tool you are looking for is not in our inventory";
    }
}
