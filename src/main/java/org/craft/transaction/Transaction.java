package org.craft.transaction;

import org.craft.exceptions.InvalidTransaction;
import org.craft.exceptions.NoInventoryFoundException;
import org.craft.inventory.Inventory;
import org.craft.inventory.Tool;
import org.craft.price.ChargePeriod;
import org.craft.price.CostBreakdown;
import org.craft.price.PriceIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Transaction {
    private final Inventory inventory;
    private BufferedReader reader;
    private TransactionInput input;
    private PriceIndex priceIndex;
    private Optional<CostBreakdown> breakdown;

    public Transaction(Inventory inventory, BufferedReader reader, PriceIndex index) {
        this.inventory = inventory;
        this.reader = reader;
        this.priceIndex = index;
    }

    public Transaction(Inventory inventory, TransactionInput input, PriceIndex index){
        this.inventory = inventory;
        this.input = input;
        this.priceIndex = index;
    }
    public RentalAgreement process() {
        RentalAgreement agreement = null;
        try {
            if(this.reader != null && this.reader.ready()){
                String entry;
                while((entry = this.reader.readLine()) != null){
                    String[] entryByComponents = entry.split(" ");
                    String[] dateComponents = entryByComponents[1].split("/");
                    TransactionInput transactionInput =
                            new TransactionInput(
                                    entryByComponents[0],
                                    LocalDate.of(Integer.parseInt("20" + dateComponents[2]), Integer.parseInt(dateComponents[0]), Integer.parseInt(dateComponents[1])),
                                    Integer.parseInt(entryByComponents[2]),
                                    Integer.parseInt(Optional.ofNullable(entryByComponents[3])
                                            .filter(str -> str.length() != 0)
                                            .map(str -> str.substring(0, str.length() - 1))
                                            .orElse(entryByComponents[3])));
                    Transaction entryTransaction = new Transaction(this.inventory, transactionInput, this.priceIndex);
                    entryTransaction.process();
                }
            } else
                if(this.input != null){
                    if(this.input.rentalDays() < 0 || this.input.discount() < 0 || this.input.discount() > 100){
                        throw new InvalidTransaction();
                    }
                    Optional<Tool> inventoryResult = this.inventory.checkInventory(this.input.toolCode());
                    if(inventoryResult.isPresent()) {
                        ChargePeriod period = new ChargePeriod(
                                this.input.rentalDays(),
                                this.input.date(),
                                this.inventory.checkInventory(this.input.toolCode()).get(),
                                this.priceIndex);
                        this.breakdown = period.getCharges();
                        agreement =
                                new RentalAgreement(
                                        this.inventory.checkInventory(this.input.toolCode()).get(),
                                        this.input.rentalDays(),
                                        this.input.date(),
                                        this.input.date().plusDays(this.input.rentalDays() - 1),
                                        this.priceIndex.getPrice(this.input.toolCode()).get(),
                                        this.breakdown.get(),
                                        this.input.discount());
                        System.out.println(agreement);
                } else throw new NoInventoryFoundException();
            } else System.out.println("No transactions to process");
        } catch (IOException e){
            e.printStackTrace();
        } catch (NoInventoryFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTransaction e){
            System.out.println(e.getMessage());
        }
        return agreement;
    }
}
