package org.craft.price;

import org.craft.exceptions.NoInventoryFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PriceIndex {
    private Map<String, Charge> price_index;
    public PriceIndex(BufferedReader reader){
        this.price_index = new HashMap<>();
        try {
            String in;
            while((in = reader.readLine()) != null){
                String[] index_entry = in.split(" ");
                for(int i = 0; i < index_entry.length; i++ ){
                    this.price_index.put(index_entry[0],
                            new Charge(Double.parseDouble(index_entry[1]),
                                    index_entry[2].equalsIgnoreCase("yes") ? true : false,
                                    index_entry[3].equalsIgnoreCase("yes") ? true : false,
                                    index_entry[4].equalsIgnoreCase("yes") ? true : false));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Optional<Double> getPrice(String toolCode){
        String toolType = convertKey(toolCode);
        if(this.price_index.containsKey(toolType)) {
            return Optional.of(this.price_index.get(toolType).charge());
        }
        return Optional.empty();
    }

    public Optional<Boolean> getWeekdayCharge(String toolCode){
        String toolType = convertKey(toolCode);
        if(this.price_index.containsKey(toolType)){
            return Optional.of(this.price_index.get(toolType).weekday());
        }
        return Optional.empty();
    }

    public Optional<Boolean> getWeekendCharge(String toolCode){
        String toolType = convertKey(toolCode);
        if(this.price_index.containsKey(toolType)){
            return Optional.of(this.price_index.get(toolType).weekend());
        }
        return Optional.empty();
    }

    public Optional<Boolean> getHolidayCharge(String toolCode){
        String toolType = convertKey(toolCode);
        if(this.price_index.containsKey(toolType)){
            return Optional.of(this.price_index.get(toolType).holiday());
        }
        return Optional.empty();
    }

    private String convertKey(String toolCode){
        String toolType;
        try {
            switch (toolCode){
                case "JAKR":
                    toolType = "Jackhammer";
                    break;
                case "JAKD":
                    toolType = "Jackhammer";
                    break;
                case "LADW":
                    toolType = "Ladder";
                    break;
                case "CHNS":
                    toolType = "Chainsaw";
                    break;
                default:
                    throw new NoInventoryFoundException();
            }
        } catch (NoInventoryFoundException e) {
            throw new RuntimeException(e);
        }
        return toolType;
    }

    public Map<String, Charge> getIndex(){
        return this.price_index;
    }
}
