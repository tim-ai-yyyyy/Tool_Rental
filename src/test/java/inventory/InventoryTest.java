package inventory;

import org.craft.inventory.Inventory;
import org.craft.inventory.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryTest {

    Inventory inventory;
    Tool testTool;
    String testCode = "testCode", testType = "testType", testBrand = "testBrand";


    @BeforeEach
    public void init() throws IOException {
        testTool = new Tool(testCode, testType, testBrand);
        String testToolString = testCode + " " + testType + " " + testBrand;

        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn(testToolString, null);
        inventory = new Inventory(mockReader);

    }

    @Test
    public void test_CheckInventory_ReturnTool(){
        Optional<Tool> result = inventory.checkInventory(testCode);
        if(result.isPresent()) {
            Assertions.assertEquals(result.get(), testTool);
        } else fail();
    }

    @Test
    public void test_CheckInventory_ReturnEmpty(){
        Optional<Tool> result = inventory.checkInventory("empty");
        if(result.isPresent()) {
            fail();
        }
    }
}
