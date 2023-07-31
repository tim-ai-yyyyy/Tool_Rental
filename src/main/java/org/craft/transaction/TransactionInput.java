package org.craft.transaction;

import java.time.LocalDate;

public record TransactionInput(String toolCode, LocalDate date, int rentalDays, int discount) {
    public TransactionInput readFromInputFile(String entryFromFile){
        return this;
    }
}
