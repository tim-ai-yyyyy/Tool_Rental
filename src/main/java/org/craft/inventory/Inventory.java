package org.craft.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Inventory {
    Map<String, Tool> inventory = new HashMap<>();

    public Inventory(BufferedReader reader) {
        int code = 0, type = 1, brand = 2;
        try {
            String entries = "";
            while((entries = reader.readLine()) != null){
                try {
                    String[] entry = entries.split(" ");
                    this.inventory.put(entry[code], new Tool(entry[code], entry[type], entry[brand]));
                } catch (Exception e){
                    System.out.println("Sorry, but the file you read inventory from is malformed");
                    System.out.println("Please submit a file that uses the below format: ");
                    System.out.println("TOOLCODE TOOLTYPE TOOLBRAND NUM_IN_INVENTORY");
                }
            }

        } catch(FileNotFoundException e){
            System.out.println("Sorry, could not initialize inventory. No inventory source was provided");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Tool> checkInventory(String toolCode){
        if(inventory.containsKey(toolCode)){
            return Optional.of(inventory.get(toolCode));
        } else return Optional.empty();
    }
}
