package org.craft.main;

import org.craft.price.PriceIndex;
import org.craft.transaction.RentalAgreement;
import org.craft.transaction.Transaction;
import org.craft.inventory.Inventory;
import org.craft.util.Constants;
import org.craft.util.InputsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        Transaction transaction;
        Inventory inventory;
        PriceIndex index;
        Optional<BufferedReader> inventoryReader = InputsUtil.getFileInput(Constants.INVENTORY_FILE);
        Optional<BufferedReader> transactionReader = InputsUtil.getFileInput(Constants.TRANSACTION_FILE);
        Optional<BufferedReader> priceIndexReader = InputsUtil.getFileInput(Constants.PRICE_INDEX_FILE);

        if(inventoryReader.isPresent() && transactionReader.isPresent() && priceIndexReader.isPresent()){
            inventory = new Inventory(inventoryReader.get());
            index = new PriceIndex(priceIndexReader.get());
            transaction = new Transaction(inventory, transactionReader.get(), index);
            transaction.process();
        }
    }
}