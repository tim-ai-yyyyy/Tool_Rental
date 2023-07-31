package price;

import org.craft.inventory.Inventory;
import org.craft.inventory.Tool;
import org.craft.price.Charge;
import org.craft.price.PriceIndex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class PriceIndexTest {

    @Mock
    private
    BufferedReader mockReader;

    private Map<String, Charge> testMap;

    private PriceIndex index;

    @Test
    public void test_Constructor(){
//        try{
//            boolean weekday = true, weekend = true, holiday = true;
//            double charge = 1.0;
//            Charge testCharge = new Charge(charge, weekday, weekend, holiday);
//            String testChargeString = "testCharge " + charge + " " + weekday + " " + weekend + " " + holiday;
//            mockReader = mock(BufferedReader.class);
//            index = new PriceIndex(mockReader);
//            doReturn(testChargeString).when(mockReader).readLine();
//            testMap = index.getIndex();
//            Assertions.assertEquals(testMap.get("testCharge").charge(), "1.0");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
