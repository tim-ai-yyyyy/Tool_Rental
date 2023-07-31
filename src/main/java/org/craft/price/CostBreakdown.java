package org.craft.price;

public class CostBreakdown {
    private int holidayDays;
    private int weekdayDays;
    private int weekendDays;

    private int chargedDays;

    private double costOfChargedDays;

    public CostBreakdown(){
        this.holidayDays = 0;
        this.weekdayDays = 0;
        this.weekendDays = 0;
    }

    public void addChargedDays(int i){
        this.chargedDays += i;
    }

    public void addHoliday(){
        this.holidayDays++;
    }

    public void addWeekend(){
        this.weekendDays++;
    }

    public void addWeekdays(int i){
        this.weekdayDays += i;
    }

    public int getHolidayDays() {
        return holidayDays;
    }

    public int getWeekdayDays() {
        return weekdayDays;
    }

    public int getWeekendDays() {
        return weekendDays;
    }

    public int getChargedDays() {
        return this.chargedDays;
    }

    public void addToTotalOfChargedDays(double price){
        this.costOfChargedDays = price * this.chargedDays;
    }

    public double getCostToRent(){
        return this.costOfChargedDays;
    }
}
