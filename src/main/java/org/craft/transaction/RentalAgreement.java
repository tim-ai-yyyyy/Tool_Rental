package org.craft.transaction;

import org.craft.inventory.Tool;
import org.craft.price.CostBreakdown;

import java.text.DecimalFormat;
import java.time.LocalDate;

public record RentalAgreement(
    Tool tool,
    int days,
    LocalDate checkoutDate,
    LocalDate returnDate,
    double dailyRentalFee,
    CostBreakdown breakdown,
    int discount) {

    @Override
    public String toString(){
        DecimalFormat f = new DecimalFormat("0.00");
        String dicountAmount = String.format("%.2f", this.breakdown.getCostToRent()*discount/100);
        String finalCharge = String.format("%.2f", this.breakdown.getCostToRent()*(100 - discount)/100);
        return "Tool : \n " +
                "\t" + this.tool.toolType() + "\n" +
                "\t" + this.tool.toolCode() + "\n" +
                "\t" + this.tool.brand() + "\n" +
                "Number of Days Renting : " + days + "\n" +
                "Checkout Date : " + checkoutDate + "\n" +
                "Return Date : " + returnDate + "\n" +
                "Daily Rental Fee : $" + dailyRentalFee + "\n" +
                "Charge Days : " + breakdown.getChargedDays() + "\n" +
                "Pre-discount Charge : $" + breakdown.getCostToRent() + "\n" +
                "Discount : " + discount + "%\n" +
                "Discount Amount : $" + dicountAmount + "\n" +
                "Final Charge Amount : $" + finalCharge + "\n";
    }
}
