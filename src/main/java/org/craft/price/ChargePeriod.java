package org.craft.price;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.craft.inventory.Tool;

public class ChargePeriod {
    private int days;
    private LocalDate starting_day;
    private Tool tool;

    private LocalDate[] rentalPeriod;

    private LocalDate time_period_labor_day;
    private LocalDate time_period_independence_day;
    private CostBreakdown breakdown;

    private PriceIndex index;

    public ChargePeriod(int days, LocalDate day, Tool tool, PriceIndex index){
        this.days = days;
        this.starting_day = day;
        this.tool = tool;
        this.rentalPeriod = getRentalPeriod();
        this.time_period_independence_day = getIndependenceDay().get();
        this.time_period_labor_day = getLaborDay().get();
        this.breakdown = new CostBreakdown();
        this.index = index;
    }

    private LocalDate[] getRentalPeriod() {
        LocalDate[] time_period = new LocalDate[this.days];
        for(int i = 0; i < this.days; i++){
            time_period[i] = starting_day.plusDays(i);
        }
        return time_period;
    }

    private void checkHoliday(){
        for(int i = 0; i < days; i++) {
            if(this.rentalPeriod[i].isEqual(this.time_period_independence_day)
                    || this.rentalPeriod[i].isEqual(this.time_period_labor_day) ){
                this.breakdown.addHoliday();
            }
        }
    }

    private Optional<LocalDate> getIndependenceDay(){
        LocalDate date = LocalDate.of(this.starting_day.getYear(), 7, 4);
        switch (date.getDayOfWeek()){
            case SATURDAY:
                return Optional.of(date.minusDays(1));
            case SUNDAY:
                return Optional.of(date.plusDays(1));
            default:
                return Optional.of(date);
        }
    }

    private Optional<LocalDate> getLaborDay() {
        LocalDate date = LocalDate.of(this.starting_day.getYear(), 9, 1);
        while(date.getDayOfWeek() != DayOfWeek.MONDAY){
            date = date.plusDays(1);
        }
        return Optional.of(date);
    }

    private void checkWeekend(){
        for(int i = 0; i < days; i++) {
            if(this.rentalPeriod[i].getDayOfWeek() == DayOfWeek.SATURDAY
                    || this.rentalPeriod[i].getDayOfWeek() == DayOfWeek.SUNDAY ){
                this.breakdown.addWeekend();
            }
        }
    }

    private void checkWeekdays(){
        this.breakdown.addWeekdays(this.rentalPeriod.length - this.breakdown.getHolidayDays() - this.breakdown.getWeekendDays());
    }

    public Optional<CostBreakdown> getCharges() {
        checkHoliday();
        checkWeekend();
        checkWeekdays();
        if(this.index.getHolidayCharge(this.tool.toolCode()).get()){
            this.breakdown.addChargedDays(this.breakdown.getHolidayDays());
        }
        if(this.index.getWeekendCharge(this.tool.toolCode()).get()){
            this.breakdown.addChargedDays(this.breakdown.getWeekendDays());
        }
        if(this.index.getWeekdayCharge(this.tool.toolCode()).get()){
            this.breakdown.addChargedDays(this.breakdown.getWeekdayDays());
        }
        if(this.breakdown != null){
            this.breakdown.addToTotalOfChargedDays(this.index.getPrice(this.tool.toolCode()).get());
            return Optional.of(this.breakdown);
        } else return Optional.empty();
    }

}
