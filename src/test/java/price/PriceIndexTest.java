package price;

import org.craft.inventory.Inventory;
import org.craft.inventory.Tool;
import org.craft.price.Charge;
import org.craft.price.PriceIndex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PriceIndexTest {

    private BufferedReader mockReader;

    private Map<String, Charge> testMap;

    private PriceIndex index;
    private double charge = 1.00;

    Charge testCharge;

    @BeforeEach
    public void init(){
    }

    @Test
    public void test_getPrice_returnPrice(){
        String weekday = "yes", weekend = "yes", holiday = "yes";
        try{
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(1.0, index.getPrice(testToolCode).get());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_getPrice_returnEmpty(){
        String weekday = "yes", weekend = "yes", holiday = "yes";
        try {
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(Optional.empty(), index.getPrice("empty"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_WeekdayCharge_Returntrue(){
        String weekday = "yes", weekend = "yes", holiday = "yes";
        try{
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(true, index.getWeekdayCharge(testToolCode).get());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_WeekdayCharge_Returnfalse(){
        String weekday = "no", weekend = "yes", holiday = "yes";
        try {
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(false, index.getWeekdayCharge(testToolCode).get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_WeekendCharge_Returntrue(){
        String weekday = "yes", weekend = "yes", holiday = "yes";
        try{
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(true, index.getWeekendCharge(testToolCode).get());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_WeekendCharge_Returnfalse(){
        String weekday = "yes", weekend = "no", holiday = "yes";
        try {
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(false, index.getWeekendCharge(testToolCode).get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_HolidayCharge_Returntrue(){
        String weekday = "yes", weekend = "yes", holiday = "yes";
        try{
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(true, index.getHolidayCharge(testToolCode).get());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_HolidayCharge_Returnfalse(){
        String weekday = "yes", weekend = "yes", holiday = "no";
        try {
            String testToolCode = "JAKD";
            String testChargeString = "Jackhammer " + charge + " " + weekday + " " + weekend + " " + holiday;

            mockReader = mock(BufferedReader.class);
            when(mockReader.readLine()).thenReturn(testChargeString, null);
            index = new PriceIndex(mockReader);

            Assertions.assertEquals(false, index.getHolidayCharge(testToolCode).get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
