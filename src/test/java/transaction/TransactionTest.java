package transaction;

import org.craft.inventory.Inventory;
import org.craft.inventory.Tool;
import org.craft.price.PriceIndex;
import org.craft.transaction.RentalAgreement;
import org.craft.transaction.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionTest {

    Inventory inventory;

    BufferedReader reader;

    PriceIndex index;

    @Test
    public void testGetInventory(){
        try{
            reader = mock(BufferedReader.class);
            index = mock(PriceIndex.class);
            inventory = mock(Inventory.class);

            String toolCode = "JAKR", toolType = "Jackhammer", testBrand = "Ridgid";
            Tool tool = new Tool(toolCode, toolType, testBrand);

            String transactionInput = "JAKR 7/2/20 4 50%";
            when(index.getHolidayCharge(any())).thenReturn(Optional.of(true));
            when(index.getWeekendCharge(any())).thenReturn(Optional.of(true));
            when(index.getWeekdayCharge(any())).thenReturn(Optional.of(true));
            when(index.getPrice(any())).thenReturn(Optional.of(1.00));
            when(reader.readLine()).thenReturn(transactionInput, null);
            when(reader.ready()).thenReturn(true);
            when(inventory.checkInventory(any())).thenReturn(Optional.of(tool));

            Transaction transaction = new Transaction(this.inventory, this.reader, this.index);

            List<Optional<RentalAgreement>> agreements = transaction.process();

            Assertions.assertEquals(1, agreements.size());
            Assertions.assertEquals(4.00, agreements.get(0).get().breakdown().getCostToRent());
            Assertions.assertEquals(4, agreements.get(0).get().breakdown().getChargedDays());
            Assertions.assertEquals(1, agreements.get(0).get().breakdown().getHolidayDays());
            Assertions.assertEquals(1, agreements.get(0).get().breakdown().getWeekdayDays());
            Assertions.assertEquals(2, agreements.get(0).get().breakdown().getWeekendDays());
            Assertions.assertEquals(4, agreements.get(0).get().days());
            Assertions.assertEquals("JAKR", agreements.get(0).get().tool().toolCode());
            Assertions.assertEquals("Jackhammer", agreements.get(0).get().tool().toolType());
            Assertions.assertEquals("Ridgid", agreements.get(0).get().tool().brand());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
