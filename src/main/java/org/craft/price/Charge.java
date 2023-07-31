package org.craft.price;

public record Charge(double charge, boolean weekday, boolean weekend, boolean holiday) { }
