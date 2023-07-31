package price;

import org.craft.inventory.Tool;
import org.craft.price.ChargePeriod;
import org.craft.price.CostBreakdown;
import org.craft.price.PriceIndex;
import org.craft.transaction.RentalAgreement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ChargePeriodTest {

    @Mock
    PriceIndex index;

    @Test
    public void test_ChargePeriod(){
        index = mock(PriceIndex.class);
        Optional<Boolean> charge = Optional.of(true);
        Optional<Double> price = Optional.of(1.0);
        Tool tool = new Tool("testCode", "testType", "testBrand");
        ChargePeriod period = new ChargePeriod(3, LocalDate.now(), tool, index );
        doReturn(charge).when(index).getWeekdayCharge(any());
        doReturn(charge).when(index).getWeekendCharge(any());
        doReturn(charge).when(index).getHolidayCharge(any());
        doReturn(price).when(index).getPrice(any());
        Optional<CostBreakdown> breakdown = period.getCharges();
        Assertions.assertEquals(breakdown.get().getChargedDays(), 3);
        Assertions.assertEquals(breakdown.get().getCostToRent(), 3);

    }
}
