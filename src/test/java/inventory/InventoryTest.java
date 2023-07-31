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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryTest {

    @Mock
    private
    BufferedReader mockReader;

    private Map<String, Tool> testMap;

    private Inventory inventory;

    @Test
    public void test_Constructor(){
        try {
            String testCode = "testCode", testType = "testType", testBrand = "testBrand";
            Tool testTool = new Tool(testCode, testType, testBrand);
            String testToolString = testCode + " " + testType + " " + testBrand;
            mockReader = mock(BufferedReader.class);
            testMap = new HashMap<>();
            testMap.put(testCode, testTool);
            inventory = new Inventory(mockReader);
            doReturn(testToolString).when(mockReader).readLine();
            Optional<Tool> result = inventory.checkInventory(testCode);
            if(result.isPresent()) {
                Assertions.assertEquals(result.get(), testTool);
            }
        } catch ( IOException e ){
            e.printStackTrace();
        }
    }
}
