package price;

import org.craft.inventory.Tool;
import org.craft.price.ChargePeriod;
import org.craft.price.CostBreakdown;
import org.craft.price.PriceIndex;
import org.craft.transaction.RentalAgreement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ChargePeriodTest {

    PriceIndex index;
    Optional<Boolean> charge;
    Optional<Double> price;
    Tool tool = new Tool("testCode", "testType", "testBrand");
    ChargePeriod period;


    @BeforeEach
    public void init(){
        this.index = mock(PriceIndex.class);
        price = Optional.of(1.00);

    }

    @Test
    public void test_ChargePeriod_HolidayTrue_WeekendTrue_WeekdayTrue(){
        this.period = new ChargePeriod(3, LocalDate.of(2020, 9, 3), tool, index );
        Optional<Boolean> charge = Optional.of(true);
        doReturn(charge).when(index).getWeekdayCharge(any());
        doReturn(charge).when(index).getWeekendCharge(any());
        doReturn(charge).when(index).getHolidayCharge(any());
        doReturn(price).when(index).getPrice(any());

        Optional<CostBreakdown> breakdown = period.getCharges();
        Assertions.assertEquals(breakdown.get().getChargedDays(), 3);
        Assertions.assertEquals(breakdown.get().getCostToRent(), 3);
    }

    @Test
    public void test_ChargePeriod_Holidaytrue_Weekendfalse_WeekdayTrue(){
        this.period = new ChargePeriod(5, LocalDate.of(2023, 8, 1), tool, index );
        doReturn(Optional.of(true)).when(index).getWeekdayCharge(any());
        doReturn(Optional.of(false)).when(index).getWeekendCharge(any());
        doReturn(Optional.of(true)).when(index).getHolidayCharge(any());
        doReturn(price).when(index).getPrice(any());

        Optional<CostBreakdown> breakdown = period.getCharges();
        System.out.println(breakdown.get().getWeekdayDays());
        Assertions.assertEquals(4, breakdown.get().getChargedDays());
        Assertions.assertEquals(4, breakdown.get().getCostToRent());
    }

    @Test
    public void test_ChargePeriod_Holidaytrue_Weekendtrue_Weekdayfalse(){
        this.period = new ChargePeriod(7, LocalDate.of(2023, 7, 3), tool, index );
        doReturn(Optional.of(false)).when(index).getWeekdayCharge(any());
        doReturn(Optional.of(true)).when(index).getWeekendCharge(any());
        doReturn(Optional.of(true)).when(index).getHolidayCharge(any());
        doReturn(price).when(index).getPrice(any());

        Optional<CostBreakdown> breakdown = period.getCharges();
        System.out.println(breakdown.get().getWeekdayDays());
        Assertions.assertEquals(3, breakdown.get().getChargedDays());
        Assertions.assertEquals(3, breakdown.get().getCostToRent());
    }
}
